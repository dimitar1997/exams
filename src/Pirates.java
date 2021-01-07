import java.sql.Array;
import java.util.*;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, int[]> pirates = new TreeMap<>();
        
        while (true) {
            String inpu1 = scanner.nextLine();
            String[] tokens1 = inpu1.split("\\|\\|");
            String cityName = tokens1[0];
            if (inpu1.equals("Sail")) break;
            int population = Integer.parseInt(tokens1[1]);
            int gold = Integer.parseInt(tokens1[2]);
            if (!pirates.containsKey(cityName)) {
                pirates.put(cityName, new int[]{population, gold});
            } else {
                pirates.get(cityName)[0] = pirates.get(cityName)[0] + population;
                pirates.get(cityName)[1] = pirates.get(cityName)[1] + gold;
            }

        }
        while (true) {
            String inpu2 = scanner.nextLine();
            String[] tokens2 = inpu2.split("=>");
            String command = tokens2[0];
            if (command.equals("End")) break;

            switch (command) {
                case "Plunder":
                    String townP = tokens2[1];
                    int peopleKilled = Integer.parseInt(tokens2[2]);
                    int stolenGold = Integer.parseInt(tokens2[3]);
                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", townP,
                            stolenGold, peopleKilled);
                    pirates.get(townP)[0] = pirates.get(townP)[0] - peopleKilled;
                    pirates.get(townP)[1] = pirates.get(townP)[1] - stolenGold;
                    if (pirates.get(townP)[0] == 0 || pirates.get(townP)[1] == 0) {
                        pirates.remove(townP);
                        System.out.printf("%s has been wiped off the map!%n", townP);
                    }
                    break;
                case "Prosper":
                    String townPr = tokens2[1];
                    int moreGold = Integer.parseInt(tokens2[2]);
                    if (moreGold >= 0) {
                        pirates.get(townPr)[1] = pirates.get(townPr)[1] + moreGold;
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n",
                                moreGold, townPr, pirates.get(townPr)[1]);
                    } else {
                        System.out.printf("Gold added cannot be a negative number!%n");
                    }
                    break;
            }

        }
        if (pirates.size() == 0) {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        } else {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n",
                    pirates.size());

            pirates.entrySet().stream().sorted((k, m) ->
                    Integer.compare(m.getValue()[1],k.getValue()[1])
            ).forEach(e -> System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n",
                    e.getKey(), e.getValue()[0], e.getValue()[1]));
        }

    }
}
