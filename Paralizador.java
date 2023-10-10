import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paralizador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paralizador extends Ataque
{
    private int turnosAParalizar = 2;
   public Paralizador(){
       super("Paralizador",0,0,"Mistico",10);
   }
   public int turnosAParalizar(){
       return turnosAParalizar;
   }
}
