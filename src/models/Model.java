package models;

import models.datastructures.DataScores;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String[] randomWordArr; // Juhuslik sõna teisendatud array
    private String[] userWord; // Sõna mida kasutaja arvab, hoiab meeles arvatud ja arvamata tähed [m_ja]
    private ArrayList<String> quessedCorrectChars = new ArrayList<String>(); // Arvatud õiged tähed
    private ArrayList<String> quessedWrongChars = new ArrayList<String>(); // Arvatud tähed aga valed
    private boolean mistakeQuess = false; // kas valitud täht oli õige või vale
    private boolean wordQuessed = false; // kas sõna on ära arvatud




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
     *
     * @return
     */
    public String[] getRandomWordArr() {
        return randomWordArr.clone(); // loob arrayst uue obj, et algne jääks muutmata ()
        //https://stackoverflow.com/questions/55428172/how-to-prevent-changing-the-value-of-array-or-object
    }


    /**
     * Meetod salvestab model muutujasse juhusliku sõna mida kasutaja hakkab arvama
     * @param randomWord
     */
    public void setRandomWord(String randomWord) {
        String randW = randomWord.toUpperCase(); // Muudab trükitähtedeks
        this.randomWordArr = randW.split(""); // Salvestab väärtuse [] kujul, teisendab sõna String[] {"a","b","c"}
        String temp = randomWord.replaceAll(".","_"); // Asendab tähed _-ga, abi rida
        this.userWord = temp.split(""); // Salvestab väärtuse [] kujul
    }
    public boolean isWordQuessed() {
        return wordQuessed;
    }
    public void setWordQuessed(boolean state) {
        this.wordQuessed = state;
    }
    public boolean isMistakeQuess() {
        return mistakeQuess;
    }
    public void setMistakeQuess(boolean state) {
        this.mistakeQuess = state;
    }


    public String[] getWordToShow(String userChar) {
        if (!isInCorrectChars(userChar)) { // JÄTKA SIIT, VAJA ENNE KONTROLLIDA ÕIGETE VALEDE HULGAST, ENNE KUI USERWOED MUUDETAKSE
        if(isChar(userChar)) { // Kontroll kas täht on sõnas, kutsub meetodi, kaasa kasutaja täht
            System.out.println("täht on sõnas"); // Test
            //if(isInCorrectChars(userChar)){ // Kui täht on õige ja juba arvatud siis
                // suurenda vigade arvu ja vaheta pilt
                //this.mistakeQuess = true;
            }
        } else { // kui tähte ei ole sõnas siis
            this.quessedWrongChars.add(userChar); // salvestab arvatud vale tähe
            System.out.println("täht ei ole sõnas");
            // suurenda vigade arvu ja vaheta pilt
            this.mistakeQuess = true;

        }
        return this.userWord;
    }

    /**
     * Meetod kontrollib kas täht on sõnas, õige tähe korral asendab _ õige tähega.
     * Kontrollib kas sõna on lõpuni arvatud, kui jah siis muudab kontrolleri staatust true
     * @param userChar On kasutaja sisestatud täht
     * @return Tagastab bbblean kas täht oli või mitte
     */
    // https://stackoverflow.com/questions/8777257/equals-vs-arrays-equals-in-java
    // https://www.baeldung.com/java-array-contains-value
    private boolean isChar(String userChar) {

        boolean isCh = false; // Init. muutuja
        int x = 0; // Init. loendur

        for (String s : this.randomWordArr) { // Loop s on üksikud tähed, võetakse radomW... massiivist
            //System.out.println("kõik tähed " + s); // Test
            if(userChar.equals(s)) { // Kui kasutaja sisestatud täht võrdub sõnas oleva tähega siis
                //System.out.println("x väärtus on " + x); // Test
                //System.out.println("loobitud täht " + s); // Test
                this.userWord[x] = userChar; // Asendab _ sümboli kasutaja sisestatud tähega
                // kontroll kas sõna on arvatud, kui array'd on võrdsed siis on arvatud
                if (Arrays.equals(this.userWord, this.randomWordArr)) {
                    this.wordQuessed = true;
                    break;
                }
                isCh = true; // Täht oli sõnas olemas
            }
            x++; // loendur kasvab
        }
        //System.out.println("käis isChar, näita userWord " + String.join("",this.userWord)); // Test
        return isCh;
    }

    private boolean isInCorrectChars(String userChar){
        System.out.println("näita userWord isInCorr meetodis " + Arrays.toString(this.userWord));
        boolean isCh = false;
        for (String s : this.userWord) {
            if (s.equals(userChar)){
                isCh = true;
            }
        }
        return isCh;
    }
    private boolean isInWrongChars(String userChar){
        boolean isCh = false;
        return isCh;
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
erinevad näited stringi ja char[]



public class Test {
    public static void main(String[] args) {

        String alg = ("sel");
        String[] algArr = alg.split("");

        String[] teineArr = {"s", "o", "l"};
        boolean c = Arrays.equals(algArr, teineArr);
        System.out.println(c);

        List<String> kolmas = Arrays.asList(teineArr);
        Collections.replaceAll(kolmas,?,"_");

    }
}
 */