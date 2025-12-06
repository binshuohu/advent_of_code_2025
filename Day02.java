import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day02 {
    record Range(long low, long high) {
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.home") + "/input.txt";
        List<String> lines = InputReader.read(path);
        String input = lines.get(0);
        List<Range> ranges = new ArrayList<>();
        for (String range: input.split(",")) {
            String[] bounds = range.split("-");
            ranges.add(new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1])));
        }
        System.out.println(part1(ranges));
        System.out.println(part2(ranges));
    }

    private static long part1(List<Range> ranges) {
        long result = 0;
        for (Range range: ranges) {
            for (long i = range.low; i <= range.high; i++) {
                String str = Long.toString(i);
                if (str.length() % 2 == 1) continue;
                if (str.substring(0, str.length() / 2).equals(str.substring(str.length() / 2))) result += i;
            }
        }
        return result;
    }

    private static long part2(List<Range> ranges) {
        long result = 0;
        for (Range range: ranges) {
            for (long i = range.low; i <= range.high; i++) {
                String str = Long.toString(i);
                for (int l = 1; l <= str.length() / 2; l++) {
                    if (str.length() % l == 0 && str.substring(0, l).repeat(str.length() / l).equals(str)) {
                        result += i;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
