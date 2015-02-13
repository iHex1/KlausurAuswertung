package de.iHex.KlausurAuswertung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// kleiner kommentar um git zu testen (und jetzt diese änderung)(2)(3)
public class KlausurAuswertungMain {
    private static final String NOTEN_DATEI = "notenCAB.txt";

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Double> notenListe = new ArrayList<Double>();
        
        // *** Notendatei einlesen ***
        notenListe = getMarksFromFile(NOTEN_DATEI);
        
        // *** Ausgabe Klausurenanzahl ***
        System.out.println("Klausurenanzahl: " + notenListe.size());
        
        // *** Durchschnittsnote über alle Klausuren ***
        double summe = 0;
        for (Double eineNote : notenListe) {
           summe += eineNote; 
        }
        System.out.println("Durchschnittsnote: " + summe / notenListe.size());
        
        // *** Platzierung ***
        System.out.println("Wie ist deine eigene Note? ");
        double eingeneNote = Double.valueOf(br.readLine());
        
        int platz = 1;
        for (Double eineNote : notenListe) {
            if (eineNote < eingeneNote) {
                platz++;
            } 
        }
        System.out.println("Dein Ranglistenplatz: " + platz);
        // ******************************************************
        br.close();
    }
    
    /**
     * Die angegebene Notendatei wird eingelesen und die einzelnen Noten in einer Liste 
     * zurückgegeben.
     * @param fileName Name der Notendatei
     * @return Liste der Noten
     */
    private static ArrayList<Double> getMarksFromFile(String fileName) {
        String notenString = null;
        ArrayList<Double> localListe = new ArrayList<Double>();
        BufferedReader fr = null;
        
        try {
            fr = new BufferedReader(new FileReader(fileName));
            
            // *** Notendatei einlesen ***
            while (true) {
                notenString = fr.readLine();
                if (notenString == null) {
                    break;
                }
                localListe.add(Double.valueOf(notenString));
            }
        } catch (IOException e) {
            System.err.println("Datei \"" + fileName + "\" wurde nicht gefunden!!!");
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                System.err.println("Datei \"" + fileName + "\" kann nicht geschlossen werden!!!");
            }
        }
        return localListe;
    }
}
