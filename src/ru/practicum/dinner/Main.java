package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dinnerConstructor;
    static Scanner scanner;

    public static void main(String[] args) {
        dinnerConstructor = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Такой команды нет! Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        if (dishType.isEmpty() || dishName.isEmpty()) {
            System.out.println("Ошибка: тип и название не могут быть пустыми!");
            return;
        }

        dinnerConstructor.addNewDish(dishType, dishName);
        System.out.println("Блюдо '" + dishName + "' типа '" + dishType + "' успешно добавлено!");
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        // Показываем доступные типы
        ArrayList<String> availableTypes = dinnerConstructor.getAvailableTypes();
        if (!availableTypes.isEmpty()) {
            System.out.println("Доступные типы блюд: " + availableTypes);
        }

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        ArrayList<String> selectedTypes = new ArrayList<>();
        while (!nextItem.isEmpty()) {
            if (dinnerConstructor.checkType(nextItem)) {
                selectedTypes.add(nextItem);
                int dishCount = dinnerConstructor.getDishCountByType(nextItem);
                System.out.println("Тип '" + nextItem + "' добавлен! (доступно блюд: " + dishCount + ")");
            } else {
                System.out.println("Такой тип блюд мы еще не умеем готовить. Попробуйте что-нибудь другое!");
            }
            nextItem = scanner.nextLine();
        }

        if (selectedTypes.isEmpty()) {
            System.out.println("Не выбрано ни одного типа блюд!");
            return;
        }

        ArrayList<ArrayList<String>> generatedCombos = dinnerConstructor.generateCombos(numberOfCombos, selectedTypes);

        for (int i = 0; i < generatedCombos.size(); i++) {
            System.out.println("Комбинация " + (i + 1) + ": " + generatedCombos.get(i));
        }
    }
}
