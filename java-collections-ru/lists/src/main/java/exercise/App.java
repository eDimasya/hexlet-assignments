package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        List<String> lettersList = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < letters.length(); i++) {
            lettersList.add(String.valueOf(letters.charAt(i)));
        }
        for (int i = 0; i < word.length(); i++) {
            wordList.add(String.valueOf(word.charAt(i)));
        }
        for (String letter : wordList) {
            if (lettersList.contains(letter.toLowerCase())) {
                lettersList.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
