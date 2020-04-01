
public class NaiveStringMatch extends StringMatch {


    public int match(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        boolean found;

        for (int i = 0; i <= (n - m); i ++) {


            found = true;

            for (int j = 0; j < m; j ++) {

                counter.add(2);
                if (text.charAt(i+j) != pattern.charAt(j)) {

                    found = false;
                    break;
                }
            }
            if (found) {

                return i;
            }
        }
        return -1;
    }
}
