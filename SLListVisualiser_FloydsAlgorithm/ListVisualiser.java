
public class ListVisualiser {


    SLList l; // variable to store the linked list
    SLList start; // variable to store the start node of the cycle (if there is one)

    public ListVisualiser(SLList list) {

        l = list;
    }



    // My visualise function just chooses which type of block it should visualise for the next Node.
    // Those 5 different blocks are created by those 5 functions:
    // simpleBlock, lastBlock, startCycleBlock, middleCycleBlock, endCycleBlock
    public void visualise() {

        // some space between different lists
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        int cycleCounter = 0;

        // if there is a cycle in the list,
        // save the start node of the cycle in the variable start
        if (this.Floyds(l)) {
            start = this.cycleStart(l);
        }

        // if the list is NIL just return an ending block
        // (that will have 0 as a list.first())
        if (l == SLList.NIL) {
            this.lastBlock(l);
        }
        else {

            // while loop that has two conditions connected by an OR:
            // do stuff while:
            // 1. if there isn't a cycle, while the list doesn't reach NIL;
            // 2. if there is a cycle, while we don't reach the last element in the cycle
            while ((!this.Floyds(l) && l != SLList.NIL) || (this.Floyds(l) && cycleCounter < this.cycleLength(l))) {

                // if there is no cycle and it's not the last element of the list,
                // or there is a cycle but we didn't reach the beginning of it yet,
                // then draw a simple start/middle block (1)*
                if ((!this.Floyds(l) && l.rest() != SLList.NIL) || (this.Floyds(l) && l != start && cycleCounter <= 0)) {

                    this.simpleBlock(l);
                }
                // if it's the last element of a list with no cycles, then print a normal end block (2)*
                else if (l.rest() == SLList.NIL) {
                    this.lastBlock(l);
                }

                // if there is a cycle in the list and we've reached the start of it...
                else if (this.Floyds(l)) {


                    // if the cycle starts and ends in the same Node, then print a self-terminating cycle block (6)*
                    if (l == start && this.cycleLength(l) == 1) {
                        this.selfCycleBlock(l);
                        cycleCounter ++;
                    }

                    // if it's the start of the cycle, print the start block with cycle (3)*
                    // and increase the counter of the elements in the cycle by one
                    else if (l == start) {
                        this.startCycleBlock(l);
                        cycleCounter ++;
                    }
                    // if it's the end of the cycle (when the counter has the same value of the length of the list),
                    // print the end block with cycle (5)*
                    // and increase the counter of the elements in the cycle by one
                    else if (cycleCounter == this.cycleLength(l) - 1) {
                        this.endCycleBlock(l);
                        cycleCounter ++;
                    }
                    // if it's not either the start or the end of the cycle,
                    // print the middle block with cycle (4)*
                    // and increase the counter of the elements in the cycle by one
                    else {
                        this.middleCycleBlock(l);
                        cycleCounter ++;
                    }
                }
                l = l.rest();
            }
        }

    }

/*
    *
    Those 6 functions (simpleBlock, lastBlock, startCycleBlock, middleCycleBlock, endCycleBlock)
    print the basic building blocks of the list visualisation.
    My visualise function just chooses which one of those it should visualise.
    Basically, I considered all possible blocks my code should display, which are:

         ===             ===             ===          |  ===          |  ===             ===
        | * | -> 1      | * | -> 1     >| * | -> 1    | | * | -> 1    | | * | -> 1     >| * | -> 1
         ===             ===          |  ===          |  ===          |  ===          |  ===
        | * |           | / |         | | * |         | | / |          <| * |          <| * |
         ===             ===          |  ===          |  ===             ===             ===
          |                           |   |           |   |
          v                           |   v           |   v

          1.              2.              3.              4.               5.              6.
      no cycle;       no cycle;      with cycle;      with cycle;      with cycle;      with cycle;
    start/middle      end block      start block     middle block       end block    self-terminating
       block       (no connection                                                       cycle block
                    to next node)

*/

    // 1
    public void simpleBlock(SLList l) {

        System.out.println("   ===");
        System.out.println("  | * | -> " + l.first());
        System.out.println("   ===");
        System.out.println("  | * |");
        System.out.println("   ===");
        System.out.println("    |");
        System.out.println("    v");

    }

    // 2
    public void lastBlock(SLList l) {

        System.out.println("   ===");
        System.out.println("  | * | -> " + l.first());
        System.out.println("   ===");
        System.out.println("  | / |");
        System.out.println("   ===");

    }

    // 3
    public void startCycleBlock(SLList l) {

        System.out.println("   ===");
        System.out.println(" >| * | -> " + l.first());
        System.out.println("|  ===");
        System.out.println("| | * |");
        System.out.println("|  ===");
        System.out.println("|   |");
        System.out.println("|   v");

    }

    // 4
    public void middleCycleBlock(SLList l) {

        System.out.println("|  ===");
        System.out.println("| | * | -> " + l.first());
        System.out.println("|  ===");
        System.out.println("| | * |");
        System.out.println("|  ===");
        System.out.println("|   |");
        System.out.println("|   v");

    }

    // 5
    public void endCycleBlock(SLList l) {

        System.out.println("|  ===");
        System.out.println("| | * | -> " + l.first());
        System.out.println("|  ===");
        System.out.println(" <| * |");
        System.out.println("   ===");

    }

    // 6
    public void selfCycleBlock(SLList l) {

        System.out.println("   ===");
        System.out.println(" >| * | -> " + l.first());
        System.out.println("|  ===");
        System.out.println(" <| * |");
        System.out.println("   ===");

    }


    // Function to detect a cycle in the list (Floyd's algorithm):
    // it will return true if there is a cycle, else it will return false
    public boolean Floyds(SLList sequence) {

        // if the list is empty, it can't have cycles, so return false
        if (sequence == SLList.NIL) {
            return false;
        }

        // set two pointers at the start of the list.
        // these two pointers will go through the whole list,
        // one, the tortoise, will move slowly (by 1 step each time);
        // the second one, the hare, will be faster: it will move by 2 steps each time.
        SLList tortoise = sequence;
        SLList hare = sequence;

        // while the faster pointer doesn't find NIL (so the end of a list without cycles), do:
        while (hare != SLList.NIL) {

            // move the slow tortoise by 1
            tortoise = tortoise.rest();

            // if the rest (the next step) is not NIL, move the fast hare by 2 steps (two rests)
            if (hare.rest() != SLList.NIL) {
                hare = hare.rest().rest();
            }
            // else if the rest (the next step) is NIL, then there is no cycle, so return false!
            else {
                return false;
            }

            // if they finally meet, it means that there is a cycle, so return true!
            if (tortoise == hare) {

                return true;
            }
        }
        return false;
    }


    // Function to find the start of cycle
    public SLList cycleStart(SLList sequence) {

        // after finding out that the list has a cycle,
        // we might want to find out where this cycle starts.
        // in order to do that,
        // I'll let the two pointers meet again as they did in the Floyd's algorithm.
        SLList tortoise = sequence;
        SLList hare = sequence;

        // while(true) as I can assume that the list has a cycle
        while (true) {

            tortoise = tortoise.rest();
            hare = hare.rest().rest();

            if (tortoise == hare) {
                // now that they have met again,
                // I'll take one pointer (tortoise for example)
                // back to the start of the list,
                // while leaving the other (hare) at the meeting point.
                tortoise = sequence;

                // Then, from this position,
                // if I keep incrementing both of them by one,
                // they will meet again at the start of loop.
                while (tortoise != hare) {

                    // incrementing by one
                    hare = hare.rest();
                    tortoise = tortoise.rest();
                }
                // return one of them after they have met
                return tortoise;
            }
        }
    }


    // Function to find the length of the cycle
    public int cycleLength(SLList sequence) {

        // after finding out that the list has a cycle,
        // we might want to find out its length.
        // in order to do that,
        // I'll let the two pointers meet again as they did in the Floyd's algorithm.
        SLList tortoise = sequence;
        SLList hare = sequence;

        // counter to count the length
        int counter = 0;

        // while(true) as I can assume that the list has a cycle
        while (true) {

            tortoise = tortoise.rest();
            hare = hare.rest().rest();

            if (tortoise == hare) {
                // now that they have met again,
                // I'll keep one pointer (hare for example) fixed at the meeting point,
                // while incrementing the other (tortoise) until they are the same again.
                // In the meantime, I also increment a counter as the tortoise goes along:
                // the counter value (== the number of steps to reach the meeting point again)
                // will correspond to the length of the cycle.
                do {
                    counter++;
                    tortoise = tortoise.rest();

                } while (tortoise != hare);

                return counter;
            }
        }
    }


    public void hare_tortoise() {
        // cute ascii art ^-^

        System.out.println("               /\\ /|");
        System.out.println("              |||| |");
        System.out.println("               \\ | \\");
        System.out.println("           _ _ /  @ @          ._     .--.");
        System.out.println("         /    \\   =>X<=       (' \\_ /     `.");
        System.out.println("      @ /|      |   /           `._:^       \\:>");
        System.out.println("        \\|     /__| |              ^T~~~~~~T'");
        System.out.println("         \\_____\\ \\__\\               ~\"     ~\"");
    }


}

