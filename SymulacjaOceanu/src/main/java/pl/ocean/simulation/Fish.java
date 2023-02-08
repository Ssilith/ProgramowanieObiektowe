package pl.ocean.simulation;
import pl.ocean.map.Map;

public abstract class Fish implements FishInterface{
    public static int FishFight;
    public int X, Y, Z, Hp, Strength, Size;
    public boolean Sex;
    public int RuchX, RuchY, RuchZ, Hunger;

    public Fish(int x, int y, int z) {
        X = RandomNumber.losuj(0, x);
        Y = RandomNumber.losuj(0, y);
        Z = RandomNumber.losuj(0, z);
    }

    //Poruszanie sie obiektu
    public void move(){
        int a, b, c;

        //Sprawdzenie czy nie wychodzi poza obszar mapy
        do{
            a = X + RandomNumber.losuj(-RuchX, RuchX);
        } while(a > Map.MaxX);

        do{
            b = Y + RandomNumber.losuj(-RuchY, RuchY);
        } while(b > Map.MaxY);

        do{
            c = Z + RandomNumber.losuj(-RuchZ, RuchZ);
        } while(c > Map.MaxZ);

        this.X = a;
        this.Y = b;
        this.Z = c;
    }

    //Spadajace ilość punktów zdrowia po każdej rundzie
    public void hungry(){
        this.Hp=this.Hp - Hunger;
    }
}