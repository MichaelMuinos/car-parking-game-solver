import parser.Sanitizer;
import robot.RobotManager;
import solver.CarParkingSolver;
import userinterace.CommandLineInterface;

import java.awt.*;
import java.util.Scanner;

// Goal: Given a level with a car and an even number of
// boxes and parking spots, return the exact moves to win the level.
// https://www.mindgames.com/game/Car+Parking+2
public class Driver {

    public static void main(String[] args) throws AWTException {
        // create our scanner
        Scanner scanner = new Scanner(System.in);
        // create our sanitizer
        Sanitizer sanitizer = new Sanitizer();
        // create our robot manager
        RobotManager robotManager = new RobotManager();
        // create our car parking solver object
        CarParkingSolver carParkingSolver = new CarParkingSolver();
        // create our command line
        CommandLineInterface commandLineInterface = new CommandLineInterface(scanner, carParkingSolver, robotManager, sanitizer);
        commandLineInterface.init();
    }

}
