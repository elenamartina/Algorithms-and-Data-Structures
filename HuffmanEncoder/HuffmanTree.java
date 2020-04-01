import java.util.BitSet;
import java.util.HashMap;
import java.util.ArrayList;


class HuffmanTree {

    private Node DAG; // DAG node
    private Node ROOT; // ROOT of the tree


    // This is an HashMap containing all the characters
    // that have been seen which are mapped to their corresponding Nodes.
    private HashMap<Character, Node> alphabet = new HashMap<>();

    // This is an ArrayList of Nodes used as an numbering/ordering system.
    // The index of a Node will correspond to its position in the tree.
    // The higher the weight of a node, the higher its index will be.
    private ArrayList<Node> orders = new ArrayList<>();


    // Initialises an Empty tree (containing only DAG,
    // which makes DAG also the ROOT of the tree).
    public HuffmanTree() {
        this.ROOT = new Node(null);
        this.DAG = this.ROOT;
        this.orders.add(this.ROOT);
    }


    // Boolean function.
    // Returns true if the tree is empty, false otherwise.
    public boolean isEmpty() { return this.ROOT == this.DAG; }

    // Function that returns the Node corresponding to a particular character (value)
    private Node getNode(char character){ return this.alphabet.get(character); }

    // Boolean function.
    // Returns true if the character has already been seen, false otherwise.
    private boolean contains(char character) { return this.alphabet.containsKey(character); }



    // Inserts a new character in the tree.
    //
    // If a Node containing that character already exists,
    // the updateTree() function is called,
    // which will update the weights and rearrange the tree if necessary.
    // If the character is new, the createNode() function
    // is called, which creates a new node.
    //
    // @param: character
    public void insert(char character) {

        // If it's not a new character, get that node
        // and update the tree with the new weight
        if (this.contains(character))
            this.updateTree(this.getNode(character));

        // else, add a new node and update the tree
        else
            this.updateTree(this.createNode(character));
    }


    // Creates a new node.
    //
    // @param: character
    // @return: old Node
    private Node createNode(char character) {

        // current DAG is the node that will become an internal node
        // and where we need to attach the new leaf node and the new DAG
        Node oldNode = this.DAG;

        // create instance of new DAG
        Node newDAG = new Node(this.DAG);
        // create instance of new node containing the new character
        // and having as a parent the old DAG
        Node newNode = new Node(character, this.DAG);

        // add the two new nodes as children of the old DAG
        this.DAG.setRelationships(this.DAG.getParent(), newNode, newDAG);

        // Set new DAG
        this.DAG.isDAG = false;
        this.DAG = newDAG;

        // add the new node and character to the alphabet
        // and add new node and new DAG to the orders array
        this.alphabet.put(character, newNode);
        this.orders.add(0, newNode);
        this.orders.add(0, newDAG);

        // Update the orders in the array
        this.updateOrders();

        return oldNode;
    }


    private void updateTree(Node node) {

        if (node == this.ROOT) {

            node.setWeight(node.getWeight() + 1);
            node.setOrder(this.orders.indexOf(node));
        }
        else {
            int order = this.orders.indexOf(node);

            for (int i = order; i < this.orders.size() - 1; i++) {

                Node next = this.orders.get(i + 1);

                if (node.getWeight() == next.getWeight() && next != node.getParent()) {

                    Node toSwap = this.getHighestNode(node);
                    this.swap(node, toSwap);
                }
            }

            node.setWeight(node.getWeight() + 1);
            this.updateTree(node.getParent());
        }
    }


    // Update and set the correct order value to each node
    private void updateOrders() {

        for (int i = 0; i < this.orders.size(); i++) {

            Node node = this.orders.get(i);
            node.setOrder(this.orders.indexOf(node));
        }
    }


    private Node getHighestNode(Node node) {

        for (int i = node.getOrder() + 1; i < this.ROOT.getOrder(); i++) {

            if (node.getWeight() != this.orders.get(i).getWeight()) {

                return node;
            }
            node = this.orders.get(i);
        }

        return node;
    }


    // Swap two nodes.
    private void swap(Node a, Node b) {

        Node aParent = a.getParent();
        Node bParent = b.getParent();

        if (aParent.left == a)
            aParent.left = b;
        else
            aParent.right = b;

        if (bParent.left == b)
            bParent.left = a;
        else
            bParent.right = a;

        a.parent = bParent;
        b.parent = aParent;

        this.orders.set(a.getOrder(), b);
        this.orders.set(b.getOrder(), a);
        // Update the orders in the array
        this.updateOrders();
    }


    /**
     * Given a value, find its code by traversing the tree.
     * Moving left = 0 bit, moving right = 1 bit
     *
     * Bits are stored as booleans in a list in reverse order
     * because tree is traversed from leaf to root.
     *
     * @param c - value to find in tree.
     * @param seen - flag to say if c exists in tree or not
     * @param buffer - list of bools representing bits
     * @return - number of bits in the code.
     */
    public int getCode(char c, boolean seen, ArrayList<Boolean> buffer) {
        int length = 0;
        if(!seen) { // Return NYT code
            if(DAG == ROOT) {
                return length; // Nothing in tree;
            }
            else {
                length = generateCode(DAG, buffer);
            }
        }
        else {
            length = generateCode(this.getNode(c),buffer);
        }
        return length;
    }


    /**
     * Generate in reverse order a list of booleans that represent
     * the bits for the code of a value in the tree.
     *
     * List generated in reverse order because we traverse the tree
     * from node up to root.
     *
     * @param in - Node to start from (leaf or nyt)
     * @param buffer - list of bools representing bits
     * @return number of bits in code == length of list.
     */
    private int generateCode(Node in, ArrayList<Boolean> buffer) {
        Node node = in;
        Node parent;
        int length = 0;
        while(node.parent != null) {
            parent = node.parent;
            if(parent.left == node) {
                buffer.add(false);
                length++;
            }
            else {
                buffer.add(true);
                length++;
            }
            node = parent;
        }
        return length;
    }


    public BitSet getCharacterCode(char character) {

        ArrayList<Character> code = new ArrayList<>();

        Node node = this.getNode(character);

        while (node != this.ROOT) {

            if (node.getParent().left == node) {

                code.add('0');
            }
            else {
                code.add('1');
            }
            node = node.getParent();
        }

        BitSet bitSet = new BitSet(code.size());
        int bitCounter = 0;
        for(Character c : code) {
            if(c.equals('1')) {
                bitSet.set(bitCounter);
            }
            bitCounter++;
        }
        return bitSet;

    }



    // Print the alphabet HashMap
    public void printAlphabet() {

        System.out.println("");
        System.out.print("Alphabet = [ ");

        for (Character c: this.alphabet.keySet()){

            System.out.print("(" + c + ", " + this.alphabet.get(c).getWeight() + ")");
        }
        System.out.println(" ]");
    }

    // Print the orders ArrayList
    public void printOrderList() {

        System.out.println("");
        System.out.print("Order List = [ ");

        for (int i = this.orders.size() - 1; i >= 0 ; i --) {

            System.out.print("(" + i + ": '" + this.orders.get(i).getCharacter() + "', " + this.orders.get(i).getWeight() + ")");
        }
        System.out.println(" ]");
    }

    // Auxiliary public function calling private recursive function depthFirst()
    public void printTree() {
        System.out.println("");
        depthFirst("", this.ROOT, false);
    }

    // Recursive Dept-First Tree traversal that prints
    // the nodes and the structure of the tree.
    private void depthFirst(String indentation, Node node, boolean isLeft) {

        if (node != null) {

            if (node == ROOT)
                System.out.println(indentation + "└── ROOT, " + node.getWeight());

            else if (node.isDAG())
                System.out.println(indentation + "└── DAG, " + node.getWeight());

            else if (node.isLeaf())
                System.out.println(indentation + (isLeft ? "├── " : "└── ") + node.getCharacter() + ", " + node.getWeight());

            else
                System.out.println(indentation + (isLeft ? "├── " : "└── ") + node.getWeight());

            this.depthFirst(indentation + (isLeft ? "│   " : "    "), node.left, true);
            this.depthFirst(indentation + (isLeft ? "│   " : "    "), node.right, false);
        }
    }

}


