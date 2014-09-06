package week4;

class VertexTime implements Comparable<VertexTime> {

    private int vertex;
    private int time;

    public VertexTime(int i, int t) {
        this.vertex = i;
        this.time = t;
    }

    public int getVertex() {
        return vertex;
    }
    
    public int getTime() {
        return time;
    }
    
    @Override
    public int compareTo(VertexTime pair) {
        if (this.time < pair.time || this.time == pair.time && this.vertex < pair.vertex)
            return 1;
        else if (this.time == pair.time && this.vertex == pair.vertex)
            return 0;
        else
            return -1;
    }
    
    @Override
    public String toString() {
        return vertex + ": " + time;
    }
}