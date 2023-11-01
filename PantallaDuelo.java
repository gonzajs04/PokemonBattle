import greenfoot.*;
import java.util.*;

public class PantallaDuelo extends World {
    private Texto turnoTexto;
    private UIAtaques uiAtaques;
    private Criatura[] criaturas = new Criatura[4];
    private Criatura[] criaturasOrdenadasPorVelocidad = new Criatura[4];
    private int ronda = 0;
    private int turno = 0;
    private int criaturaActualIndex =0;
    private int MAX_CRIATURAS_A_GANAR = 2;
    public PantallaDuelo() {
        super(700, 400, 1);

        agregarCriaturas();

        turnoTexto = new Texto("Ronda " + ronda +" | " + "Turno " + turno, 20, Color.BLACK, Color.WHITE);
        addObject(turnoTexto, turnoTexto.getImage().getWidth() / 2, turnoTexto.getImage().getHeight() / 2);

        uiAtaques = new UIAtaques(criaturas);
        addObject(uiAtaques, 350, 300);

        GreenfootImage imagenFondo = new GreenfootImage("fondo2.png"); //SE CAMBIA EL FONDO
        getBackground().drawImage(imagenFondo, 0, 0);  

        uiAtaques.asignarCriaturaActual(criaturas[criaturaActualIndex]);
        crearCriaturasParaOrdenarPorVelocidad();

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

    }

    public void generarPantallaGanador(){
        Greenfoot.setWorld(new PantallaGanador());
    }

    public void turno() {
        if(turno < 3){ // 4 vuelve a estar en pikachu
            turno++;
            for (int i = 0; i < criaturas.length; i++) {
                criaturas[i].setVisualSeleccionado(false);
            }

        }else{
            turno = 0;
            ronda();
        }
        turnoTexto.actualizarTexto("Ronda " + ronda + " | Turno " + turno);

    }

    public void click(Criatura c) {

        uiAtaques.click(c);

        criaturaActualIndex = (criaturaActualIndex + 1) % 4; //EJ (3+1)% 4 = 0; ||||||||||||| (0+1)%4 = 1 residuo == NO ES DIVISIBLE POR 4
        uiAtaques.asignarCriaturaActual(criaturasOrdenadasPorVelocidad[criaturaActualIndex]);
        turno();
        verificarSiHayGanador();

    } 

    public void verificarSiHayGanador(){
        if(Criatura.getContadorEquipo1() == MAX_CRIATURAS_A_GANAR || Criatura.getContadorEquipo2() == MAX_CRIATURAS_A_GANAR){
            System.out.println("Hay un ganador");
            Criatura.setContadorEquipo1(0);
            Criatura.setContadorEquipo2(0);
            generarPantallaGanador();

        }
    }

    public void crearCriaturasParaOrdenarPorVelocidad(){
        for( int i=0; i<criaturas.length;i++){
            criaturasOrdenadasPorVelocidad[i] = criaturas[i];
        }
        ordenarCriaturasPorVelocidad();

    }

    public void ordenarCriaturasPorVelocidad(){
        int i=0;
        int j=0;
        Criatura aux = null;
        for(i=0; i<criaturasOrdenadasPorVelocidad.length;i++){
            for(j=0; j<criaturasOrdenadasPorVelocidad.length-1;j++){

                if(criaturasOrdenadasPorVelocidad[j].getVelocidad()<criaturasOrdenadasPorVelocidad[j+1].getVelocidad()){
                    aux = criaturasOrdenadasPorVelocidad[j];
                    criaturasOrdenadasPorVelocidad[j] = criaturasOrdenadasPorVelocidad[j+1];
                    criaturasOrdenadasPorVelocidad[j+1]=aux;
                }

            }
        }

    }

    public void hover(Criatura c) {
        uiAtaques.hover(c);
    }
}
