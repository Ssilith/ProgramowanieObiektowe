package pl.ocean.simulation;
import pl.ocean.map.Map;
import java.util.Scanner;

public class Simulation {

    public static int LiczbaTur;
    public Map plansza;
    Scanner scan = new Scanner(System.in);

    //Pobranie od użytkownika danych potrzebnych do uruchomienia symulacji
    public void StartSimulation(){

       //Pobieranie liczby tur symulacji
        System.out.println("Podaj liczbe tur trwania symulacji:");
        LiczbaTur = scan.nextInt();
        while(LiczbaTur<1){
            System.out.println("Liczba tur nie moze byc mniejsza od 1");
            System.out.print("Podaj nowa liczbe tur:");
            LiczbaTur = scan.nextInt();
        }

        //Pobieranie wymiarów planszy i utworzenie obiektu planszy
        int x, y, z;
        System.out.println("Podaj wymiary planszy (x, y, z): ");
        System.out.println("Kazda z wartosci powinna wynosic minimum 5 ");
        do{
            System.out.print("x = ");
            x = scan.nextInt();
        }while(x<5);
        do{
            System.out.print("y = ");
            y = scan.nextInt();
        }while(y<5);
        do{
            System.out.print("z = ");
            z = scan.nextInt();
        }while(z<5);


        plansza = new Map(x, y, z);
        System.out.println("Liczba utworzonych obiektow nie moze przekraczac 28% liczby dostepnych pol na planszy");
        //Utworzenie i dodanie na mape HerbFish
        System.out.print("Dla Herbfish: ");
        plansza.herbLista();

        //Utworzenie i dodanie na mape OmniFish
        System.out.print("Dla Omnifish: ");
        plansza.omniLista();

        //Utworzenie i dodanie na mape CarnFish
        System.out.print("Dla Carnfish: ");
        plansza.carnLista();

        //Utworzenie i dodanie na mape Algae
        System.out.print("Dla Algae: ");
        plansza.algaeLista();

        //Utworzenie i dodanie na mape Plant
        System.out.print("Dla Oyster: ");
        plansza.oysterLista();
    }


    //Własciwa symulacja
    public void Rounds(){
        for(int i = 0; i < LiczbaTur; i++){
            plansza.CheckReproduce();
            plansza.CheckEat();
            plansza.CheckFight();
            plansza.FishMove();
            Results plik = new Results();
            plik.dopiszTure();
        }
    }
}