import java.lang.reflect.Array;
import java.util.*;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Map<String, Integer> storeInf = new LinkedHashMap<>();
        Map<String, List<Double>> exhibition = new LinkedHashMap<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            String input = scanner.nextLine();
            String[] tokens = input.split("<->");
            String name = tokens[0];
            int rarity = Integer.parseInt(tokens[1]);
            storeInf.compute(name, (k, v) -> rarity);
            exhibition.putIfAbsent(name, new ArrayList<>());

        }
        while (true){
            String input2 = scanner.nextLine();
            String[] tokens2 = input2.split("[: -]+");
            String command = tokens2[0];
            if (tokens2[0].equals("Exhibition")) break;
            String name = tokens2[1];
            if (!storeInf.containsKey(name)){
                System.out.println("error");
                continue;
            }
            switch (command){
                case "Rate":
                    String name2 = tokens2[1];
                    double rating = Double.parseDouble(tokens2[2]);
                    exhibition.get(name2).add(rating);
                    break;
                case "Update":
                    String name3 = tokens2[1];
                    int newRarity = Integer.parseInt(tokens2[2]);
                    storeInf.compute(name3, (k, v) -> newRarity);
                    break;
                case "Reset":
                    String name4 = tokens2[1];
                    exhibition.get(name4).clear();
                    break;
                default:
                    System.out.println("error");
            }
        }
        System.out.println("Plants for the exhibition:");
        storeInf.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparingDouble(x -> exhibition.get(x.getKey()).stream()
                                .mapToDouble(Double::doubleValue)
                                .average().orElse(0.0))
                        .reversed())
                .forEach(m ->
                        System.out.printf("- %s; Rarity: %d; Rating: %.2f%n",
                                m.getKey(), m.getValue(), exhibition.get(m.getKey()).stream()
                        .mapToDouble(Double::doubleValue).average().orElse(0.00)));
    }
}
