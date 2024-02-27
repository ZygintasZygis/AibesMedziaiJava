/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab3Cegelskis;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;

/**
 *
 * @author zygis
 */
public final class Knyga implements KTUable<Knyga>{
    
    private String name;
    private String category;
    private double price;
    private String author;
    private int pageNumber;
    private int printYear;
    private static int number = 100;
    private String knyga;
    private static final String ID = "ID";
    
    public Knyga() {
        knyga = ID + (number++);    // suteikiamas originalus autoRegNr
    }

    public Knyga(String pav, String kat,
            double pr, String aut, int page, int printY) {
        knyga = ID + (number++);    // suteikiamas originalus autoRegNr
        this.name = pav;
        this.category = kat;
        this.price = pr;
        this.author = aut;
        this.pageNumber = page;
        this.printYear = printY;
    }

    public Knyga(String dataString) {
        knyga = ID + (number++);
        this.parse(dataString);
    }

    public Knyga(Builder builder) {
        knyga = ID + (number++);    // suteikiamas originalus autoRegNr
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
        this.author = builder.author;
        this.pageNumber = builder.pageNumber;
        this.printYear = builder.printYear;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public Knyga create(String dataString) {
        return new Knyga(dataString);
    }
    
    @Override
    public final void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            name = ed.next();
            category = ed.next();
            price = ed.nextDouble();
            author = ed.next();
            pageNumber = ed.nextInt();
            printYear = ed.nextInt();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie auto -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> " + dataString);
        }
    }

    @Override
    public String toString() {  // papildyta su autoRegNr
        return getKnygosID() + "=" + name + "_" + category + "_" + price + "_" + author + "_" + pageNumber + "_" + printYear;
    }

    public String getKnygosID() {  //** nauja.
        return knyga;
    }
    
    public void setKey(String n){
        this.knyga = n;
    }
    @Override
    public int compareTo(Knyga a) {
        
        int b = getKnygosID().compareTo(a.getKnygosID());
        return b;
    }
    
    public static Comparator<Knyga> pagalPav = (Knyga a1, Knyga a2) -> a1.name.compareTo(a2.name);

    public static Comparator<Knyga> pagalPuslapius = (Knyga a1, Knyga a2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (a1.pageNumber < a2.pageNumber) {
            return -1;
        }
        if (a1.pageNumber > a2.pageNumber) {
            return +1;
        }
        return 0;
    };

    public static Comparator<Knyga> pagalMetusPuslapius = (Knyga a1, Knyga a2) -> {
        // metai mažėjančia tvarka, esant vienodiems lyginama kaina
        if (a1.printYear > a2.printYear) {
            return +1;
        }
        if (a1.printYear < a2.printYear) {
            return -1;
        }
        if (a1.pageNumber > a2.pageNumber) {
            return +1;
        }
        if (a1.pageNumber < a2.pageNumber) {
            return -1;
        }
        return 0;
    };

    @Override
    public String validate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
     public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] knygos = { // galimų automobilių markių ir jų modelių masyvas
            {"BOOK1", "CATEGORY1", "AUTHOR1"},
            {"BOOK2", "CATEGORY2", "AUTHOR2"},
            {"BOOK3", "CATEGORY3", "AUTHOR3"},
            {"BOOK4", "CATEGORY4", "AUTHOR4"},
            {"BOOK5", "CATEGORY5", "AUTHOR5"},
            {"BOOK6", "CATEGORY6", "AUTHOR6"}
        };

        private String name = "";
        private String category = "";
        private double price = -1;
        private String author = "";
        private int pageNumber = -1;
        private int printYear = -1;

        public Knyga build() {
            return new Knyga(this);
        }

        public Knyga buildRandom() {
            int ma = RANDOM.nextInt(knygos.length-1);        // markės indeksas  0..             
            return new Knyga(knygos[ma][0],
                    knygos[ma][1],
                    30 + RANDOM.nextDouble(30),
                    knygos[ma][2],
                    300 + RANDOM.nextInt(400),
                    2000 + RANDOM.nextInt(23));
        }

        public Builder printYear(int py) {
            this.printYear = py;
            return this;
        }

        public Builder name(String n) {
            this.name = n;
            return this;
        }

        public Builder category(String c) {
            this.category = c;
            return this;
        }

        public Builder price(double p) {
            this.price = p;
            return this;
        }

        public Builder pageNumber(int pn) {
            this.pageNumber = pn;
            return this;
        }
        public Builder author(String a){
            this.author = a;
            return this;
        }
    }
}
