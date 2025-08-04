# 🏦 Banking Management System

Built a Banking Management System in Java following a guided YouTube tutorial. Modified and expanded features while gaining hands-on experience with JDBC, MySQL, and basic OOP principles.

---

## 🚀 Features

- 🔐 Open a new bank account with email and PIN
- 💰 Deposit and withdraw funds
- 💳 Secure login using PIN
- 🧾 Balance inquiry and basic validation
- ❌ Prevent duplicate account creation

---

## 🛠️ Technologies Used

- Java (JDK 8+)
- JDBC (Java Database Connectivity)
- MySQL Database
- IntelliJ IDEA / NetBeans

---

## 📦 Project Structure
Banking-Management-System/  

├── AccountManager.java  

├── Accounts.java  

├── BankingApp.java  

└── User.java  

---


---

## 🧰 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Divy1912/Banking-Management-System.git
cd Banking-Management-System
```

### 2. Create MySQL Database  
Run the following SQL commands in your MySQL client to set up the required tables:  


```sql
CREATE DATABASE banking_system;
USE banking_system;

CREATE TABLE user (
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE accounts (
    account_number BIGINT NOT NULL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    balance DECIMAL(10,2) NOT NULL,
    security_pin CHAR(4) NOT NULL
);
```

This reflects your new schema with:

- A separate `user` table for login/signup (email as primary key)
- An `accounts` table linked to the `email`, ensuring each user has one account

### 3. Update DB Credentials
In BankingApp.java, modify:  
```
private static final String url = "jdbc:mysql://localhost:3306/banking_system";
private static final String username = "root";
private static final String password = "your_mysql_password";
```

### 4. Compile and Run  
```
javac *.java
java BankingApp
```
---
### 🧪 Sample Workflow
Open a new account by entering name, email, amount, and PIN.

Perform deposit or withdrawal operations.

Check balance using secure PIN.

Try duplicate email — get blocked.

Attempt wrong PIN — get rejected.
---
### 🙋‍♂️ Author

👤 Divyansh Goyal

📧 View GitHub Profile


