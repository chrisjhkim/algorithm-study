package com.chrisjhkim.algorithm.problems.codetest.leetcode;

/**
 * https://leetcode.com/problems/number-of-islands/description/
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public class NumberOfIslands {
	private static final char WATER = '0';
	private boolean[][] checked;
	private int islandCounter;

	public int numIslands(char[][] grid) {

		init(grid);


		for ( int i = 0 ; i < grid.length ; i ++ ) {
			for ( int j = 0 ; j < grid[0].length ; j ++ ) {
				if ( !checked[i][j]){
					startCountIsland(i,j, true, grid);
				}
			}

		}
		return this.islandCounter;
	}

	private void startCountIsland(int i, int j, boolean startedNewIsland, char[][] grid) {
		checked[i][j] = true;
		if ( grid[i][j] == WATER) {
			return;
		}

		if ( startedNewIsland ) {
			islandCounter += 1;
		}

		// up
		if ( i-1 >= 0 && !checked[i-1][j]){
			startCountIsland(i-1, j, false, grid);
		}

		// down
		if ( i+1 < grid.length && !checked[i+1][j]){
			startCountIsland(i+1, j, false, grid);
		}



		// left
		if ( j-1 >= 0 && !checked[i][j-1]){
			startCountIsland(i, j-1, false, grid);
		}

		// right
		if ( j+1 < grid[0].length && !checked[i][j+1]){
			startCountIsland(i, j+1, false ,grid);
		}






	}

	private void init(char[][] grid) {
		this.checked = new boolean[grid.length][grid[0].length];
	}


}
