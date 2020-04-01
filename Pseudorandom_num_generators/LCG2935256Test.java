import static java.util.Arrays.asList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.lang.Math;


public class LCG2935256Test {
    @Test
    public void testSequence() {
        LCG g = new LCG(29, 35, 256, 1);
        {
            long z[] = {64, 99, 90, 85, 196, 87, 254, 233, 136, 139};
            for (int i = 0; i < 10; i++) {
                assertEquals("unexpected next", z[i], g.next());
            }
        }
        g = new LCG(29, 35, 256, 2);
        {
            long z[] = {93, 172, 159, 38, 113, 240, 83, 138, 197, 116};
            for (int i = 0; i < 10; i++) {
                assertEquals("unexpected next", z[i], g.next());
            }
        }
        g = new LCG(29, 35, 256, 3);
        {
            long z[] = {122, 245, 228, 247, 30, 137, 168, 43, 2, 93};
            for (int i = 0; i < 10; i++) {
                assertEquals("unexpected next", z[i], g.next());
            }
        }
    }
    @Test
    public void testNext() {
        LCG g = new LCG(29, 35, 256, 0);
        long z = 0;
        for (int i = 0; i < 256; i++) {
            z = (29*z + 35) % 256;
            long x = g.next();
            assertEquals("unexpected next", z, x);
        }
    };
    @Test
    public void testSeed() {
        LCG g = new LCG(29, 35, 256, 11);
        g.seed(1);
        assertEquals("seed appears not to have seeded", 64, g.next());
    }

    @Test
    public void testShift() {
/*            Seed is: 107048004364969

            seed = (seed * 25214903917 + 11) mod 2^48

            long mask = (1L << 48) - 1L;

// Equivalent to Random.setSeed
seed = (seed ^ 25214903917L) & mask;

System.out.println("Seed is: " + seed);

for (int i = 1; i <= 10; ++i)
{
    // Equivalent to Random.next(32) or Random.nextInt()
    seed = (seed * 25214903917L + 11L) & mask;
    int output = (int)(seed >>> 16);

    System.out.println("Output #" + i + " is " + output);
}
            */

        LCG g = new LCG(25214903917L, 11L, (long)Math.pow(2, 48), 107048004364969L, 16);
        {
            long z[] = {0, 0, 4232237, 178803790, 758674372, 1565954732, 392261992, 396415378, 2092582042, 2262375224L, 1951776693};
            for (int i = 0; i < z.length; i++) {
                assertEquals("unexpected next", z[i], g.next());
            }
        }
    }



    // For the LCG with a = 1664525, c = 1013904223 and m = 4294967296,
    // what value should be passed to the seed method in order for next to return your five-digit moodle ID?
    // 1715700167
/*    @Test
    public void testMoodle() {

        LCG g = new LCG(1664525, 1013904223, 4294967296L, 0);

        for (long i = 0; i < 4294967296L; i++) {

            g.seed(i);

            if (g.next() == 54394) {
                assertEquals("X has been found: ", 0, i);
                break;
            }
        }
    }*/
}
