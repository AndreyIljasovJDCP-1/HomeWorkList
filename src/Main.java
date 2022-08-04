import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Класс формирования списка покупок.
 * Позволяет добавлять, просматривать и/или удалять покупки из списка,
 * а также искать товар по маске в списке.
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> shoppingList = new ArrayList<>();

        while (true) {
            int input = showMenu();

            switch (input) {
                case 1:
                    addBuy(shoppingList);
                    break;

                case 2:
                    showShoppingList(shoppingList);
                    break;

                case 3:
                    deleteBuy(shoppingList);
                    break;

                case 4:
                    searchProduct(shoppingList);
                    break;

                default:
                    System.out.println("Программа завершена!");
                    return;
            }

        }

    }

    /**
     * Метод поиска товара в списке покупок по маске.
     *
     * @param shoppingList - список покупок
     */
    public static void searchProduct(List<String> shoppingList) {

        if (shoppingList.isEmpty()) {
            System.out.println("Список покупок пуст.");
        } else {
            System.out.println("Введите текст для поиска:");
            String stringMatch = scanner.nextLine().toLowerCase(Locale.ROOT);
            for (int i = 0; i < shoppingList.size(); i++) {
                String stringCheck = shoppingList.get(i).toLowerCase();

                if (stringCheck.contains(stringMatch)) {
                    System.out.println((i + 1) + ". " + shoppingList.get(i));
                }
            }
        }
    }

    /**
     * Метод удаления товара из списка покупок, по названию или коду товара.
     *
     * @param shoppingList - список покупок
     */
    public static void deleteBuy(List<String> shoppingList) {

        if (!shoppingList.isEmpty()) {
            showShoppingList(shoppingList);
            System.out.println("Какую покупку хотите удалить? Введите код или название.");
            String deleteBuy = scanner.nextLine();

            try {
                int index = Integer.parseInt(deleteBuy);

                if (index <= shoppingList.size() && index > 0) {
                    System.out.println("Покупка " + shoppingList.get(index - 1) + " удалена.");
                    shoppingList.remove(index - 1);
                } else {
                    System.out.println("Код вне списка!");
                }
            } catch (NumberFormatException e) {

                if (shoppingList.contains(deleteBuy)) {
                    System.out.println("Покупка " + deleteBuy + " удалена.");
                    shoppingList.remove(deleteBuy);
                    showShoppingList(shoppingList);
                } else {
                    System.out.println("Введенного значения нет в списке!");
                }
            }

        } else {
            System.out.println("Список пуст, удалять нечего!");
        }
    }

    /**
     * Метод добавления покупки в список.
     *
     * @param shoppingList - список покупок
     */
    public static void addBuy(List<String> shoppingList) {
        System.out.println("Какую покупку хотите добавить?");
        String addBuy = scanner.nextLine();
        shoppingList.add(addBuy);
        System.out.println("Итого в списке покупок: " + shoppingList.size());
    }

    /**
     * Метод вывода на экран списка покупок.
     *
     * @param shoppingList - список покупок
     */
    public static void showShoppingList(List<String> shoppingList) {

        if (shoppingList.isEmpty()) {
            System.out.println("Список покупок пуст.");
        } else {
            System.out.println("Список покупок:");
            for (int i = 0; i < shoppingList.size(); i++) {
                System.out.println((i + 1) + ". " + shoppingList.get(i));
            }
        }
    }

    /**
     * Метод вывода основного меню.
     * Дает возможность выбрать пользователю код операции от 1 до 5.
     *
     * @return int число 1-5, в зависимости от выбора пользователя
     */
    public static int showMenu() {
        int output;

        do {
            System.out.println("\nВведите код операции (1-5):");
            System.out.println("1. Добавить.");
            System.out.println("2. Показать.");
            System.out.println("3. Удалить.");
            System.out.println("4. Поиск.");
            System.out.println("5. Выход.");

            String input = scanner.nextLine();
            input = input.replaceAll(" ", "");

            if (input.length() != 1) {
                System.out.println("Некорректные данные. Введите 1, 2, 3, 4 или 5.");
                output = 0;
                continue;
            }

            try {
                output = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректные данные. Введите 1, 2, 3, 4 или 5.");
                output = 0;
                continue;
            }

            if (output > 5 || output < 1) {
                System.out.println("Некорректные данные. Введите 1, 2, 3, 4 или 5.");
            }

        } while (output != 1 && output != 2 && output != 3 && output != 4 && output != 5);
        return output;
    }
}