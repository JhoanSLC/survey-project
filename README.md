# Java project - Surveys

The objective of this project is, create a program using ***JAVA*** in which a **USER** can ***LOG IN*** to the program and respond some of the surveys in there. 

And also, the user could be an ***ADMIN*** and acces to a ***CRUD*** for each table of the ***SURVEY DATABASE***  

### Survey's database

<br>

![Relative](/src/main/resources/dbDiagram.png)


## Requirements

* [x] ***hexagonal architecture and vertical slicing*** 
* [x] **DDL*** for the database.
* [ ] ***CRUD*** for each table.
* [ ] ***UI*** using ***JFrame***.
* [ ] ***log in ui***.
* [ ] Register user option.
* [ ] Common user and Admin user UI.
* [ ] Show all surveys to the common users.
* [ ] Permit the admin to access all CRUDS.
* [ ] Permit a common user to respond whatever survey he wants.
* [ ] Ask the common user for a category to show the surveys for that category.
* [ ] Store the user's answers to the database.

<br>

## How the project is going

* [x] Initialize github repository.
* [x] Study a little bit about ***JFrame***.
* [x] Analyze requirements.
* [x] Create ***DDL***. 
* [ ] Create surveys table's ***CRUD***.
* [ ] Create categories_catalog table's ***CRUD***.
* [ ] Create chapter table's ***CRUD***.
* [ ] Create questions table's ***CRUD***.
* [ ] Create response_options table's ***CRUD***.
* [ ] Create subResponse_options table's ***CRUD***.
* [ ] Create response_question table's ***CRUD***.
* [ ] Create roles table's ***CRUD***.
* [ ] Create users table's ***CRUD***.
* [ ] Create users_roles table's ***CRUD***.

<br>
<br>

## Useful pieces of code

This class is used to create a connection between Java and mysql

```java

public class DatabaseConnection {
    final private String URL = "DATABASE'S URL";
    final private String USER = "MYSQL USER";
    final private String PASSWORD = "MYSQL PASSWORD";

    public DatabaseConnection() {}

    public Connection connectDatabase(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
```

The ***connectDatabse*** method returns a **Connection** type data and with this variable you can work with mysql all within the project.