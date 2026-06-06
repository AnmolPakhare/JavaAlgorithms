package com.practise;


import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class LargeFileSorter {

    private static final int FILE_SIZE = 5000;
    private static final int BUFFER_SIZE = 8 * 1024; // 8 KB
    private static final int CHUNK_SIZE = 1000;

    private static final Path INPUT_FILE = Paths.get("input.txt");
    private static final Path OUTPUT_FILE = Paths.get("sorted.txt");

    private static final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    // ---------------- MAIN ----------------
    public static void main(String[] args) throws Exception {

        generateInputFile();
        List<Path> tempFiles = splitAndSort();
        mergeFiles(tempFiles);

        executor.shutdown();
        System.out.println("Sorting completed.");
    }

    // ---------------- STEP 1: Generate Input ----------------
    private static void generateInputFile() throws IOException {
        Random random = new Random();

        try (BufferedWriter writer = Files.newBufferedWriter(INPUT_FILE)) {
            for (int i = 0; i < FILE_SIZE; i++) {
                writer.write(String.valueOf(random.nextInt(100_000)));
                writer.newLine();
            }
        }
    }

    // ---------------- STEP 2: Split + Sort ----------------
    private static List<Path> splitAndSort() throws Exception {

        List<CompletableFuture<Path>> futures = new ArrayList<>();

        try (FileChannel channel = FileChannel.open(INPUT_FILE, StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            StringBuilder lineBuilder = new StringBuilder();
            List<Integer> numbers = new ArrayList<>();

            while (channel.read(buffer) > 0) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    char c = (char) buffer.get();

                    if (c == '\n') {
                        processLine(lineBuilder, numbers, futures);
                    } else {
                        lineBuilder.append(c);
                    }
                }
                buffer.clear();
            }

            // Last line
            processLine(lineBuilder, numbers, futures);
        }

        // Wait for all chunk sorts
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static void processLine(StringBuilder lineBuilder,
                                    List<Integer> numbers,
                                    List<CompletableFuture<Path>> futures) {

        if (lineBuilder.length() == 0) return;

        int value = Integer.parseInt(lineBuilder.toString().trim());
        numbers.add(value);
        lineBuilder.setLength(0);

        if (numbers.size() >= CHUNK_SIZE) {
            List<Integer> chunk = new ArrayList<>(numbers);
            numbers.clear();
            futures.add(sortAndWriteChunk(chunk));
        }
    }

    private static CompletableFuture<Path> sortAndWriteChunk(List<Integer> chunk) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Collections.sort(chunk);

                Path tempFile = Files.createTempFile("chunk_", ".txt");
                try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
                    for (int n : chunk) {
                        writer.write(Integer.toString(n));
                        writer.newLine();
                    }
                }
                return tempFile;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    // ---------------- STEP 3: Merge ----------------
    private static void mergeFiles(List<Path> files) throws IOException {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));

        List<BufferedReader> readers = new ArrayList<>();

        for (Path file : files) {
            BufferedReader br = Files.newBufferedReader(file);
            readers.add(br);

            String line = br.readLine();
            if (line != null) {
                pq.add(new Node(Integer.parseInt(line), br));
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_FILE)) {
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                writer.write(Integer.toString(node.value));
                writer.newLine();

                String next = node.reader.readLine();
                if (next != null) {
                    pq.add(new Node(Integer.parseInt(next), node.reader));
                }
            }
        }

        for (BufferedReader br : readers) {
            br.close();
        }
    }

    // ---------------- Helper Node ----------------
    private static class Node {
        int value;
        BufferedReader reader;

        Node(int value, BufferedReader reader) {
            this.value = value;
            this.reader = reader;
        }
    }
}


