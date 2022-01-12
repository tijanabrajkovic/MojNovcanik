package Meniji;

public class meniPreIPosleLogovanja {

    public static void meniDobrodoslice() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~           Dobro dosli u MojRacun CLI banku           ~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("Izaberite jednu od dve ponudjene opcije:");
        System.out.println();
        System.out.println("1) Sign up");
        System.out.println("2) Login");
        System.out.println("3) Izlaz");
        System.out.println();
        System.out.println("Izaberi >> ");
    }

    public static void meniUlogovani(String username) {
        System.out.println("\nDobrodosli " + username);
        System.out.println();
        System.out.println("Izaberite jednu od ponudjenih opcija:");
        System.out.println();
        System.out.println("1) Napravi racun");
        System.out.println("2) Ukini racun");
        System.out.println("3) Ispisi racun");
        System.out.println("4) Dodaj ili skini novac sa racuna");
        System.out.println("5) Promeni valutu racuna");
        System.out.println("6) Placanje");
        System.out.println("7) Pretraga transakcija");
        System.out.println("8) Povratak na Login meni");
        System.out.println();
        System.out.println("Izaberi >> ");
    }
    public static void meniDodavanjeISkidanje() {
        System.out.println("\nOpcije");
        System.out.println();
        System.out.println("1) Dodavanje na racun");
        System.out.println("2) Skidanje sa racuna");
        System.out.println("3) Povratak na prethodni meni\n");
        System.out.println("Izaberi >> ");
    }

}
