import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.PriorityQueue;


public class HuffmanTest {

    private HuffmanTree tree;

    @Before
    public void setUp() throws Exception {
        tree = new HuffmanTree();
    }


    @Test
    public void testNoSwapRequired() {

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('a');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('b');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('c');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
    }


    @Test
    public void testCourseworkExample() {
        // abcbbdaaddd
        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('a');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('b');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('c');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('b');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('b');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('d');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('a');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('a');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('d');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('d');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
        tree.insert('d');

        tree.printAlphabet();
        tree.printOrderList();
        tree.printTree();
    }


    public static void main(String args[]) {
        JUnitCore core = new JUnitCore();
        core.run(HuffmanTest.class);
    }
}
