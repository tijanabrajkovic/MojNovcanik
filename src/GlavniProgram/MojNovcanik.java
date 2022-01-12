package GlavniProgram;

import Meniji.mainMeni;
import Meniji.meniPreIPosleLogovanja;
import dodatneKlase.Korisnik;
import dodatneKlase.Racun;
import dodatneKlase.Transakcija;

import java.util.ArrayList;
import java.util.Scanner;

public class MojNovcanik {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //dodavanje novog korisnika u listu korisnika ili logovanje sa postojecim validnim username-om i passwordom
        //login info za defaultni user za testiranje i demo aplikacije je username: admin, password: admin
        ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
        ArrayList<Racun> racuni = new ArrayList<Racun>();
        ArrayList<Transakcija> transakcije = new ArrayList<Transakcija>();
        Korisnik k = new Korisnik("admin", "admin");
        korisnici.add(k);

        //omogucava da aplikacija radi bez prekida
        boolean pokrenutProgram = true;

        while (pokrenutProgram) {
            meniPreIPosleLogovanja.meniDobrodoslice();
            int izbor = sc.nextInt();
            //validator za unos 1 ili 2 opcije
            while (izbor < 1 && izbor > 3) {
                System.out.println("Pogresan izbor opcije! Pokusajte ponovo izabrati opciju (1 ili 2) iz menija");
                System.out.println("Izaberi >> ");
                izbor = sc.nextInt();
            }
            //Kreiranje novog korisnika
            if (izbor == 1) {
                System.out.println("Unesite novi username: ");
                String username = sc.next();
                System.out.println("Uneiste novi password: ");
                String password = sc.next();
                korisnici = mainMeni.signUp(username, password, korisnici);
                System.out.println("\nLista korisnika aplikacije MojNovcanik: ");
                for(Korisnik korisnik : korisnici) {
                    System.out.println(korisnik.getUsername());
                }
                System.out.println("");

            }
            //Logovanje u aplikaciju
            if (izbor == 2) {
                System.out.println("Unesite Username: ");
                String username = sc.next();
                System.out.println("Unesite Password: ");
                String password = sc.next();
                //ako je korisnik ulogovan, ovde ce dobiti dodatni meni sa opcijama
                mainMeni.logIn(username, password, korisnici, racuni, transakcije);
            }
            if (izbor == 3) {
                break;
            }
        }
    }
}
