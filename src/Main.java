import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AccountsList db = new AccountsList();
        db.loadFromFile("accounts.txt");

        Scanner sc = new Scanner(System.in);

        mainMenu(sc, db);
    }

    public static void mainMenu(Scanner sc, AccountsList db) {
        while (true) {
            System.out.println("\n1 Add");
            System.out.println("2 Show");
            System.out.println("3 Save");
            System.out.println("4 Login");
            System.out.println("0 Exit");

            int cmd = Integer.parseInt(sc.nextLine());

            if (cmd == 1) {
                int id = db.getNextId();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Balance: ");
                double balance = Double.parseDouble(sc.nextLine());

                db.add(new Account(id, email, name, balance, true));
            }

            if (cmd == 2) {
                for (Account acc : db.getAll()) {
                    System.out.println(acc);
                }
            }

            if (cmd == 3) {
                db.saveToFile("accounts.txt");
                System.out.println("Saved.");
            }

            if (cmd == 4) {
                loginMenu(sc, db);
            }

            if (cmd == 0) {
                db.saveToFile("accounts.txt");
                break;
            }
        }
    }

    public static void loginMenu(Scanner sc, AccountsList db) {
        while (true) {
            System.out.println("\n=== LOGIN ===");
            System.out.println("1 Login");
            System.out.println("0 Back");

            int cmd = Integer.parseInt(sc.nextLine());

            if (cmd == 1) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Pin: ");
                int pin = Integer.parseInt(sc.nextLine());

                Account acc = db.login(email, pin);

                if (acc != null) {
                    System.out.println("Welcome " + acc.getName());

                    if (acc.mustChangePin()) {
                        userActivation(sc, acc);
                    }

                    userMenu(sc, acc, db);

                } else {
                    System.out.println("Invalid credentials");
                }
            }

            if (cmd == 0) {
                break;
            }
        }
    }

    public static void userMenu(Scanner sc, Account acc, AccountsList db) {
        while (true) {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1. Show Account Info");
            System.out.println("2. Show balance");
            System.out.println("3. Add balance");
            System.out.println("4. Withdraw");
            System.out.println("5. Change pin");
            System.out.println("6. Logout");
            System.out.println("0 Logout");

            int cmd = Integer.parseInt(sc.nextLine());

            if (cmd == 1) {
                System.out.println(acc);
            }

            if (cmd == 2) {
                System.out.println("Balance: " + acc.getBalance());
            }

            if (cmd == 3) {
                System.out.print("Enter amount to add: ");
                int amount = Integer.parseInt(sc.nextLine());
                System.out.println(acc.addBalance(amount));
            }

            if (cmd == 4) {
                System.out.print("Enter amount to withdraw: ");
                int amount = Integer.parseInt(sc.nextLine());
                System.out.println(acc.withdraw(amount));
            }

            if (cmd == 5) {
                System.out.print("Enter new pin: ");
                int newPin = Integer.parseInt(sc.nextLine());
                System.out.println(acc.changePin(newPin));
            }

            if (cmd == 6 || cmd == 0) {
                db.saveToFile("accounts.txt");
                break;
            }
        }
    }

    public static void userActivation(Scanner sc, Account acc) {
        System.out.println("Welcome to activation page.");
        System.out.println("In order to continue you must change your pin.");

        while (acc.mustChangePin()) {
            System.out.print("Enter new pin: ");
            int newPin = Integer.parseInt(sc.nextLine());

            System.out.println(acc.changePin(newPin));
        }
    }
}