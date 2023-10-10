import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

 
public class AtaqueBurbuja extends Ataque {
        
    private int turnosSuspendido = 1;

    public AtaqueBurbuja(){
        super("Ataque Burbuja",15,15, "Agua",25);
    }

    public int turnosSuspendido(){
        return turnosSuspendido;
    }
}