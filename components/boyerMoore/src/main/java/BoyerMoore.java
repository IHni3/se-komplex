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
        ext.BoyerMoore boyerMoore = new ext.BoyerMoore(pattern);
        int firstOccurence=boyerMoore.search(text);
        return firstOccurence!=text.length();
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