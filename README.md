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

### Java - Mysql connection

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

--------------------

### Clean terminal

This method is used to clean the terminal

```java

public void clean() {

    try {
        if (os.contains("Windows")) {
            // Cleans on windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            // Cleans on Unix/Linux/Mac
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

```

--------------------------------------------------------

## CRUD

<br>

### Create - CRUD

this method is used to create and insert a new row in a specific table

```java
// This method receive the entity of the table you're going to insert into
public void create(Entity entity) {
    String createQuery = "INSERT INTO table(values) values (?,?,?,?)";
    // Using preparedStatement to prepare the query
    try (PreparedStatement ps = con.prepareStatement(createQuery)){
        // Insert every value to the prepared query
        ps.setTimestamp(1, Timestamp.valueOf(survey.getCreatedAt()));
        ps.setTimestamp(2, Timestamp.valueOf(survey.getUpdatedAt()));
        ps.setString(3, survey.getDescription());
        ps.setString(4, survey.getName());
        ps.executeUpdate(); // Once all the query is finished, execute it
    } catch (SQLException e) {
        e.printStackTrace();
    }

}

```

-----------------------------------------------------

### Find by id (READ) - CRUD

This method is used to find a specific row of a table according to the id typed by the user

```java
// Receive: The id to search
public Optional<Entity> findById(long id) {
    String findByIdQuery = "SELECT (what you want to show) FROM table WHERE id = ? ";
    // Prepare the query
    try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
        // Set the id typed by the user into the WHERE statement
        ps.setLong(1, id);
        // Store the result of the query into a ResultSet type variable
        try (ResultSet rs = ps.executeQuery();){
            // If the resultSet returns a true (means that something was found and false if not)
            if (rs.next()){
                // Create a new instance of the entity of this CRUD
                Entity result = new Entity();
                // Set the new instance's values with the result of the query
                result.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                result.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                result.setDescription(rs.getString("description"));
                result.setName(rs.getString("name"));
                // At the end, return the optional of the instance of entity
                return Optional.of(result);
            }
        } catch (Exception e) {
            scr.clean();
            System.out.println("Error at result set line 41 - SurveysRepository");
            e.printStackTrace();
            scr.pause();
        }
    } catch (Exception e) {
        scr.clean();
        e.printStackTrace();
        scr.pause();
    }
    // If nothing was found, then return an optional empty
    return Optional.empty();
    
}

```

-----------------------------------------------

## List all (READ) - CRUD

```java
public List<Entity> listAll() {
    String listAllQuery = "SELECT (Values you want to show) FROM table";
    // Create the list that will store all the rows of a table
    List<Entity> result = new ArrayList<>();
    // Prepare statement and store the result of the execute query in a ResultSet type variable
    try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
        // While the resulset is true
        while (rs.next()){
            // Create a new instance of the entity
            Entity entity = new Entity();
            entity.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            entity.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            entity.setName(rs.getString("name"));
            // Add the new instance to the list
            result.add(survey);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    // return the list of entitities
    return result;
}


```

------------------------------------

## Update - CRUD

This method is used to modify a field of a specific row from a specific table

```java
// Receive an instance of the entity
public void update(Entity entity) {
    String updateQuery = "UPDATE table SET value1 = ?, value2 = ? ... ... ..."
    try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
        // Replace the "?" in the query with the new values
        ps.setString(1, entity.getDescription());
        ps.setString(2, entity.getName());
        ps.setLong(3, entity.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        scr.pause();
    }
}

```

---------------------------------------

## Delete - CRUD

This method is used to delete a specific row from a table

```java

public void delete(long id) {
    String deleteQuery = "DELETE FROM table WHERE id = ?";
    try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
        ps.setLong(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        scr.pause();
    }
}

```

