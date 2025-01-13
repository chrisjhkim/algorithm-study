package com.chrisjhkim.algorithm.problems.codetest.hackerrank;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (a + b) % m = ((a % m) + (b % m)) % m
 * (a * b) % m = ((a % m) * (b % m)) % m
 * (a - b) % m = ((a % m) - (b % m) + p) % m # 음수 방지
 *
 *
 */
@Slf4j
public class LegoBlocks {
	private static final boolean print = false;
	private int[] ways;
	private List<Row> rowCandidates = new ArrayList<>();
	private int targetWidth;
	private int targetHeight;
	private Boolean[][] fromTo;
	/**
	 *
	 * @param height n
	 * @param n m
	 * @return 답
	 */
	public int solve(int height , int n) {
		this.targetWidth = n;

		getRowCandidate();
		fromTo = new Boolean[rowCandidates.size()][rowCandidates.size()];

		if ( print ) log.info("row candidates --------------------------");
//		if ( print ) {
			this.rowCandidates.forEach(System.out::println);
//		}

		int result = 0;

		for (int to = 0; to < this.rowCandidates.size(); to++) {
			int tempResult = findPathCount(height, to);
			if ( print )  log.info("lastBlock={} sum={}", to,tempResult);
			result += tempResult;
		}
		for ( int i = 0 ; i < fromTo.length ; i ++ ) {
			for ( int j = 0 ; j < fromTo[0].length ; j ++ ) {
				if ( print) {
					System.out.print(fromTo[i][j]?"O " : "X ");

				}
			}
			if ( print )  System.out.println();

		}
		return result;
	}

	/**
	 */
	private int findPathCount(int height, int to) {
		if ( height == 1 ) {
			return 1;
		}else{
			int result=0;
			for (int from = 0; from < this.rowCandidates.size(); from++) {
				if ( isMovable(from,to)){
					int count = findPathCount(height - 1, from);
					result += count;
					if ( print ) {
						log.info("from={} to={}, height={}, count={}", from, to ,height, count);

					}
				}
			}
			return result;
		}
	}



	private boolean isMovable(int from, int to) {
		if ( fromTo[from][to] == null ) {
			if ( fromTo[to][from] != null ) {
				fromTo[from][to] = fromTo[to][from];
			}

			fromTo[from][to] = isMoveableCalculate(from, to);
//			log.info("from {} to {} result {}", from,to,fromTo[from][to]);
		}

		return fromTo[from][to];
		// case 1
		/*
		switch (from) {
			case 0:
				if ( to == 1 ){
					return true;
				}else{
					break;
				}

			case 1:
				return true;
		}
		return false;
		*/

		// case 3
//		switch (from) {
//			case 0:
//				if ( to == 1 || to == 3 )
//					return true;
//				break;
//			case 1:
//				if ( to == 0 || to == 3 )
//					return true;
//				break;
//			case 2:
//				if ( to == 3 )
//					return true;
//				break;
//			case 3:
//				return true;
//		}
//		return false;
	}

	private boolean isMoveableCalculate(int from, int to) {
		int topSum = 0;
		int bottomSum = 0;
		List<Integer> top = this.rowCandidates.get(from).getBlocks();
		List<Integer> bottom = this.rowCandidates.get(to).getBlocks();

		int topP = 0;
		int bottomP = 0;
		while (topP < top.size() && bottomP < bottom.size() ) {
			if ( topSum + bottomSum == 0 ) {
				topSum += top.get(topP++);
				bottomSum += bottom.get(bottomP++);
			}else if ( topSum == bottomSum ) {
				return false;
			}else if ( topSum > bottomSum ) {     // 0 0
				bottomSum += bottom.get(bottomP++);
			}else{
				topSum += top.get(topP++);
			}
		}

		return true;
	}

	private void getRowCandidate() {
		for ( int i = 1 ; i <= 4 ; i ++ ){
			bfs(i, new Row());
		}

	}


	private void bfs(int newBlockSize, Row currentRow) {
		currentRow.add(newBlockSize);

		if ( currentRow.getCurrentWidth() > targetWidth ) {
//			throw new IllegalStateException();
			return;
		}
		if ( currentRow.getCurrentWidth() == targetWidth ){
			rowCandidates.add(currentRow.copy());
		}else{
			int maxSize =  Math.max(4, targetWidth-currentRow.getCurrentWidth());
			for ( int i = 1 ; i <= maxSize ; i ++ ) {
				bfs(i, currentRow.copy());
			}

		}



	}
	@Getter
	static class Row{
		private int currentWidth = 0;
		private List<Integer> blocks = new ArrayList<>();

		public void add(int block){
			this.blocks.add(block);
			this.currentWidth += block;
		}
		public Row copy() {
			Row row1 = new Row();
			this.blocks.forEach(row1::add);
			return row1;
		}



		@Override
		public String toString() {
			return "Row{" +
					"blocks=" + blocks +
					'}';
		}
	}

	/**
	 * point 부터 갈 수 있는 방향의 수
	 * @param point
	 * @return
	 */
	private int fromA(int point) {
		if ( ways[point] == 0 ) {

		}
		return ways[point];
	}
}

