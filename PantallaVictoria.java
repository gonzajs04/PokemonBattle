import greenfoot.*;  
import java.util.*;

public class PantallaVictoria extends World
{


    public PantallaVictoria()
    {    
        super(1800, 1020, 1); 
        GreenfootImage imagenFondo = new GreenfootImage("PantallaVictoria.png"); //SE CAMBIA EL FONDO
        getBackground().drawImage(imagenFondo, 0, 0);
        System.out.println("AUTISTA");
    }
}
