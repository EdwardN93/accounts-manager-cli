public class Account {
    private int id;
    private int pin;
    private int failedAttempts;

    private String email;
    private String name;

    private double balance;

    private boolean active;

    private boolean mustChangePin;

    private boolean locked;

    public Account(int id, String email, String name, double balance, boolean active) {
        this(id, email, name, balance, active, 1111, true, 0, false);
    }

    public Account(int id, String email, String name, double balance, boolean active,
                   int pin, boolean mustChangePin, int failedAttempts, boolean locked) {
        this.id = id;
        this.email = email;
        this.name = name;

        this.balance = Math.max(0, balance);
        this.active = active;

        this.pin = pin;
        this.mustChangePin = mustChangePin;

        this.failedAttempts = Math.max(0, failedAttempts);
        this.locked = locked;
    }


    public String addBalance(int amount) {
        if (amount <= 0) return "Your amount should be higher than 0";
        balance += amount;
        return "Your balance is: " + balance;
    }

    public String withdraw(int amount) {
        if (amount <= 0) return "Your amount should be higher than 0";
        if (amount > balance) return "Your balance is: " + balance + ". The amount to withdraw exceeds your balance";

        balance -= amount;
        return "Withdrawal successful. Balance: " + balance;
    }


    public String toCsv() {
        return id + "," + email + "," + name + "," + balance + "," + active + "," + pin + "," + mustChangePin
                + "," + failedAttempts + "," + locked;
    }

    public static Account fromCsv(String line) {
        String[] parts = line.split(",");

        int id = Integer.parseInt(parts[0].trim());
        String email = parts[1].trim();
        String name = parts[2].trim();
        double balance = Double.parseDouble(parts[3].trim());
        boolean active = Boolean.parseBoolean(parts[4].trim());

        int pin = 1111;
        boolean mustChangePin = true;
        int failedAttempts = 0;
        boolean locked = false;

        if (parts.length >= 7) {
            pin = Integer.parseInt(parts[5].trim());
            mustChangePin = Boolean.parseBoolean(parts[6].trim());
        }
        if (parts.length >= 9) {
            failedAttempts = Integer.parseInt(parts[7].trim());
            locked = Boolean.parseBoolean(parts[8].trim());
        }

        return new Account(id, email, name, balance, active, pin, mustChangePin, failedAttempts, locked);
    }

    // ------------------ auth / status ------------------

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

    public String changePin(int newPin) {
        if (newPin < 1000 || newPin > 9999) {
            return "Invalid pin, try again";
        }
        this.pin = newPin;

        if (mustChangePin) {
            mustChangePin = false;
            return "Pin changed successfully. Your account is now activated!";
        }
        return "Pin changed successfully";
    }

    public String canOperateMessage() {
        if (locked) return "Account is locked (too many failed attempts)";
        if (mustChangePin) return "You must change your pin first";
        if (!active) return "Account is deactivated";
        return null;
    }

    public boolean canOperate() {
        return !locked && !mustChangePin && active;
    }


    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public boolean mustChangePin() { return mustChangePin; }
    public boolean isAccountActive() { return active; }
    public boolean isLocked() { return locked; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + balance
                + " | active=" + active + " | locked=" + locked + " | mustChangePin=" + mustChangePin;
    }
}