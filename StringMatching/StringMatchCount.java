class StringMatchCount {
    public static void main(String args[]) {

        String T = "";
        String P = "";

/**
 // WORST CASE - NAIVE

        for (int i = 0; i < 54394; i ++) {
            T = T + "a";
        }

        for (int i = 0; i < 3345; i ++) {
            P = P + "a";
        }

        P = P + "b";


 // EQUAL SIZE PATTERNS - RK

         for (int i = 0; i < 54394; i ++) {
            T = T + "a";
            P = P + "a";
         }


 // PREFIX TABLE COUNT - KMP

         for (int i = 0; i < 54393; i ++) {
            P = P + "a";
         }
         P = P + "b";
**/

        for (int i = 0; i < 60981; i ++) {
            T = T + "a";
            P = P + "a";
        }

        StringMatch naive = new NaiveStringMatch();

        naive.match(T, P);
        System.out.println("------------------------------------------------");
        System.out.println("Naive String Matching Algorithm - char reads: " + naive.counter.report());


        StringMatch RK = new RKStringMatch();

        RK.match(T, P);
        System.out.println("------------------------------------------------");
        System.out.println("RK String Matching Algorithm -  char reads: " + RK.counter.report());


        StringMatch KMP = new KMPStringMatch();

        KMP.match(T, P);
        System.out.println("------------------------------------------------");
        System.out.println("KMP String Matching Algorithm - char reads: " + KMP.counter.report());
        System.out.println("------------------------------------------------");

    }
}

/*
        String T = "";
        for (int i = 0; i < 33464991; i ++) {
            T = T + "a";
        }
        String P = "";
        for (int i = 0; i < 54393; i ++) {
            P = P + "a";
        }
        P = P + "b";*/

        /*String P = "ab";
        String T = "aaaaaaaaaa";*/