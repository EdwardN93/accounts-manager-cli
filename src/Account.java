public class Account {
    private int id;
    private int pin, failedAttempts;;
    private String email;
    private String name;
    private double balance;
    private boolean active;
    private boolean mustChangePin;
    private boolean locked;

    public Account(int id, String email, String name, double balance, boolean active){
        this.id = id;
        this.email = email;
        this.name = name;
        if (balance <= 0){
            this.balance = 0;
        } else {
            this.balance = balance;
        }
        this.active = active;
        this.pin = 1111;
        this.failedAttempts = 0;
        this.locked = false;
        this.mustChangePin = true;
    }

    public String addBalance(int amount){
        if(amount <= 0){
            return "Your amount should be higher than 0";
        }

        balance += amount;
        return "Your balance is: " + balance;
    }

    public String withdraw(int amount) {

        if(amount <= 0) {
            return "Your amount should be higher than 0";
        }

        if(amount > balance) {
            return "Your balance is: " + balance + ".The amount to withdraw exceeds your balance";
        }

        balance -= amount;
        return "Withdrawal successful. Balance: " + balance;
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

    public String warnUserAboutPinChange() {
        return "You must change your pin first";
    }

    public boolean isAccountActive() {
        return active;
    }

    public String changePin(int newPin) {
        if(newPin < 1000 || newPin > 9999) {
            return "Invalid pin, try again";
        } else {
            this.pin = newPin;
            active = true;
            if(mustChangePin) {
                mustChangePin = false;
                return "Pin changed succesfuly. Account is now active!";
            } else {
                return "Pin changed succesfuly";
            }
        }
    }

    public boolean checkPin(int enteredPin) {
        if (locked) return false;

        if (enteredPin == pin) {
            failedAttempts = 0;
            return true;
        }

        failedAttempts++;

        if (failedAttempts >= 3) {
            locked = true;
        }

        return false;
    }

    public String canOperateMessage() {
        if(mustChangePin) return warnUserAboutPinChange();
        if(!active) return "Account is deactivated";
        return null;
    }

    public String toString(){
        return id + " | " + name + " | " + email + " | " + balance + " | active = " + active;
    }

}
