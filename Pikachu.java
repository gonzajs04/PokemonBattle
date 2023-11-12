import java.util.*;
public class Pikachu extends Criatura {
    private int velocidad = 500;
    private int defensa = 100;
    private int ataque=150;
    private String [] debilidades = {"Normal","Fuego"};
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 10, new String[] { "Puño", "- Rayo -", "- Placaje -", "- Furia de rayo -" }, imagenEspejada,
        new String[] { "Causa un daño moderado a un enemigo", "50% de probabilidad de golpe critico", "Quita 5% de daño con un 85% de golpe critico",
        "90% de probabilidad de golpe critico" },4,"Electricidad","pikachu-muerto.png","pikachu-paralizado.png");
        super.setDefensa(this.defensa);//LE ASIGNO EL ATRIBUTO DEFENSA EN LA CLASE CRIATURA
        super.setVelocidad(this.velocidad);//LE ASIGNO EL ATRIBUTO VELOCIDAD EN LA CLASE CRIATURA
        super.setAtaqueAtributo(this.ataque); //LE ASIGNO EL ATRIBUTO ATAQUE EN LA CLASE CRIATURA
        super.setDebilidades(debilidades); //Le ASIGNO LAS DEBILIDADES EN LA CLASE CRIATURA
        crearAtaques();

        //PONER CARACTERISTICA DEL ATAQUE DENTRO DE CADA ATAQUE.
        //ELIMINAR DE CONSTRUCTOR LOS ATAQUES Y BUSCAR UNA FORMA DE RECORRER EL ARRAY DE ATAQUES Y MOSTRARLOS EN LOS BOTONES Y AL CLICKEARLOS QUE MUESTRE SUS CARACTERISTICAS
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
        super.getAtaques()[1] = new Rayo();
        super.getAtaques()[2] = new Placaje();
        super.getAtaques()[3] = new FuriaDeRayo();

    }
}
