import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AccountsList db = new AccountsList();
        db.loadFromFile("accounts.txt");

        Scanner sc = new Scanner(System.in);

        mainMenu(sc, db);

    }

    public static void mainMenu(Scanner sc, AccountsList db){
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

            if (cmd == 4){
                loginMenu(sc, db);
            }

            if (cmd == 0) {
                db.saveToFile("accounts.txt");
                break;
            }
        }
    }

    public static void loginMenu(Scanner sc, AccountsList db){
        while (true){

            System.out.println("\n=== LOGIN ===");
            System.out.println("1 Login");
            System.out.println("0 Back");

            int cmd = Integer.parseInt(sc.nextLine());

            if (cmd == 1) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Id: ");
                int id = Integer.parseInt(sc.nextLine());

                Account acc = db.findByEmailAndId(email, id);

                if (acc != null) {
                    System.out.println("Welcome " + acc.getName());
                    userMenu(sc, acc);
                } else {
                    System.out.println("Invalid credentials");
                }
            }

            if (cmd == 0){
                break;
            }
        }
    }

    public static void userMenu(Scanner sc, Account acc) {
        while (true) {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1 Show balance");
            System.out.println("0 Logout");

            int cmd = Integer.parseInt(sc.nextLine());

            if (cmd == 1) {
                System.out.println("Balance: " + acc.getBalance());
            }

            if (cmd == 0) {
                break;
            }
        }
    }

}