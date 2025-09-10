# Bank Management System

This is a Bank Management System built with Java Servlets and JSP (JavaServer Pages), designed to manage banking operations for different user roles. The system uses a MySQL database for data persistence and is intended to be run on a web server like Apache Tomcat.

## Author

Shaipali Maurya

## Features

The application provides functionalities for three types of users:

  * **User:** A regular customer with a bank account.
  * **Manager:** An administrator who manages user and cashier accounts.
  * **Cashier:** A teller who handles deposits and withdrawals.

### User Features

  * [cite\_start]**Account Dashboard**: Users can view their account balance, recent notices from the bank, and links to other banking services[cite: 45, 48, 127].
  * [cite\_start]**Account Statements**: View a detailed history of all transactions, including withdrawals, deposits, and fund transfers[cite: 22, 24].
  * [cite\_start]**Fund Transfer**: Transfer money to another bank account by entering the recipient's account number and the amount to be transferred[cite: 167, 174].
  * [cite\_start]**Notifications**: Check notices and updates sent by the bank manager[cite: 157, 158].

### Manager Features

  * [cite\_start]**Dashboard**: The main page for managers lists all user accounts[cite: 95, 99].
  * [cite\_start]**User Account Management**: View, send notices to, or delete any user account[cite: 104].
  * [cite\_start]**Teller Account Management**: Add or delete cashier accounts[cite: 61, 67].
  * **Customer Registration**: Register new user accounts with a minimum starting balance of Rs. [cite\_start]500[cite: 15, 17].

### Cashier Features

  * [cite\_start]**Account Information**: Retrieve a user's account details by entering their account number[cite: 111, 114].
  * **Deposit**: Accept deposits from users by entering a check number and the amount. [cite\_start]The system prevents the use of duplicate check numbers[cite: 107, 122, 123].
  * **Withdrawal**: Process cash withdrawals. [cite\_start]The system validates the account balance to prevent withdrawals if there's insufficient funds and also checks for duplicate check numbers[cite: 111, 120, 121].

## Technologies Used

  * **Backend**: Java, Servlets, JSP
  * **Frontend**: HTML, CSS, Bootstrap, Font Awesome
  * **Database**: MySQL
  * **IDE**: Apache NetBeans

## Project Structure

  * `src/java/BankServlet/`

      * [cite\_start]`Dao.java`: Provides data access methods for fetching user details and notices[cite: 158].
      * [cite\_start]`Deposite.java`: Servlet to handle deposit transactions[cite: 107].
      * [cite\_start]`Login.java`: Manages user authentication for all three user roles[cite: 83, 88, 93].
      * [cite\_start]`Notice.java`: Servlet for sending notices from a manager to a specific user[cite: 148].
      * [cite\_start]`Register.java`: Handles the registration of new user accounts[cite: 11].
      * [cite\_start]`TellerDao.java`: Manages cashier accounts and handles deposit and withdrawal logic[cite: 64, 115].
      * [cite\_start]`Transfer.java`: Processes fund transfers between user accounts[cite: 169].
      * [cite\_start]`User.java`: A simple Java Bean (POJO) to represent user data[cite: 1].
      * [cite\_start]`UserDao.java`: Manages database operations for user accounts, including saving, reading, updating, and deleting data[cite: 22, 41, 104].
      * [cite\_start]`Withdraw.java`: Servlet to handle withdrawal transactions[cite: 120, 121].

  * `web/`

      * [cite\_start]`AllUsers.jsp`: Displays a list of all user accounts for the manager[cite: 95].
      * [cite\_start]`Accounts.jsp`: Displays and manages all staff (cashier) accounts[cite: 54].
      * [cite\_start]`Cashier.jsp`: The main interface for cashiers to perform transactions[cite: 107].
      * [cite\_start]`FundTransfer.jsp`: Interface for users to initiate a fund transfer[cite: 160].
      * [cite\_start]`index.jsp`: The main dashboard for a logged-in user[cite: 127].
      * [cite\_start]`Login.jsp`: The login page for all user types[cite: 80].
      * [cite\_start]`Manager.jsp`: The main page for managers[cite: 76].
      * [cite\_start]`nav.jsp`: A reusable navigation bar that changes based on the user's role[cite: 37, 44, 46].
      * [cite\_start]`Register.jsp`: The form for managers to create new user accounts[cite: 11].
      * [cite\_start]`Statement.jsp`: Displays the user's transaction history[cite: 18].
      * [cite\_start]`View.jsp`: Shows detailed information for a specific user account[cite: 1, 5, 6].
      * `web.xml`: The deployment descriptor that maps servlets to URL patterns.

## Database Setup

1.  Create a MySQL database named `bank`.
2.  The application will automatically create the necessary tables (`users`, `accounts`, `teller`, `transactions`, `transfer`, `notice`) upon the first run if they don't exist.
3.  The database connection is configured in `UserDao.java`:
    ```java
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
    ```
    You may need to change the username and password in this line to match your MySQL configuration.

## How to Run

1.  Open the project in Apache NetBeans.
2.  Ensure you have a MySQL server running and have created the `bank` database.
3.  Right-click the project and select **Run**.
4.  The application will deploy to the configured web server (e.g., Apache Tomcat), and you can access it through your web browser.