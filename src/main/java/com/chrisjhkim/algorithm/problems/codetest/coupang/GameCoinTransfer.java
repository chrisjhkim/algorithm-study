package com.chrisjhkim.algorithm.problems.codetest.coupang;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameCoinTransfer {
    public static int[] solution(int[] balance, int[][] transactions, int[] abNormal) {
        Arrays.sort(abNormal);

        List<User> users = new ArrayList<>();

        for ( int i = 0 ; i < balance.length ; i ++ ) {
            User user = new User(i);

            user.addCoin(new Coin(balance[i], i+1));


            users.add(user);

        }
        System.out.println("init Status");
        printStatus(users);


        for (int[] transaction : transactions) {
            System.out.println("transaction = " + Arrays.toString(transaction));
            int from = transaction[0]-1;
            int to = transaction[1]-1;
            int amount = transaction[2];

//            users.get(from).transferCoin(users.get(to), amount);
            printStatus(users);


            CoinTransaction.builder()
                    .fromUser(users.get(from))
                    .toUser(users.get(to))
                    .amount(amount)
                    .build()
                    .makeTransfer();
        }
        System.out.println("transfer result");
        printStatus(users);


        Abnormal abnormal = new Abnormal(abNormal);
        return users.stream()
                .map(user -> user.excludeAbnormal(abnormal))
                .map(User::getTotalCoinAmount)
                .mapToInt(Integer::intValue)
                .toArray();

    }

    private static void printStatus(List<User> users) {
        for (User user : users) {
            System.out.println("user = " + user);
        }
        System.out.println("-------------");
    }

    @Getter
    @RequiredArgsConstructor
    static class Coin{
        private final int amount;
        private final int originOwner;

        @Override
        public String toString() {
            return "$"+amount+"("+originOwner+")";
        }

        public CoinSplit split(int targetAmount) {
            if ( targetAmount >= this.amount ) throw new IllegalArgumentException("target amount is too large");
            Coin targetCoin = new Coin(targetAmount, this.originOwner);
            Coin restCoin = new Coin(this.amount-targetAmount, this.originOwner);
            return new CoinSplit(targetCoin, restCoin);
        }
    }
    @Getter
    static class CoinSplit{
        private final Coin targetCoin;
        private final Coin restCoin;

        public CoinSplit(Coin targetCoin, Coin restCoin) {
            this.targetCoin = targetCoin;
            this.restCoin = restCoin;
        }
    }

    @Getter
    @RequiredArgsConstructor
    static class User{
        private final int no;
        private Deque<Coin> coins = new ArrayDeque<>();

        @Override
        public String toString() {
            return "User["+no+"] "+ coins;
        }

        public int getTotalCoinAmount(){
            return coins.stream()
//                    .map(Coin::getAmount)
                    .mapToInt(Coin::getAmount)
                    .sum();

        }
        public void addCoin(Coin coin){
            coins.add(coin);
        }

        public void addCoins(Deque<Coin> coins){
            while ( !coins.isEmpty()){
                Coin coin = coins.pollLast();
                this.coins.add(coin);
            }
        }

        public Deque<Coin> removeCoins(int amount){
            Deque<Coin> coinsInTransfer = new ArrayDeque<>();
            int inTransferAmount = 0;

//            pollFirst(); // 먼저 들어간것 제거
//            pollLast(); // 마지막 들어간것 제거

            while ( inTransferAmount < amount ) {
                int need = amount - inTransferAmount;
                Coin coin = this.coins.pollLast();
                if ( coin == null ) throw new IllegalStateException("need more money for some reason");
                if ( coin.getAmount() > need ) {
                    CoinSplit split = coin.split(need);
                    coinsInTransfer.addLast(split.getTargetCoin());
                    this.coins.add(split.getRestCoin());
                    inTransferAmount += need;
                }else { // coin.targetAmount <= need
                    coinsInTransfer.addLast(coin);
                    inTransferAmount += coin.getAmount();
                }
            }
            return coinsInTransfer;
        }

        public User excludeAbnormal(Abnormal abNormal) {
            this.coins = this.coins.stream()
                    .filter(Predicate.not(abNormal::isAbnormal)).
                    collect(Collectors.toCollection(ArrayDeque::new));
            return this;
        }
    }
    static class Abnormal {
        private final Set<Integer> abnormalUserNo;

        public Abnormal(int[] abnormal) {
            this.abnormalUserNo = Arrays.stream(abnormal)
//                    .map(userNo -> )
                    .boxed() // int를 Integer로 박싱
                    .collect(Collectors.toSet());
        }
        public boolean isAbnormal(Coin coin){
            return this.abnormalUserNo.contains(coin.originOwner);
        }
    }


    @Builder
    static class CoinTransaction{
        private User fromUser;
        private User toUser;
        private int amount;


        private void validateStatus(){
            assert (fromUser != null);
            assert (toUser != null);
            assert (amount != 0);
        }

        public void makeTransfer() {
            validateStatus();

            Deque<Coin> coinsInTransfer = this.fromUser.removeCoins(this.amount);
            this.toUser.addCoins(coinsInTransfer);

        }

    }
}
