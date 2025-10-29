package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomDishSelector {
    private final Random random;

    public RandomDishSelector() {
        this.random = new Random();
    }


    public String selectRandomDish(ArrayList<String> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            return null;
        }
        int index = random.nextInt(dishes.size());
        return dishes.get(index);
    }


    public ArrayList<String> generateCombo(ArrayList<String> dishTypes,
                                           HashMap<String, ArrayList<String>> dishesByType) {
        ArrayList<String> combo = new ArrayList<>();

        for (String dishType : dishTypes) {
            ArrayList<String> availableDishes = dishesByType.get(dishType);
            String selectedDish = selectRandomDish(availableDishes);
            if (selectedDish != null) {
                combo.add(selectedDish);
            }
        }

        return combo;
    }


    public ArrayList<ArrayList<String>> generateCombos(int numberOfCombos,
                                                       ArrayList<String> dishTypes,
                                                       HashMap<String, ArrayList<String>> dishesByType) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();

        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> combo = generateCombo(dishTypes, dishesByType);
            combos.add(combo);
        }

        return combos;
    }
}
