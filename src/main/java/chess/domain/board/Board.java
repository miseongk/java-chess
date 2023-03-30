package chess.domain.board;

import chess.domain.Position;
import chess.domain.score.Score;
import chess.domain.score.ScoreCalculator;
import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public static Board init() {
        Map<Position, Piece> setting = PieceFactory.createPieces();
        return new Board(setting);
    }

    public boolean isNotTurn(Position source, Team turn) {
        return !board.get(source).isSameTeam(turn);
    }

    public void move(Position source, Position target) {
        validateMovable(source, target);

        Piece sourcePiece = board.get(source);
        validatePathBeforeTarget(sourcePiece.findPath(source, target));

        movePiece(source, target);
    }

    private void validateMovable(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        if (!sourcePiece.isMovable(source, target, targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
    }

    private void validatePathBeforeTarget(List<Position> path) {
        for (int index = 0; index < path.size() - 1; index++) {
            validateBlocked(path, index);
        }
    }

    private void validateBlocked(List<Position> path, int index) {
        if (!isEmptyPiece(path.get(index))) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 있습니다.");
        }
    }

    private void movePiece(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        board.put(target, sourcePiece);
        if (!targetPiece.isSameTeam(Team.EMPTY)) {
            board.put(source, new Empty(Team.EMPTY));
            return;
        }
        board.put(source, targetPiece);
    }

    public boolean isEmptyPiece(Position source) {
        return board.get(source).getClass() == Empty.class;
    }

    public Score calculateScore(Team team) {
        return ScoreCalculator.calculateScore(board, team);
    }

    public boolean hasKing(Team team) {
        return board.keySet().stream()
                .filter(key -> board.get(key).isSameTeam(team))
                .anyMatch(key -> board.get(key).getClass() == King.class);
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
