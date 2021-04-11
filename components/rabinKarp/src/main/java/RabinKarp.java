public class RabinKarp {
    private static final RabinKarp instance = new RabinKarp();

    public Port port;

    private RabinKarp() {
        port = new Port();
    }

    public static RabinKarp getInstance() {
        return instance;
    }

    private boolean searchString(String text, String pattern) {
        int d = 10;
        int patternLength = pattern.length();
        int textLength = text.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;
        int q = 13;

        for (i = 0; i < patternLength - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < patternLength; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= textLength - patternLength; i++) {
            if (p == t) {
                for (j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }

                if (j == patternLength) {
                    return true;
                }
            }

            if (i < textLength - patternLength) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + patternLength)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return false;
    }

    public class Port implements IStringMatcher {
        public boolean search(String text, String pattern) {
            return searchString(text, pattern);
        }
    }


}



