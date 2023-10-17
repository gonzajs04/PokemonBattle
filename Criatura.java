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
    // private boolean estaAtacando = false;
    private final MyGreenfootImage imagenOriginal;
    private int velocidad;
    private int defensa;
    private int ataqueAtributo;
    private String[] debilidades;
    // private boolean isResistente = false;
    // private boolean tieneDebilidad = false;
    private String tipo;
    
    private static int contMuertosEquipo1 = 0;
    private static int contMuertosEquipo2 = 0;

  

    public Criatura(String nombre, int vida, String[] nombresAtaque, boolean equipo1, String[] detallesAtaque,
            int cantAtaques, String tipo) {
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
        crearArrayDeAtaques();
    }

    public Ataque[] getAtaques() {
        return this.ataques;
    }

    public String getTipo() {
        return this.tipo;
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

    // public boolean getEstaAtacando() {
    // return estaAtacando;
    // }

    // public void setEstaAtacando(boolean estaAtacando) {
    // this.estaAtacando = estaAtacando;
    // }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
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

    public void setEstaParalizado(Criatura nombre) {
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

    protected int atacar(Criatura otro, Ataque ataque) {
                        System.out.println(this.getNombre() + " "+ this.estaDesmayado);

        if (!this.estaDesmayado && !otro.estaDesmayado) {
            Random rand = new Random();
            double numeroAleatorio = 0.5 + rand.nextDouble() * (1.25 - 0.5);

            double factor = verificarFactorCriatura(otro, ataque); // LE PASO EL OPONENTE Y EL ATAQUE QUE SE ESTA
                                                                   // REALIZANDO PARA SABER SI LO TIENE COMO DEBILIDAD
            double daño = 2 * (1 + (otro.ataqueAtributo / otro.defensa) * factor * numeroAleatorio); // FALTA EL FACTOR
                                                                                                     // TIPO
            double dañoGolpeCritico = verificarGolpeCritico(daño, ataque); // VERIFICO SI CABE LA POSIBILIDAD DE TENER
                                                                           // UN GOLPE CRITICO
            double dañoFinal = Math.round(daño) + dañoGolpeCritico;
            if(dañoFinal>otro.vida){ //SI EL DAÑO ES MAYOR A LA VIDA DEL RIVAL, QUE SETEE LA VIDA EN 0 PARA QUE ASI NO DE UN NUMERO NEGATIVO
                otro.setVida(0);
                System.out.println(otro.getNombre() + " "+ otro.estaDesmayado);
            }else{
                otro.vida -= dañoFinal;
                  System.out.println("El pokemon " + this.getNombre() + " Ataco con " + ataque.getNombre() + " y quito "
                    + dañoFinal + " de vida a " + otro.getNombre());

            }


        }
        desmayar(otro); //UNA VEZ VERIFICADA LA VIDA, VERIFICO SI EL POKEMON ESTA LISTO PARA DESMAYARSE
        otro.uiInfoCriatura.actualizar(); // Actualizo la info del rival
        return otro.vida;
    }
    public void desmayar(Criatura otro){
        if(otro.getVida()<=0){
            otro.setEstaDesmayado(true); // SE DESMAYO
        }
    }

    public double verificarGolpeCritico(double daño, Ataque ataque) {
        double posibleCritico = 0;
        double golpeCritico = 0; // LO ESTABLEZCO EN 0 POR SI NO HAY GOLPE CRITICO
        Random rd = new Random();
        posibleCritico = rd.nextInt(101); // Calculo un random del 0 al 100 pra el golpe critico
        if (ataque.getProbabilidadGolpeCritico() > posibleCritico) { // Si el random es menor que la probabiliad del
                                                                     // critico del ataque, se genera el GolpeCritico
            golpeCritico = calcularGolpeCritico(daño);
            System.out.println("Se produjo un golpe critico de parte de " + this.getNombre());
        }
        return golpeCritico;
    }

    public double calcularGolpeCritico(double daño) {
        return daño + (daño * 0.5);
    }

 

    public double verificarFactorCriatura(Criatura oponente, Ataque ataque) {
        double factor = 1; // EN CASO DE QUE NO TENGA DEBILIDAD EL FACTOR ES 1
        for (int i = 0; i < oponente.getDebilidades().length; i++) {
            if (oponente.getDebilidades()[i] == ataque.getTipo()) { // ME FIJO SI TIENE DEBILIDADES AL ATAQUE
                // EN CASO DE QUE SI, EL FACTOR EL 1.25
                System.out.println("El pokemon que estas atacando tiene una debilidad hacia el ataque: "
                        + ataque.getTipo() + " , el daño sera de 1.25 mayor");
                factor = 1.25;
            } else if (oponente.getTipo() == ataque.getTipo()) { // En caso de que no tenga debilidad, me fijo, si es
                                                                 // tipo del oponente es igual al tipo de ataque. En
                                                                 // caso de que si, tiene un factor de 0.75
                System.out.println("El pokemon que estas atacando tiene una resistencia hacia el ataque: "
                        + ataque.getTipo() + " , el daño sera de 0.75 mayor");

                factor = 0.75;
            }
        }
        // en caso que no tenga no tenga debilidades ni resistencias el factor es de 1
        return factor;
    }

    protected boolean esDelMismoEquipoQue(Criatura otro) {
        return this.equipo1 == otro.equipo1;
    }

    public boolean puedeRealizarAtaque1En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public boolean getEquipo() {
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

    public boolean esEquipo1() {
        return equipo1;
    }

    public void setDebilidades(String[] debilidades) {
        this.debilidades = debilidades; // LE SETEO LAS DEBILIDADES DEL POKEMON

    }

    public String[] getDebilidades() {
        return this.debilidades; // Obtengo las debilidades
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

                " - Defensa: " + getDefensa() + "\n" +
                " - Velocidad: " + getVelocidad() + "\n" +
                "- Debilidades: " + Arrays.toString(getDebilidades()); // Las convierto para mostrarlas como texto
    }

    public void crearArrayDeAtaques() {
        ataques = new Ataque[this.cantAtaques];
    }

}
