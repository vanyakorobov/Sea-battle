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
//        System.out.println("Player#1, please enter year name:");
//        playerName1 = scanner.nextLine();
//        System.out.println("Player#2, please enter year name:");
//        playerName2 = scanner.nextLine();
//        System.out.println("-".repeat(25));
//        System.out.println("Player#1 name: ");
//        System.out.println(playerName1);
//        System.out.println("Player#2 name: ");
//        System.out.println(playerName2);
        placeShips(playerName1, battleField1);
        placeShips(playerName2, battleField2);
        makeTurn(playerName1, monitor1, battleField2); // игрок - Polly, монитор - Polly's, отображение поля - Ивана
        makeTurn(playerName2, monitor2, battleField1); // игрок - Иван, монитор - Ивана, отображение поля - Polly's
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

            for (int i = 0; i < deck; i++) {

                if (direction == 1) {
                    battleField[x][y + i] = 1;

                } else {
                    battleField[x + i][y] = 1;

                }
            }
            deck--;
        }
    }

    public static void drawField(int[][] battleField) {
        // печать поля
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < battleField.length; i++) {

            System.out.print(i + " ");
            for (int j = 0; j < battleField[i].length; j++) {

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
                for (int j = 0; j < monitor[i].length; j++) {

                    if (monitor[j][i] == 0) {
                        System.out.print("- "); // пустой квадрат

                    } else if (monitor[j][i] == 1) {
                        System.out.print("* "); // на моем экране отобразится, что в этом квадрате противника пусто

                    } else { // monitor[x][y] = 2; // hit
                        System.out.println("X "); // установлен корабль (конкретно здесь - подбит/потоплен и это видно)

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
        }
    }
}
