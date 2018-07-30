import java.util.Arrays;
import java.util.List;

public class Level {

    int[][] board;
    List<Character> moves;
    int[] car;

    public Level(int[][] board, List<Character> moves, int[] car) {
        this.board = board;
        this.moves = moves;
        this.car = car;
    }

    public boolean isSolved(List<int[]> parkingSpots) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            int[] parkingSpot = parkingSpots.get(i);
            if (board[parkingSpot[0]][parkingSpot[1]] != CarParkingSolver.BOX) return false;
        }
        return true;
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
