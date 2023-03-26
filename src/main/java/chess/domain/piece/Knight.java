package chess.domain.piece;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Piece pieceInTarget) {
        return MoveStrategy.KNIGHT.isMovable(source, target)
                && !this.isSameTeam(pieceInTarget);
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PieceType type() {
        return PieceType.Knight;
    }
}
