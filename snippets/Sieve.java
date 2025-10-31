// Sieve of Eratosthenes - Find all primes up to n
public class Sieve {
    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    // Segmented Sieve - Find all primes in range [left, right]
    // Useful when the range is large but the difference is small
    static boolean[] segmentedSieve(long left, long right) {
        long limit = (long) Math.sqrt(right) + 1;
        boolean[] basePrimes = sieve((int) limit);

        boolean[] isPrime = new boolean[(int)(right - left + 1)];
        for (int i = 0; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        // Handle special case for 0 and 1
        if (left <= 1) {
            for (long i = left; i <= Math.min(right, 1); i++) {
                isPrime[(int)(i - left)] = false;
            }
        }

        // Mark multiples of primes in basePrimes
        for (int i = 2; i <= limit; i++) {
            if (basePrimes[i]) {
                // Find first multiple of i in range [left, right]
                long firstMultiple = ((left + i - 1) / i) * i;
                if (firstMultiple == i) firstMultiple += i;

                // Mark all multiples as composite
                for (long j = firstMultiple; j <= right; j += i) {
                    isPrime[(int)(j - left)] = false;
                }
            }
        }
        return isPrime;
    }
}
