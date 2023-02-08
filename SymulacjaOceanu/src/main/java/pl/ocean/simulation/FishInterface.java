package pl.ocean.simulation;

public interface FishInterface {
    int getX();
    int getY();
    int getZ();
    int getHp();
    int getStrength();
    int getSize();
    boolean getSex();
    void move();
    void hungry();
}