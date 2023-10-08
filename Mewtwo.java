import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Mewtwo extends Criatura
{   
    private int ataque = 30;
    private int velocidad= 200;;
    private int defensa = 200; ;
    public Mewtwo(String nombre, boolean imagenEspejada) {
        super(nombre, 200, new String[] { "Puño", "- Golpe Aereo -", "- Paralizador -", "- Psicocorte -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Realiza el puño 5 veces dentro de 1 ataque", "Cancela 2 turnos del rival ",
                "Quita entre 20-30 de daño con un 10% de golpe critico " },4);
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);

                    
    }

    public Mewtwo(String nombre) {
        this(nombre, false);
    }

    public void atacar(Criatura otro) {
        atacarCriatura(otro);
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


    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }



    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
    
    
    public void crearAtaques(){
        super.ataques[0] = new Puño();
        super.ataques[1] = new GolpeAereo();
        super.ataques[2] = new Paralizador();
        super.ataques[3] = new Psicocorte();

    }
}
