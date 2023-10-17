import greenfoot.*;
import java.util.*;

public class PantallaDuelo extends World {
    private Texto turnoTexto;
    private UIAtaques uiAtaques;
    private Criatura[] criaturas = new Criatura[4];
    private Criatura[] equipo1 = new Criatura[2];
    private Criatura[] equipo2 = new Criatura[2];


    private int ronda = 0;
    private int turno = 0;


    public PantallaDuelo() {
        super(700, 400, 1);

        agregarCriaturas();

        turnoTexto = new Texto("Ronda 1 | Turno 1", 20, Color.BLACK, Color.WHITE);
        addObject(turnoTexto, turnoTexto.getImage().getWidth() / 2, turnoTexto.getImage().getHeight() / 2);

        uiAtaques = new UIAtaques(criaturas);
        addObject(uiAtaques, 350, 300);

        GreenfootImage imagenFondo = new GreenfootImage("fondo2.png"); //SE CAMBIA EL FONDO
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
    
     private void asignarEquipos(){
          int contEq1 = 0; //DECLARO ESTAS 2 VARIABLES PARA LLEGAR AL LIMITE DE INTEGRANTE POR EQUIPO, LA CUAL ES 2. SI USO I, ME LLEVARA AL INDICE POR ENCIMA DE 2 Y NOS DARA ERROR
          int contEq2 = 0;
         for(int i = 0; i<criaturas.length; i++){
             if(criaturas[i].getEquipo()){ //DETERMINO SI ES DEL EQUIPO 1
                 equipo1[contEq1] = criaturas[i]; //SI ES DEL EQUIPO 1 LE ASIGNO LA CRIATURA
                 contEq1++; //SUMO EL CONTADOR
            }else{
                equipo2[contEq2] = criaturas[i];
                 contEq2++;  

             }
        }
        

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
        if(!c.getEstaDesmayado()){
           uiAtaques.click(c);
           uiAtaques.asignarCriaturaActual(c); //CAMBIAMOS EL PERSONAJE CADA VEZ QUE HACEMOS CLICK
        }
    } 

    public void hover(Criatura c) {
        uiAtaques.hover(c);
    }
}
