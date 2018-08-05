package userinterace;

import javafx.util.Pair;
import parser.Sanitizer;
import robot.RobotManager;
import solver.CarParkingSolver;
import solver.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private Scanner scanner;
    private CarParkingSolver carParkingSolver;
    private RobotManager robotManager;
    private Sanitizer sanitizer;
    private List<Level> levels;

    public CommandLineInterface(Scanner scanner, CarParkingSolver carParkingSolver,
                                RobotManager robotManager, Sanitizer sanitizer) {
        this.scanner = scanner;
        this.carParkingSolver = carParkingSolver;
        this.robotManager = robotManager;
        this.sanitizer = sanitizer;
    }

    public void init() {
        System.out.println("-----Converting data to levels-----");
        // extract our list of levels
        levels = sanitizer.parseDataToLevels();
        System.out.println("-----Conversion completed-----\n");
        showLevelChoices();
    }

    private void showLevelChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1) Solve all levels");
        System.out.println("2) Solve single level");
        System.out.println("3) Solve range of levels");
        System.out.println("4) Exit");
        int choice = getUserInput();
        while(choice <= -1 || choice >= 5) choice = getUserInput();
        handleLevelChoices(choice);
    }

    private void handleLevelChoices(int choice) {
        switch (choice) {
            case 1:
                showModeChoices(levels);
                break;
            case 2:
                showSingleLevelChoice();
                break;
            case 3:
                showRangeLevelChoice();
                break;
            case 4:
                System.exit(0);
                break;
        }
        showLevelChoices();
    }

    private void showModeChoices(List<Level> levels) {
        System.out.println("Choose a mode.");
        System.out.println("1. Use robot mode");
        System.out.println("2. Print results");
        int choice = getUserInput();
        while (choice <= -1 || choice >= 3) choice = getUserInput();
        handleModeChoices(choice, levels);
    }

    private void showRangeLevelChoice() {
        System.out.println("\n------------------------------------INFO--------------------------------------");
        System.out.println("Enter a min/max number to represent a range of levels to solve.");
        System.out.println("The min/max number must be in the interval 1 <= num <= 200 and the max number");
        System.out.println("must be greater than or equal to the min number.\n");
        System.out.println("------------------------------------------------------------------------------\n");
        System.out.println("Enter min:");
        int firstChoice = getUserInput();
        while (firstChoice < 1 || firstChoice > 200) firstChoice = getUserInput();
        System.out.println("Enter max:");
        int secondChoice = getUserInput();
        while (secondChoice < 1 || secondChoice > 200 || secondChoice < firstChoice) secondChoice = getUserInput();
        List<Level> chosenLevels = new ArrayList<>();
        for (int i = firstChoice - 1; i < secondChoice; i++) chosenLevels.add(levels.get(i));
        showModeChoices(chosenLevels);
    }

    private void showSingleLevelChoice() {
        System.out.println("Choose a level between 1 and 200.");
        int choice = getUserInput();
        while (choice < 1 || choice > 200) choice = getUserInput();
        List<Level> level = Arrays.asList(levels.get(choice - 1));
        showModeChoices(level);
    }

    private void handleModeChoices(int choice, List<Level> levels) {
        // create list of moves for each level
        List<Pair<Integer, List<Character>>> allLevelResults = new ArrayList<>();
        System.out.println("\n-----Solving level(s)-----");
        for (Level level : levels) {
            allLevelResults.add(new Pair<>(level.getLevel(), carParkingSolver.solveLevel(level)));
            System.out.println("Level " + level.getLevel() + " solved.");
        }
        System.out.println("-----Level(s) solved-----\n");
        switch (choice) {
            case 1:
                showRobotMode(allLevelResults);
                break;
            case 2:
                showPrintResultsMode(allLevelResults);
                break;
        }
    }

    private void showRobotMode(List<Pair<Integer, List<Character>>> results) {
        System.out.println("------------------------------------INFO--------------------------------------");
        System.out.println("To use robot mode, you must have the Car Parking Game open in the browser.");
        System.out.println("Once you click enter to start, click on the browser running the game and wait");
        System.out.println("for the robot to solve all of the levels!");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nPress ENTER when you are ready to start!");
        // block until key is pressed
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nDelaying robot for 5 seconds. Open up the car parking game now!");
        System.out.println("Warning: Do not click arrow keys or mouse while the robot is performing.");
        try {
            robotManager.solveLevels(results);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----Control given back to user, completed level(s)-----\n");
    }

    private void showPrintResultsMode(List<Pair<Integer, List<Character>>> results) {
        System.out.println("-----Printing results-----");
        for (int i = 0; i < results.size(); i++) {
            Pair<Integer, List<Character>> pair = results.get(i);
            System.out.println("Level " + pair.getKey() + ":\t");
            for (int j = 0; j < pair.getValue().size(); j++) System.out.print(pair.getValue().get(j) + "\t");
            System.out.println();
        }
        System.out.print("-----Completed printing results-----\n\n");
    }

    private int getUserInput() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            // You messed up!
        }
        return choice;
    }

}
