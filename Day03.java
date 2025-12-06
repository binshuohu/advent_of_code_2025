import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {
    record Range(long low, long high) {
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.home") + "/input.txt";
        List<String> lines = InputReader.read(path);
        System.out.println(part1(lines));
        System.out.println(part2(lines));
    }

    private static long part1(List<String> lines) {
        long result = 0;
        for (String line: lines) {
            long[] digits = new long[2];
            int idx = 0;
            for (int i = line.length() - 2; i >= 0; --i) {
                int digit = line.charAt(i) - '0';
                if (digit >= digits[0]) {
                    digits[0] = digit;
                    idx = i;
                }
            }
            line.substring(idx+1).chars().max().ifPresent(c -> digits[1] = c - '0');
            result += digits[0] * 10 + digits[1];
        }
        return result;
    }

    private static long part2(List<String> lines) {
        long result = 0;
        for (String line: lines) {
            int len = line.length();
            long num = 0;

            int lo = 0;
            for (int i = 1; i <= 12; ++i) {
                int idx = idxOfLargestDigit(line, lo, len - 1 - (12 - i));
                num = num * 10 + (line.charAt(idx) - '0');
                lo = idx + 1;
            }
            result += num;
        }
        return result;
    }

    private static int idxOfLargestDigit(String str, int lo, int hi) {
        int result = 0;
        char maxChar = '0';
        for (int i = hi; i >= lo; --i) {
            if (str.charAt(i) >= maxChar) {
                maxChar = str.charAt(i);
                result = i;
            }
        }
        return result;
    }
}
