package pl.ocean.simulation;
import pl.ocean.map.Map;
import java.util.List;

public class HerbFish extends Fish{

    public static int HerbHungerDead, HerbBorn, HerbFightDead;
    public HerbFish(int x, int y, int z) {
        super(x, y, z);
        Hp = RandomNumber.losuj(50, 100);
        Strength = RandomNumber.losuj(1, 5);
        Size = RandomNumber.losuj(10, 20);
        Sex = RandomNumber.losujBoolean();
        RuchX = 3;
        RuchY = 3;
        RuchZ = 2;
        Hunger = 4;
    }

    //Umieranie
    public void dieHerb(List<HerbFish> lista, int i){
        if(lista.get(i).Hp < 1){
            lista.remove(i);
            //System.out.println("HerbFish umiera z glodu");
            HerbHungerDead++;
        }
    }

    //Rozmnażanie
    public void reproduceHerb(List<HerbFish> lista, int i){
        if(lista.get(i).Sex){
            //Sprawdzenie czy nie wychodzi poza obszar mapy
            int a;
            if(lista.get(i).Y+1 > Map.MaxY)
                a = lista.get(i).Y-1;
            else
                a = lista.get(i).Y+1;

            lista.add(new HerbFish(lista.get(i).X, a, lista.get(i).Z));
            //System.out.println("Rodzi sie nowy HerbFish");
            HerbBorn++;
        }
    }

    // Zjadanie glonow
    public void eatAlgaeHerb(List<HerbFish> listaRyb, List<Algae> listaAlg, int i, int j){
        listaRyb.get(i).Hp = listaRyb.get(i).Hp + listaAlg.get(j).Size;
        listaAlg.remove(j);
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public int getZ() {
        return Z;
    }

    @Override
    public int getHp() {
        return Hp;
    }

    @Override
    public int getStrength() { return Strength; }

    @Override
    public int getSize() {
        return Size;
    }

    @Override
    public boolean getSex() {
        return Sex;
    }
}