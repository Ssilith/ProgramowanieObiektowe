package pl.ocean.map;

import pl.ocean.simulation.*;

import java.util.LinkedList;
import java.util.List;

import static pl.ocean.simulation.CarnFish.CarnFightDead;
import static pl.ocean.simulation.HerbFish.HerbFightDead;
import static pl.ocean.simulation.OmniFish.OmniFightDead;
import static pl.ocean.simulation.Oyster.OysterDead;
import static pl.ocean.simulation.Algae.AlgaeDead;

public class Map {

    public static int MaxX;
    public static int MaxY;
    public static int MaxZ;
    public static int LicznikTur;

    public Map(int MaxX, int MaxY, int MaxZ) {
        Map.MaxX = MaxX;
        Map.MaxY = MaxY;
        Map.MaxZ = MaxZ;
    }

    //Tworzenie listy ryb HerbFish
    public static List<HerbFish> HerbList = new LinkedList<>();

    public void herbLista() {
        int a = RandomNumber.losujPrzedzial();
        //Sprawdzanie czy liczba ryb nie przekracza 1/20 dostepnych pol
        if(a>((MaxX*MaxY*MaxZ)/20)) a=(MaxX*MaxY*MaxZ)/20;
        while (a != 0) {
            HerbList.add(new HerbFish(MaxX, MaxY, MaxZ));
            a--;
        }
    }

    //Tworzenie listy ryb OmniFish
    public static List<OmniFish> OmniList = new LinkedList<>();

    public void omniLista() {
        int a = RandomNumber.losujPrzedzial();
        //Sprawdzanie czy liczba ryb nie przekracza 1/20 dostepnych pol
        if(a>((MaxX*MaxY*MaxZ)/20)) a=(MaxX*MaxY*MaxZ)/20;
        while (a != 0) {
            OmniList.add(new OmniFish(MaxX, MaxY, MaxZ));
            a--;

        }
    }

    //Tworzenie listy ryb CarnFish
    public static List<CarnFish> CarnList = new LinkedList<>();

    public void carnLista() {
        int a = RandomNumber.losujPrzedzial();
        //Sprawdzanie czy liczba ryb nie przekracza 1/20 dostepnych pol
        if(a>((MaxX*MaxY*MaxZ)/20)) a=(MaxX*MaxY*MaxZ)/20;
        while (a != 0) {
            CarnList.add(new CarnFish(MaxX, MaxY, MaxZ));
            a--;
        }
    }

    //Tworzenie listy jedzenia Algae
    public static List<Algae> AlgaeList = new LinkedList<>();

    public void algaeLista() {
        int a = RandomNumber.losujPrzedzial();
        //Sprawdzanie czy liczba jedzenia nie przekracza 1/16 dostepnych pol
        if(a>((MaxX*MaxY*MaxZ)/16)) a=(MaxX*MaxY*MaxZ)/16;
        while (a != 0) {
            AlgaeList.add(new Algae(MaxX, MaxY, MaxZ));
            a--;
        }
    }

    //Tworzenie listy jedzenia Oyster
    public static List<Oyster> OysterList = new LinkedList<>();

    public void oysterLista() {
        int a = RandomNumber.losujPrzedzial();
        //Sprawdzanie czy liczba jedzenia nie przekracza 1/16 dostepnych pol
        if(a>((MaxX*MaxY*MaxZ)/16)) a=(MaxX*MaxY*MaxZ)/16;
        while (a != 0) {
            OysterList.add(new Oyster(MaxX, MaxY, MaxZ));
            a--;
        }
    }

    //Poruszanie się wszystkich ryb oraz spadek ilości punktów zdrowia po każdej rundzie
    public void FishMove() {

        //Poruszanie się obiektów OmniFish
        for (int i = 0; i < OmniList.size(); i++) {
            OmniList.get(i).move();
            OmniList.get(i).hungry();
            OmniList.get(i).dieOmni(OmniList, i);
        }

        //Poruszanie się obiektów CarnFish
        for (int i = 0; i < CarnList.size(); i++) {
            CarnList.get(i).move();
            CarnList.get(i).hungry();
            CarnList.get(i).dieCarn(CarnList, i);
        }

        //Poruszanie się obiektów HerbFish
        for (int i = 0; i < HerbList.size(); i++) {
            HerbList.get(i).move();
            HerbList.get(i).hungry();
            HerbList.get(i).dieHerb(HerbList, i);
        }
    }

    //Rozmnażanie się ryb
    public void CheckReproduce() {

        //Rozmnażanie obietków CarnFish
        for (int i = 0; i < CarnList.size(); i++) {
            for (int j = 0; j < CarnList.size(); j++) {
                if (i != j) {
                    if (CarnList.get(i).X == CarnList.get(j).X && CarnList.get(i).Y == CarnList.get(j).Y && CarnList.get(i).Z == CarnList.get(j).Z) {
                        CarnList.get(i).reproduceCarn(CarnList, i);
                    }
                }
            }
        }

        //Rozmnażanie obietków OmniFish
        for (int i = 0; i < OmniList.size(); i++) {
            for (int j = 0; j < OmniList.size(); j++) {
                if (i != j) {
                    if (OmniList.get(i).X == OmniList.get(j).X && OmniList.get(i).Y == OmniList.get(j).Y && OmniList.get(i).Z == OmniList.get(j).Z) {
                        OmniList.get(i).reproduceOmni(OmniList, i);
                    }
                }
            }
        }

        //Rozmnażanie obietków HerbFish
        for (int i = 0; i < HerbList.size(); i++) {
            for (int j = 0; j < HerbList.size(); j++) {
                if (i != j) {
                    if (HerbList.get(i).X == HerbList.get(j).X && HerbList.get(i).Y == HerbList.get(j).Y && HerbList.get(i).Z == HerbList.get(j).Z) {
                        HerbList.get(i).reproduceHerb(HerbList, i);
                    }
                }
            }
        }
    }

    //Zjadanie jedzenia przez ryby
    public void CheckEat() {

        //Obiekty CarnFish zjadają obiekty Oyster
        for (int i = 0; i < CarnList.size(); i++) {
            for (int j = 0; j < OysterList.size(); j++) {
                if (CarnList.get(i).X == OysterList.get(j).X && CarnList.get(i).Y == OysterList.get(j).Y && CarnList.get(i).Z == OysterList.get(j).Z) {
                    CarnList.get(i).eatOysterCarn(CarnList, OysterList, i, j);
                    //System.out.println("CarnFish zjada Oyster");
                    OysterDead++;
                }
            }
        }

        //Obiekty OmniFish zjadają obiekty Oyster
        for (int i = 0; i < OmniList.size(); i++) {
            for (int j = 0; j < OysterList.size(); j++) {
                if (OmniList.get(i).X == OysterList.get(j).X && OmniList.get(i).Y == OysterList.get(j).Y && OmniList.get(i).Z == OysterList.get(j).Z) {
                    OmniList.get(i).eatOysterOmni(OmniList, OysterList, i, j);
                    //System.out.println("OmniFish zjada Oyster");
                    OysterDead++;
                }
            }
        }

        //Obiekty OmniFish zjadają obiekty Algae
        for (int i = 0; i < OmniList.size(); i++) {
            for (int j = 0; j < AlgaeList.size(); j++) {
                if (OmniList.get(i).X == AlgaeList.get(j).X && OmniList.get(i).Y == AlgaeList.get(j).Y && OmniList.get(i).Z == AlgaeList.get(j).Z) {
                    OmniList.get(i).eatAlgaeOmni(OmniList, AlgaeList, i, j);
                    //System.out.println("OmniFish zjada Algae");
                    AlgaeDead++;
                }
            }
        }

        //Obiekty HerbFish zjadają obiekty Algae
        for (int i = 0; i < HerbList.size(); i++) {
            for (int j = 0; j < AlgaeList.size(); j++) {
                if (HerbList.get(i).X == AlgaeList.get(j).X && HerbList.get(i).Y == AlgaeList.get(j).Y && HerbList.get(i).Z == AlgaeList.get(j).Z) {
                    HerbList.get(i).eatAlgaeHerb(HerbList, AlgaeList, i, j);
                    //System.out.println("HerbFish zjada Algae");
                    AlgaeDead++;
                }
            }
        }
    }

    //Walki ryb rożnych gatunków
    public void CheckFight(){

        //Walki Carnfish z OmniFish
        for (int i = 0; i < CarnList.size(); i++) {
            for (int j = 0; j < OmniList.size(); j++) {
                if (CarnList.get(i).X == OmniList.get(j).X && CarnList.get(i).Y == OmniList.get(j).Y && CarnList.get(i).Z == OmniList.get(j).Z) {
                    //System.out.println("CarnFish walczy z OmniFish");
                    Fish.FishFight++;
                    if(CarnList.get(i).Strength > OmniList.get(j).Strength){
                        CarnList.get(i).Hp = CarnList.get(i).Hp + OmniList.get(j).Size;
                        OmniList.remove(j);
                        //System.out.println("Wygrywa CarnFish");
                        OmniFightDead++;
                    }
                    else if(CarnList.get(i).Strength < OmniList.get(j).Strength){
                        OmniList.get(j).Hp = OmniList.get(j).Hp + CarnList.get(i).Size;
                        CarnList.remove(i);
                        //System.out.println("Wygrywa OmniFish");
                        CarnFightDead++;
                    }
                }
            }
        }

        //Walki CarnFish z HerbFish
        for (int i = 0; i < CarnList.size(); i++) {
            for (int j = 0; j < HerbList.size(); j++) {
                if (CarnList.get(i).X == HerbList.get(j).X && CarnList.get(i).Y == HerbList.get(j).Y && CarnList.get(i).Z == HerbList.get(j).Z) {
                    //System.out.println("CarnFish walczy z HerbFish");
                    Fish.FishFight++;
                    if(CarnList.get(i).Strength > HerbList.get(j).Strength){
                        CarnList.get(i).Hp = CarnList.get(i).Hp + HerbList.get(j).Size;
                        HerbList.remove(j);
                        //System.out.println("Wygrywa CarnFish");
                        HerbFightDead++;
                    }
                    else if(CarnList.get(i).Strength < HerbList.get(j).Strength){
                        CarnList.remove(i);
                        //System.out.println("Wygrywa HerbFish");
                        CarnFightDead++;
                    }
                }
            }
        }

        //Walki OmniFish z HerbFish
        for (int i = 0; i < OmniList.size(); i++) {
            for (int j = 0; j < HerbList.size(); j++) {
                if (OmniList.get(i).X == HerbList.get(j).X && OmniList.get(i).Y == HerbList.get(j).Y && OmniList.get(i).Z == HerbList.get(j).Z) {
                    //System.out.println("OmniFish walczy z HerbFish");
                    Fish.FishFight++;
                    if(OmniList.get(i).Strength > HerbList.get(j).Strength){
                        OmniList.get(i).Hp = OmniList.get(i).Hp + HerbList.get(j).Size;
                        HerbList.remove(j);
                        //System.out.println("Wygrywa OmniFish");
                        HerbFightDead++;
                    }
                    else if(OmniList.get(i).Strength < HerbList.get(j).Strength){
                        OmniList.remove(i);
                        //System.out.println("Wygrywa HerbFish");
                        OmniFightDead++;
                    }
                }
            }
        }
    }
}