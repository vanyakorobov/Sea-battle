import java.io.IOException;
import java.util.Scanner;

public class BattleShip {
    static Scanner scanner = new Scanner(System.in);
    static String playerName1 = "Player#1";
    static String playerName2 = "Player#2";

    static int[][] battleField1 = new int[10][10];
    static int[][] battleField2 = new int[10][10];
    static int[][] monitor1 = new int[10][10];
    static int[][] monitor2 = new int[10][10];

    public static void main(String[] args) {
        System.out.println("Player#1, please enter year name:");
        playerName1 = scanner.nextLine();
        System.out.println("Player#2, please enter year name:");
        playerName2 = scanner.nextLine();
        System.out.println("-".repeat(25));
        System.out.println("Player#1 name: ");
        System.out.println(playerName1);
        System.out.println("Player#2 name: ");
        System.out.println(playerName2);
        placeShips(playerName1, battleField1);
        placeShips(playerName2, battleField2);
        while (true) {
            makeTurn(playerName1, monitor1, battleField2); // игрок - Polly, монитор - Polly's, отображение поля - Ивана
            if (isWinCondition()) {
                break;
            }
            makeTurn(playerName2, monitor2, battleField1); // игрок - Иван, монитор - Ивана, отображение поля - Polly's
            if (isWinCondition()) {
                break;
            }
        }
    }

    public static void placeShips(String playerName, int[][] battleField) {

        int deck = 4;
        while (deck >= 1) {
            System.out.println(playerName + ", please place your " + deck + "-deck ship on the battleField:");

            drawField(battleField);
            System.out.print("Please, enter OX coordinate: ");
            int x = scanner.nextInt();
            System.out.print("Please, enter OY coordinate: ");
            int y = scanner.nextInt();
            System.out.println("Choose direction:");
            System.out.println("1 - Vertical");
            System.out.println("2 - Horizontal");
            int direction = scanner.nextInt();

            if (!isAvailable(x, y, deck, direction, battleField)) {
                System.out.println("Wrong coordinates!");
                continue;
            }

            for (int i = 0; i < deck; i++) {

                if (direction == 1) {
                    battleField[x][y + i] = 1;

                } else {
                    battleField[x + i][y] = 1;

                }
            }
            deck--;
            clearScreen();
        }
    }

    public static void drawField(int[][] battleField) {
        // печать поля
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < battleField.length; i++) {

            System.out.print(i + " ");
            for (int j = 0; j < battleField[1].length; j++) {

                // получается всегда только первая строка?: [1]
                if (battleField[j][i] == 0) { // отзеркалили - развернули на 90 градусов корабль (для человеческого
                    // представления)
                    System.out.print("- "); // пустой квадрат

                } else { // if (battleField[j][i] == 1)
                    System.out.print("X "); // установлен корабль

                }
            }
            System.out.println();
        }
    }

    public static void makeTurn(String playerName, int[][] monitor, int[][] buttleField) {
        // печать поля
        while (true) {
            System.out.println(playerName + ", please, make your turn.");

            System.out.println("  0 1 2 3 4 5 6 7 8 9");
            for (int i = 0; i < monitor.length; i++) {

                System.out.print(i + " ");
                for (int j = 0; j < monitor[1].length; j++) {

                    if (monitor[i][j] == 0) {
                        System.out.print("- "); // пустой квадрат

                    } else if (monitor[i][j] == 1) {
                        System.out.print("* "); // на моем экране отобразится, что в этом квадрате противника пусто

                    } else { // monitor[x][y] = 2; // hit
                        System.out.print("X "); // установлен корабль (конкретно здесь - подбит/потоплен и это видно)

                    }
                }
                System.out.println();
            }

            System.out.print("Please, enter OX coordinate: ");
            int x = scanner.nextInt();
            System.out.print("Please, enter OY coordinate: ");
            int y = scanner.nextInt();

            if (buttleField[x][y] == 1) {
                System.out.println("Hit! Make your turn again!");
                monitor[x][y] = 2; // hit
            } else {
                System.out.println("Miss! Your opponents turn!");
                monitor[x][y] = 1;
                break;
            }
            clearScreen();
        }
    }

    //56 минута - 2-е видео
    public static boolean isWinCondition() {
        int counter1 = 0;
        for (int i = 0; i < monitor1.length; i++) {
            for (int j = 0; j < monitor1[1].length; j++) {
                if (monitor1[i][j] == 2) {
                    counter1++;
                }
            }
        }

        int counter2 = 0;
        for (int i = 0; i < monitor2.length; i++) {
            for (int j = 0; j < monitor2[1].length; j++) {
                if (monitor2[i][j] == 2) {
                    counter2++;
                }
            }
        }

        if (counter1 >= 10) {
            System.out.println(playerName1 + " WIN!!!");
            return true;
        }
        if (counter2 >= 10) {
            System.out.println(playerName2 + " WIN!!!");
            return true;
        }
        return false;
    }

    public static boolean isAvailable(int x, int y, int deck, int rotation, int[][] battlefield) {
        // out of bound check
        if (rotation == 1) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (rotation == 2) {
            if (x + deck > battlefield[0].length) {
                return false;
            }
        }

        //neighbours check without diagonals
        //XXXX
        while (deck != 0) {
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation == 1) {
                    yi = i;
                } else {
                    xi = i;
                }
//                battlefield[x ][y];
                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0) {
                    if (battlefield[x + 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0) {
                    if (battlefield[x - 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0) {
                    if (battlefield[x + xi][y + 1 + yi] != 0) {
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0) {
                    if (battlefield[x + xi][y - 1 + yi] != 0) {
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
