package chess.controller;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public void runChess() {
        OutputView.printStartGameMessage();
        if (!InputView.inputInitialCommand()) {
            return;
        }

        final Board board = new Board();
        proceedMain(board);
    }

    private void proceedMain(final Board board) {
        try {
            OutputView.printCurrentBoard(board.unwrap());
            proceed(board);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceedMain(board);
        }
    }

    private void proceed(final Board board) {
        final List<String> runtimeCommands = InputView.inputRuntimeCommand();

        if (InputView.END_COMMAND.equals(runtimeCommands.get(0))) {
            return;
        }

        final Position start = getPositionByCommands(runtimeCommands.get(1).split(""));
        final Position end = getPositionByCommands(runtimeCommands.get(2).split(""));
        board.move(start, end);
        proceed(board);
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }
}
