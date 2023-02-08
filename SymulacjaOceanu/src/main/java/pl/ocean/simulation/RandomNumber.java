package pl.ocean.simulation;
import java.util.Random;
import java.util.Scanner;

public class RandomNumber {

    //Losowanie z przedziału wpisanego przez użytkownika
    public static int losujPrzedzial(){
        int a, b;
        Random generator = new Random();
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj przedzial <a, b>, z ktorego chcesz losowac liczbe elementow:");
        System.out.println("Obie liczby musza byc wieksze od zera");
        do{
            System.out.print("a = ");
            a=scan.nextInt();
        }while(a<0);
        do{
            System.out.print("b = ");
            b=scan.nextInt();
        }while(b<0);
        b++;
        if(a > b){
            int bufor;
            bufor = a;
            a = b;
            b = bufor;
        }

        int c = generator.nextInt(b - a) + a;
        return c;
    }

    //Losowanie z przedziału wpisanego do funkcji
    public static int losuj(int a, int b){
        Random generator = new Random();
        if(a > b){
            int bufor;
            bufor = a;
            a = b;
            b = bufor;
        }
        b++;
        int c = generator.nextInt(b - a) + a;
        return c;
    }

    //Losowanie dla boolean
    public static boolean losujBoolean(){
        Random generator = new Random();
        boolean c = generator.nextBoolean();
        return c;
    }
}