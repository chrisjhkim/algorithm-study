package com.chrisjhkim.algorithm.problems.codetest.coupang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameCoinTransferTest {

    @Test
    void testSample1(){
        int[] balance = new int[]{30, 30, 30, 30};
        int[][] transaction = new int[][]{{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
        int[] abNormal = new int[]{1};

        int[] solution = GameCoinTransfer.solution(balance, transaction, abNormal);
        assertThat(solution).containsSequence(new int[]{0,20,15,55});
    }

    @Test
    void testSample2(){
        int[] balance = new int[]{30, 30, 30, 30};
        int[][] transaction = new int[][]{{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
        int[] abNormal = new int[]{2,3};

        int[] solution = GameCoinTransfer.solution(balance, transaction, abNormal);
        assertThat(solution).containsSequence(new int[]{20,0,0,40});
    }

    @Test
    void testSample3(){
        int[] balance = new int[]{40, 30, 50};
        int[][] transaction = new int[][]{
                {1, 3, 20}, {2, 1, 10}, {3, 1, 45},
                {2, 3, 10},{1, 3, 35}, {2, 1, 5},
                {3, 1, 10},{3, 2, 5}};
        int[] abNormal = new int[]{2};

        int[] solution = GameCoinTransfer.solution(balance, transaction, abNormal);
        assertThat(solution).containsSequence(new int[]{40,5,45});
    }

    @Test
    void testSample4(){
        int[] balance = new int[]{100, 1, 1, 1,1};
        int[][] transaction = new int[][]{
                {1, 2, 100}, {2, 3, 101}, {3, 4, 102},
                {4, 5, 103},{5, 1, 104}};
        int[] abNormal = new int[]{1};

        int[] solution = GameCoinTransfer.solution(balance, transaction, abNormal);
        assertThat(solution).containsSequence(new int[]{4,0,0,0,0});
    }



}