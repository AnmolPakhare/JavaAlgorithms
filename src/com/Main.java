package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {

        Map<String, String> graph = new HashMap<>();
        List<Set<String>> startingNodeSets = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String line;

            boolean parsingEdges = true;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) continue;

                // If we see a line without '->', it's a set of starting nodes
                if (!line.contains("->")) {
                    parsingEdges = false;
                }

                if (parsingEdges) {
                    // Parse edges (a->b)
                    String[] parts = line.split("->");
                    graph.put(parts[0], parts[1]); // a -> b means a points to b
                } else {
                    // Parse starting node sets (comma-separated)
                    String[] nodes = line.split(",");
                    Set<String> startingNodes = new HashSet<>(Arrays.asList(nodes));
                    startingNodeSets.add(startingNodes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Process each set of starting nodes
        for (Set<String> startNodes : startingNodeSets) {
            boolean cycleFound = false;
            for (String start : startNodes) {
                if (hasCycle(graph, start)) {
                    System.out.println("CYCLE");
                    cycleFound = true;
                    break;
                }
            }
            if (cycleFound) continue;

            if (hasIntersection(graph, startNodes)) {
                System.out.println("INTERSECTION");
                continue;
            }

            // If no cycle or intersection detected
            System.out.println("OK");
        }

    }

    // Detects cycle using Slow and Fast Pointer
    private static boolean hasCycle(Map<String, String> graph, String start) {
        String slow = start, fast = start;

        while (fast != null && graph.containsKey(fast)) {
            slow = graph.get(slow); // Move slow one step
            fast = graph.get(fast); // Move fast one step
            if (fast != null) fast = graph.get(fast); // Move fast another step

            if (slow != null && slow.equals(fast)) {
                return true; // Cycle detected
            }
        }
        return false; // No cycle
    }

    // Detects intersection using tail tracking
    private static boolean hasIntersection(Map<String, String> graph, Set<String> startNodes) {
        Map<String, String> tailMap = new HashMap<>(); // Maps starting nodes to their tail

        for (String start : startNodes) {
            String current = start;
            while (graph.containsKey(current)) {
                current = graph.get(current);
            }
            if (tailMap.containsValue(current)) {
                return true; // Intersection detected
            }
            tailMap.put(start, current);
        }
        return false;
    }
}

