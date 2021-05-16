package sibenice;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logic {

    private SpravaSlov ss;
    private String currentWord = "";
    private String[] guessProgress;
    private String[] splitWord;
    private boolean gameOver = false;

    public Logic() {
        try {
            ss = new SpravaSlov("src\\slova.txt");
            loadNewWord();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void loadNewWord() {
        currentWord = ss.VyberNahodnehoSlova();
        
        // Check how many times 'ch' appears in the word
        String fixedWord = currentWord.replace("ch", "%");
        
        int length = fixedWord.length();
        splitWord = new String[length];
        guessProgress = new String[length];

        for (int i = 0; i < length; i++) {
            char c = fixedWord.charAt(i);
            if (c == '%') {
                splitWord[i] = "ch";
            } else {
                // normal letters
                splitWord[i] = "" + c;
            }
        }
    }

    public boolean checkLetter(String letter) {
        boolean found = false;
        for (int i = 0; i < splitWord.length; i++) {
            // System.out.println(splitWord[i] + " = " + letter);
            if(splitWord[i].equals(letter)) {
                found = true;
                guessProgress[i] = letter;
            }
        }
        return found;
    }
    
    public boolean checkVictory() {
        boolean noError = true;
        for (int i = 0; i < guessProgress.length; i++) {
            if(guessProgress[i] == null) {
                noError = false;
            }
        }
        return noError;
    }

    public String[] getGuessProgress() {
        return guessProgress;
    }

    public String[] getSplitWord() {
        return splitWord;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    
}
