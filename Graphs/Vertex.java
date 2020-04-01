import java.util.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Vertex {

    public String name;
    public int dist;
    public Vertex prev;


    public ArrayList<Edge> adjlist;

    public Vertex() {};

    public Vertex(String name, int dist) {

        this.name = name;
        this.dist = dist;
    }

    public Vertex(String _name) {

        this.name = _name;
        this.adjlist = new ArrayList<Edge>();
    }

    // set
    public void setDist(int dist) {

        this.dist = dist;
    }

    public void setPrev(Vertex prev) {

        this.prev = prev;
    }

}