package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map.Entry;

public class MysqlChessBoardDao implements ChessBoardDao {

    private final Database database = new Database();

    @Override
    public void save(long chessGameId, Board board) {
        String query = "INSERT INTO chess_board (chess_game_id, piece_type, piece_rank, piece_file, team)"
                + "VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Entry<Position, Piece> boardEntry : board.getBoard().entrySet()) {
                preparedStatement.setLong(1, chessGameId);
                preparedStatement.setString(2, boardEntry.getValue().type().getType());
                preparedStatement.setInt(3, boardEntry.getKey().getRank());
                preparedStatement.setInt(4, boardEntry.getKey().getFile());
                preparedStatement.setString(5, boardEntry.getValue().getTeam().name());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long chessGameId, Position piecePosition, Piece piece) {
        String query = "UPDATE chess_board SET piece_type = ?, team = ? "
                + "WHERE piece_file = ? and piece_rank = ? and chess_game_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.type().getType());
            preparedStatement.setString(2, piece.getTeam().name());
            preparedStatement.setInt(3, piecePosition.getFile());
            preparedStatement.setInt(4, piecePosition.getRank());
            preparedStatement.setLong(5, chessGameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
