import solver.CarParkingSolver;
import solver.Level;
import java.util.*;

// Goal: Given a level with a car and an even number of
// boxes and parking spots, return the exact moves to win the level.
// https://www.mindgames.com/game/Car+Parking+2
public class Driver {

    public static void main(String[] args) {
        // create our sanitizer
        Sanitizer sanitizer = new Sanitizer();
        // extract our list of levels
        List<Level> levels = sanitizer.parseDataToLevels();

        // create our car parking solver object
        CarParkingSolver carParkingSolver = new CarParkingSolver();

        for (Level level : levels) {
            // retrieve the moves and print the results
            List<Character> moves = carParkingSolver.solveLevel(level);
            System.out.print("Level " + level.getLevel() + ":\t");
            if (moves == null) System.out.print("Invalid level!");
            else for (char c : moves) System.out.print(c + "\t");
            System.out.println("\n");
        }
    }

}
