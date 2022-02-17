package ch15;

public class TailRecNaive {
    public static void main(String[] args) {
        long l = Long.parseLong(args[0]);
        System.out.println(tailrecFactorial(l));
    }

    private static long helpFact(long i, long j) {
        if (i == 0) {
            return j;
        }
        return helpFact(i - 1, i * j);
    }

    public static long tailrecFactorial(long n) {
        if (n <= 0) {
            return 1;
        }
        return helpFact(n, 1);
    }
}
