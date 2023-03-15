package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"4:3", "4:5", "6:4"}, delimiter = ':')
    @DisplayName("룩은 상하좌우로 움직일 수 있다.")
    void isMovable(int rank, int file) {
        Piece rook = new Rook();
        Position source = new Position(4, 4);
        Position target = new Position(rank, file);
        assertThat(rook.isMovable(source, target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "7:5", "0:0"}, delimiter = ':')
    @DisplayName("룩은 상하좌우를 제외한 곳으로 움직일 수 없다.")
    void isUnmovable(int rank, int file) {
        Piece rook = new Rook();
        Position source = new Position(4, 4);
        Position target = new Position(rank, file);
        assertThat(rook.isMovable(source, target)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        Piece rook = new Rook();
        assertThat(rook.findPath(source, target)).containsAll(expectedPath);
    }

    static Stream<Arguments> findPathProvider() {
        return Stream.of(
                Arguments.of(
                        new Position(4, 4),
                        new Position(6, 4),
                        List.of(
                                new Position(5, 4),
                                new Position(6, 4)
                        )
                ),
                Arguments.of(
                        new Position(2, 1),
                        new Position(2, 6),
                        List.of(
                                new Position(2, 2),
                                new Position(2, 3),
                                new Position(2, 4),
                                new Position(2, 5),
                                new Position(2, 6)
                        )
                )
        );
    }
}
