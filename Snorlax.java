import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snorlax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snorlax extends Criatura
{
    public Snorlax(String nombre, boolean imagenEspejada) {
        super(nombre, 100, new String[] { "Placaje", "- Panzaso -", "- Fabian -", "- Piña -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Baja 5 de daño", "Te mata de un chivazo", "Baja 3 de daño" });
    }

    public Snorlax(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return false;
    }

    public void atacar3(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }

    public void atacar4(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
}
