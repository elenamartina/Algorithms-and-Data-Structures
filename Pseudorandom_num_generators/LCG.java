
public class LCG {

    long a;
    long c;
    long m;
    long state;
    long shift;

    public LCG(long _a, long _c, long _m, long seed) {

        this.a = _a;
        this.c = _c;
        this.m = _m;
        this.state = seed;
    }

    LCG(long _a, long _c, long _m, long seed, long _shift) {

        this.a = _a;
        this.c = _c;
        this.m = _m;
        this.state = seed;
        this.shift = _shift;
    }


    public long next() {

        this.state = (this.state * this.a + this.c) % m;

        long output = (this.state >>> this.shift) % 4294967296L;

        return output;
    }

    public void seed(long seed) {

        this.state = seed;
    }
}
