import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snorlax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squirtle extends Criatura
{
      private int velocidad = 100;
    private int defensa = 100;
    private int ataque=10;
    public Squirtle(String nombre, boolean imagenEspejada) {
        super(nombre, 100, new String[] { "Puño", "- Alivio -", "- Ataque burbuja -", "- Piña -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Quita la paralizacion del rival",
                    "15 de daño y envuelve al rival en una burbuja que lo suspende por 1 turno", "Baja 3 de daño" });
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
    }

    public Squirtle(String nombre) {
        this(nombre, false);
    }


    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return false;
    }



    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }

    public void atacar(Criatura otro) {
        atacarCriatura(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
}
