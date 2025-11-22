package ui;

import model.BalancedNodeInfo;
import model.TreeNode;
import service.FileService;
import service.TreeService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final TreeService treeService;
    private final FileService fileService;
    private final Scanner scanner;

    public ConsoleInterface() {
        this.treeService = new TreeService();
        this.fileService = new FileService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        loadDataFromFile();

        if (treeService.isEmpty()) {
            System.out.println("Дерево пустое. Завершение программы.");
            return;
        }

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntInput("Выберите операцию: ");

            switch (choice) {
                case 1:
                    displayBalancedNodes();
                    break;
                case 2:
                    displayTreeHeight();
                    break;
                case 3:
                    findAndDisplayKthLeaf();
                    break;
                case 4:
                    deleteNodeByKey();
                    break;
                case 5:
                    running = false;
                    System.out.println("Завершение программы.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.\n");
            }
        }

        scanner.close();
    }

    private void loadDataFromFile() {
        System.out.print("Введите имя файла с данными: ");
        String filename = scanner.nextLine().trim();

        try {
            List<Integer> numbers = fileService.readIntegersFromFile(filename);
            if (numbers.isEmpty()) {
                System.out.println("Файл пуст или не содержит корректных чисел.\n");
                return;
            }

            treeService.buildTreeFromList(numbers);
            System.out.println("Дерево успешно построено из " + numbers.size() + " элементов.\n");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage() + "\n");
        }
    }

    private void displayMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.println("1. Найти узлы с разницей потомков = 1");
        System.out.println("2. Определить высоту дерева");
        System.out.println("3. Найти k-й лист");
        System.out.println("4. Удалить узел по ключу");
        System.out.println("5. Выход");
    }

    private void displayBalancedNodes() {
        List<BalancedNodeInfo> nodes = treeService.findBalancedNodes();
        System.out.println("\n--- Узлы с разницей количества потомков = 1 ---");
        if (nodes.isEmpty()) {
            System.out.println("Таких узлов не найдено.");
        } else {
            for (BalancedNodeInfo info : nodes) {
                System.out.println(info);
            }
        }
    }

    private void displayTreeHeight() {
        int height = treeService.getHeight();
        System.out.println("\n--- Высота дерева ---");
        System.out.println("Высота: " + height);
    }

    private void findAndDisplayKthLeaf() {
        int k = readIntInput("\nВведите номер листа (k): ");
        TreeNode leaf = treeService.findKthLeaf(k);

        System.out.println("\n--- Поиск " + k + "-го листа ---");
        if (leaf != null) {
            System.out.println("Найден лист с ключом: " + leaf.getKey());
        } else {
            System.out.println("Лист с номером " + k + " не найден.");
        }
    }

    private void deleteNodeByKey() {
        int key = readIntInput("\nВведите ключ узла для удаления: ");
        boolean deleted = treeService.deleteNode(key);

        System.out.println("\n--- Удаление узла ---");
        if (deleted) {
            System.out.println("Узел с ключом " + key + " успешно удален.");
        } else {
            System.out.println("Узел с ключом " + key + " не найден.");
        }
    }

    private int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
}
