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

    public void add(Account acc) {
        accounts.add(acc);
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

    public Account findByEmailAndId(String email, int id){
        for (Account acc : accounts){
            if(acc.getEmail().equals(email) && acc.getId() == id){
                return acc;
            }
        }
        return null;
    }
}