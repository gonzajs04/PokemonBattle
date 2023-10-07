import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Mewtwo extends Criatura
{
    private int velocidad= 200;;
    private int defensa = 200; ;
    public Mewtwo(String nombre, boolean imagenEspejada) {
        super(nombre, 200, new String[] { "Puño", "- Golpe Aereo -", "- Paralizador -", "- Psicocorte -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Realiza el puño 5 veces dentro de 1 ataque", "Cancela 2 turnos del rival ",
                "Quita entre 20-30 de daño con un 10% de golpe critico " });
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
                    
    }

    public Mewtwo(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }
    
       public int getVelocidad(){
        return this.velocidad;
    }
    public int getDefensa(){
        return this.defensa;
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
