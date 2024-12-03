package org.example;

import java.sql.*;

public class Main {
  public static void main(String[] args) throws SQLException {
    System.out.println();
    System.out.println(">>> ༼ つ ◕_◕ ༽つ access sqlite/mysql/postgres database avoiding sql injection");
    System.out.println();

    String databasePath = "example.db"; // SQLite database file path
    String connectionString = "jdbc:sqlite:" + databasePath;

    try (Connection connection = DriverManager.getConnection(connectionString)) {
      System.out.println("Connected to SQLite database at " + databasePath);

      String createTableQuery = """
        CREATE TABLE IF NOT EXISTS Users (
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            Name TEXT NOT NULL,
            Age INTEGER NOT NULL
        );
        """;

      try (Statement createTableStatement = connection.createStatement()) {
        createTableStatement.execute(createTableQuery);
        System.out.println("Table 'Users' created successfully.");
      }

      String insertQuery = "INSERT INTO Users (Name, Age) VALUES (?, ?);";
      try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        preparedStatement.setString(1, "Alice");
        preparedStatement.setInt(2, 30);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " record(s) inserted successfully.");
      }
    }
  }
}
