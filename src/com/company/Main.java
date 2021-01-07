package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (true){
            String command = scanner.nextLine();
            String[] tokens = command.split(":");

            if (tokens[0].equals("Travel")) break;

            switch (tokens[0]){
                case "Add Stop":
                    int index = Integer.parseInt(tokens[1]);
                    String city = tokens[2];
                    if (index <= input.length()){
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < input.length() ; i++) {
                            stringBuilder.append(input.charAt(i));
                        }
                        stringBuilder.insert(index,city);
                        input = stringBuilder.toString();
                    }
                    System.out.println(input);
                    break;
                case "Remove Stop":
                    int start = Integer.parseInt(tokens[1]);
                    int end = Integer.parseInt(tokens[2]) + 1;
                    if (start >= 0 && end <= input.length()){
                        String firstString = input.substring(0,start);
                        String secondString = input.substring(end);
                        input = firstString + secondString;
                    }
                    System.out.println(input);
                    break;
                case "Switch":
                    String old = tokens[1];
                    String newStr = tokens[2];
                    if (input.contains(old)){
                       input = input.replace(old,newStr);
                    }
                    System.out.println(input);
                    break;
            }
        }
        System.out.printf("Ready for world tour! Planned stops: %s", input);
    }
}
