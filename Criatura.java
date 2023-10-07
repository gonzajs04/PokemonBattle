import java.util.List;
import java.lang.*;
import java.util.*;
 
import greenfoot.*;

public abstract class Criatura extends Actor {
    protected final String nombre;
    protected final int vidaMaxima;

    protected final String[] nombresAtaque;
    protected final String[] detallesAtaque;

    protected final boolean equipo1;

    protected int vida;

    private UIInfoCriatura uiInfoCriatura;

    private boolean visualHover;
    private boolean visualSeleccionado;

    private final MyGreenfootImage imagenOriginal;
    private int velocidad;
    private int defensa;

    public Criatura(String nombre, int vida, String[] nombresAtaque, boolean equipo1, String[] detallesAtaque) {
        this.nombre = nombre;
        this.vidaMaxima = vida;
        this.nombresAtaque = nombresAtaque;
        this.detallesAtaque = detallesAtaque;
        this.vida = vida;
   
        this.equipo1 = equipo1;
        this.imagenOriginal = new MyGreenfootImage(getImage());
        this.imagenOriginal.scale(130, 130);
        this.uiInfoCriatura = new UIInfoCriatura(this);
        System.out.println(Arrays.toString(nombresAtaque));
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


    
    public void setDefensa(int defensa){
        this.defensa = defensa;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
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

    public void atacar1(Criatura otro) {
        otro.recibirDaño(this);
    }

    public abstract void atacar2(Criatura otro);

    public abstract void atacar3(Criatura otro);

    public abstract void atacar4(Criatura otro);

    protected boolean esDelMismoEquipoQue(Criatura otro) {
        return this.equipo1 == otro.equipo1;
    }

    public boolean puedeRealizarAtaque1En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public abstract boolean puedeRealizarAtaque2En(Criatura otro);

    public abstract boolean puedeRealizarAtaque3En(Criatura otro);

    public abstract boolean puedeRealizarAtaque4En(Criatura otro);

    protected int recibirDaño(Criatura atacante) {
        if(this.vida>0){
            this.vida -= 5;
            uiInfoCriatura.actualizar();
            return 5;
        } return 0;
    }

    public int getVida() {
        return vida;
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
        " - Ataque: 0\n" +
        " - Defensa: " + getDefensa() + "\n" +
        " - Velocidad: " + getVelocidad();
    }
}
