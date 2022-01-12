package Meniji;

import dodatneKlase.Korisnik;
import dodatneKlase.Racun;
import dodatneKlase.Transakcija;

import java.util.ArrayList;
import java.util.Scanner;

//Funkcije za logovanje postojecih i za registraciju novih korisnika
//Ako se korisnik uspesno uloguje ispisuje opcije iz meniUlogovani
public class mainMeni {

    //proverava da li postoji username u listi korisnika
    // ako ne postoji kreira novog korisnika
    public static ArrayList<Korisnik> signUp(String username, String password, ArrayList<Korisnik> korisnici) {
        Korisnik korisnik;
        boolean exists = false;
        for (Korisnik k : korisnici) {
            if (k.getUsername().equals(username)) {
                System.out.println("Username vec postoji!");
                exists = true;
            }
        }
        if (!exists) {
            korisnik = new Korisnik(username, password);
            korisnici.add(korisnik);
            System.out.println("Uspesno dodat korisnik!");
        }
        return korisnici;
    }
    //proverava da li su username i password validni
    public static void logIn(String username, String password, ArrayList<Korisnik> korisnici, ArrayList <Racun> racuni, ArrayList<Transakcija> transakcije) {
        Scanner sc = new Scanner(System.in);
        boolean registrovanKorisnik = false;
        for (Korisnik k : korisnici) {
            if (k.getUsername().equals(username) && k.getPassword().equals(password)) {
                registrovanKorisnik = true;
            }
        }
        if (registrovanKorisnik) {
            System.out.println("Uspesan Login! \n");
            //Ovde ispise meniUlogovani za kreiranje, promenu lozinke, podizanje novca, uplate, itd..
            meniPreIPosleLogovanja.meniUlogovani(username);
            int izbor = sc.nextInt();
            //dokle god korisnik ne odabere izlaz iz aplikacije omogucavace mu da obavlja izmene i placanje na racunima
            while (izbor != 8) {
                while (izbor <= 0 && izbor > 8) {
                    System.out.println("Pogresan izbor opcije! Molimo pokusajte ponovo...");
                    System.out.println("Izaberi >> ");
                    izbor = sc.nextInt();
                }
                //kreirajRacun trazi unos brojRacuna
                //ako vec postoji ispisace gresku, ako ne postoji kreira racun za ulogovanog korisnika
                if (izbor == 1) {
                    Korisnik k = new Korisnik(username, password);
                    Racun.kreirajRacun(racuni, k);
                    System.out.println("\nRacuni: \n");
                    //ovo sluzi samo za proveru da li je uspesno kreiran racun u listu racuna
                    for (Racun r : racuni) {
                        System.out.println("Broj racuna: " + r.getBrojracuna());
                        System.out.println("Korisnicko ime: " + r.getKorisnik().getUsername());
                        System.out.println();
                    }
                }
                //ukiniRacun trazi unos brojRacuna
                //ako racun postoji izbrisace ga, suprotnom ide ispis greske
                if (izbor == 2) {
                    Racun.ukiniRacun(racuni);
                    //ovo sluzi samo za proveru da li je uspesno obrisan racun
                    for (Racun r : racuni) {
                        System.out.println("Broj racuna: " + r.getBrojracuna());
                        System.out.println("Korisnicko ime: " + r.getKorisnik().getUsername());
                    }
                }
                //ispisRacuna trazi unos brojRacuna
                //ako ne postoji ispis greske, ako postoji ispisuje detalje racuna
                if (izbor == 3) {
                    Racun.ispisRacuna(racuni);
                }
                //dodavanjeISkidanjeSredstava trazi unos brojRacuna
                //ako ne postoji racun za uneti broj, ispisace podmeni za dodavanje ili skidanje sa racuna
                // ali nece dozvoliti izmene, u suportnom omogucava izmene i povratak u prethodni meni pre izmena
                if (izbor == 4) {
                    Racun.dodavanjeISkidanjeSredstava(racuni);
                    //ovo sluzi samo za proveru da li je bilo izmena na racunu
                    for (Racun r : racuni) {
                        System.out.println("\nKorisnik: " + r.getKorisnik().getUsername());
                        System.out.println("Iznos na racunu: " + r.getKredit());
                    }
                }
                //promeniValutu trazi unos Stringa jedna od 4 ponudjene valute
                //korisnik moze da izabere izmedju EUR USD BAM i RSD (RSD je podrazumevana valuta svakog racuna)
                if (izbor == 5) {
                    Racun.promeniValutu(racuni);
                    //ovo sluzi samo za proveru da li je bilo promene valute na odredjenom racunu
                    for (Racun r : racuni) {
                        System.out.println("Korisnik: " + r.getKorisnik().getUsername());
                        System.out.println("Iznos na racunu: " + r.getKredit());
                        System.out.println("Valuta: " + r.getValuta());
                    }
                }
                //placanje trazi unos brojaRacuna, trosak placanja koji mora biti negativan,
                // odabir kategorije za trosak, i opis kao svrhu placanja
                if (izbor == 6) {
                    Transakcija.placanje(transakcije, racuni, username, password);
                }
                //izlistajTransakcije - prosledjuju se racuni u transakcije iz main metode
                // trazi unos brojRacuna i odabir kategorije i vrsi ispis svih transakcija
                if (izbor == 7) {
                    Transakcija.izlistajTransakcije(racuni, transakcije);
                }
                //omogucava povratak na login meni
                if (izbor == 8) {
                    System.out.println("Povratak na login meni...");
                }
                //nakon izvrsenja bilo koje funkcije ponovi ispis menija i unos opcije
                //dokle god ne vraca na prethodni meni (tj. izbor bude != 8)
                meniPreIPosleLogovanja.meniUlogovani(username);
                izbor = sc.nextInt();
            }
        }
        else {
            System.out.println("Neuspesan Login, molimo pokusajte ponovo!\n");
        }
    }

}
