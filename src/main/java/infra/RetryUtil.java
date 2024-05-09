package infra;


import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class RetryUtil {
    private static long waitingABitCounter = 0;

    private RetryUtil() {}

    public static <R> R retry(Supplier<R> function, Integer attempts) {
        return retry(function, attempts, 3_000);
    }

    public static <R> R retry(Supplier<R> function, Integer attempts, Integer waitMillis) {
        Exception exception;
        int retryCounter = 0;
        do {
            try {
                return function.get();
            } catch (RuntimeException e) {
                exception = e;
                log.warn("Exception: {}, retry: {} of {}", e.getMessage(), retryCounter, attempts);
                log.error(e.getMessage(), e);
                waitABit(waitMillis);
            }
        } while (++retryCounter < attempts);
        throw new ExceptionWrapper(
                String.format("Did not succeed after retries: %s", exception.getMessage()));
    }

    public static void waitABit(int waitingTimeMillis) {
        try {
            log.debug("Wait a bit: {} ms", waitingTimeMillis);
            waitingABitCounter = waitingABitCounter + waitingTimeMillis;
            Thread.sleep(waitingTimeMillis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

