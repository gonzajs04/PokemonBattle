import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Mewtwo extends Criatura
{   
    private int ataque = 30;
    private int velocidad= 200;;
    private int defensa = 200;
        private String[] debilidades = {"Normal","Electricidad"};

    public Mewtwo(String nombre, boolean imagenEspejada) {
        super(nombre, 10, new String[] { "Puño", "- Golpe Aereo -", "- Paralizador -", "- Psicocorte -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Realiza una patada voladora y golpea en la cabeza con alta probabilidad de golpe critico", "Paraliza al enemigo, solo se puede utilizar 1 vez ",
                "90 porciento de probabilidad de golpe critico " },4,"Mistico","mewtwo-muerto.png","mewtwo-paralizado.png");
        super.setDefensa(this.defensa);
        super.setVelocidad(this.velocidad);
        super.setAtaqueAtributo(this.ataque);
        super.setDebilidades(debilidades);
        crearAtaques();
                    
    }

    public Mewtwo(String nombre) {
        this(nombre, false);
    }

   /* public void atacar(Criatura otro) {
       
        
    }*/
    
       public int getVelocidad(){
        return this.velocidad;
    }
    public int getDefensa(){
        return this.defensa;
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
        super.getAtaques()[1] = new GolpeAereo();
        super.getAtaques()[2] = new Paralizador();
        super.getAtaques()[3] = new Psicocorte();

    }
}
