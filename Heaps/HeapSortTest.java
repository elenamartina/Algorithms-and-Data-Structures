import static java.util.Arrays.asList;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class HeapSortTest {

    @Test
    public void testHeapSortEmpty() {

        List<Integer> v = asList();
        Heap h = new Heap(v, false);
        ArrayList<Integer> a = h.sort();

        assertEquals("Heapsort of empty array should be an empty array", v, a);
        assertEquals("Length of empty array should be 0", 0, a.size());
    }

    @Test
    public void testsortOne() {

        List<Integer> v = asList(17);
        Heap h = new Heap(v, false);
        ArrayList<Integer> a = h.sort();

        assertEquals("Heapsort of [17] should be [17]", v, a);
        assertEquals("Length of 1 element array should be 1", 1, a.size());
    }

    @Test
    public void testNonTrivialsort() {

        List<Integer> v = asList(10, 8, 14, 15, 12, 5, 6, 1);

        Heap h = new Heap(v, false);
        ArrayList<Integer> a = h.sort();

        System.out.println("------------------------------ ");
        System.out.print("Non Trivial HeapSort array: ");
        System.out.println(a);
        System.out.println("------------------------------ ");

        assertEquals("The resulting array should have length 8", 8, a.size());

        assertEquals("The first element is wrong", Integer.valueOf(1), a.get(0));
        assertEquals("The second element  is wrong", Integer.valueOf(5), a.get(1));
        assertEquals("The third element  is wrong", Integer.valueOf(6), a.get(2));
        assertEquals("The fourth element  is wrong", Integer.valueOf(8), a.get(3));
        assertEquals("The fifth element  is wrong", Integer.valueOf(10), a.get(4));
        assertEquals("The sixth element  is wrong", Integer.valueOf(12), a.get(5));
        assertEquals("The seventh element  is wrong", Integer.valueOf(14), a.get(6));
        assertEquals("The eighth element  is wrong", Integer.valueOf(15), a.get(7));

    }

}