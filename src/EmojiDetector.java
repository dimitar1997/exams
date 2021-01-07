import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regexWord = "((?:::)|(?:\\*\\*))(?<word>[A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regexWord);
        Matcher matcher = pattern.matcher(input);
        List<String> emojisFinal = new ArrayList<>();

        String regexNumbers = "(\\d)";
        Pattern pattern1 = Pattern.compile(regexNumbers);
        Matcher matcher1 = pattern1.matcher(input);
        int sum = 1;
        while (matcher1.find()) {
            int num = Integer.parseInt(matcher1.group());

            sum = sum * num;

        }
        System.out.println("Cool threshold: " + sum);

        int emojis = 0;
        while (matcher.find()) {
            emojis = emojis + 1;
            int currentEmojiSum = 0;
            String emojiWords = matcher.group("word");
            String symbols1 = matcher.group(0);
            for (int i = 0; i < emojiWords.length(); i++) {
                currentEmojiSum = currentEmojiSum + emojiWords.charAt(i);
            }
            if (currentEmojiSum >= sum) {
                emojisFinal.add(symbols1);
            }
        }
        System.out.println(emojis + " emojis found in the text. The cool ones are:");
        emojisFinal.forEach(System.out::println);
    }
}
