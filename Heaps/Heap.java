import java.util.ArrayList;
import java.util.List;

public class Heap {

    public OpCounter counter = new OpCounter();

    // public for JUnit testing purposes
    public ArrayList<Integer> array;
    public int heap_size;

    public Heap(int size) {

        this.heap_size = size;
        this.array = new ArrayList<Integer>(this.heap_size);
    }

    public Heap(List<Integer> source) {

        this(source, false);
    }

    public Heap(List<Integer> source, boolean incremental) {

        this(0);

        if (incremental) {

            for (int i = 0; i < source.size(); i ++) {

                counter.add(1);
                this.insert(source.get(i));
            }
        }

        else if (!incremental) {

            for (int i = 0; i < source.size(); i ++) {


                counter.add(1);
                this.array.add(source.get(i));
            }

            this.heap_size = this.array.size();

            buildMaxHeap();
        }


    }

    public void buildMaxHeap() {

        int h = (int) this.heap_size/2;

        for (int j = h; j >= 0; j --)

            maxHeapify(j);
    }

    public static int parent(int index) {

        return (int)((index - 1) / 2);
    }

    public static int left(int index) {

        return 2 * index + 1;
    }

    public static int right(int index) {

        return 2 * index + 2;
    }

    public void swap(int a, int b) {

        counter.add(2);

        Integer temp = this.array.get(a);
        this.array.set(a, this.array.get(b));
        this.array.set(b, temp);
    }


    public void insert(Integer k) {

        this.array.add(this.heap_size, k);

        int i = this.heap_size;
        this.heap_size ++;

        while ( i > 0 && this.array.get(parent(i)) < this.array.get(i) ) {

            swap(i, parent(i));
            i = parent(i);
        }
    }


    public Integer maximum() {

        Integer max = 0;

        for (int i = 0; i < this.array.size(); i ++) {

            if (max < this.array.get(i))
                max = this.array.get(i);
        }

        return max;
    }


    public void maxHeapify(int i) {

        int l = left(i);
        int r = right(i);
        int largest = i;

        if (l < this.heap_size && this.array.get(l) > this.array.get(largest))
            largest = l;


        if (r < this.heap_size && this.array.get(r) > this.array.get(largest))
            largest = r;

        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }


    public Integer extractMax() {

        Integer max = this.array.get(0);
        this.array.set(0, this.array.get(this.heap_size - 1));
        this.heap_size --;

        maxHeapify(0);

        return max;
    }




    public ArrayList<Integer> sort() {

        while (this.heap_size > 0) {

            int i = this.heap_size - 1;
            this.array.set(i, this.extractMax());
        }
        return this.array;
    }

}