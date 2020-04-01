
public class Node {

    private char character; // Value of the node
    private int weight; // Frequency of the value contained in the node
    private int order; // Indexing system for the weights; each order corresponds to a specific position in the tree.
    Node parent = null; // Pointer to Parent Node
    Node left = null; // Pointer to Left Child Node
    Node right = null; // Pointer to Right Child Node
    boolean isDAG = false;


    // DAG Node constructor.
    // As DAG has no value or children, and its weight
    // and order are 0, we just need to set its Parent node.
    public Node(Node parent) {

        this.parent = parent;
        this.weight = 0;
        this.order = 0;
        this.isDAG = true;
    }

    // Leaf Node constructor.
    // As a Leaf node has no children, we just need to set the value
    // (the character) it contains and its Parent node.
    // The weight and order of a new leaf node always correspond to 1.
    public Node(char character, Node parent) {

        this.character = character;
        this.weight = 1;
        this.order = 1;
        this.parent = parent;
    }

    // Internal Node constructor.
    // It doesn't contain a character, as it is not a Leaf node,
    // but everything else need to be set (weight, order, pointers).
    public Node(int weight, int order, Node parent, Node left, Node right) {

        this.weight = weight;
        this.order = order;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    // Boolean function.
    // Returns true if the node is a Leaf node,
    // false otherwise (if it is an internal node).
    public boolean isLeaf() {

        return this.left == null && this.right == null;
    }


    // Boolean function.
    // Returns true if the node is DAG, false otherwise.
    public boolean isDAG() {

        return this.isDAG;
    }


    //*************** GET and SET FUNCTIONS ***************//

    public char getCharacter() {
        return this.character;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Node getParent() {
        return this.parent;
    }

    // Sets the parent, right and left child nodes of a Node.
    public void setRelationships(Node parent, Node left, Node right) {

        this.parent = parent;
        this.left = left;
        this.right = right;
    }

}