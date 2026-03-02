# Accounts Manager CLI (Java)

Console-based Java application for managing user accounts with **CSV file persistence**.

## Features

- Load accounts from file at startup
- Add new accounts
- Display all accounts
- Save accounts to file
- Automatic save on exit
- Simple menu-based interface

## Data Format

Accounts are stored in `accounts.txt`:


id,email,name,balance,active


Example:

1,john@email.com
,John Doe,1500.0,true


## Project Structure

- `Account` – data model
- `AccountsList` – business logic + file operations
- `Main` – CLI interface

## Technologies

- Java
- OOP
- ArrayList
- File I/O (Scanner, FileWriter)

## Future Improvements (v2)

- Login with email + PIN
- User session & separate menus
- Deposit / Withdraw operations
- Activate / Deactivate accounts
- Search & filtering
- Extended persistence (PIN, roles)

---

**Author:** Eduard Nedelcu  
Computer Science Student – Java Learning Journey