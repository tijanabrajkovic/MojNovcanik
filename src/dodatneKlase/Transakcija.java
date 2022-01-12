package dodatneKlase;

import Meniji.meniKategorijaTransakcija;

import java.util.ArrayList;
import java.util.Scanner;

public class Transakcija {
    private Racun racun;
    private double trosak;
    private String kategorija;
    private String opis;

    public Transakcija(Racun racun, double trosak, String kategorija, String opis) {
        this.racun = racun;
        this.trosak = trosak;
        this.kategorija = kategorija;
        this.opis = opis;
    }

    public Racun getRacun() {
        return racun;
    }

    public double getTrosak() {
        return trosak;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String getOpis() {
        return opis;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public void setTrosak(double trosak) {
        this.trosak = trosak;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    //uzima liste transakcije i racuni iz main-a, username i password od ulogovanog korisnika
    //korisnik unosi broj racuna, iznos za placanje
    //bira odredjenu kategoriju za transakciju iz menijaKategorije
    //unosi se opis za transakciju placanja sa tastature i
    //vraca listu transakcija (ako je izvrsena transakcija doci ce i do azuriranja liste u main-u)
    //
    public static ArrayList<Transakcija> placanje(ArrayList<Transakcija> transakcije, ArrayList<Racun> racuni, String username, String password) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");
        System.out.println("Unesite broj racuna sa kog zelite da obavite transakciju: ");
        int brojRacuna = sc.nextInt();
        //provera validnosti za brojRacuna
        boolean validanbrojRacuna = false;
        for (Racun r : racuni) {
            if (r.getBrojracuna() == brojRacuna) {
                validanbrojRacuna = true;
            }
        }
        //ako je validan, da bi zapamtili ko je napravio transakciju keiramo ulogovanog korisnika
        if (validanbrojRacuna) {
            Korisnik korisnik = new Korisnik(username,password);
            Racun racun = new Racun(brojRacuna, korisnik);

            System.out.println("Unesite iznos placanja: ");
            double iznosPlacanja = sc.nextDouble();
            while (iznosPlacanja >= 0) {
                System.out.println("Iznos ne moze biti veci ili jedna nuli! Unesite ponovo trosak placanja");
                System.out.println("Pokusajte ponovo unos iznosa placanja: ");
                iznosPlacanja = sc.nextDouble();
            }
            //provera da li korisnik ima dovoljno sredstava da izvrsi placanje
            for (Racun r : racuni) {
                if (r.getKredit() < iznosPlacanja) {
                    System.out.println("Nemate dovoljno sredstava na racunu za ovu transakciju!");
                    return transakcije;
                }
            }

            System.out.println("Izaberite kategoriju: ");
            meniKategorijaTransakcija.ispisKategorija();
            System.out.println("");
            int izborKategorije = sc.nextInt();
            while (izborKategorije < 1 && izborKategorije >7) {
                System.out.println("Pogresno izabrana kategorija! Molimo pokusajte ponovo...");
                meniKategorijaTransakcija.ispisKategorija();
                System.out.println("\nIzaberi >> ");
                izborKategorije = sc.nextInt();
            }
            String kategorija;
            switch(izborKategorije) {
                case 1:
                    kategorija = "Racun";
                    break;
                case 2:
                    kategorija = "Zabava";
                    break;
                case 3:
                    kategorija = "Hrana";
                    break;
                case 4:
                    kategorija = "Odeca";
                    break;
                case 5:
                    kategorija = "Infostan";
                    break;
                case 6:
                    kategorija = "Elektroprivreda";
                    break;
                case 7:
                    kategorija = "BusPlus dopuna";
                    break;
                default:
                    kategorija = "Ostalo";
                    break;
            }
            System.out.println("Unesite opis za placanje: ");
            String opis = sc.next();
            //azuriranje stanja na racunu nakon izvrsenog placanja
            for (Racun r : racuni) {
                if (r.getBrojracuna() == brojRacuna) {
                    r.setKredit(r.getKredit() + iznosPlacanja);
                }
            }
            Transakcija transakcija = new Transakcija(racun, iznosPlacanja, kategorija, opis);
            transakcije.add(transakcija);
            System.out.println("Uspesno obavljena transakcija!\n");
        }
        else {
            System.out.println("broj racuna nije validan!");
        }
        return transakcije;
    }
    //uzima listu transakcija i listu racuna (da bi mogao da proveri za broj racuna da li postoji)
    //ako postoj iracun za uneseni brojRacuna, korisnik bira kategoriju za koju zeli da vidi listu transakcija
    public static void izlistajTransakcije(ArrayList<Racun> racuni, ArrayList<Transakcija> transakcije) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite broj racuna");
        int brojRacuna = sc.nextInt();
        String kategorija;
        System.out.println("Izaberite kategoriju");
        meniKategorijaTransakcija.ispisKategorija();
        int izborKategorije = sc.nextInt();
        while (izborKategorije < 0 && izborKategorije > 7) {
            System.out.println("Pogresan izbor kategorije. Molimo pokusajte ponovo...");
            meniKategorijaTransakcija.ispisKategorija();
            System.out.println("Izaberi >> ");
            izborKategorije = sc.nextInt();
        }
        switch (izborKategorije) {
            case 1:
                kategorija = "Racun";
                break;
            case 2:
                kategorija = "Zabava";
                break;
            case 3:
                kategorija = "Hrana";
                break;
            case 4:
                kategorija = "Odeca";
                break;
            case 5:
                kategorija = "Infostan";
                break;
            case 6:
                kategorija = "Elektroprivreda";
                break;
            case 7:
                kategorija = "BusPlus dopuna";
                break;
            default:
                kategorija = "Ostalo";
                break;
        }
        boolean pronadjeneTransakcije = false;
        boolean pronadjenRacun = false;
        for (Racun r : racuni) {
            if (r.getBrojracuna() == brojRacuna) {
                pronadjenRacun = true;
                for (Transakcija t : transakcije) {
                    if (t.kategorija.equalsIgnoreCase(kategorija)) {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Broj racuna: " + brojRacuna);
                        System.out.println("Kategorija transakcije: " + kategorija);
                        System.out.println("Opis transakcije: " + t.getOpis());
                        System.out.println("Iznos transakcije: " + t.getTrosak());
                        System.out.println();
                        pronadjeneTransakcije = true;
                    }
                }
            }
        }
        if (!pronadjeneTransakcije) {
            System.out.println("Nema pronadjenih transakcije");
        }
        if (!pronadjenRacun) {
            System.out.println("Racun nije pronadjen!");
        }
    }
}
