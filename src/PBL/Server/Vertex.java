package PBL.Server;

public class Vertex {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int row;
    private int col;
    private int distance;
    private Vertex prev;

    public Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public char getSymbol() {
        return ALPHABET.charAt(col);
    }

    public static int getColFromSymbol(char symbol){
        return ALPHABET.indexOf(symbol);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }
}
