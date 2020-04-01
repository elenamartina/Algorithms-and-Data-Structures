public class StackQueue {

    private static Stack s;

    private static Queue q;

    public static void main(String[] args) {

        prepare();
        System.out.print(q.dequeue()); // 3

        System.out.print(s.pop()); // 3
        System.out.print(s.pop()); // 4

        System.out.print(q.dequeue()); // 6

        s.pop(); // mao
        q.dequeue(); // gna
        System.out.print(s.pop()); // 4
        System.out.print(q.dequeue()); // 9
        System.out.print(s.pop()); // 9
        System.out.println(q.dequeue()); // 1
        s.pop(); // sushi
        q.dequeue(); // meow
    }

    private static void prepare() {

        //33464991

        s = new Stack();
        q = new Queue();

        s.push(7);
        s.push(9);
        s.push(4);
        s.push(7);
        s.push(4);
        s.push(3);

        q.enqueue(3);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(9);
        q.enqueue(1);
        q.enqueue(7);

    }
}
