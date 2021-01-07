import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "(?<match>[\\/=])(?<first>[A-Z][A-Za-z]{2,})\\1";
String input = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> destination = new ArrayList<>();

        int points = 0;
        while (matcher.find()){
            String current = matcher.group("first");
            points = points + current.length();

            destination.add(current);
        }
        System.out.printf("Destinations: %s%n", String.join(", ", destination));
        System.out.printf("Travel Points: %d", points);
    }
}
