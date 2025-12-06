import java.io.FileNotFoundException;
import java.util.*;

public class Day01 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.home") + "/input.txt";
        List<String> lines = InputReader.read(path);
        List<Step> steps = lines.stream().map(line -> {
            String dir = line.substring(0, 1);
            int turn = Integer.parseInt(line.substring(1));
            return new Step(dir.equals("L") ? Direction.LEFT : Direction.RIGHT, turn);
        }).toList();
        System.out.println(part1(steps));
        System.out.println(part2(steps));
    }

    private static Integer part1(List<Step> steps) {
        int result = 0, dial = 50;
        for (Step step : steps) {
            dial = Math.floorMod(dial + step.turn * (step.dir == Direction.LEFT ? -1 : 1), 100);
            result += dial == 0 ? 1 : 0;
        }
        return result;
    }

    private static Integer part2(List<Step> steps) {
        int result = 0, dial = 50;
        for (Step step : steps) {
            result += step.turn / 100;
            int remaining = step.turn % 100;
            if (step.dir == Direction.LEFT) {
                //when dial is already at 0, turning left doesn't count as crossing 0
                if (dial != 0 && remaining >= dial) result += 1;
                dial = Math.floorMod(dial - remaining, 100);
            } else {
                if (remaining + dial >= 100) result += 1;
                dial = Math.floorMod(dial + remaining, 100);
            }
        }
        return result;
    }

    enum Direction {
        LEFT,
        RIGHT;
    }

    record Step(Direction dir, int turn) {}
}
