/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab3Cegelskis;
import laborai.studijosktu.Ks;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.BstSetKTUx;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
/**
 *
 * @author zygis
 */
public class knyguTestai {
    
    static Knyga[] knyguBaze;
    static SortedSetADTx<Knyga> knyguMedis = new BstSetKTUx(new Knyga(), Knyga.pagalPuslapius);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        aibėsTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis, int generN) {
        knyguBaze = new Knyga[generN];
        for (int i = 0; i < generN; i++) {
            knyguBaze[i] = new Knyga.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(knyguBaze));
        knyguMedis.clear();
        for (int i = 0; i < kiekis; i++) {
            knyguMedis.add(knyguBaze[i]);
        }
        return knyguMedis;
    }

    public static void aibėsTestas() throws CloneNotSupportedException {
        Knyga a1 = new Knyga("KNYGA7", "CATEGORY7", 50.0, "AUTHOR7", 333, 2020);
        Knyga a2 = new Knyga.Builder()
                .name("KNYGA8")
                .category("CATEGORY8")
                .price(33.33)
                .author("AUTHOR8")
                .pageNumber(888)
                .printYear(2022)
                .build();
        
        Knyga a3 = new Knyga.Builder().buildRandom();
        Knyga a4 = new Knyga("KNYGA9 CATEGORY9 66.66 AUTHOR9 456 2015");
        Knyga a5 = new Knyga("KNYGA10 CATEGORY10 55.67 AUTHOR10 457 2016");
        Knyga a6 = new Knyga("KNYGA11 CATEGORY11 67.55 AUTHOR11 458 2017");
        Knyga a7 = new Knyga("KNYGA12 CATEGORY12 56.67 AUTHOR12 459 2018");
        Knyga a8 = new Knyga("KNYGA13 CATEGORY13 67.56 AUTHOR13 559 2019");
        Knyga a9 = new Knyga("KNYGA14 CATEGORY14 75.66 AUTHOR14 560 2020");

        Knyga[] knyguMasyvas = {a9, a7, a8, a5, a1, a6};

        Ks.oun("Auto Aibė:");
        SortedSetADTx<Knyga> knyguAibe = new BstSetKTUx(new Knyga());

        for (Knyga a : knyguMasyvas) {
            knyguAibe.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + knyguAibe.size());
        }
        Ks.oun("");
        Ks.oun(knyguAibe.toVisualizedString(""));
        
        System.out.println("Atspausdintos knygos po headSet metodo:");
        Ks.oun("\n" + knyguAibe.headSet(a7));
        System.out.println();

        SortedSetADTx<Knyga> knyguAibeKopija
                = (SortedSetADTx<Knyga>) knyguAibe.clone();

        knyguAibeKopija.add(a2);
        knyguAibeKopija.add(a3);
        knyguAibeKopija.add(a4);
        Ks.oun("Papildyta knyguaibės kopija:");
        Ks.oun(knyguAibeKopija.toVisualizedString(""));


        Ks.oun("Originalas:");
        Ks.ounn(knyguAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Knyga a : knyguMasyvas) {
            Ks.oun(a + ": " + knyguAibe.contains(a));
        }
        Ks.oun(a2 + ": " + knyguAibe.contains(a2));
        Ks.oun(a3 + ": " + knyguAibe.contains(a3));
        Ks.oun(a4 + ": " + knyguAibe.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Knyga a : knyguMasyvas) {
            Ks.oun(a + ": " + knyguAibeKopija.contains(a));
        }
        Ks.oun(a2 + ": " + knyguAibeKopija.contains(a2));
        Ks.oun(a3 + ": " + knyguAibeKopija.contains(a3));
        Ks.oun(a4 + ": " + knyguAibeKopija.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + knyguAibeKopija.size());
        for (Knyga a : new Knyga[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
            knyguAibeKopija.remove(a);
            Ks.oun("Iš knyguaibės kopijos pašalinama: " + a + ". Jos dydis: " + knyguAibeKopija.size());
        }
        Ks.oun("");

        Ks.oun("Knygu aibė su iteratoriumi:");
        Ks.oun("");
        for (Knyga a : knyguAibe) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Knygu aibė AVL-medyje:");
        SortedSetADTx<Knyga> knyguAibe3 = new AvlSetKTUx(new Knyga());
        for (Knyga a : knyguMasyvas) {
            knyguAibe3.add(a);
        }
        Ks.ounn(knyguAibe3.toVisualizedString(""));

        Ks.oun("Knygu aibė su iteratoriumi:");
        Ks.oun("");
        for (Knyga a : knyguAibe3) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Knygu aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = knyguAibe3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Automobilių aibės toString() metodas:");
        Ks.ounn(knyguAibe3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        knyguAibe.clear();
        knyguAibe3.clear();

        Ks.oun("");
        Ks.oun("Knygu aibė DP-medyje:");
        knyguAibe.load("Duomenys\\duomLAB3.txt");
        Ks.ounn(knyguAibe.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Knygu aibė AVL-medyje:");
        knyguAibe3.load("Duomenys\\duomLAB3.txt");
        Ks.ounn(knyguAibe3.toVisualizedString(""));

        SetADT<String> knyguAibe4 = knyguApskaita.automobiliuMarkes(knyguMasyvas);
        Ks.oun("Pasikartojančios knygu pavadinimai:\n" + knyguAibe4.toString());
        System.out.println();
      
    }
}
