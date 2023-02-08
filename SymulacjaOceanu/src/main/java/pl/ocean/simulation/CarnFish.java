package pl.ocean.simulation;
import pl.ocean.map.Map;
import java.util.List;

public class CarnFish extends Fish{

    public static int CarnHungerDead, CarnBorn, CarnFightDead;
    public CarnFish(int x, int y, int z) {
        super(x, y, z);
        Hp = RandomNumber.losuj(50, 100);
        Strength = RandomNumber.losuj(3, 10);
        Size = RandomNumber.losuj(10, 20);
        Sex = RandomNumber.losujBoolean();
        RuchX = 2;
        RuchY = 2;
        RuchZ = 1;
        Hunger = 4;
    }

    //Umieranie
    public void dieCarn(List<CarnFish> lista, int i){
        if(lista.get(i).Hp < 1){
            lista.remove(i);
            //System.out.println("CarnFish umiera z glodu");
            CarnHungerDead++;
        }
    }

    //Rozmnażanie
    public void reproduceCarn(List<CarnFish> lista, int i){
        if(lista.get(i).Sex){
            //Sprawdzenie czy nie wychodzi poza obszar mapy
            int a;
            if(lista.get(i).X+1 > Map.MaxX)
                a = lista.get(i).X-1;
            else
                a = lista.get(i).X+1;
            lista.add(new CarnFish(a, lista.get(i).Y, lista.get(i).Z));
            //System.out.println("Rodzi sie nowy CarnFish");
            CarnBorn++;
        }
    }

    //Zjadanie ostryg
    public void eatOysterCarn(List<CarnFish> listaRyb, List<Oyster> listaOstryg, int i, int j){
        listaRyb.get(i).Hp = listaRyb.get(i).Hp + listaOstryg.get(j).Size;
        listaOstryg.remove(j);
    }

    @Override
    public int getX() { return X; }

    @Override
    public int getY() { return Y; }

    @Override
    public int getZ() { return Z; }

    @Override
    public int getHp() { return Hp; }

    @Override
    public int getStrength() { return Strength; }

    @Override
    public int getSize() { return Size; }

    @Override
    public boolean getSex() { return Sex; }
}