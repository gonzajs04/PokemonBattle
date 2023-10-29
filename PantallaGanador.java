import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public  class PantallaGanador extends World
{
    private GreenfootSound winnerMusic = new GreenfootSound("victory.mp3");

    public PantallaGanador()
    {    
        // Create a new world with 700x400 cells with a cell size of 1x1 pixels.
        super(700, 400, 1); 
        TitleScreen.getMusicaInicio().stop();
        winnerMusic.play();
        
    }
    
    
 
}
