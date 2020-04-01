import java.util.Iterator;
import java.util.PriorityQueue;

public class FrequencyTable<T extends Node> extends PriorityQueue<T> {

    @Override
    public boolean add(T e) {

        boolean added = false;
        int newWeight = 1;


        for (Iterator<T> i = this.iterator(); i.hasNext(); ) {

            T n = i.next();

            if (n.equalTo(e)) {
                newWeight = n.getWeight() + 1;
                i.remove();
            }
        }

        Node newNode = new Node(e.getCharacter());
        newNode.setWeight(newWeight);

        added = super.add((T) newNode);

        return added;
    }

    public boolean contains(T e) {

        for (Iterator<T> i = this.iterator(); i.hasNext(); ) {

            T n = i.next();

            if (n.equalTo(e)) {
                return true;
            }
        }
        return false;
    }

    public void print() {

        System.out.println(" Char | Weight");
        System.out.println("_____________");
        Iterator<T> i = this.iterator();

        while (i.hasNext()) {

            T n = i.next();

            System.out.println("  " + n.getCharacter() + "   |  " + n.getWeight());
        }

    }
}