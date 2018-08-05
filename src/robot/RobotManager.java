package robot;

import javafx.util.Pair;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class RobotManager {

    private static final int INITIAL_DELAY = 5000;
    private static final int LEVEL_CHANGE_DELAY = 1850;
    private static final int MOVE_DELAY = 150;

    private Robot robot;

    public RobotManager() throws AWTException {
        robot = new Robot();
    }

    public void solveLevels(List<Pair<Integer, List<Character>>> results) throws InterruptedException {
        performInitialDelay();
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.get(i).getValue().size(); j++) {
                handleMovement(results.get(i).getValue().get(j));
                performMoveDelay();
            }
            performLevelChangeDelay();
        }
    }

    private void handleMovement(char move) {
        int key = move == '<' ? KeyEvent.VK_LEFT :
                  move == '>' ? KeyEvent.VK_RIGHT :
                  move == '^' ? KeyEvent.VK_UP : KeyEvent.VK_DOWN;
        robot.keyPress(key);
    }

    private void performInitialDelay() {
        delay(INITIAL_DELAY);
    }

    private void performLevelChangeDelay() {
        delay(LEVEL_CHANGE_DELAY);
    }

    private void performMoveDelay() {
        delay(MOVE_DELAY);
    }

    private void delay(int delay) {
        robot.delay(delay);
        // sleep the thread to ensure we are only looping
        // through our movements as fast as our robot delay
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
