import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Random;
import org.junit.Test;

public class BigIntDivTest {
    /*@Test
    public void testDiv() {
        BigInt b1 = new BigInt(3);
        b1.data[0] = 6;
        b1.data[1] = 4;
        b1.data[2] = 3;
        BigInt b2 = new BigInt(1);
        b2.data[0] = 7;
        BigInt b3 = b1.Div(b2);
        BigInt b11 = new BigInt(3);
        b11.data[0] = 6;
        b11.data[1] = 4;
        b11.data[2] = 3;
        BigInt b4 = b11.Rem(b2);
        assertEquals("346 / 7 = 49.4285714, integer part is wrong", 9, b3.get(0));
        assertEquals("346 / 7 = 49.4285714, integer part is wrong", 4, b3.get(1));
        assertEquals("346 / 7 = 49.4285714, remainder 3; remainder is wrong", 3, b4.get(0));
    }
*/
   /* @Test
    public void div0() {
        BigInt b1 = new BigInt(5);
        b1.data[0] = 7;
        b1.data[1] = 3;
        b1.data[2] = 5;
        b1.data[3] = 5;
        b1.data[4] = 6;


        BigInt b2 = new BigInt(5);
        b2.data[0] = 5;
        b2.data[1] = 3;
        b2.data[2] = 5;
        b2.data[3] = 5;
        b2.data[4] = 6;

        BigInt b3 = b1.Sub(b2);

        assertEquals("65537 - 65535 = 2, sub is wrong", 2, b3.get(0));
    }*/

    @Test
    public void sub() {
        BigInt b1 = new BigInt(6);
        b1.data[0] = 2;
        b1.data[1] = 7;
        b1.data[2] = 0;
        b1.data[3] = 1;
        b1.data[4] = 3;
        b1.data[5] = 1;

        BigInt b2 = new BigInt(5);
        b2.data[0] = 7;
        b2.data[1] = 3;
        b2.data[2] = 5;
        b2.data[3] = 5;
        b2.data[4] = 6;

        BigInt b3 = b1.Div(b2);

        BigInt b11 = new BigInt(6);
        b11.data[0] = 2;
        b11.data[1] = 7;
        b11.data[2] = 0;
        b11.data[3] = 1;
        b11.data[4] = 3;
        b11.data[5] = 1;

        BigInt b4 = b11.Rem(b2);

        assertEquals("131072 / 65536 = 2, integer part is wrong", 1, b3.get(0));
        assertEquals("131072 / 65536 = 2, remainder 0; remainder is wrong", 5, b4.get(0));
    }
}
