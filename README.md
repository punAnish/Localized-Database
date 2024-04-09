Employees Table Structure: This application demonstrates how to add records to the employee's table in a MySQL database using Java. Before running the application, ensure that you have set up your MySQL database and configured the JDBC driver in your project.

The employee's table is structured with the following fields:

1. id: Primary key of type INT, uniquely identifying each employee.
2. first_name: VARCHAR field representing the first name of the employee.
3. last_name: VARCHAR field representing the last name of the employee.
4. email: VARCHAR field representing the email address of the employee.
   
Java Application to Add Records to the Employees Table: The Java application AddEmployees.java adds records to the employee's table in the MySQL database. Here's a brief overview of the application:

1. It establishes a connection to the MySQL database using JDBC.
2. It prepares an SQL INSERT statement to add a new employee record to the employee's table.
3. It prompts the user to enter the employee details such as first_name, last_name, email.
4. It executes the INSERT statement with the provided employee details.
5. If successful, it displays a message confirming the insertion of the employee record.
6. If an error occurs during the insertion process, it catches the exception and prints the error message
   
To run the application:

1. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA).
2. Ensure that the MySQL Connector/J library is included in your project's dependencies.
3. Run the AddEmployees.java file.
4. Follow the prompts to enter the employee details (firstName, lastName, email).
5. The application will attempt to add the employee record to the database.
6. If successful, a message confirming the insertion will be displayed. If an error occurs, an error message will be shown.
7. Ensure that your MySQL database is running and accessible, and that the table structure matches the employees table described above.
