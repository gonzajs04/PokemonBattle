import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Charmander extends Criatura{
    private int ataque = 10;
    private int velocidad = 300;
    private int defensa = 100;
    private String[] debilidades = {"Agua"};
    public Charmander(String nombre, boolean imagenEspejada) {
        super(nombre, 10, new String[] { "Puño", "- Gruñido -", "- Chuchillada -", "- Ascuas -" }, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo", "2% de probabilidad de golpe critico",
            "Golpea con alta probalidadad de golpe critico", "90% de golpe critico" },4,"Fuego","charmander-muerto.png","charmander-paralizado.png");
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
        super.setDebilidades(debilidades);
        crearAtaques();

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
    
      public String getImagenMuerto(){
        return "charmander-muerto.png";
    }

  
    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }


    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

   
    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public void crearAtaques(){
        super.getAtaques()[0] = new Puño();
        super.getAtaques()[1] = new Gruñido();
        super.getAtaques()[2] = new Cuchillada();
        super.getAtaques()[3] = new Ascuas();
       

    }

}
