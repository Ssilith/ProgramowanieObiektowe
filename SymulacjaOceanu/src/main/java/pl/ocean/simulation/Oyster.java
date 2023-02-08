package pl.ocean.simulation;

public class Oyster extends Food {

    public static int OysterDead;
    public Oyster(int x, int y, int z) {
        super(x = RandomNumber.losuj(0, x), y = RandomNumber.losuj(0, y), z = RandomNumber.losuj(0, z));
        Size = RandomNumber.losuj(1, 10);
    }

    @Override
    public int getX() { return X; }

    @Override
    public int getY() { return Y; }

    @Override
    public int getZ() { return Z; }

    @Override
    public int getSize() { return Size; }
}