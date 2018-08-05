package solver;

import java.util.*;

public class CarParkingSolver {

    public static final int OUT_OF_BOUNDS = 0;
    public static final int EMPTY_SPACE = 1;
    public static final int CAR = 2;
    public static final int BOX = 3;
    public static final int PARKING_SPOT = 4;
    public static final int PARKING_SPOT_AND_BOX = 5;
    public static final int PARKING_SPOT_AND_CAR = 6;


    public List<Character> solveLevel(Level level) {
        // sanity check -> check for null values
        if (level == null || level.parkingSpots == null || level.parkingSpots.size() == 0) return null;

        // create queue of level objects
        Queue<Level> levelQueue = new LinkedList<>();
        levelQueue.add(level);

        // create hash set of level objects to ensure we do not get stuck
        Set<Level> levelSet = new HashSet<>();

        // loop through our queue until we solve the level
        while (!levelQueue.isEmpty()) {
            // poll the front of the queue and check if the level is solved
            Level levelDequeue = levelQueue.poll();
            if (levelDequeue.isSolved()) return levelDequeue.moves;

            // add our levelDequeue object to the set
            levelSet.add(levelDequeue);

            // extract the board
            int[][] board = levelDequeue.board;
            // extract our car position
            int[] carPosition = levelDequeue.car;
            // extract the moves
            List<Character> movesDequeue = levelDequeue.moves;

            // move our car left, right, up, and down if there is a valid move
            Direction[] directions = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
            for (Direction direction : directions) {
                int[][] copy = copyLevelBoard(board);
                if (moveCar(copy, carPosition, direction)) {
                    // create our moves array with the new moves
                    List<Character> moves = new ArrayList<>(movesDequeue);
                    char move = determineMove(direction);
                    moves.add(move);

                    // create our new car position
                    int[] childCarPosition = determineCarPosition(carPosition, direction);

                    // create our new level
                    Level childLevel = new Level(levelDequeue.level, copy, childCarPosition, moves, levelDequeue.parkingSpots);
                    if (!levelSet.contains(childLevel)) levelQueue.add(childLevel);
                }
            }
        }

        // if there are no moves left
        return null;
    }

    public int[][] copyLevelBoard(int[][] board) {
        int[][] copyBoard = new int[board.length][board[0].length];
        for (int i = 0; i < copyBoard.length; i++) {
            for (int j = 0; j < copyBoard[0].length; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        return copyBoard;
    }

    public boolean moveCar(int[][] board, int[] car, Direction direction) {
        // Cases:
        // 1) car cannot move in the direction due to it being out of bounds
        // 2) a box is in the spot the car needs to move, thus check if the box can be moved in the same direction as well
        //      2a) check to make sure the box being moved will not go out of bounds
        //      2b) check to make sure the box being moved does not have another box
        int[] pos = determineCarPosition(car, direction);
        // case 1: out of bounds check for car
        if (isOutOfBounds(board, pos[0], pos[1])) return false;
        // case 2: box check
        if (board[pos[0]][pos[1]] == BOX) {
            // case 2a: out of bounds check for box
            // case 2b: check if another box is present
            int[] boxPos = determineCarPosition(pos, direction);
            if (isOutOfBounds(board, boxPos[0], boxPos[1]) || board[boxPos[0]][boxPos[1]] == BOX) return false;
            // move the box
            board[boxPos[0]][boxPos[1]] = BOX;
        }
        // move the car
        board[pos[0]][pos[1]] = CAR;
        // remove the old car position to be empty space
        board[car[0]][car[1]] = EMPTY_SPACE;
        return true;
    }

    public boolean isOutOfBounds(int[][] board, int i, int j) {
        return i < 0 || j < 0 || i >= board.length || j == board[0].length || board[i][j] == OUT_OF_BOUNDS;
    }

    public int[] determineCarPosition(int[] carPosition, Direction direction) {
        int x = carPosition[0], y = carPosition[1];
        if (direction == Direction.DOWN) ++x;
        else if (direction == Direction.UP) --x;
        else if (direction == Direction.LEFT) --y;
        else ++y;
        return new int[] {x, y};
    }

    public Character determineMove(Direction direction) {
        if (direction == Direction.DOWN) return 'v';
        else if (direction == Direction.UP) return '^';
        else if (direction == Direction.LEFT) return '<';
        else return '>';
    }

}
