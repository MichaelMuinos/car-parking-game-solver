import java.util.*;

// Goal: Given a level with a car and an even number of
// boxes and parking spots, return the exact moves to win the level.
// https://www.mindgames.com/game/Car+Parking+2
public class Driver {

    public static void main(String[] args) {
        // Test Case 1: Level 1 Board
//        int[][] level1Board = {
//                {CarParkingSolver.CAR, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.BOX, CarParkingSolver.EMPTY_SPACE},
//                {CarParkingSolver.EMPTY_SPACE, CarParkingSolver.BOX, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE},
//                {CarParkingSolver.OUT_OF_BOUNDS, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE}
//        };
//
//        // parking spot positions on the board
//        List<int[]> parkingSpots = Arrays.asList(new int[] {0, 2}, new int[] {2, 3});
//
//        // create our level object containing the car position, moves, and board
//        Level level1 = new Level(level1Board, new ArrayList<>(), new int[] {0, 0});

        // Test Case 2: Level 2 Board
        int[][] level2Board = {
                {CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.OUT_OF_BOUNDS, CarParkingSolver.OUT_OF_BOUNDS, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE},
                {CarParkingSolver.EMPTY_SPACE, CarParkingSolver.BOX, CarParkingSolver.CAR, CarParkingSolver.BOX, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE},
                {CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.OUT_OF_BOUNDS, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE, CarParkingSolver.EMPTY_SPACE}
        };

        List<int[]> parkingSpots = Arrays.asList(new int[] {2, 0}, new int[] {2, 3});

        Level level2 = new Level(level2Board, new ArrayList<>(), new int[] {1, 2});

        // create our car parking solver object
        CarParkingSolver carParkingSolver = new CarParkingSolver();

        // retrieve the moves and print the results
        List<Character> moves = carParkingSolver.solveLevel(level2, parkingSpots);
        if (moves == null) System.out.println("Invalid level!");
        else for (char c : moves) System.out.print(c + "\t");
    }

}
