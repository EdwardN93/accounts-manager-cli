import java.util.*;
import java.io.*;

public class AccountsList {

    private List<Account> accounts = new ArrayList<>();

    public int getNextId(){
        if(accounts.isEmpty()){
            return 1;
        }

        int maxId = 0;
        for(Account acc : accounts){
            if(acc.getId() > maxId){
                maxId = acc.getId();
            }
        }
        return maxId + 1;
    }

    public String add(Account acc) {

        for(Account account : accounts){
            if(account.getEmail().equals(acc.getEmail())){
                return "Email is already in use, please login";
            }
        }
        accounts.add(acc);
        return "Account created successfully!";
    }

    public List<Account> getAll() {
        return new ArrayList<>(accounts);
    }

    // LOAD FROM LIST
    public void loadFromFile(String path) {
        accounts.clear();

        try (Scanner file = new Scanner(new File(path))) {
            while (file.hasNextLine()) {
                String line = file.nextLine().trim();
                if (line.isEmpty()) continue;

                Account acc = Account.fromCsv(line);
                accounts.add(acc);
            }
        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    //SAVE IN LIST
    public void saveToFile(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (Account acc : accounts) {
                writer.write(acc.toCsv() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public Account login(String email, int pin){
        for(Account acc : accounts){
            if(acc.getEmail().equals(email)){
                if(!acc.isAccountActive()){
                    return null;
                }
                if(acc.checkPin(pin)){
                    return acc;
                }
            }
        }
        return null;
    }

    public String transferMoney(Account accFrom, String emailTo, double amount){
        if(accFrom.getEmail().equals(emailTo)){
            return "You cannot transfer to your own account";
        }
        if(amount <= 0){
            return "Amount must be positive number";
        }
        for(Account acc : accounts){
            if(acc.getEmail().equals(emailTo)){
                if(!accFrom.withdraw(amount)){
                    return "Transfer failed";
                }
                System.out.println("Account found.");
                acc.addBalance(amount);
                return "Successfuly transfered " + amount + " to " + emailTo;
            }
        }
        return "Account not found";
    }
}