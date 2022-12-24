package v0;

import java.util.Scanner;

public class ChessGame {
    private Board board;
    private Color currentPlayer;
    private boolean gameOver;

    public ChessGame() {
        board = new Board();
        currentPlayer = Color.WHITE;
        gameOver = false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        // main game loop
        while (!gameOver) {
            // print the board
            printBoard();

            // ask the current player for their move
            System.out.println(currentPlayer + " player's turn. Enter your move (e.g. a2 a4):");
            String move = scanner.nextLine();
            String[] coordinates = move.split(" ");
            if (coordinates.length != 2) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            int fromRow = 8 - Integer.parseInt(coordinates[0].substring(1));
            int fromCol = coordinates[0].charAt(0) - 'a';
            int toRow = 8 - Integer.parseInt(coordinates[1].substring(1));
            int toCol = coordinates[1].charAt(0) - 'a';

            // try to make the move
            if (!board.movePiece(fromRow, fromCol, toRow, toCol)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // switch players
            switchPlayer();
        }

        // game over
        scanner.close();
    }

    private void printBoard() {
        // print the board
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece == null) {
                    System.out.print("_ ");
                } else {
                    System.out.print(piece.getColor() == Color.WHITE ? piece.getType().name().charAt(0) : piece.getType().name().toLowerCase().charAt(0) + " ");
                }
            }
            System.out.println();
        }
    }

    private void switchPlayer() {
        // switch the current player from white to black, or black to white
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.play();
    }
}
