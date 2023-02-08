package pl.ocean.simulation;

import java.io.*;
import java.util.Scanner;

import static pl.ocean.map.Map.*;
import static pl.ocean.simulation.Algae.AlgaeDead;
import static pl.ocean.simulation.CarnFish.*;
import static pl.ocean.simulation.HerbFish.*;
import static pl.ocean.simulation.OmniFish.*;
import static pl.ocean.simulation.Oyster.OysterDead;
import static pl.ocean.simulation.Simulation.LiczbaTur;

public class Results{

    //Funkcja pobierajaca od uzytkownika nazwe pliku, w ktorym maja byc zapisywane wyniki symulacji
    public static String NazwaPliku;
    public void nazwijPlik(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj nazwe pliku, do ktorego chcesz zapisac wyniki (do nazwy automatycznie zostanie dopisane rozszerzenie .txt): ");
        NazwaPliku = scan.next();
        NazwaPliku = NazwaPliku+".txt";
    }

    //Dopisywanie zadanego Stringa na koniec pliku
     void dopisz(String wpisz){
         FileWriter file = null;
         try {
             file = new FileWriter(NazwaPliku, true);
         } catch (IOException e) {
             e.printStackTrace();
         }
         BufferedWriter out = new BufferedWriter(file);
         try {
             out.write(wpisz+"\n");
         } catch (IOException e) {
             e.printStackTrace();
         }
         try {
             out.close();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }


     //Czyszczenie pliku i wpisywanie parametrow poczatkowych symulacji do pliku
     public void poczatek(){

        //Czyszczenie pliku
         PrintWriter zapis = null;
         try {
             zapis = new PrintWriter(NazwaPliku);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         zapis.close();

         //Wpisywanie do pliku parametrow poczatkowych symulacji
         dopisz("Liczba tur: " + LiczbaTur);
         dopisz("Wymiary planszy: (" +MaxX +", " +MaxY +", "+MaxZ +")\n");
         int n = OmniList.size() + CarnList.size() + HerbList.size() + AlgaeList.size() + OysterList.size();
         dopisz("Ilosc wszystkich obiektow wynosi: " + n);

         n = OmniList.size() + CarnList.size() + HerbList.size();
         dopisz("\nIlosc wszystkich ryb wynosi: " + n);
         dopisz(" W tym: \n - OmniFish: " + OmniList.size() + "\n - CarnFish: " + CarnList.size() + "\n - HerbFish: " + HerbList.size());

         n = AlgaeList.size() + OysterList.size();
         dopisz("\nIlosc jedzenia wynosi: " + n);
         dopisz(" W tym: \n - Algae: " + AlgaeList.size() + "\n - Oyster: " + OysterList.size()+"\n");

     }


    //Wpisywanie wynikow z danej tury do pliku
     public void dopiszTure(){
         dopisz("\n\n////////////////////////////////////////////////////////////////////");
         dopisz("\nTura: " + (++LicznikTur));
         int n = OmniList.size() + CarnList.size() + HerbList.size() + AlgaeList.size() + OysterList.size();
         dopisz("Ilosc wszystkich obiektow wynosi: " + n);

         n = OmniList.size() + CarnList.size() + HerbList.size();
         dopisz("\nIlosc wszystkich ryb wynosi: " + n);
         dopisz(" W tym: \n - OmniFish: " + OmniList.size() + "\n - CarnFish: " + CarnList.size() + "\n - HerbFish: " + HerbList.size());

         n = AlgaeList.size() + OysterList.size();
         dopisz("\nIlosc jedzenia wynosi: " + n);
         dopisz(" W tym: \n - Algae: " + AlgaeList.size() + "\n - Oyster: " + OysterList.size()+"\n");


         //Dopisywanie ryb, ktore zdechly z glodu
         if(OmniHungerDead+HerbHungerDead+CarnHungerDead!=0){
             dopisz("Liczba ryb, ktore zmarly z glodu: "+(OmniHungerDead+HerbHungerDead+CarnHungerDead));
             if(OmniHungerDead!=0) dopisz(" - OmniFish: "+OmniHungerDead);
             if(CarnHungerDead!=0) dopisz(" - CarnFish: "+CarnHungerDead);
             if(HerbHungerDead!=0) dopisz(" - HerbFish: "+HerbHungerDead);
             OmniHungerDead=0;
             CarnHungerDead=0;
             HerbHungerDead=0;
         }


         //Dopisywanie walk ryb
         if(FishFight!=0){
             dopisz("Liczba walk: "+FishFight);
             dopisz("Liczba ryb, ktore zmarly w walce: "+(OmniFightDead+HerbFightDead+CarnFightDead));
             if(OmniFightDead!=0) dopisz(" - OmniFish: "+OmniFightDead);
             if(CarnFightDead!=0) dopisz(" - CarnFish: "+CarnFightDead);
             if(HerbFightDead!=0) dopisz(" - HerbFish: "+HerbFightDead);
             FishFight=0;
             OmniFightDead=0;
             CarnFightDead=0;
             HerbFightDead=0;
         }

         //Dopisywanie urodzen ryb
         if(OmniBorn+HerbBorn+CarnBorn!=0){
             dopisz("Liczba urodzonych ryb: "+(OmniBorn+HerbBorn+CarnBorn));
             if(OmniBorn!=0) dopisz(" - OmniFish: "+OmniBorn);
             if(CarnBorn!=0) dopisz(" - CarnFish: "+CarnBorn);
             if(HerbBorn!=0) dopisz(" - HerbFish: "+HerbBorn);
             OmniBorn=0;
             CarnBorn=0;
             HerbBorn=0;
         }

         //Dopisywanie zjedzonych glonow
         if(AlgaeDead!=0){
             dopisz("Liczba zjedzonych glonow: "+AlgaeDead);
             AlgaeDead=0;
         }

         //Dopisywanie zjedzonych ostryg
         if(OysterDead!=0){
             dopisz("Liczba zjedzonych ostryg: "+OysterDead);
             OysterDead=0;
         }

     }
}
