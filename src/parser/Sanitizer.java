package parser;

import solver.CarParkingSolver;
import solver.Level;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sanitizer {

    public List<Level> parseDataToLevels() {
        List<Level> levels = new ArrayList<>();

        // read from the parser directory
        String workingDirectory = System.getProperty("user.dir");
        File folder = new File(workingDirectory + "\\src\\parser\\data");
        File[] listOfFiles = folder.listFiles();

        // create level objects and add to our list
        for (File file : listOfFiles) {
            Level level = createLevel(file);
            if (level != null) levels.add(level);
        }

        // sort our list of levels
        Collections.sort(levels, Comparator.comparingInt(Level::getLevel));

        return levels;
    }

    private Level createLevel(File file) {
        // extract the level number
        int levelNumber = Integer.parseInt(file.getName().replaceAll("\\D+", ""));

        // read the passed in file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) lines.add(line);
            // if there are no lines in the file, we can return
            if (lines.size() == 0) return null;

            // create our board with the appropriate lengths and parking spots and car positions
            int[][] board = new int[lines.size()][lines.get(0).length()];
            int[] car = null;
            List<int[]> parkingSpots = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                char[] charArr = lines.get(i).toCharArray();
                for (int j = 0; j < charArr.length; j++) {
                    int number = Character.getNumericValue(charArr[j]);
                    // determine what to do based on what the number is
                    // 1) If the number is a car, assign the location.
                    // 2) If the number is a parking spot, add the location and set to empty.
                    // 3) If the number is a car + parking spot, assign and add the location.
                    // 4) If the number is a box + parking spot, add location.
                    switch (number) {
                        case CarParkingSolver.PARKING_SPOT_AND_CAR:
                            car = new int[] {i, j};
                            parkingSpots.add(new int[] {i, j});
                            // set spot to just car
                            number = CarParkingSolver.CAR;
                            break;
                        case CarParkingSolver.PARKING_SPOT_AND_BOX:
                            parkingSpots.add(new int[] {i, j});
                            // set spot to just a box
                            number = CarParkingSolver.BOX;
                            break;
                        case CarParkingSolver.PARKING_SPOT:
                            parkingSpots.add(new int[] {i, j});
                            number = CarParkingSolver.EMPTY_SPACE;
                            break;
                        case CarParkingSolver.CAR:
                            car = new int[] {i, j};
                            break;
                    }
                    board[i][j] = number;
                }
            }
            // return our new level
            return new Level(levelNumber, board, car, new ArrayList<>(), parkingSpots);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
