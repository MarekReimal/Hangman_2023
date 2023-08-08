package models;

import models.datastructures.DataScores;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A model where the entire logic of the game must take place
 */
public class Model {
    /*
    hangman_words_ee.db - Estonian words, the leaderboard table is empty
    hangman_words_en.db - English words, the leaderboard table is empty
    hangman_words_ee_test.db - Estonian words, the leaderboard table is NOT empty
     */
    private final String databaseFile = "hangman_words_ee_test.db"; // Default database
    private final String imagesFolder = "images"; // Hangman game images location
    private List<String> imageFiles = new ArrayList<>(); // All images with full folder path
    private String[] cmbNames; // Array muutuja ComboBox categories names (contents)
    private final String chooseCategory = "All categories"; // Default first ComboBox choice
    private DefaultTableModel dtmScores; // Leaderboard DefaultTableModel
    private List<DataScores> dataScores = new ArrayList<>(); // The contents of the entire database table scores
    private int imageId = 0; // Current image id (0..11)
    private String selectedCategory = chooseCategory; // Default all categories as "All categories"
    private String randomWord; // DB-st saadud juhuslik sõna mida kasutaja hakkab arvama
    private String[] randomWordArr; // Juhuslik sõna teisendatud array
    private char[] userRandomWord; // Juhuslik sõna mida kasutaja arvab, hoiab meeles arvatud ja arvamata tähed [m_ja]


    /**
     * During the creation of the model, the names of the categories to be shown in the combobox are known
     */
    public Model() {
        new Database(this);
    }

    /**
     * Sets the content to match the ComboBox. Adds "All categories" and unique categories obtained from the database.
     * @param unique all unique categories from database
     * meetod kutsutakse välja Database classist, sisendiks DB päringust saadud unikaalsed kategooriad.
     * meetod kirjutab unikaalsed kategooriate nimetused array
     */
    public void setCorrectCmbNames(List<String> unique) {
        cmbNames = new String[unique.size()+1]; // cmbNames on array, new loob array String type ja kindla suurusega
        cmbNames[0] = chooseCategory; // array esimene rida on "All categories"
        int x = 1;
        for(String category : unique) {
            cmbNames[x] = category; // kirjutab kategooria nimetused massiivi
            x++;
        }
    }

    /**
     * All ComboBox contents
     * @return ComboBox contents
     */
    public String[] getCmbNames() {
        return cmbNames;
    }

    /**
     * Sets a new DefaultTableModel
     * @param dtmScores DefaultTableModel
     */
    public void setDtmScores(DefaultTableModel dtmScores) {
        this.dtmScores = dtmScores;
    }

    /**
     * ALl leaderbaord content
     * @return List<DataScores>
     */
    public List<DataScores> getDataScores() {
        return dataScores;
    }

    /**
     * Sets the new content of the leaderboard
     * @param dataScores List<DataScores>
     */
    public void setDataScores(List<DataScores> dataScores) {
        this.dataScores = dataScores;
    }

    /**
     * Returns the configured database file
     * @return databaseFile
     */
    public String getDatabaseFile() {
        return databaseFile;
    }

    /**
     * Returns the default table model (DefaultTableModel)
     * @return DefaultTableModel
     */
    public DefaultTableModel getDtmScores() {
        return dtmScores;
    }

    /**
     * Returns the images folder
     * @return String
     */
    public String getImagesFolder() {
        return imagesFolder;
    }

    /**
     * Sets up an array of new images
     * @param imageFiles List<String>
     */
    public void setImageFiles(List<String> imageFiles) {
        this.imageFiles = imageFiles;
    }

    /**
     * An array of images
     * @return List<String>
     */
    public List<String> getImageFiles() {
        return imageFiles;
    }

    /**
     * The id of the current image
     * @return id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Sets the new image id
     * @param imageId id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Returns the selected category
     * @return selected category
     */
    public String getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * Sets a new selected category
     * @param selectedCategory new category
     */
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    /**
     * Meetod väljastab juhusliku sõna
     * @return
     */
    public String getRandomWord() {
        return randomWord;
    }

    /**
     * Meetod salvestab muutujasse juhusliku sõna mida kasutaja hakkab arvama
     * @param randomWord
     */
    public void setRandomWord(String randomWord) {
        this.randomWord = randomWord;
        this.randomWordArr = randomWord.split(""); // Teisendab sõna String array'ks
    }

    public String[] getRandomWordArr() {
        return randomWordArr;
    }


    /*
     sõna töötlemine ja arvepidamine toimub arrays
     GUI-le sõna välja kirjutamiseks eraldi meetod- lisab tühikud tähtede vahele JOIN
     */

}
/*
https://www.javatpoint.com/string-array-in-java
public class StringArrayExample {
        public static void main(String[] args) {
            String[] strArray = { "Ani", "Sam", "Joe" };
            boolean x = false; //initializing x to false
            int in = 0; //declaration of index variable
            String s = "Sam";  // String to be searched
            // Iteration of the String Array
            for (int i = 0; i < strArray.length; i++) {
                if(s.equals(strArray[i])) {
                    in = i; x = true; break;
                }
            }
            if(x)
                System.out.println(s +" String is found at index "+in);
            else
                System.out.println(s +" String is not found in the array");
        }
}

https://www.tutorialspoint.com/fill-elements-in-a-java-char-array-in-a-specified-range
erinevad näited stringi ja char[] jaoks
 */