import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;

public class HeapCounter {

    public static void main(String args[]) {

        int nitems = 55308;

        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < nitems; i++) {
            a.add(i);
        }

        List<Integer> aList = a;

        System.out.println("");
        System.out.println("");

        incrementalInfo(aList);

        System.out.println("");
        System.out.println("------------------------------------------------");
        System.out.println("");

        directInfo(aList);

        System.out.println("");
        System.out.println("------------------------------------------------");
        System.out.println("");

    }

    public static void incrementalInfo( List<Integer> l) {
        // ANSWER: 804785

        Heap h = new Heap(l, true);

        System.out.print("Incrementally:");

        System.out.println("N:  " + l.size());

        System.out.println("Number of Moves:  " + h.counter.report());



        //print(h);

    }

    public static void directInfo(List<Integer> l) {
        // ANSWER: 163160

        Heap h = new Heap(l, false);

        System.out.println("Built in place:");

        System.out.println("N:  " + l.size());

        System.out.println("Memory writes:  " + h.counter.report());

        //print(h);
    }

    public static void print(Heap h) {

        System.out.print("[ ");

        for (int i = 0; i < h.heap_size; i++) {

            System.out.print(h.array.get(i));

            if (i == h.heap_size -1)
                System.out.print(" ]");
            else
                System.out.print(", ");
        }



    }

}
