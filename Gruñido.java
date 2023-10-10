import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gruñido extends Ataque {
    
    private int ataqueReducido = 1;

    public Gruñido(){
        super("Gruñido",0,0,"Normal",15);
    }
    public int ataqueReducido(){
        return ataqueReducido;
    }
}