package org.example;

import java.util.Random;
import java.util.concurrent.Callable;

class MonteCarloTask implements Callable<Long> {
        private final long iterations;
        private final Random random = new Random();

        MonteCarloTask(long iterations) {
            this.iterations = iterations;
        }

        @Override
        public Long call() {
            long count = 0;
            for (long i = 0; i < iterations; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (x * x + y * y <= 1.0) {
                    count++;
                }
            }
            return count;
        }
    }
