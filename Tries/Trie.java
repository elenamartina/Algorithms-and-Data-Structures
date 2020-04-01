

public class Trie {

    public Trie[] children;
    public char letter;

    public final int max_index = 26;
    public final char end = '{';


    public Trie() {

        this.children = new Trie[this.max_index+1];
    }

    public int getIndex(char c) {

        if (c == this.end)
            return this.max_index;
        else
            return (int)c - 97;
    }


    public int nChildren() {

        int counter = 0;
        for (int i = 0; i < this.max_index; i++) {

            if (this.children[i] != null)
                counter ++;
        }

        return counter;
    }

    public Trie[] getChildren() {


        Trie[] childs = new Trie[this.nChildren()];

        int j = 0;
        for (int i = 0; i < this.max_index; i++) {

            if (this.children[i] != null)
                childs[j] = this.children[i];
            j++;
        }
        return childs;
    }

    public Trie getOnlyChild() {

        for (int i = 0; i <= this.max_index; i++) {

            if (this.children[i] != null)
                return this.children[i];
        }
        return null;
    }



    public void insert(String s) {

        s += this.end;
        this.insert(s, 0);
    }

    private void insert(String s, int string_i) {

        if (this.letter == this.end)
            return;

        char c = s.charAt(string_i);
        int i = this.getIndex(c);

        if (this.children[i] == null) {

            this.children[i] = new Trie();
            this.children[i].letter = c;
        }
        this.children[i].insert(s, string_i+1);
    }



    public boolean query(String s) {

        s += this.end;
        return this.query(s, 0);
    }

    private boolean query(String s, int string_i) {

        char c = s.charAt(string_i);
        int i = this.getIndex(c);

        if (this.children[i] == null)
            return false;

        if (this.children[i].letter == this.end && s.charAt(string_i) == this.end)
            return true;

        if (this.children[i].letter == this.end || s.charAt(string_i) == this.end)
            return false;

        return this.children[i].query(s, string_i+1);
    }
}
