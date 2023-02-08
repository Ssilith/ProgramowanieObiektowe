package pl.ocean.simulation;

/*
Etap 5
Symulacja oceanu
Wiktoria Jadowska i Katarzyna Hajduk
 */

public class Start {

    public static void main(String[] args){
        Results plik = new Results();
        pl.ocean.simulation.Simulation symulacja = new pl.ocean.simulation.Simulation();
        plik.nazwijPlik();
        symulacja.StartSimulation();
        plik.poczatek();
        symulacja.Rounds();
    }
}