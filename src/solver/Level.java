package solver;

import java.util.Arrays;
import java.util.List;

public class Level {

    int level;
    int[][] board;
    List<Character> moves;
    List<int[]> parkingSpots;
    int[] car;

    public Level(int level, int[][] board, int[] car, List<Character> moves, List<int[]> parkingSpots) {
        this.level = level;
        this.board = board;
        this.car = car;
        this.moves = moves;
        this.parkingSpots = parkingSpots;
    }

    public boolean isSolved() {
        for (int i = 0; i < parkingSpots.size(); i++) {
            int[] parkingSpot = parkingSpots.get(i);
            if (board[parkingSpot[0]][parkingSpot[1]] != CarParkingSolver.BOX) return false;
        }
        return true;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Level level = (Level) o;
        return Arrays.deepEquals(board, level.board);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(board);
        return result;
    }

}
