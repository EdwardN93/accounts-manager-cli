# Accounts Manager CLI (Java)

Console-based Java application for managing user accounts with **CSV file persistence**.

## Features

- Load accounts from file at startup
- Add new accounts
- Display all accounts
- Login with email + PIN
- Deposit / Withdraw operations
- Transfer between accounts
- Save accounts to file
- Automatic save on exit
- Simple menu-based interface
- User session & separate menus

## Data Format

Accounts are stored in `accounts.txt`:


id,email,name,balance,active,pin,mustChangePin,failedAttempts,locked


Example:

1,john@email.com,John Doe,1600.0,true,1111,true,0,false


## Project Structure

- `Account` – data model
- `AccountsList` – business logic + file operations
- `Main` – CLI interface

## Technologies

- Java
- OOP
- ArrayList
- File I/O (Scanner, FileWriter)

## Future Improvements

- Transaction history
- Pin hashing for better security

---

**Author:** Eduard Nedelcu  
Computer Science Student – Java Learning Journey