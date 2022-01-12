package dodatneKlase;

import java.util.ArrayList;
import java.util.Scanner;

import static Meniji.meniPreIPosleLogovanja.meniDodavanjeISkidanje;

//sluzi za pravljenje racuna, ukidanje racuna, dodavanje i skidanje novca sa racuna, pristup i promenu valute

public class Racun {
    private Korisnik korisnik;
    private int brojRacuna;
    private double stanje;
    private String valuta;

    public Racun(int brojRacuna, Korisnik korisnik) {
        this.brojRacuna = brojRacuna;
        this.korisnik = korisnik;
        stanje = 0.0;
        valuta = "RSD";
    }

    public int getBrojracuna() {
        return brojRacuna;
    }

    public double getKredit() {
        return stanje;
    }

    public String getValuta() {
        return valuta;
    }

    public void setBrojracuna(int brojracuna) {
        this.brojRacuna = brojracuna;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKredit(double kredit) {
        this.stanje = kredit;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    //uzima listu racuna iz main-a i vraca azuriranu listu u slucaju da dodje do izmene
    public static ArrayList<Racun> kreirajRacun(ArrayList<Racun> racuni, Korisnik korisnik) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna: ");
        int broj = 0;
        boolean validan = true;
        while (validan == true) {
            broj = sc.nextInt();
            for (Racun r : racuni) {
                if (r.getBrojracuna() == broj) {
                    validan = false;
                    System.out.println("Racun sa unetim brojem vec postoji! Pokusajte ponovo sa drugim brojem..");
                }
            }
            if (validan == true) {
                Racun racun = new Racun(broj, korisnik);
                racuni.add(racun);
                System.out.println("Uspesno kreiran racun");
                validan = false;
            }
        }
        return racuni;
    }
    //uzlima listu racuna iz main-a, vraca azuriranu listu ako se dogodilo brisanje (nakon nprovere broja racuna)
    public static ArrayList<Racun> ukiniRacun(ArrayList<Racun> racuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna za brisanje: ");
        int brojRacuna = sc.nextInt();
        for (Racun r : racuni) {
            if (r.brojRacuna == brojRacuna) {
                System.out.println("Da li ste sigurni da zelite da obrisete racun?");
                String potvrda = sc.next();
                if (potvrda.equalsIgnoreCase("Da")) {
                    racuni.remove(r);
                    System.out.println("Racun uspesno obrisan!");
                    return racuni;
                }
                else {
                    System.out.println("Nismo obrisali racun!");
                    return racuni;
                }
            }
            System.out.println("Broj racuna ne postoji u bazi!");
            return racuni;
        }
        return racuni;
    }
    //uzima listu racuna iz main-a, korisnik unosi brojRacuna
    // ako je pronadjen racun ispisace detalje (ili ispisuje gresku)
    public static void ispisRacuna(ArrayList<Racun> racuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna: ");
        int brojRacuna = sc.nextInt();
        boolean pronadjenRacun = false;
        for (Racun r : racuni) {
            if (r.brojRacuna == brojRacuna) {
                pronadjenRacun = true;
                System.out.println("Korisnik: " + r.getKorisnik().getUsername());
                System.out.println("Broj racuna: " + brojRacuna);
                System.out.println("Stanje na racunu: " + r.stanje);
                System.out.println("Valuta racuna: " + r.valuta);
            }
        }
        if (!pronadjenRacun) {
            System.out.println("Ne postoji racun za uneti broj!");
        }
    }
    //uzima racune iz main-a, proveri broj racuna i zavisno od unosa izbora iz menija
    //dodaje ili skida novac na stanje racuna
    //(za dodavanje mora biti unet pozitivan iznos, za skidanje mora biti unet negativan iznos)
    public static ArrayList<Racun> dodavanjeISkidanjeSredstava(ArrayList<Racun> racuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna");
        int brojRacuna = sc.nextInt();
        boolean validanBrojRacuna = false;
        for (Racun r : racuni) {
            if (r.brojRacuna == brojRacuna) {
                validanBrojRacuna = true;
            }
        }
        if (validanBrojRacuna) {
            meniDodavanjeISkidanje();
            int izbor = sc.nextInt();
            while (izbor < 1 && izbor > 3) {
                System.out.println("Pogresan unos, molimo pokusajte ponovo...");
                System.out.println("Izaberi >> ");
                izbor = sc.nextInt();
            }
            for (Racun r : racuni) {
                if (r.brojRacuna == brojRacuna) {
                    //dodavanje na racun
                    if (izbor == 1) {
                        System.out.println("Unesite zeljeni iznos za dodavanje na stanje: ");
                        double iznos = sc.nextDouble();
                        if (iznos <= 0.0) {
                            System.out.println("Iznos ne moze biti manji ili jednak nuli!");
                            return racuni;
                        } else {
                            r.setKredit(r.getKredit() + iznos);
                            System.out.println("\nUspesno dodat kredit!");
                            return racuni;
                        }
                    }
                    //skidanje sa racuna
                    if (izbor == 2) {
                        System.out.println("Unesite zeljeni iznos za skidanje sa stanja: ");
                        double iznos = sc.nextDouble();
                        if (iznos >= 0.0) {
                            System.out.println("Iznos ne moze biti veci od ili jednak nuli!");
                        } else {
                            r.setKredit(r.getKredit() + iznos);
                            System.out.println("\nUspesno skinut kredit!");
                            return racuni;
                        }
                    }
                    if (izbor == 3) {
                        System.out.println("Povratak u prethodni meni...");
                        return racuni;
                    }
                }
                return racuni;
            }
        }
        System.out.println("Racun nije pronadjen");
        return racuni;
    }
    //uzima racune iz main-a i proverava brojRacuna u listi racuna
    //zatim se bira opcija valute u koju zelimo da izvrsimo konverziju a u slucaju pogresnog odabira ispisuje gresku
    //vraca se azurirana lista racuna nazad u main (ako je bilo promene azurirace se lista)
    public static ArrayList<Racun> promeniValutu(ArrayList<Racun> racuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna: ");
        int brojRacuna = sc.nextInt();
        boolean validanBrojRacuna = false;
        for (Racun r : racuni) {
            if (r.brojRacuna == brojRacuna) {
                validanBrojRacuna = true;
            }
        }
        if (validanBrojRacuna) {
            System.out.println("Izaberite jednu od valuta u koju zelite da promenite stanje na racunu: \n");
            System.out.println("1) EUR");
            System.out.println("2) USD");
            System.out.println("3) BAM");
            System.out.println("4) RSD");
            int opcija = sc.nextInt();
            while (opcija < 1 && opcija > 4) {
                System.out.println("Pogresan odabir opcije. Molimo pokusajte ponovo..");
                System.out.println("Izbaberi >> ");
                opcija = sc.nextInt();
            }
            for (Racun r : racuni) {
                if (r.brojRacuna == brojRacuna) {
                    if (r.getValuta().equalsIgnoreCase("RSD")) {
                        if (opcija == 1) {
                            r.setKredit(r.getKredit() * 0.00848);
                            r.setValuta("EUR");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 2) {
                            r.setKredit(r.getKredit() * 0.00959);
                            r.setValuta("USD");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 3) {
                            r.setKredit(r.getKredit() * 0.01658);
                            r.setValuta("BAM");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else {
                            System.out.println("Vec koristite dinare kao valutu!");
                            return racuni;
                        }
                    }
                    if (r.getValuta().equalsIgnoreCase("EUR")) {
                        if (opcija == 2) {
                            r.setKredit(r.getKredit() * 1.1308);
                            r.setValuta("USD");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 3) {
                            r.setKredit(r.getKredit() * 1.95583);
                            r.setValuta("BAM");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 4) {
                            r.setKredit(r.getKredit() * 117.5854);
                            r.setValuta("RSD");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else {
                            System.out.println("Vec koristite eure kao valutu!");
                            return racuni;
                        }
                    }
                    if (r.getValuta().equalsIgnoreCase("USD")) {
                        if (opcija == 1) {
                            r.setKredit(r.getKredit() * 0.88433);
                            r.setValuta("EUR");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 3) {
                            r.setKredit(r.getKredit() * 1.7296);
                            r.setValuta("BAM");
                            System.out.println("Uspesna promena valute");
                            return racuni;
                        }
                        else if (opcija == 4) {
                            r.setKredit(r.getKredit() * 103.9843);
                            r.setValuta("RSD");
                            System.out.println("Uspesna promena valute");
                            return racuni;
                        }
                        else {
                            System.out.println("Vec koristite dolare kao valutu!");
                            return racuni;
                        }
                    }
                    if (r.getValuta().equalsIgnoreCase("BAM")) {
                        if (opcija == 4) {
                            r.setKredit(r.getKredit() * 60.1205);
                            r.setValuta("RSD");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 1) {
                            r.setKredit(r.getKredit() * 0.51129);
                            r.setValuta("EUR");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else if (opcija == 2) {
                            r.setKredit(r.getKredit() * 0.57817);
                            r.setValuta("USD");
                            System.out.println("Uspesna promena valute!");
                            return racuni;
                        }
                        else {
                            System.out.println("Vec koristite marke kao valutu!");
                            return racuni;
                        }
                    }
                }
            }
            return racuni;
        }
        else {
            System.out.println("Broj racuna nije validan!");
            return racuni;
        }
    }

}
