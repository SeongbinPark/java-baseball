package baseball.game;

import static baseball.util.ComputerUtil.getComputerNumbers;
import static baseball.util.UserUtil.getUserNumbers;

import java.util.List;

public class Baseball {

    private static final String STRIKE_MESSAGE = "스트라이크";
    private static final String BALL_MESSAGE = "볼 ";
    private static final String NOTHING_MESSAGE = "낫싱";

    private static final String GAME_STARTED_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String INPUT_NUMBER_MESSAGE = "숫자를 입력해주세요 : ";

    private int strikeCount;
    private int ballCount;

    public void proceedGame() {
        while (true) {
            startGame();
        }
    }

    private void startGame() {
        List<Integer> computerNumbers = getComputerNumbers();

        while (true) {
            strikeCount = 0;
            ballCount = 0;

            System.out.println(GAME_STARTED_MESSAGE);
            System.out.print(INPUT_NUMBER_MESSAGE);

            List<Integer> userNumbers = getUserNumbers();

            countStrikeAndBall(computerNumbers, userNumbers);
        }
    }

    private void countStrikeAndBall(List<Integer> computerNumbers, List<Integer> userNumbers) {
        for (int i = 0; i < 3; i++) {
            String result = getStrikeOrBall(computerNumbers, userNumbers.get(i), i);

            if (result.equals(STRIKE_MESSAGE)) {
                strikeCount++;
            }

            if (result.equals(BALL_MESSAGE)) {
                ballCount++;
            }
        }
    }

    private String getStrikeOrBall(List<Integer> computerNumbers, int userNumber, int index) {
        for (int i = 0; i < 3; i++) {
            if (hasSameNumberAndIndex(computerNumbers, userNumber, i, index)) {
                return STRIKE_MESSAGE;
            }

            if (hasSameNumber(computerNumbers, userNumber, i)) {
                return BALL_MESSAGE;
            }
        }

        return NOTHING_MESSAGE;
    }

    private boolean hasSameNumberAndIndex(List<Integer> computerNumbers, int userNumber, int i, int index) {
        return hasSameNumber(computerNumbers, userNumber, i) && hasSameIndex(i, index);
    }

    private boolean hasSameNumber(List<Integer> computerNumbers, int userNumber, int i) {
        return computerNumbers.get(i) == userNumber;
    }

    private boolean hasSameIndex(int i, int index) {
        return i == index;
    }

    private String getHint(int ballCount, int strikeCount) {
        String hint = "";

        if (strikeCount == 0 && ballCount == 0) {
            hint = NOTHING_MESSAGE;
        }

        if (ballCount > 0) {
            hint = ballCount + BALL_MESSAGE;
        }

        if (strikeCount > 0) {
            hint = hint + strikeCount + STRIKE_MESSAGE;
        }

        return hint;
    }
}
