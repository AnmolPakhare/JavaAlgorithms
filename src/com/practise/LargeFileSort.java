package com.practise;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LargeFileSort {

    private static final int CHUNK_SIZE = 1000; // numbers per chunk
    private static final Path INPUT_FILE = Paths.get("input.txt");
    private static final Path OUTPUT_FILE = Paths.get("sorted.txt");

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) throws Exception {

        generateInputFile(5000);
        List<Path> tempFiles = sortInChunks();
        mergeSortedFiles(tempFiles);

        System.out.println("Sorting completed successfully.");
    }

    // =========================
    // STEP 1: Generate Input
    // =========================
    private static void generateInputFile(int size) throws IOException {
        Random random = new Random();

        try (BufferedWriter writer = Files.newBufferedWriter(INPUT_FILE)) {
            for (int i = 0; i < size; i++) {
                writer.write(String.valueOf(random.nextInt(100_000)));
                writer.newLine();
            }
        }

        System.out.println("Input file generated with " + size + " integers.");
    }

    // =========================
    // STEP 2: Chunk Sorting
    // =========================
    private static List<Path> sortInChunks() throws IOException {
        List<Path> tempFiles = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>(CHUNK_SIZE);

        try (BufferedReader reader = Files.newBufferedReader(INPUT_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(Integer.parseInt(line));

                if (buffer.size() == CHUNK_SIZE) {
                    tempFiles.add(writeSortedChunk(buffer));
                    buffer.clear();
                }
            }

            if (!buffer.isEmpty()) {
                tempFiles.add(writeSortedChunk(buffer));
            }
        }

        return tempFiles;
    }

    private static Path writeSortedChunk(List<Integer> chunk) throws IOException {
        Collections.sort(chunk);

        Path tempFile = Files.createTempFile("chunk_", ".txt");
        tempFile.toFile().deleteOnExit();

        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            for (int num : chunk) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        }

        return tempFile;
    }

    // =========================
    // STEP 3: Merge (FIXED)
    // =========================
    private static void mergeSortedFiles(List<Path> files) throws IOException {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
        List<BufferedReader> readers = new ArrayList<>();

        // Initialize readers and PQ
        for (Path file : files) {
            BufferedReader br = Files.newBufferedReader(file);
            readers.add(br);

            String line = br.readLine();
            if (line != null) {
                pq.offer(new Node(Integer.parseInt(line), br));
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_FILE)) {

            while (!pq.isEmpty()) {
                Node node = pq.poll();

                // Write smallest value
                writer.write(String.valueOf(node.value));
                writer.newLine();

                // Read NEXT value from same reader
                String nextLine = node.reader.readLine();
                if (nextLine != null) {
                    pq.offer(new Node(Integer.parseInt(nextLine), node.reader));
                }
            }
        }

        // Close all readers
        for (BufferedReader br : readers) {
            br.close();
        }
    }

    // =========================
    // Helper Node
    // =========================
    private static class Node {
        int value;
        BufferedReader reader;

        Node(int value, BufferedReader reader) {
            this.value = value;
            this.reader = reader;
        }
    }
}
