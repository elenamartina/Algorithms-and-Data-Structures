
public class Xorshift {

    long a;
    long b;
    long c;
    long state;

    public Xorshift(long _a, long _b, long _c, long seed) {

        this.a = _a;
        this.b = _b;
        this.c = _c;
        this.state = seed;
    }

    public long next() {

        this.state ^= (this.state << a) % 4294967296L; // left shif
        this.state ^= (this.state >>> b) % 4294967296L; // right shift
        return this.state ^= (this.state << c) % 4294967296L;
    }

    public void seed(long seed) {

        this.state = seed;
    }
}
