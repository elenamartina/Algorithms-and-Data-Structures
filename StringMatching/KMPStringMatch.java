
public class KMPStringMatch extends StringMatch {



    public boolean counterComparison(OpCounter c, char p1, char p2) {

        c.add(2);
        return p1 != p2;
    }

    public int[] computePrefix(String pattern) {

        int m = pattern.length();

        int[] pi = new int[m];
        pi[0] = 0;

        int k = 0;

        for (int q = 1; q < m; q ++) {

            while (k > 0 && counterComparison(counter, pattern.charAt(k), pattern.charAt(q))) {

                k = pi[k-1];
            }

            counter.add(2);
            if (pattern.charAt(k) == pattern.charAt(q)) {

                k ++;
            }

            pi[q] = k;
        }

        return pi;
    }


    public int match(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        int[] pi = computePrefix(pattern);

        int q = 0;

        for (int i = 0; i < n; i++) {

            //while (q > 0 && counterComparison(counter, pattern.charAt(q), text.charAt(i))) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {

                q = pi[q - 1];
            }

            //counter.add(2);
            if (pattern.charAt(q) == text.charAt(i)) {

                q ++;
            }

            if (q == m) {
                return i - m + 1;
            }

        }

        return -1;
    }
}
