package graphAlgos;
class Edge {
    int to;
    int from;
    int cost;
    public Edge(int from, int to,  int cost) {
        this.to = to;
        this.from  = from;
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "from ->" + from +" to -> " + to +" cost "+cost;
    }
}