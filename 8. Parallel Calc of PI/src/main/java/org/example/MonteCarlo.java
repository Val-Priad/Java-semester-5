package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MonteCarlo {
    private static final long ITERATIONS = 1_000_000_000L;

    public static double[] runMonteCarlo(int numThreads)
            throws InterruptedException,
            ExecutionException {
        long insideCircle = 0;
        long start = System.nanoTime();

        try (ExecutorService executor = Executors.newFixedThreadPool(
                numThreads)) {
            long iterationsPerThread = ITERATIONS / numThreads;


            List<Future<Long>> results = new ArrayList<>();
            for (int i = 0; i < numThreads; i++) {
                results.add(executor.submit(
                        new MonteCarloTask(iterationsPerThread)));
            }

            for (Future<Long> f : results) {
                insideCircle += f.get();
            }

        }
        double pi = 4.0 * insideCircle / ITERATIONS;
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        return new double[]{pi, timeMs};
    }
}
