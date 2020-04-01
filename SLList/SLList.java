
class SLList {

    public Object first;
    public SLList rest;

    public SLList(Object f, SLList r) {
        first = f;
        rest = r;
    }

    public static final SLList NIL = new SLList(0, null);

    public Object first() {

        return first;
    }

    public SLList rest() {

        return rest;
    }

    public void setFirst(Object f) {

        first = f;
    }

    public void setRest(SLList r) {

        rest = r;
    }

    public Object nth(int n) {
        // WITH RECURSION:

        if (n == 0) {

            return first();

        } else {

            return rest().nth(n - 1);
        }
        // With Accumulator pattern:
/*
        public Object nth(int n) {

            SLList element = this;

            for (int i = 0; i < n; i++) {

                if (n == 0) {
                    return element.first;
                } else {
                    element = element.rest;
                }
            }
            return element.first;
        }
*/
    }

    public SLList nthRest(int n) {
        // RECURSION

        if (n == 0) {
            return this;

        } else {

            return rest().nthRest(n - 1);
        }
        // With Accumulator pattern:
/*
    public SLList nthRest(int n) {

        SLList element = this;

        for (int i = 0; i < n; i++) {

            if (n == 0) {
                return element.rest;
            } else {
                element = element.rest;
            }
        }
        return element;
     }
*/
    }

    public int length() {

        if (this == NIL) {
            return 0;
        } else {


            return 1 + rest().length();
        }

        //With Polymorphism:
/*
        public int length() {

        return this.lengthAux(0);
    }
    private int lengthAux(int counter) {
        if(rest == null) {
            return counter;
        }
        else {
            return rest.lengthAux(counter);
        }
    }
*/
    }

    public SLList remove(Object o) {

        if (this == NIL) {

            return NIL;
        } else if (first() == o) {

            return rest().remove(o);
        } else {
            return new SLList(first(), rest().remove(o));
        }
    }

    public Integer sum() {

        if (this == NIL) {
            return 0;
        } else {
            return (Integer) first() + rest().sum();
        }
    }

    public SLList reverse() {


        if (this == NIL) {
            return NIL;
        }
        if (this.length() == 1) {

            return new SLList(this.first(), this.rest());
        } else {

            return reverseAux(this, NIL);
        }
    }

    private SLList reverseAux(SLList list, SLList y) {
        if (list == NIL) {

            return y;
        } else {

            return reverseAux(list.rest(), new SLList(list.first(), y));
        }
    }

    public SLList sublist(int start, int end) {

        if (start == end) {

            return NIL;
        }
        else {

            return new SLList(this.nth(start), sublist(start+1, end));
        }
    }

    public SLList merge(SLList b) {

        if (this == NIL) {

            return b;
        }
        else if (b == NIL) {

            return this;
        }
        // sort them before merge them
        else if ((int) this.first() <= (int) b.first()) {

            return new SLList(this.first(), this.rest().merge(b));
        }
        else {
            return new SLList(b.first(), this.merge(b.rest()));
        }
    }


    public SLList mergesort() {

        int len = this.length();

        if (len <= 1) {

            return this;
        }
        else {
            int mid = (int) ((len) / 2);


            SLList left = this.sublist(0, mid).mergesort();
            SLList right = this.sublist(mid, len).mergesort();

            return left.merge(right);
        }
    }

}



