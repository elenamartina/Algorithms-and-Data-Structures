import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Xorshift3520Test {
    @Test
    public void testSequence() {
        Xorshift g = new Xorshift(3, 5, 20, 1);
        {
            long z[] = {9437193L, 32835L, 1687463497L, 3357546657L, 959044420L, 1628715375L, 2995281287L, 1692197938L, 4175043471L, 595235272L};
            for (int i = 0; i < 10; i++) {
                assertEquals("unexpected next", z[i], g.next());
            }
        }
    }
    @Test
    public void testNext() {
        Xorshift g = new Xorshift(3, 5, 20, 11);
        long z = 11;
        for (int i = 0; i < 256; i++) {
	    z ^= (z << 3) % 4294967296L;
	    z ^= (z >> 5) % 4294967296L;
	    z ^= (z << 20) % 4294967296L;
            long x = g.next();
            assertEquals("unexpected next", z, x);
        }
    }
    @Test
    public void testSeed() {
        Xorshift g = new Xorshift(3, 5, 20, 11);
        g.seed(1);
        assertEquals("seed appears not to have seeded", 9437193L, g.next());
    }



    // For Xorshift with a = 13, b = 17, c = 5 and a 32-bit state,
    // what value should be passed to the seed method in order for next to return your five-digit moodle ID?
    // 3441213009
    /*@Test
    public void testMoodle() {

        Xorshift g = new Xorshift(13, 17, 5, 0);

        for (long i = 1; i < 4294967296L; i++) {

            g.seed(i);

            if (g.next() == 54394) {
                assertEquals("X has been found: ", 0, i);
                break;
            }
        }
    }*/
}
