package sibenice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpravaSlov {

    // Vlastnosti 
    private String cesta;
    private ArrayList<String> slova;
    private File soubor;

    // Metody
    public SpravaSlov(String cesta) throws IOException {
        this.cesta = cesta;
        this.slova = new ArrayList<>();
        this.soubor = new File(cesta);

        // nacteni textu do pole
        this.nacteniTextuDoPole();
    }

    // Metody privátní
    private void nacteniTextuDoPole() throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(this.soubor));
        String line;
        this.slova.clear();
        while ((line = br.readLine()) != null) {
            this.slova.add(line);
        }
        br.close();
    }

    public void vypisPole() {
        for (String slovo : this.slova) {
            System.out.println(slovo);
        }
    }

    // Metody rozhraní
    public boolean obsahujeSlovo(String slovo) {
        return this.slova.contains(slovo);
    }

    public String VyberNahodnehoSlova() {

        Random random = new Random();
        random.nextInt(this.slova.size());
        return this.slova.get(random.nextInt(this.slova.size()));

    }

    public boolean vlozSlovo(String slovo) {
        if (this.slova.contains(slovo)) {
            return false;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.soubor, true));
            bw.append("\n" + slovo);
            bw.close();
            this.slova.add(slovo);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean smazSlovo(String slovo) {
        if (!this.slova.contains(slovo)) {
            return false;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.soubor));
            this.slova.remove(slovo);
            for (int i = 0; i < this.slova.size(); i++) {
                if (i != 0) {
                    bw.append("\n");
                }
                bw.append(this.slova.get(i));
            }
            bw.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
