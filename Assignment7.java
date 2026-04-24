import java.util.*;

public class Assignment7 {

    // Step 1: Construct LPS array
    static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // Step 2: KMP Search
    static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            System.out.println("Pattern is empty!");
            return;
        }

        int[] lps = computeLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        boolean found = false;

        System.out.println("\nPattern found at positions:");

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println((i - j));
                found = true;
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (!found) {
            System.out.println("No match found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text (library content):");
        String text = sc.nextLine();

        System.out.println("Enter pattern (keyword to search):");
        String pattern = sc.nextLine();

        KMPSearch(text, pattern);

        sc.close();
    }
}