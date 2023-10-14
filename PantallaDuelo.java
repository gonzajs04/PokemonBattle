import greenfoot.*;

public class PantallaDuelo extends World {
    private Texto turnoTexto;
    private UIAtaques uiAtaques;
    private Criatura[] criaturas = new Criatura[4];

    private int ronda = 0;
    private int turno = 0;


    public PantallaDuelo() {
        super(700, 400, 1);

        agregarCriaturas();

        turnoTexto = new Texto("Ronda 1 | Turno 1", 20, Color.BLACK, Color.WHITE);
        addObject(turnoTexto, turnoTexto.getImage().getWidth() / 2, turnoTexto.getImage().getHeight() / 2);

        uiAtaques = new UIAtaques(criaturas);
        addObject(uiAtaques, 350, 300);

        GreenfootImage imagenFondo = new GreenfootImage("fondo.png");
        getBackground().drawImage(imagenFondo, 0, 0);

        ronda();
    }

    private void agregarCriaturas() {
        criaturas[0] = new Pikachu("Pikachu");
        criaturas[1] = new Squirtle("Squirtle",false);
        criaturas[2] = new Charmander("Charmander", true);
        criaturas[3] = new Mewtwo("Mewtwo", true);
        addObject(criaturas[0], 100, 80);
        addObject(criaturas[1], 240, 80);
        addObject(criaturas[2], 460, 80);
        addObject(criaturas[3], 600, 80);
    }

    private void ronda() {
        ronda++;
        turno();
    }

    public void turno() {
        turno++;
        for (int i = 0; i < criaturas.length; i++) {
            criaturas[i].setVisualSeleccionado(false);
        }

        turnoTexto.actualizarTexto("Ronda " + ronda + " | Turno " + turno);
        uiAtaques.asignarCriaturaActual(criaturas[0]);
    }

    public void click(Criatura c) {
        if(c.getVida()>0){
            uiAtaques.click(c);
           uiAtaques.asignarCriaturaActual(c); //CAMBIAMOS EL PERSONAJE CADA VEZ QUE HACEMOS CLICK
        }
    } 

    public void hover(Criatura c) {
        uiAtaques.hover(c);
    }
}
