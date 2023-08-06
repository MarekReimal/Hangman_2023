package models;

import models.datastructures.DataScores;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for interacting with and querying a database.
 */
public class Database {
    private Connection connection = null;
    private final String databaseUrl;
    private final Model model;

    /**
     * A database constructor that is always invoked when an object is created.     *
     * @param model Model
     */
    public Database(Model model) {
        this.model = model;
        this.databaseUrl = "jdbc:sqlite:" + model.getDatabaseFile();
        this.selectUniqueCategories(); // ComboBox needs categories from the table
    }

    /**
     * Database connection
     * @return Connection
     * @throws SQLException throws error on console.
     */
    private Connection dbConnection() throws SQLException {
        // https://stackoverflow.com/questions/13891006/
        if(connection != null) { // kontrollib kas on varasem ühendus aktiivne
            connection.close(); // kui jah siis sulgeb ühenduse
        }
        connection = DriverManager.getConnection(databaseUrl); // loob ühenduse, DriverMan valib õige draiveri
        return connection;
    }

    /**
     * The method reads unique category names from the database and writes them to the cmbNames variable of the model.
     */
    public void selectUniqueCategories() {
        // sql päring muutujasse
        String sql = "SELECT DISTINCT(category) as category FROM words ORDER BY category"; // päring võtab unikaalsed read
        // list muutuja katekoorijatele
        List<String> categories = new ArrayList<>();
        try {
            Connection conn = this.dbConnection(); // kutsub meetodi, tagastub ühendus
            Statement stmt = conn.createStatement(); // loob Statement obj, saadab sql lause DB
            ResultSet rs = stmt.executeQuery(sql); // viib täide sql lause, tagastab tulemuse objektina- andmetabeli

            while(rs.next()) { // käib läbi tabeli
                String category = rs.getString("category"); // võtab rea
                categories.add(category); // kirjutab stringi massiivi
            }
            model.setCorrectCmbNames(categories); // writes unique categories to the cmbNames variable of the model
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method reads the entire leaderboard content from the database and writes it to the model's dataScores
     * variable
     */
    public void selectScores() {
        String sql = "SELECT * FROM scores ORDER BY gametime, playertime DESC, playername";
        List<DataScores> data = new ArrayList<>();
        try {
            Connection conn = this.dbConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            model.getDataScores().clear();
            while(rs.next()) {
                String datetime = rs.getString("playertime");
                LocalDateTime playerTime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String playerName = rs.getString("playername");
                String guessWord = rs.getString("guessword");
                String wrongChar = rs.getString("wrongcharacters");
                int timeSeconds = rs.getInt("gametime");
                data.add(new DataScores(playerTime, playerName, guessWord, wrongChar, timeSeconds));
            }
            model.setDataScores(data); // Write dataScore in the model variable

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
