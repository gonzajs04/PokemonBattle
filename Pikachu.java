import java.util.*;
public class Pikachu extends Criatura {
      private int velocidad = 300;
    private int defensa = 100;
    private int ataque=15;
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 100, new String[] { "Puño", "- Rayo -", "- Placaje -", "- Furia de rayo -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Quita 10 de daño y hay un 90% de probabilidades de paralizar al rival por 2 turnos", "Quita 5% de daño con un 85% de golpe critico",
                    "Se necesita 80 de energia y reduce la vida del rival un 90%" },4);
       super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
        crearAtaques();

    }

    public Pikachu(String nombre) {
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

    public void crearAtaques(){
        super.ataques[0] = new Puño();
        super.ataques[1] = new Rayo();
        super.ataques[2] = new Placaje();
        super.ataques[3] = new FuriaDeRayo();

    }
}
