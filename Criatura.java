import java.lang.*;
import java.util.*;
 
import greenfoot.*;

public abstract class Criatura extends Actor {
    protected final String nombre;
    protected final int vidaMaxima;

    protected final String[] nombresAtaque;
    protected final String[] detallesAtaque;
    private Ataque [] ataques;
    protected final boolean equipo1;

    protected int vida;

    private UIInfoCriatura uiInfoCriatura;
    private int cantAtaques;
    private boolean visualHover;
    private boolean visualSeleccionado;
    private boolean estaParalizado;
    private boolean estaDesmayado;
    private boolean estaDesmayado2;
    private boolean estaDesmayado3;
    private boolean estaAtacando = false;
    private final MyGreenfootImage imagenOriginal;
    private int velocidad;
    private int defensa;
    private int ataqueAtributo;

    public Criatura(String nombre, int vida, String[] nombresAtaque, boolean equipo1, String[] detallesAtaque,int cantAtaques) {
        this.nombre = nombre;
        this.vidaMaxima = vida;
        this.nombresAtaque = nombresAtaque;
        this.detallesAtaque = detallesAtaque;
        this.vida = vida;
        this.cantAtaques = cantAtaques;
        this.estaDesmayado = false;
        this.estaParalizado = false;
        this.equipo1 = equipo1;
        this.imagenOriginal = new MyGreenfootImage(getImage());
        this.imagenOriginal.scale(130, 130);
        this.uiInfoCriatura = new UIInfoCriatura(this);
        crearArrayDeAtaques();
    }
   
       
    public Ataque[] getAtaques(){
        return this.ataques;
    }

    @Override
    protected void addedToWorld(World world) {
        render();

        getWorld().addObject(uiInfoCriatura, getX(), getY());
        // Una vez en el mundo, actualizo segun su tamaño
        uiInfoCriatura.setLocation(getX(), getY() + getImage().getHeight() / 2 - /*Sombra*/ 10 + uiInfoCriatura.getImage().getHeight() / 2);
    }

    public void act() {
        boolean _visualHover = visualHover;
        boolean _visualSeleccionado = visualSeleccionado;

        MouseInfo m = Greenfoot.getMouseInfo();

        // TODO: detecta el mouse-over, no tocar
        if (m != null) {
            List<Actor> actor = getWorld().getObjectsAt(m.getX(), m.getY(), Actor.class);

            if (actor.size() > 0 && actor.get(0) == this) {
                visualHover = true;
                ((PantallaDuelo)getWorld()).hover(this);
            } else {
                visualHover = false;
            }
        }

        if (Greenfoot.mouseClicked(this)) {
            ((PantallaDuelo)getWorld()).click(this);
        }

        if (_visualHover != visualHover || _visualSeleccionado != visualSeleccionado) {
            render();
        }
    }

    public boolean getEstaAtacando(){
        return estaAtacando;
    }

    public void setEstaAtacando(boolean estaAtacando){
        this.estaAtacando = estaAtacando;
    }
    public void setDefensa(int defensa){
        this.defensa = defensa;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    public void setAtaqueAtributo(int ataque){
        this.ataqueAtributo = ataque;
    }
    public void setEstaDesmayado(Criatura nombre){
        this.estaDesmayado = true;
    
    }
    public void setEstaParalizado(Criatura nombre){
        this.estaParalizado = true;
    }
    
    public void render() {
        MyGreenfootImage nuevaImagen = new MyGreenfootImage(imagenOriginal) {
                public void configurar() {
                    if (!equipo1) {
                        flipHorizontally();
                    }
                    if (visualHover) {
                        scaleToRatio(1.15);
                    }
                    if (visualSeleccionado) {
                        highlight();
                    }
                    shadow();
                }
            };

        setImage(nuevaImagen);
    }

    public void atacarCriatura(Criatura otro,Ataque ataque) {
        if(otro.getVida() <= 0){
            otro.setEstaDesmayado(this);
        } else {
            otro.recibirDaño(this,ataque);
        }        
    }

       protected int recibirDaño(Criatura atacante,Ataque ataque) {
        Random rand = new Random();
        double numeroAleatorio = 0.5 + rand.nextDouble() * (1.25 - 0.5);

        if(this.vida>0){
            this.vida -= 2*(1+(atacante.ataqueAtributo/atacante.defensa)*numeroAleatorio); //FALTA EL FACTOR TIPO
            uiInfoCriatura.actualizar();
            return this.vida;
        } return 0;
    }


    protected boolean esDelMismoEquipoQue(Criatura otro) {
        return this.equipo1 == otro.equipo1;
    }

    public boolean puedeRealizarAtaque1En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public abstract boolean puedeRealizarAtaque2En(Criatura otro);

    public abstract boolean puedeRealizarAtaque3En(Criatura otro);

    public abstract boolean puedeRealizarAtaque4En(Criatura otro);

 
    public int getVida() {
        return vida;
    }

    public String getNombre(){
        return this.nombre;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    
    public int getVelocidad(){
        return this.velocidad;
    }
    public int getDefensa(){
        return this.defensa;
    }

    public boolean esEquipo1() {
        return equipo1;
    }

    public void setVisualSeleccionado(boolean visualSeleccionado) {
        this.visualSeleccionado = visualSeleccionado;
        render();
    }

    public String toString() {
        return nombre;
    }

    public String[] getNombresAtaque() {
        return nombresAtaque;
    }

    public String[] getDetallesAtaque() {
        return detallesAtaque;
    }

    public String getStats() {
         return nombre + " (" + this.getClass().getSimpleName() + ")\n" +
        " - Ataque: " + this.ataqueAtributo + "\n"+
        
        " - Defensa: " + getDefensa() + "\n" +
        " - Velocidad: " + getVelocidad();
    }

    public void crearArrayDeAtaques(){
        ataques = new Ataque[this.cantAtaques];
    }
 
  

   
}
