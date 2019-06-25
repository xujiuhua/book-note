package com.xujh.recursion;


/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class kthGrammar {

    public static void main(String[] args) {
        kthGrammar kthGrammar = new kthGrammar();
        int r = kthGrammar.helper(5, 11);
        System.out.println(r);

        int r2 = kthGrammar.helper2(5, 11);
        System.out.println(r2);
    }

    private int helper(int N, int K) {
        if (N == 1) {
            return 0;
        }
        return (~K & 1) ^ helper(N - 1, (K + 1) / 2);
    }

    private int helper2(int N, int K) {
        if (N == 1) {
            return 0;
        }

        if (K <= 1 << N - 2) {
            return helper2(N - 1, K);
        }
        return helper2(N-1, K - (1 << N-2)) ^ 1;
    }

}
