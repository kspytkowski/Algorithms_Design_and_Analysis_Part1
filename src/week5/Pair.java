package week5;

class Pair implements Comparable<Pair> {

    private int index;
    private int weight;

    public Pair(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Pair p) {
        return this.weight - p.weight;
    }

}