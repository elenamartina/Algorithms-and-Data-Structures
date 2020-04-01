

public class BST {



    public Node root;

    BST() {
        this.root = new Node(null, null);
    }

    BST(Object[] items) {

        this.root = new Node(null, null);

        for (int i = 0; i < items.length; i++) {

            this.insert(items[i]);
        }
    }


    public class Node {


        private Object key;
        private Node parent;
        private Node left = null;
        private Node right = null;

        Node(Object _key, Node _parent) {

            this.key = _key;
            this.parent = _parent;
        }
    }


    public void insert() {


    }

    public void search() {


    }

}