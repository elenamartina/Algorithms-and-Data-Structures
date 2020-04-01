
public class RKStringMatch extends StringMatch {


    public int hash(String p) {

        int m = p.length();

        int i = 0;
        int result = 0;

        while (i < m) {

            counter.add(1);
            result = result + (int)p.charAt(i);
            i ++;
        }


        return result % 256;
    }

    public int match(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();
        int hm = hash(pattern);

        boolean found;

        for (int s = 0; s <= (n - m); s ++) {

            if (hash(text.substring(s, s + m)) == hm) {

                found = true;

                for (int j = 0; j < m; j ++) {

                    counter.add(2);
                    if (text.charAt(s+j) != pattern.charAt(j)) {

                        found = false;
                        break;
                    }
                }

                if (found) {
                    return s;
                }
            }
        }
        return -1;
    }
}
