import java.lang.*;
import java.util.*;
import greenfoot.*;

public abstract class Criatura extends Actor {
    protected final String nombre;
    protected final int vidaMaxima;
    protected final String[] nombresAtaque;
    protected final String[] detallesAtaque;
    private Ataque[] ataques;
    protected final boolean equipo1;
    protected int vida;
    private UIInfoCriatura uiInfoCriatura;
    private int cantAtaques;
    private boolean visualHover;
    private boolean visualSeleccionado;
    private boolean estaParalizado;
    private boolean estaDesmayado;
    private  MyGreenfootImage imagenOriginal;
    private int velocidad;
    private int defensa;
    private int ataqueAtributo;
    private String[] debilidades;
    private String tipo;
    private static int contMuertosEquipo1 = 0;
    private static int contMuertosEquipo2 = 0;
    private Random randomParalizador;
    private String imagenPokemonMuerto;
    private String imagenPokemonParalizado;
    private boolean estaPorAtacar = false;
    public Criatura(String nombre, int vida, String[] nombresAtaque, boolean equipo1, String[] detallesAtaque,
    int cantAtaques, String tipo, String imagenPokemonMuerto, String imagenPokemonParalizado) {
        this.nombre = nombre;
        this.vidaMaxima = vida;
        this.nombresAtaque = nombresAtaque;
        this.detallesAtaque = detallesAtaque;
        this.vida = vida;
        this.cantAtaques = cantAtaques;
        this.estaDesmayado = false;
        this.estaParalizado = false;
        this.equipo1 = equipo1;
        this.tipo = tipo;
        this.imagenOriginal = new MyGreenfootImage(getImage());
        this.imagenOriginal.scale(130, 130);
        this.uiInfoCriatura = new UIInfoCriatura(this);
        this.imagenPokemonMuerto = imagenPokemonMuerto;
        this.imagenPokemonParalizado = imagenPokemonParalizado;
        crearArrayDeAtaques();
    }

    @Override
    protected void addedToWorld(World world) {
        render();

        getWorld().addObject(uiInfoCriatura, getX(), getY());
        // Una vez en el mundo, actualizo segun su tamaño
        uiInfoCriatura.setLocation(getX(),
            getY() + getImage().getHeight() / 2 - /* Sombra */ 10 + uiInfoCriatura.getImage().getHeight() / 2);
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
                ((PantallaDuelo) getWorld()).hover(this);
            } else {
                visualHover = false;
            }
        }

        if (Greenfoot.mouseClicked(this)) {
            ((PantallaDuelo) getWorld()).click(this);
        }

        if (_visualHover != visualHover || _visualSeleccionado != visualSeleccionado) {
            render();
        }
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setEstaPorAtacar(boolean estaPorAtacar){
        this.estaPorAtacar  = estaPorAtacar;
    }

    public boolean estaPorAtacar(){
        return estaPorAtacar;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setAtaqueAtributo(int ataque) {
        this.ataqueAtributo = ataque;
    }

    public void setEstaDesmayado(boolean desmayado) {
        this.estaDesmayado = desmayado;

    }

    public boolean getEstaDesmayado() {
        return this.estaDesmayado;
    }

    public void setEstaParalizado(boolean estaParalizado) {
        this.estaParalizado = estaParalizado;
    }

    public boolean getEstaParalizado(){
        return this.estaParalizado;
    }

    public Ataque[] getAtaques() {
        return this.ataques;
    }

    public String getTipo() {
        return this.tipo;
    }

    public static int getContadorEquipo1(){
        return contMuertosEquipo1;
    }

    public static int getContadorEquipo2(){
        return contMuertosEquipo2;
    }

    public static void setContadorEquipo1(int valor){
        contMuertosEquipo1 = valor;
    }

    public static void setContadorEquipo2(int valor){
        contMuertosEquipo2 = valor;
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

    protected int atacar(Criatura otro, Ataque ataque) {

        if (!this.estaDesmayado && !otro.estaDesmayado && !this.estaParalizado) {
            if( paralizaOQuitaDaño(ataque) ){ 

                otro.setEstaParalizado(true);
                ataque.setSeHizoUso(true); 
                System.out.println("El ataque paralizador ya no hara nada de ahora en mas");

                generarImagen(otro,otro.imagenPokemonParalizado); 

            }else{ 
                realizarAtaqueDaño(otro,ataque);
                verificarPosibilidadDesmayo(otro); 
            }

            otro.uiInfoCriatura.actualizar(); 

        }
        return otro.vida;
    }

    public void realizarAtaqueDaño(Criatura otro, Ataque ataque){
        Random rand = new Random();
        double numeroAleatorio = 0.5 + rand.nextDouble() * (1.25 - 0.5);

        double factor = verificarFactorCriaturaEnemiga(otro, ataque);

        double daño = 2 * (1 + (this.ataqueAtributo / otro.getDefensa()) * factor * numeroAleatorio); 
        double dañoGolpeCritico = verificarGolpeCritico(daño, ataque); 
        double dañoFinal = Math.round(daño) + dañoGolpeCritico;
        if(dañoFinal>otro.vida){
            otro.setVida(0);
        }else{
            otro.vida -= dañoFinal;
            System.out.println("El pokemon " + this.getNombre() + " Ataco con " + ataque.getNombre() + " y quito "
                + dañoFinal + " de vida a " + otro.getNombre());
        } 
    }

    public boolean paralizaOQuitaDaño(Ataque ataque){
        return( ataque.getNombre().equals("Paralizador") || ataque.getNombre().equals("Ataque Burbuja") && !ataque.getSeHizoUso());
    }

    public void verificarPosibilidadDesmayo(Criatura otro){
        if(otro.getVida()<=0){
            otro.setEstaDesmayado(true); 
            verificarEquipoIntegranteDesmayado(otro); 
        }
    }

    public void verificarEquipoIntegranteDesmayado(Criatura otro){
        if(otro.esEquipo1()){
            contMuertosEquipo1++; 
            System.out.println("Murio el pokemon "+ otro.getNombre() + " del equipo 1");

        }else{
            contMuertosEquipo2++; 
            System.out.println("Murio el pokemon "+ otro.getNombre() + " del equipo 2");
        }     
        generarImagen(otro,otro.imagenPokemonMuerto);

    }

    public double verificarGolpeCritico(double daño, Ataque ataque) {
        double posibleCritico = 0;
        double golpeCritico = 0; 
        Random rd = new Random();
        posibleCritico = rd.nextInt(101); 
        if (ataque.getProbabilidadGolpeCritico() > posibleCritico) { 
            golpeCritico = calcularGolpeCritico(daño);
            System.out.println("Se produjo un golpe critico de parte de " + this.getNombre());
        }
        return golpeCritico;
    }

    public double calcularGolpeCritico(double daño) {
        return daño + (daño * 0.5);
    }

    public void generarImagen(Criatura c, String imagenPokemon){
        GreenfootImage miImagen = new GreenfootImage(imagenPokemon); 
        c.imagenOriginal = new MyGreenfootImage(miImagen); 
        c.imagenOriginal.scale(130,130);
    }

    public double verificarFactorCriaturaEnemiga(Criatura oponente, Ataque ataque) {
        double factor = 1; 
        for (int i = 0; i < oponente.getDebilidades().length; i++) {
            if (oponente.getDebilidades()[i] == ataque.getTipo()) { 
                System.out.println("El pokemon que estas atacando tiene una debilidad hacia el ataque: "
                    + ataque.getTipo() + " , el daño sera de 1.25 mayor");
                factor = 1.25;
            } 
        }

        if (oponente.getTipo() == ataque.getTipo()) {  
            System.out.println("El pokemon que estas atacando tiene una resistencia hacia el ataque: "
                + ataque.getTipo() + " , el daño sera de 0.75 mayor");

            factor = 0.75;
        }

        return factor;
    }

    protected boolean esDelMismoEquipoQue(Criatura otro) {
        return this.equipo1 == otro.equipo1;
    }

    public boolean puedeRealizarAtaque1En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public boolean esEquipo1() {
        return this.equipo1;
    }

    public abstract boolean puedeRealizarAtaque2En(Criatura otro);

    public abstract boolean puedeRealizarAtaque3En(Criatura otro);

    public abstract boolean puedeRealizarAtaque4En(Criatura otro);

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public int getDefensa() {
        return this.defensa;
    }


    public void setDebilidades(String[] debilidades) {
        this.debilidades = debilidades; 

    }

    public String[] getDebilidades() {
        return this.debilidades; 
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
        " - Ataque: " + this.ataqueAtributo + "\n" +
        " - Defensa: " + this.getDefensa() + "\n" +
        " - Velocidad: " + this.getVelocidad() + "\n" +
        "- Debilidades: " + Arrays.toString(this.getDebilidades());
    }

    public void crearArrayDeAtaques() {
        ataques = new Ataque[this.cantAtaques];
    }

}
