import java.util.ArrayList;

public class GCD {

    public static void main(String[] args) {
        int n1 = 366, n2 = 60;
        timefind(n1, n2);
    }

    public static int euclid_hcf(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        } else {
            int rem = n1 % n2;
            return euclid_hcf(n2, rem);
        }
    }

    public static int consec_hcf(int a, int b) {
        int cur_hcf = 1;
        for (int i = 1; i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                cur_hcf = i;
            }
        }
        return cur_hcf;
    }

    public static int midsch_hcf(int a, int b) {
        int cur_hcf = 1;

        ArrayList<Integer> fac1 = new ArrayList<Integer>();
        ArrayList<Integer> fac2 = new ArrayList<Integer>();

        int c1 = 2;
        int c2 = 2;

        while (a != 1) {
            if ((a % c1) == 0) {
                fac1.add(c1);
                a = a / c1;
            } else {
                c1++;
            }
        }

        while (b != 1) {
            if ((b % c2) == 0) {
                fac2.add(c2);
                b = b / c2;
            } else {
                c2++;
            }
        }

        for (Integer element : fac1) {
            if (fac2.contains(element)) {
                cur_hcf = cur_hcf * element;
                fac2.remove(element);
            }
        }
        return cur_hcf;
    }

    public static void timefind(int a, int b) {
        long startTime1 = System.nanoTime();
        int result1 = euclid_hcf(a, b);
        long endTime1 = System.nanoTime();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("\nEuclid HCF: " + result1);
        System.out.println("Execution time: " + executionTime1 + " nanoseconds");

        long startTime2 = System.nanoTime();
        int result2 = consec_hcf(a, b);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("\nConsecutive HCF: " + result2);
        System.out.println("Execution time: " + executionTime2 + " nanoseconds");

        long startTime3 = System.nanoTime();
        int result3 = midsch_hcf(a, b);
        long endTime3 = System.nanoTime();
        long executionTime3 = endTime3 - startTime3;
        System.out.println("\nMid School HCF: " + result3);
        System.out.println("Execution time: " + executionTime3 + " nanoseconds");
    }
}
