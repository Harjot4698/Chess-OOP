package v0;

public class Board {
    private final Piece[][] pieces;

    public Board() {
        pieces = new Piece[8][8];

        // initialize the board with the starting positions of the pieces
        initializeWhitePieces();
        initializeBlackPieces();
    }

    private void initializeWhitePieces() {
        // initialize white pawns
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Piece(PieceType.PAWN, Color.WHITE, 1, i);
        }

        // initialize white rooks
        pieces[0][0] = new Piece(PieceType.ROOK, Color.WHITE, 0, 0);
        pieces[0][7] = new Piece(PieceType.ROOK, Color.WHITE, 0, 7);

        // initialize white knights
        pieces[0][1] = new Piece(PieceType.KNIGHT, Color.WHITE, 0, 1);
        pieces[0][6] = new Piece(PieceType.KNIGHT, Color.WHITE, 0, 6);

        // initialize white bishops
        pieces[0][2] = new Piece(PieceType.BISHOP, Color.WHITE, 0, 2);
        pieces[0][5] = new Piece(PieceType.BISHOP, Color.WHITE, 0, 5);

        // initialize the white queen and king
        pieces[0][3] = new Piece(PieceType.QUEEN, Color.WHITE, 0, 3);
        pieces[0][4] = new Piece(PieceType.KING, Color.WHITE, 0, 4);
    }

    private void initializeBlackPieces() {
        // initialize black pawns
        for (int i = 0; i < 8; i++) {
            pieces[6][i] = new Piece(PieceType.PAWN, Color.BLACK, 6, i);
        }

        // initialize black rooks
        pieces[7][0] = new Piece(PieceType.ROOK, Color.BLACK, 7, 0);
        pieces[7][7] = new Piece(PieceType.ROOK, Color.BLACK, 7, 7);

        // initialize black knights
        pieces[7][1] = new Piece(PieceType.KNIGHT, Color.BLACK, 7, 1);
        pieces[7][6] = new Piece(PieceType.KNIGHT, Color.BLACK, 7, 6);

        // initialize black bishops
        pieces[7][2] = new Piece(PieceType.BISHOP, Color.BLACK, 7, 2);
        pieces[7][5] = new Piece(PieceType.BISHOP, Color.BLACK, 7, 5);

        // initialize the black queen and king
        pieces[7][3] = new Piece(PieceType.QUEEN, Color.BLACK, 7, 3);
        pieces[7][4] = new Piece(PieceType.KING, Color.BLACK, 7, 4);
    }
    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // check if the move is valid, and if so, move the piece and return true
        // otherwise, return false

        Piece fromPiece = pieces[fromRow][fromCol];
        Piece toPiece = pieces[toRow][toCol];

        if (fromPiece == null) {
            // there is no piece at the from position
            return false;
        }

        // check if the to position is occupied by a piece of the same color
        if (toPiece != null && toPiece.getColor() == fromPiece.getColor()) {
            return false;
        }

        // check if the move is valid for the specific piece type
        switch (fromPiece.getType()) {
            case PAWN:
                if (!isValidPawnMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            case KNIGHT:
                if (!isValidKnightMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            case BISHOP:
                if (!isValidBishopMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            case ROOK:
                if (!isValidRookMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            case QUEEN:
                if (!isValidQueenMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            case KING:
                if (!isValidKingMove(fromRow, fromCol, toRow, toCol)) {
                    return false;
                }
                break;
            default:
                return false;
        }

        // the move is valid, so make the move and return true
        pieces[toRow][toCol] = fromPiece;
        pieces[fromRow][fromCol] = null;
        fromPiece.setRow(toRow);
        fromPiece.setCol(toCol);
        return true;
    }

    private boolean isValidKingMove(int fromRow, int fromCol, int toRow, int toCol) {
        return false;
    }

    private boolean isValidQueenMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow == toRow && fromCol == toCol) {
            // the queen hasn't moved
            return false;
        }

        // check if the move is a valid diagonal move
        if (Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol)) {
            // check if there are any pieces blocking the way
            int rowStep = (toRow - fromRow) / Math.abs(toRow - fromRow);
            int colStep = (toCol - fromCol) / Math.abs(toCol - fromCol);
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow) {
                if (pieces[row][col] != null) {
                    // there is a piece blocking the way
                    return false;
                }
                row += rowStep;
                col += colStep;
            }

            // the move is a valid diagonal move
            return true;
        }

        // check if the move is a valid horizontal or vertical move
        if (fromRow == toRow || fromCol == toCol) {
            // check if there are any pieces blocking the way
            int rowStep = fromRow == toRow ? 0 : (toRow - fromRow) / Math.abs(toRow - fromRow);
            int colStep = fromCol == toCol ? 0 : (toCol - fromCol) / Math.abs(toCol - fromCol);
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow || col != toCol) {
                if (pieces[row][col] != null) {
                    // there is a piece blocking the way
                    return false;
                }
                row += rowStep;
                col += colStep;
            }

            // the move is a valid horizontal or vertical move
            return true;
        }

        // the move is not valid
        return false;
    }


    private boolean isValidRookMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow == toRow && fromCol == toCol) {
            // the rook hasn't moved
            return false;
        }

        // check if the move is a valid horizontal or vertical move
        if (fromRow == toRow || fromCol == toCol) {
            // check if there are any pieces blocking the way
            int rowStep = fromRow == toRow ? 0 : (toRow - fromRow) / Math.abs(toRow - fromRow);
            int colStep = fromCol == toCol ? 0 : (toCol - fromCol) / Math.abs(toCol - fromCol);
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow || col != toCol) {
                if (pieces[row][col] != null) {
                    // there is a piece blocking the way
                    return false;
                }
                row += rowStep;
                col += colStep;
            }

            // the move is a valid horizontal or vertical move
            return true;
        }

        // the move is not valid
        return false;
    }


    private boolean isValidBishopMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow == toRow && fromCol == toCol) {
            // the bishop hasn't moved
            return false;
        }

        // check if the move is a valid diagonal move
        if (Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol)) {
            // check if there are any pieces blocking the way
            int rowStep = (toRow - fromRow) / Math.abs(toRow - fromRow);
            int colStep = (toCol - fromCol) / Math.abs(toCol - fromCol);
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow) {
                if (pieces[row][col] != null) {
                    // there is a piece blocking the way
                    return false;
                }
                row += rowStep;
                col += colStep;
            }

            // the move is a valid diagonal move
            return true;
        }

        // the move is not valid
        return false;
    }


    private boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 1) {
            // the move is a valid "L" shape
            return true;
        }

        if (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 2) {
            // the move is a valid "L" shape
            return true;
        }

        // the move is not valid
        return false;
    }


    private boolean isValidPawnMove(int fromRow, int fromCol, int toRow, int toCol) {
        Piece pawn = pieces[fromRow][fromCol];
        int direction = pawn.getColor() == Color.WHITE ? 1 : -1;

        if (fromCol != toCol) {
            // pawns can only move straight ahead, so the columns must be the same
            // however, pawns can capture pieces diagonally, so we need to check for that
            if (Math.abs(fromCol - toCol) != 1 || toRow - fromRow != direction) {
                return false;
            }

            // check if there is a piece to capture
            Piece capturedPiece = pieces[toRow][toCol];
            if (capturedPiece == null || capturedPiece.getColor() == pawn.getColor()) {
                return false;
            }

            // the move is a valid capture
            return true;
        }

        // the columns are the same, so this is a straight move
        if (toRow - fromRow != direction) {
            return false;
        }

        // check if the destination is occupied
        if (pieces[toRow][toCol] != null) {
            return false;
        }

        // the move is a valid straight move
        return true;
    }


    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }
}

