package pl.ocean.simulation;
import pl.ocean.map.Map;
import java.util.List;

public class OmniFish extends Fish{

    public static int OmniHungerDead, OmniBorn, OmniFightDead;
    public OmniFish(int x, int y, int z) {
        super(x, y, z);
        Hp = RandomNumber.losuj(70, 100);
        Strength = RandomNumber.losuj(2, 8);
        Size = RandomNumber.losuj(10, 20);
        Sex = RandomNumber.losujBoolean();
        RuchX = 1;
        RuchY = 1;
        RuchZ = 1;
        Hunger = 5;
    }

    //Umieranie
    public void dieOmni(List<OmniFish> lista, int i){
        if(lista.get(i).Hp < 1){
            lista.remove(i);
            //System.out.println("OmniFish umiera z glodu");
            OmniHungerDead++;
        }
    }

    //Rozmnażanie
    public void reproduceOmni(List<OmniFish> lista, int i){
        if(lista.get(i).Sex){
            //Sprawdzenie czy nie wychodzi poza obszar mapy
            int a;
            if(lista.get(i).Z+1 > Map.MaxZ)
                a = lista.get(i).Z-1;
            else
                a = lista.get(i).Z+1;

            lista.add(new OmniFish(lista.get(i).X, lista.get(i).Y, a));
            //System.out.println("Rodzi sie nowy OmniFish");
            OmniBorn++;
        }
    }

    //Zjadanie glonow
    public void eatAlgaeOmni(List<OmniFish> listaRyb, List<Algae> listaAlg, int i, int j){
        listaRyb.get(i).Hp = listaRyb.get(i).Hp + listaAlg.get(j).Size;
        listaAlg.remove(j);
    }
    //Zjadanie ostryg
    public void eatOysterOmni(List<OmniFish> listaRyb, List<Oyster> listaOstryg, int i, int j){
        listaRyb.get(i).Hp = listaRyb.get(i).Hp + listaOstryg.get(j).Size;
        listaOstryg.remove(j);
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
    public int getStrength() {
        return Strength;
    }

    @Override
    public int getSize() {
        return Size;
    }

    @Override
    public boolean getSex() {
        return Sex;
    }
}