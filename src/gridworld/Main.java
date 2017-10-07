package gridworld;

public class Main {
	public static void main(String[] args) {
		float r = -0.01F;
		float gamma = 0.9F;
		int iteration = 1000;
		Float x = null;
		Float[][] R = {
				{ r, x, x, r},
				{ x, x, r, r},
				{ r, x, r, r},
				{-1F, x, r, x},
				{ r, r, x, x},
				{ x, x, x, x},
				{ r, r, x, -1F},
				{ x, x, x, x},
				{ x, r, x, r},
				{ x, x, r, r},
				{ x, r, r, 1F},
				{ x, x, x, x}
		};
		Float[][] Q = {
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x},
				{ x, x, x, x}
		};
		
		int z = Integer.MIN_VALUE;
		int[][] nextStates = {
				{ 4, z, z, 1},	
				{ z, z, 0, 2},	
				{ 6, z, 1, 3},	
				{ 7, z, 2, z},	
				{ 8, 0, z, z},	
				{ z, z, z, z},	
				{ 10, 2, z, 7},	
				{ z, z, z, z},	
				{ z, 4, z, 9},	
				{ z, z, 8, 10},	
				{ z, 6, 9, 11},	
				{ z, z, z, z}
		};
		
		for (int i = 0; i < iteration; i++) {
			for (int state = 0; state < 12; state++) {
				if (state == 5 || state == 7 || state == 11) {
					continue;
				}
				for (int action = 0; action < 4; action++) {
					if (R[state][action] != x) {
						int n = nextStates[state][action];
						if (n == 11 ||n == 5 || n == 7) {
							Q[state][action] = R[state][action];
						} else {
							Float max = null;
							for (int j = 0; j < 4; j++) {
								if (Q[n][j] != null) {
									if (max == null) {
										max = Q[n][j];
									} else {
										max = Math.max(max, Q[n][j]);
									}
								}
							}
							if (max == null) {
								Q[state][action] = R[state][action];
							} else {
								Q[state][action] = R[state][action] + gamma * max;
							}
						}
//						System.out.println("state:" + state + ", action:" + action
//								+ ", max:" + max);
//						System.out.println("next:" + n);
//						System.out.println("R:" + R[state][action]);
//						System.out.println("Q:" + Q[state][action]);
					}
				}
			}
		}
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(Q[i][j] == null ? "x" : Q[i][j]);
				if (j != 3) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}
}