import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Squirtle extends Criatura
{
      private int velocidad = 100;
    private int defensa = 100;
    private int ataque=10;
        private String[] debilidades = {"Fuego"};

    public Squirtle(String nombre, boolean imagenEspejada) {
        super(nombre, 10, new String[] { "Puño", "- Cañon de agua -", "- Ataque burbuja -", "- Piña -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Quita daño",
                    "15 de daño y envuelve al rival en una burbuja que lo suspende por 1 turno", "Baja 3 de daño" },4,"Agua","squirtle-muerto.png","squirtle-paralizado.png");
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
        super.setDebilidades(debilidades);
        crearAtaques();
    }

    public Squirtle(String nombre) {
        this(nombre, false);
    }


    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }



    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    /* public void atacar(Criatura otro) {
       
        
    }*/

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public void crearAtaques(){
        super.getAtaques()[0] = new Puño();
        super.getAtaques()[1] = new Alivio();
        super.getAtaques()[2] = new AtaqueBurbuja();
        super.getAtaques()[3] = new Piña();

    }
}
