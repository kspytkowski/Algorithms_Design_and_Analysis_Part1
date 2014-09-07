package week3;

public class Edge implements Cloneable {

    private int first; // first vertex of edge
    private int second; // second vertex of edge

    public Edge(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirstVertex() {
        return first;
    }

    public int getSecondVertex() {
        return second;
    }

    public void setFirstVertex(int first) {
        this.first = first;
    }

    public void setSecondVertex(int second) {
        this.second = second;
    }

    public boolean isLoop() {
        if (first == second)
            return true;
        return false;
    }
    
    public String toString() {
        return "[" + first + ", " + second + "]";
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Edge)super.clone();
    }

}
