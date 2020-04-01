

public class BinarySearch {


    public boolean search(int array[], int key, int lo, int hi) {

        int root = lo + (hi - lo) / 2;

        if (lo == hi)
            return false;

        else if (key == array[root])
            return true;

        else if (key < array[root])
            return search(array, key, lo, root);

        else
            return search(array, key, root + 1, hi);
    }

    public int count(int array[], int key, int lo, int hi) {

        int root = lo + (hi - lo - 1) / 2;

        if (lo == hi)
            return 0;

        else if (key == array[root])
            return 1;

        else if (key < array[root])
            return 1 + count(array, key, lo, root);

        else
            return 1 + count(array, key, root + 1, hi);
    }

}
