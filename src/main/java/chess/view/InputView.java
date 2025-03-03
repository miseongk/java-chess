package chess.view;

import static chess.view.InputValidator.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMANDS_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public void readStart() {
        String input = scanner.nextLine();
        validateStartCommand(input);
    }

    public String readGame() {
        System.out.println("게임을 이어서 하려면 게임 아이디를, 게임을 새로 시작하려면 'new' 를 입력해주세요.");
        String input = scanner.nextLine();
        if (GameCommand.isNew(input)) {
            return input;
        }
        validateDigit(input);
        return input;
    }

    public String readCommand() {
        String input = scanner.nextLine();

        if (GameCommand.isEnd(input) || GameCommand.isStatus(input)) {
            return input;
        }

        List<String> commands = Arrays.asList(input.split(COMMANDS_DELIMITER));
        validateCommands(commands);

        return commands.get(1) + commands.get(2);
    }

    private void validateCommands(List<String> commands) {
        validateBlank(commands);
        validateMoveCommand(commands.get(0));
        validateCommandSize(commands);
        validateCommandLength(commands.get(1), commands.get(2));
        validateFile(commands.get(1).charAt(0));
        validateFile(commands.get(2).charAt(0));
        validateRank(commands.get(1).charAt(1));
        validateRank(commands.get(2).charAt(1));
    }
}
