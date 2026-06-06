package com.practise;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Queue to store the positions of rotten oranges
        Queue<int[]> queue = new LinkedList<>();

        // Count of fresh oranges
        int fresh = 0;

        // Initialize the queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // Add rotten orange to queue
                } else if (grid[i][j] == 1) {
                    fresh++; // Count fresh oranges
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (fresh == 0) {
            return 0;
        }

        // Directions for 4-directional adjacency
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int minutes = 0;

        // Perform BFS
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            minutes++;

            // Process all rotten oranges at the current level
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                // Check all 4 directions
                for (int[] dir : directions) {
                    int x = current[0] + dir[0];
                    int y = current[1] + dir[1];

                    // If the adjacent cell is fresh, rot it
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // Mark as rotten
                        queue.offer(new int[]{x, y}); // Add to queue
                        fresh--; // Decrease the count of fresh oranges
                    }
                }
            }
        }

        // If no fresh oranges remain, return the total minutes
        // Otherwise, return -1
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottingOranges solution = new RottingOranges();
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(solution.orangesRotting(grid)); // Output: 4
    }
}
