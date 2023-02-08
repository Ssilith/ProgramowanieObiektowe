package pl.ocean.simulation;

public abstract class Food implements FoodInterface{
    public int X, Y, Z, Size;

    public Food(int x, int y, int z) {
        X = x;
        Y = y;
        Z = z;
    }
}