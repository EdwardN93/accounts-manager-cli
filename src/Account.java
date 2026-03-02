public class Account {
    private int id;
    private String email;
    private String name;
    private double balance;
    private boolean active;

    public Account(int id, String email, String name, double balance, boolean active){
        this.id = id;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.active = active;
    }

    public String toCsv(){
        return id + "," + email + "," + name + "," + balance + "," + active;
    }

    public String getName(){
        return this.name;
    }

    public int getId() { return this.id; }

    public String getEmail() { return this.email; }

    public double getBalance() { return this.balance; }

    public static Account fromCsv(String line){
        String[] parts = line.split(",");

        int id = Integer.parseInt(parts[0]);
        String email = parts[1];
        String name = parts[2];
        double balance = Double.parseDouble(parts[3]);
        boolean active = Boolean.parseBoolean(parts[4]);

        return new Account(id, email, name, balance, active);
    }

    public String toString(){
        return id + " | " + name + " | " + email + " | " + balance + " | active = " + active;
    }

}
