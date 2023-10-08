import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Criatura{
    private int ataque = 10;
    private int velocidad = 300;
    private int defensa = 100;
    public Charmander(String nombre, boolean imagenEspejada) {
        super(nombre, 100, new String[] { "Puño", "- Gruñido -", "- Chuchillada -", "- Ascuas -" }, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo", "Baja un nivel el Ataque al rival",
            "Golpea con alta probalidadad de golpe critico", "Quema al rival. Reduce un 10% de la vida del rival" },4);
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);

    }

    public Charmander(String nombre) {
        this(nombre, false);
    }
    public int getVelocidad(){
        return this.velocidad;
    }
    public int getDefensa(){
        return this.defensa;
    }

    public void atacar(Criatura otro) {
        atacarCriatura(otro);
    }

 
    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return false;
    }


    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }

   
    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }

}
