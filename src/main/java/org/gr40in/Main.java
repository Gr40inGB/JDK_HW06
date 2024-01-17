package org.gr40in;

import java.util.*;

public class Main {

    public static Random doorsRandom = new Random();
    public static Random choicesRandom = new Random();
    public static Random whenTwoDoorsWithGoat = new Random();

    public static void main(String[] args) {
        HashMap<Integer, Boolean> gameAndResult = new HashMap<>();
        HashMap<Integer, Boolean> gameAndResultWithChangeChoice = new HashMap<>();

        for (int i = 0; i < 10000; i++) {
            boolean[] currentDoors = getDoors();
            int fistChoice = choicesRandom.nextInt(3);
            int doorWithGoat = getAnotherDoorWithGoat(currentDoors, fistChoice);
            int newChoice = changeChoice(fistChoice, doorWithGoat);
            gameAndResult.put(i + 1, currentDoors[fistChoice]);
            gameAndResultWithChangeChoice.put(i + 1, currentDoors[newChoice]);
        }

        System.out.printf("Statistic for first choice: \n\twin - %d, loses - %d\n" +
                        "Statistic for changed choice: \n\twin - %d, loses - %d",
                gameAndResult.values().stream().filter(x -> x).count(),
                gameAndResult.values().stream().filter(x -> !x).count(),
                gameAndResultWithChangeChoice.values().stream().filter(x -> x).count(),
                gameAndResultWithChangeChoice.values().stream().filter(x -> !x).count()
        );
    }

    public static boolean[] getDoors() {
        // array
        // door0  door1  door2
        // false  false  true
        // goat   goat   auto
        boolean[] array = new boolean[3];
        array[doorsRandom.nextInt(3)] = true;
        return array;
    }

    public static int changeChoice(int doorsA, int doorsB) {
        // 0 1 2
        // 0+1   = 1  -> 2
        // 0 + 2 = 2  -> 1
        //   1+2 = 3  -> 0
        int sum = doorsA + doorsB;
        return sum == 1 ? 2 : sum == 2 ? 1 : 0;
    }

    public static int getAnotherDoorWithGoat(boolean[] array, int selectedDoor) {
        List<Integer> possibleDoors = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (!array[i] && i != selectedDoor) {
                possibleDoors.add(i);
            }
        }
        if (possibleDoors.size() == 1) return possibleDoors.getFirst();
        else return possibleDoors.get(whenTwoDoorsWithGoat.nextInt(2));
    }
}