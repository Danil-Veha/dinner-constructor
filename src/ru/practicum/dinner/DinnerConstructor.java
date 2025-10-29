package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();
    private final RandomDishSelector dishSelector;

    public DinnerConstructor() {
        this.dishSelector = new RandomDishSelector();
    }


    public void addNewDish(String dishType, String dishName) {
        dinnersByType.putIfAbsent(dishType, new ArrayList<>());
        dinnersByType.get(dishType).add(dishName);
    }

    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        return dishSelector.generateCombos(comboNumber, dishTypes, dinnersByType);
    }

    public boolean checkType(String type) {
        return dinnersByType.containsKey(type) &&
                !dinnersByType.get(type).isEmpty();
    }

    // Дополнительные полезные методы
    public ArrayList<String> getAvailableTypes() {
        return new ArrayList<>(dinnersByType.keySet());
    }

    public int getDishCountByType(String type) {
        ArrayList<String> dishes = dinnersByType.get(type);
        return dishes != null ? dishes.size() : 0;
    }
}