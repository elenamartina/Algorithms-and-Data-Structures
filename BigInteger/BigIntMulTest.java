import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Random;
import org.junit.Test;

public class BigIntMulTest {
    @Test
    public void testMul() {
        BigInt b1 = new BigInt(1);
        b1.data[0] = 7;
        BigInt b2 = new BigInt(1);
        b2.data[0] = 8;
        BigInt b3 = b1.Mul(b2);
        assertEquals("7*8 % 10 != 6", 6, b3.get(0));
        assertEquals("7*8/10 % 10 != 5", 5, b3.get(1));
    }
    @Test
    public void testMulwith3Elements() {
        BigInt b1 = new BigInt(4);
        b1.data[0] = 3;
        b1.data[1] = 8;
        b1.data[2] = 3;
        b1.data[3] = 5;
        BigInt b2 = new BigInt(3);
        b2.data[0] = 7;
        b2.data[1] = 3;
        b2.data[2] = 7;
        BigInt b3 = b1.Mul(b2);
        assertEquals("First element of 3'967'271 should be: 1", 1, b3.get(0));
        assertEquals("Second element of 3'967'271 should be: 7", 7, b3.get(1));
        assertEquals("Third element of 3'967'271 should be: 2", 2, b3.get(2));
        assertEquals("Fourth element of 3'967'271 should be: 7", 7, b3.get(3));
        assertEquals("Fifth element of 3'967'271 should be: 6", 6, b3.get(4));
        assertEquals("Sixth element of 3'967'271 should be: 9", 9, b3.get(5));
        assertEquals("Last element of 3'967'271 should be: 3", 3, b3.get(6));
    }
    @Test
    public void testRandomMul() {
        BigInt ba = new BigInt(6);
        BigInt bb = new BigInt(6);
        Random r = new Random();
        long a = r.nextInt(1000);
        long b = r.nextInt(1000);
        long atmp = a;
        long btmp = b;
        for (int i = 0; i < 6; i++) {
            ba.data[i] = (char) (atmp % 10);
            atmp = atmp / 10;
            bb.data[i] = (char) (btmp % 10);
            btmp = btmp / 10;
        }
        BigInt bc = ba.Mul(bb);
        long c = a * b;
        long ctmp = c;
        for (int i = 0; i < 6; i++) {
            assertEquals("mismatch in random multiplication", (ctmp % 10), bc.get(i));
            ctmp = ctmp / 10;
        }
    }
}
