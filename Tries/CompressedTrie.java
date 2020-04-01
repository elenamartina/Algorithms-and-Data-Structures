import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class CompressedTrie {

    public Map<String, CompressedTrie> children;

    private static final int max_index = 26;
    private static final String end = "{";

    private String prefix = "";


    public CompressedTrie() {

        this.children = new HashMap<String, CompressedTrie>();
    }


    public static CompressedTrie compressTrie(Trie trie) {

        CompressedTrie t = new CompressedTrie();

        for (int i = 0; i < max_index; i++) {
            t.prefix = "";

            if (trie.children[i] == null)
                continue;

            if (trie.nChildren() <= 1)
                t.prefix = computePrefix(trie.getOnlyChild());

            if (trie.letter == trie.end) {
                t.children.put(t.prefix, new CompressedTrie());

                return t;
            }
            else {

                t.prefix = computePrefix(trie.children[i]);
                int levels = t.prefix.length();

                while (levels > 1) {
                    trie.children[i] = trie.children[i].getOnlyChild();
                    levels--;
                }

                t.children.put(t.prefix, t.compressTrie(trie.children[i]));
            }
        }
        return t;
    }


    public static String computePrefix(Trie trie) {

        String prefix = "";

        if ((int) trie.letter != 0)
            prefix += trie.letter;

        while (trie.nChildren() <= 1 && trie.letter != trie.end) {

            trie = trie.getOnlyChild();
            prefix += trie.letter;
        }
        return prefix;
    }


    public boolean query(String s) {

        s += this.end;
        return this.query(s, 0);
    }


    private boolean query(String s, int string_i) {

        if (this.children.isEmpty())
            return false;

        Set<String> keys = this.children.keySet();
        Iterator<String> iter = keys.iterator();

        int l = 0;
        String prefix = "";

        boolean found;

        while (iter.hasNext()) {

            found = true;
            prefix = iter.next();

            if (s.length()-string_i < prefix.length())
                continue;

            for (int j = 0; j < prefix.length(); j++) {

                if (s.charAt(string_i + j) != prefix.charAt(j)) {
                    found = false;
                }
            }
            if (found) {
                if (prefix.charAt(prefix.length() - 1) == '{')
                    return true;
                else
                    return this.children.get(prefix).query(s, string_i + prefix.length());
            }
        }
        return false;
    }

    public void insert(String s) {

    }
}
