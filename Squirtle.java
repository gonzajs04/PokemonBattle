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
                new String[] { "Causa un daño moderado a un enemigo", "Quita la paralizacion del aliado",
                    "15 de daño y envuelve al rival en una burbuja que lo suspende por 1 turno", "Baja 3 de daño" },4);
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
        crearAtaques();
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

    public void crearAtaques(){
        super.ataques[0] = new Puño();
        super.ataques[1] = new Alivio();
        super.ataques[2] = new AtaqueBurbuja();
        super.ataques[3] = new Piña();

    }
}
