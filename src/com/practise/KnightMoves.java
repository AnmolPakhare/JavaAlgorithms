package com.practise;

import java.util.LinkedList;
import java.util.Queue;

class KnightMoves {
    // Possible moves of a Knight
    private static final int[][] MOVES = {
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
    };

    // BFS to find shortest path
    public static int minKnightMoves(int N, int sx, int sy, int tx, int ty) {
        boolean[][] visited = new boolean[N][N]; // Track visited cells
        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from (sx, sy)
        queue.add(new int[]{sx, sy, 0}); // {x, y, steps}
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], steps = current[2];

            // If target reached, return steps
            if (x == tx && y == ty) return steps;

            // Explore all 8 possible moves
            for (int[] move : MOVES) {
                int newX = x + move[0], newY = y + move[1];

                // Check if within bounds and not visited
                if (isValid(N, newX, newY, visited)) {
                    queue.add(new int[]{newX, newY, steps + 1});
                    visited[newX][newY] = true;
                }
            }
        }
        return -1; // Should never reach here
    }

    // Check if the move is within bounds and not visited
    private static boolean isValid(int N, int x, int y, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < N && y < N && !visited[x][y];
    }

    public static void main(String[] args) {
        int N = 8; // Chessboard size
        int startX = 0, startY = 0;
        int targetX = 7, targetY = 7;

        int moves = minKnightMoves(N, startX, startY, targetX, targetY);
        System.out.println("Minimum Knight Moves: " + moves);
    }
}

