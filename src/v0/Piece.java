package v0;

public class Piece {
    private PieceType type;
    private Color color;
    private int row;
    private int col;

    public Piece(PieceType type, Color color, int row, int col) {
        this.type = type;
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
}
