package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();
    private final Random random;

    public DinnerConstructor() {
        this.random = new Random();
    }

    public void addNewDish(String dishType, String dishName) {
        dinnersByType.putIfAbsent(dishType, new ArrayList<>());
        dinnersByType.get(dishType).add(dishName);
    }

    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();

        for (int i = 0; i < comboNumber; i++) {
            ArrayList<String> combo = generateCombo(dishTypes);
            combos.add(combo);
        }

        return combos;
    }

    private ArrayList<String> generateCombo(ArrayList<String> dishTypes) {
        ArrayList<String> combo = new ArrayList<>();

        for (String dishType : dishTypes) {
            ArrayList<String> availableDishes = dinnersByType.get(dishType);
            String selectedDish = selectRandomDish(availableDishes);
            if (selectedDish != null) {
                combo.add(selectedDish);
            }
        }

        return combo;
    }

    private String selectRandomDish(ArrayList<String> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            return null;
        }
        int index = random.nextInt(dishes.size());
        return dishes.get(index);
    }

    public boolean checkType(String type) {
        return dinnersByType.containsKey(type) &&
                !dinnersByType.get(type).isEmpty();
    }

    public ArrayList<String> getAvailableTypes() {
        return new ArrayList<>(dinnersByType.keySet());
    }

    public int getDishCountByType(String type) {
        ArrayList<String> dishes = dinnersByType.get(type);
        return dishes != null ? dishes.size() : 0;
    }
}