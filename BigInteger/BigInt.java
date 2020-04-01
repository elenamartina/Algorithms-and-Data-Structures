

public class BigInt {

    public int ndigits;
    public char data[];


    public BigInt(int _ndigits) {

        this.ndigits = _ndigits;
        this.data = new char[this.ndigits];
    }

    public BigInt(String s) {

        this.ndigits = s.length();
        this.data = new char[this.ndigits];

        int i = 0;
        int j = this.ndigits - 1;

        while (i < this.ndigits) {

            this.set(s.charAt(j), i);
            i++;
            j--;
        }
    }


    public char get(int i) {

        if (i < this.ndigits && i > -1)
            return data[i];
        else
            return 0;
    }

    public void set(char s, int i) {

        this.data[i] = this.transform(s);

    }

    public char transform(char c) {

        int value = Character.getNumericValue(c);

        return (char) Integer.parseInt(String.valueOf(value));
    }


    public BigInt Add(BigInt y) {

        int n = 1 + Math.max(this.ndigits, y.ndigits);
        char carry = 0;
        int sum = 0;
        BigInt r = new BigInt(n);

        for (int i = 0; i < n; i++) {

            sum = this.get(i) + y.get(i) + (int) carry;

            if (sum > 9) {
                carry = 1;
                r.set(String.valueOf(Math.abs((int) sum)).charAt(1), i);
            } else {
                carry = 0;
                r.set(String.valueOf(Math.abs((int) sum)).charAt(0), i);
            }
        }
        r.removeZeros();
        return r;
    }

    public BigInt Sub(BigInt y) {

        int n = this.ndigits;
        char borrow = 0;
        int diff = 0;
        BigInt r = new BigInt(n);

        for (int i = 0; i < n; i++) {

            if (this.get(i) < y.get(i)) {
                borrow = 10;


                if (this.data[i + 1] != 0)
                    this.data[i + 1] = (char) (this.get(i + 1) - 1);
                else {

                    int j = i + 1;

                    while (this.get(j) == 0) {

                        this.data[j] = 9;
                        j++;
                    }

                    this.data[j] = (char) (this.get(j) - 1);
                }
            } else {
                borrow = 0;
            }
            diff = this.get(i) - y.get(i) + (int) borrow;

            r.set(String.valueOf(Math.abs((int) diff)).charAt(0), i);

        }
        r.removeZeros();
        return r;
    }

    public BigInt Shift(int n) {

        char[] temp = this.data;

        this.ndigits += n;
        this.data = new char[this.ndigits + n];

        int i = 0;
        while (i < this.ndigits) {

            if (i < n)
                this.data[i] = 0;
            else
                this.data[i] = temp[i - n];

            i++;
        }

        return this;
    }

    public BigInt MulByDigit(char c) {

        int n = this.ndigits + 1;
        char carry = 0;
        int product = 0;
        BigInt r = new BigInt(n);

        for (int i = 0; i < n; i++) {


            product = this.get(i) * c + (int) carry;

            if (product > 9) {
                carry = this.transform(String.valueOf(Math.abs((int) product)).charAt(0));
                r.set(String.valueOf(Math.abs((int) product)).charAt(1), i);
            } else {
                carry = 0;
                r.set(String.valueOf(Math.abs((int) product)).charAt(0), i);
            }
        }
        r.removeZeros();
        return r;
    }

    public BigInt Mul(BigInt y) {

        BigInt r = new BigInt(this.ndigits + y.ndigits);

        for (int i = 0; i < this.ndigits; i++) {

            r = r.Add(this.MulByDigit(y.get(i)).Shift(i));

        }
        r.removeZeros();
        return r;
    }


    public void print() {

        System.out.println("");
        System.out.println("Length: " + this.ndigits);
        System.out.println("");
        System.out.print("[ ");
        for (int j = 0; j < this.ndigits; j++) {

            System.out.print((int) this.get(j) + ", ");
        }

        System.out.print("]");
        System.out.println("");
        System.out.println("");
    }

    public void reversePrint() {

        System.out.println("");
        System.out.println("Length: " + this.ndigits);
        System.out.println("");
        System.out.print("[ ");
        for (int j = this.ndigits; j > -1; j--) {

            System.out.print((int) this.get(j));
        }

        System.out.print("]");
        System.out.println("");
        System.out.println("");
    }


    public String toString() {

        String s = "";

        for (int j = this.ndigits; j > -1; j--) {

            s += String.valueOf((int) this.get(j));
        }

        return s;
    }



    public int nOfZeros() {

        int i = this.ndigits - 1;
        int counter = 0;

        while (i >= 0 && this.get(i) == 0) {

            counter++;
            i--;
        }

        return counter;
    }

    public void removeZeros() {

        char[] temp = new char[this.ndigits - this.nOfZeros()];

        for (int i = 0; i < temp.length; i++)
            temp[i] = this.get(i);


        this.data = new char[temp.length];

        for (int i = 0; i < temp.length; i++)
            this.data[i] = temp[i];
        this.ndigits = temp.length;
    }


    public boolean isLarger(BigInt b) {

        if (this.ndigits - this.nOfZeros() > b.ndigits - b.nOfZeros())
            return true; //return this;

        else if (b.ndigits - b.nOfZeros() > this.ndigits - this.nOfZeros())
            return false; //return b;

        else {
            for (int i = this.ndigits - 1; i > -1; i--) {

                if (this.get(i) > b.get(i)) {

                    return true; //return this;
                } else if (b.get(i) > this.get(i)) {

                    return false; //return b;
                } else {
                    continue;
                }
            }
            return true;
        }
    }

    public BigInt Div(BigInt d) {

        BigInt r = new BigInt(this.ndigits);
        for (int i = 0; i < this.ndigits; i++)
            r.data[i] = this.data[i];

        int counter = 0;

        while (r.isLarger(d)) {
            counter++;

            if (r.nOfZeros() == r.ndigits)
                return new BigInt(Integer.toString(counter));

            r = r.Sub(d);
        }

        return new BigInt(Integer.toString(counter));
    }

    public BigInt Rem(BigInt d) {


        BigInt r = new BigInt(this.ndigits);

        for (int i = 0; i < this.ndigits; i++)
            r.data[i] = this.data[i];


        while (r.isLarger(d)) {

            if (r.nOfZeros() == r.ndigits)
                return new BigInt(Integer.toString(0));

            r = r.Sub(d);

        }

        return r;
    }

    public BigInt Factorial() {

        this.removeZeros();

        if (this.ndigits < 2 && ((int)this.get(0)) == 1) {
            return new BigInt("1");
        }

        return this.Mul( (this.Sub( new BigInt("1") ) ).Factorial() );
    }
}