package com.chrisjhkim.algorithm.problems.codetest.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberOfIslandsTest {

	@Test
	void testSample1(){
		// Test # Given
		char [][] input = new char[][]{
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
		};

		// Test # When
		int result = new NumberOfIslands().numIslands(input);

		// Test # Then
		assertThat(result).isEqualTo(1);
	}

	@Test
	void testSample2(){
		// Test # Given
		char [][] input = new char[][]{
			{'1','1','0','0','0'},
		  {'1','1','0','0','0'},
		  {'0','0','1','0','0'},
		  {'0','0','0','1','1'}
		};

		// Test # When
		int result = new NumberOfIslands().numIslands(input);
		
		// Test # Then
		assertThat(result).isEqualTo(3);
	}
	@Test
	void testExecSample1(){
		// Test # Given
		char [][] input = new char[][]{
				{'1'},{'1'}
		};

		// Test # When
		int result = new NumberOfIslands().numIslands(input);

		// Test # Then
		assertThat(result).isEqualTo(1);
	}
	@Test
	void testExecSample2(){
		// Test # Given
		char [][] input = new char[][]{
				{'1','1'}
		};

		// Test # When
		int result = new NumberOfIslands().numIslands(input);

		// Test # Then
		assertThat(result).isEqualTo(1);
	}
}