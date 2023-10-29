import greenfoot.*;  
import java.util.*;

public class TitleScreen extends World
{
    private int contadorTiempo = 0;
     private static final GreenfootSound mainMusic = new GreenfootSound("music.mp3");
    public TitleScreen()
    {    
        // Create a new world with 700x400 cells with a cell size of 1x1 pixels.
        super(700, 400, 1); 
        Greenfoot.start();
    }
    public void act()
    {
        if (++contadorTiempo == 100) Greenfoot.setWorld(new PantallaDuelo());
        if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("Enter")) Greenfoot.setWorld(new PantallaDuelo());
        mainMusic.play();
    }
    
    public static GreenfootSound getMusicaInicio(){
        return mainMusic;
    }
    
}
