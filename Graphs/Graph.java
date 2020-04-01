import java.util.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Graph {

    public ArrayList<Vertex> vlist;
    public ArrayList<Edge> elist;

    public Graph() {

        this.vlist = new ArrayList<Vertex>();
        this.elist = new ArrayList<Edge>();
    }

    public void addVertex(String name) {

        Vertex v = new Vertex(name);

        if (!this.belongsTo(v, this.vlist))
            this.vlist.add(v);
    }

    public Vertex getVertex(String name) {

        for (Vertex v : this.vlist) {

            if (name == v.name)
                return v;
        }
        return null;
    }

    public void addEdge(String from, String to, int weight) {

        Vertex v1 = this.getVertex(from);
        Vertex v2 = this.getVertex(to);

        Edge e = new Edge(v1, v2, weight);

        v1.adjlist.add(e);
        v2.adjlist.add(e);
        this.elist.add(e);
    }

    public Edge getEdge(String from, String to) {

        Vertex v1 = this.getVertex(from);
        Vertex v2 = this.getVertex(to);

        if (v1 != null && v2 != null && v1 != v2) {

            for (Edge e : v1.adjlist) {
                for (Edge f : v2.adjlist) {

                    if (e == f)
                        return e;
                }
            }
        }
        return null;
    }

    public boolean belongsTo(Vertex elem, ArrayList<Vertex> list) {

        for (Vertex v : list) {

            if (v.name == elem.name)
                return true;
        }
        return false;
    }

    public boolean belongsToPrevs(Vertex elem, ArrayList<Vertex> list) {

        for (Vertex v : list) {
            if (v.prev != null && v.prev.name == elem.name)
                return true;
        }
        return false;
    }


    public Graph Prims() {

        Graph t = new Graph();

        if (this.vlist.size() == 0)
            return t;

        t.addVertex(this.vlist.get(0).name);

        while (t.vlist.size() < this.vlist.size()) {

            Edge newE = null;
            Vertex newV = null;
            int w = Integer.MAX_VALUE;

            for (Edge e : this.elist) {

                if (this.belongsTo(e.from, t.vlist) && !(this.belongsTo(e.to, t.vlist))) {

                    if (e.weight < w) {

                        w = e.weight;
                        newE = e;
                        newV = e.to;
                    }

                } else if (this.belongsTo(e.to, t.vlist) && !(this.belongsTo(e.from, t.vlist))) {

                    if (e.weight < w) {

                        w = e.weight;
                        newE = e;
                        newV = e.from;
                    }
                }
            }

            t.addVertex(newV.name);
            t.addEdge(newE.from.name, newE.to.name, w);

        }
        return t;

    }

    public Graph MST() {

        return Prims();
    }

    public int MSTCost() {

        Graph st = MST();

        int sum = 0;

        for (Edge e : st.elist) {

            sum += e.weight;
        }
        return sum;
    }

    public class MyQueue {


        public ArrayList<Vertex> queue;

        public MyQueue (int size) {

            this.queue = new ArrayList<Vertex>(size);
        }

        public boolean isEmpty() {

            if (this.queue.size() == 0)
                return true;
            return false;

        }

        public void add(Vertex v) {

            this.queue.add(v);
        }

        public Vertex extractMin() {

            int min = Integer.MAX_VALUE;
            int i = 0;

            for (Vertex v : this.queue) {

                if (v.dist < min) {

                    min = v.dist;
                    i = this.queue.indexOf(v);
                }
            }
            System.out.println("");
            for (Vertex x : this.queue) {
                System.out.print("     " + x.name + ", " + x.dist);
            }
            System.out.println("");
            System.out.println("Minimum =  " + this.queue.get(i).name);
            System.out.println("");
            System.out.println("");

            return this.queue.remove(i);
        }

    }

    public ArrayList<Vertex> Neighbours(Vertex v) {

        ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

        for (Edge e : this.elist) {

            if (e.from.name == v.name && e.to.name != v.name)

                neighbours.add(e.to);

            if (e.to.name == v.name && e.from.name != v.name)

                neighbours.add(e.from);

        }
        return neighbours;
    }


    public void update(Vertex current, Vertex neww, String start, String end) {

        if (current.name == start || current.name == end)
            return;

        else {
            Vertex previous = current.prev;

            System.out.println("---------------------");
            System.out.println("");
            System.out.println("--------------UP--------------");
            System.out.println("Value of:          " + current.name + "  changed from  " + previous.name + " to " + neww.name);
            System.out.println("");
            current.setPrev(neww);

            this.update(previous, current, start, end);
        }
    }


    public Graph Dijkstras(String start, String end) {

        //ArrayList<Vertex> nodes = new ArrayList<Vertex>(this.vlist.size());
        Set<Vertex> visited = new HashSet<Vertex>();
        MyQueue q = new MyQueue(this.vlist.size());

        if (this.vlist.size() == 0) return new Graph();

        for (Vertex v : this.vlist) {

            if (v.name == start) {
                v.setDist(0);
                q.add(v);
            } else {
                v.setDist(Integer.MAX_VALUE);
                q.add(v);
            }
        }

        while (!q.isEmpty()) {
            // extract minimum from the priority queue!!!!
            Vertex u = q.extractMin();
            visited.add(u);

            if (u.name == end) {
                return makeFinalGraph(u, start);
            }




            for (Vertex v : this.Neighbours(u)) {

                System.out.println("");System.out.print("Neighbours of " + u.name + ":");
                for (Vertex x : this.Neighbours(u)) { System.out.print("   " + x.name); }


                System.out.println("");
                System.out.println(u.dist);
                System.out.println(this.getEdge(u.name, v.name).weight);
                System.out.println("");

                int d = u.dist + this.getEdge(u.name, v.name).weight;
                System.out.println("");System.out.println("V is :    " + v.name);System.out.println("U is :    " + u.name);System.out.println("d is :    " + d);

                if (d < v.dist) {

                    v.setDist(d);
                    v.setPrev(u);
                }
            }
        }
        return null;
    }


    public Graph makeFinalGraph(Vertex u, String start) {

        Graph s = new Graph();

        while (u.name != start) {


            System.out.println("-------END-------");
            System.out.println("Edge from:     " + u.name + " to:  " +  u.prev.name);

            s.addVertex(u.name);
            s.addVertex(u.prev.name);
            s.addEdge(u.name, u.prev.name, this.getEdge(u.name, u.prev.name).weight);

            u = u.prev;
        }
        return s;
    }



    public Graph SP(String from, String to) {

        return Dijkstras(from, to);
    }

    public int SPCost(String from, String to) {

        Graph sp = SP(from, to);

        int sum = 0;

        for (Edge e : sp.elist) {

            sum += e.weight;
        }
        return sum;
    }

}