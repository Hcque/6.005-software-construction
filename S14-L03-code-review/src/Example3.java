import java.util.List;


public class Example3 {

    public static int LONG_WORD_LENGTH = 5;
    public static String longestWord;
    
    public static void countLongWords(List<String> words) {
       int n = 0;
       longestWord = "";
       for (String word: words) {
           if (word.length() > LONG_WORD_LENGTH) ++n;
           if (word.length() > longestWord.length()) longestWord = word;
       }
       System.out.println(n);
    }
}
