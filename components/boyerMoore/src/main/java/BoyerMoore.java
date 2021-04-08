import java.util.Arrays;

public class BoyerMoore {
    private static BoyerMoore instance = new BoyerMoore();

    public Port port;

    private BoyerMoore() {
        port = new Port();
    }

    public static BoyerMoore getInstance() {
        return instance;
    }

    private boolean searchString(String text, String pattern) {
        int[] last = buildLast(pattern);
        var textLength = text.length();
        var patternLength = pattern.length();

        var i = patternLength - 1;

        if (i > textLength - 1) {
            return false;
        }

        int j = patternLength - 1;

        do {
            if (pattern.charAt(j) == text.charAt(i)) {
                if (j == 0) {
                    return true;
                } else {
                    i--;
                    j--;
                }
            } else {
                int lo = last[text.charAt(i)];
                i =+ patternLength - Math.min(j, 1 + lo);
                j = patternLength - 1;
            }
        } while (i <= textLength - 1);

        return false;
    }

    private int[] buildLast(String pattern) {
        int[] last = new int[128];

        Arrays.fill(last, -1);

        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }

        return last;
    }

    public class Port implements IStringMatcher {
        public boolean search(String text, String pattern) {
            return searchString(text, pattern);
        }
    }
}