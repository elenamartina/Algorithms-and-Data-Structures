
// ((11342 * 33464991) + 11630) % 54394 -> 21796
// ((11342 * 33551353) + 11630) % 60981 -> 27961

public class HashTable {

    // public for testing purposes
    public int buckets[];
    public long a;
    public long c;
    public int size;
    public int nitems;
    public int tombstone = -1;

    public HashTable(long _a, long _c, long _m) {

        this.a = _a;
        this.c = _c;
        this.size = (int) _m;
        this.buckets = new int[this.size];
    }


    public int hash(int key) {

        return (int) ((this.a * key) + this.c) % this.size;
    }


    public boolean empty(int i) {

        if (this.buckets[i] == 0) {
            return true;
        }
        return false;
    }


    public boolean free(int i) {

        if (this.buckets[i] == this.tombstone || this.buckets[i] == 0) {

            return true;
        }
        return false;
    }


    public boolean find(int key) {

        int i = this.hash(key);
        int k = 0;

        do {
            i = this.probe(i, k);
            k ++;

            if (this.buckets[i] == key) {
                return true;
            }
        }
        while (!this.empty(i));

        return false;
    }


    public boolean full() {

        return nitems >= this.size;
    }


    public double loadFactor() {

        return (float)this.nitems/this.size;
    }


    public int probe(int i, int k) {

        return (i + k) % this.size;
    }


    public void extend() {

        HashTable newTable = new HashTable(this.a, this.c, this.size * 4);

        for (int i = 0; i < this.size; i++) {

            if (!this.free(i)) {

                newTable.insert(this.buckets[i]);
            }
        }

        this.buckets = newTable.buckets;
        this.size = newTable.size;

    }


    public void insert(int key) {

        this.nitems++;

        if (this.full()) {

            System.out.println("extend called");
            System.out.println("key == " + key);
            System.out.println("nitems == " + nitems);

            this.extend();
        }


        int i = this.hash(key);
        int j;
        int k = 0;

        do {

            j = this.probe(i, k);
            k++;

        }
        while (!this.free(j));

        this.buckets[j] = key;

    }


    public void remove(int key) {

        int i = this.hash(key);
        int k = 0;

        if (this.find(key)) {

            do {
                i = this.probe(i, k);
                k ++;
            }
            while (this.buckets[i] != key);

            this.buckets[i] = this.tombstone;
        }
    }

}
