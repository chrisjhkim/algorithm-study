package com.chrisjhkim.algorithm.problems.codetest.hackerrank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LegoBlocksTest {

	LegoBlocks solver = new LegoBlocks();

	@Test
	void testGetRowCandidates(){

		System.out.println(solver.solve(2,4));
		System.out.println(solver.solve(2,5));
		System.out.println(solver.solve(2,6));
		System.out.println(solver.solve(2,7));
		System.out.println(solver.solve(2,8));
		System.out.println(solver.solve(2,9));
	}

	@Test
	void testSolve_1() {
		int result = solver.solve(2, 2);
		assertThat(result).isEqualTo(3);
	}
	@Test
	void testSolve_2() {
		int result = solver.solve(3, 2);
		assertThat(result).isEqualTo(7);
	}

	@Test
	void testSolve_3() {
		int result = solver.solve(2, 3);
		assertThat(result).isEqualTo(9);
	}

	@Test
	void testSolve_4() {
		int result = solver.solve(4, 4);
		assertThat(result).isEqualTo(3375);
	}


}