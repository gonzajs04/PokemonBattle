import greenfoot.*;
public class BotonReactor {
    Boton boton;
    UIAtaques uiAtaques;
    Criatura criaturaActual;
    int numeroAtaque;

    public BotonReactor(Boton boton, UIAtaques uiAtaques, Criatura criaturaActual, int numeroAtaque) {
        this.boton = boton;
        this.uiAtaques = uiAtaques;
        this.criaturaActual = criaturaActual;
        this.numeroAtaque = numeroAtaque;
        boton.actualizar(this);
    }

    public void run() { //Runnable que se llama en Class Btn
        Ataque ataque = this.criaturaActual.getAtaques()[this.numeroAtaque-1]; //OBTENGO EL ATAQUE DE LA CRIATURA QUE PASAMOS A TRAVES DE LA CLASE UIATAQUES EN LA FUNCION asignarCriaturaActual()
        
        if (uiAtaques.botonSeleccionado == boton) {
            // Si clickeo lo que está seleccionado, lo des-selecciona
            uiAtaques.ataque = null;
            criaturaActual.setEstaPorAtacar(false);
            uiAtaques.resetColorBotones();
            uiAtaques.descripcion.setText(criaturaActual.getStats()); //OBTIENE LAS STATS EN CASO DE QUE SEA DESELECCIONADO 
            uiAtaques.botonSeleccionado = null;
      
        } else {
             uiAtaques.descripcion.setText(this.criaturaActual.getDetallesAtaque()[this.numeroAtaque - 1]);
             criaturaActual.setEstaPorAtacar(true);//LO DEFINO PARA DETERMINAR SI CUANDO CLICKEO EN OTRO POKEMON SE ESTA ATACANDO O NO. EN CASO DE QUE NO ESTE POR ATACAR, SIGNIFICA QUE NO DEBE CAMBIAR DE PERSONAJE.
            // Si clickeo algo NO seleccionado, lo selecciona y prepara el posible ataque
            uiAtaques.ataque = () -> { //Si puede atacar que cambie ataque = true | Solo se verifica cuanddo ataca a otro pokemon
                if (puedeAtacarlo(uiAtaques.ataqueObjetivo)) { //VERIFICO SI PUEDE ATACAR A LA CRIATURA
                    atacarBoton(uiAtaques.ataqueObjetivo,ataque);
                    // pantallaDuelo.turno();
                }
                
            };
            uiAtaques.resetColorBotones();
            boton.actualizar(Color.YELLOW);
            uiAtaques.botonSeleccionado = boton;
        }
    }

    public String getNombreAtaque() {
        return this.criaturaActual.getNombresAtaque()[this.numeroAtaque - 1];
    }

  

    public boolean puedeAtacarlo(Criatura otro) {
        switch (this.numeroAtaque) {
        case 1:
            return this.criaturaActual.puedeRealizarAtaque1En(otro);
        case 2:
            return this.criaturaActual.puedeRealizarAtaque2En(otro);
        case 3:
            return this.criaturaActual.puedeRealizarAtaque3En(otro);
        case 4:
            return this.criaturaActual.puedeRealizarAtaque4En(otro);
        }
        return false;
    }

    public void atacarBoton(Criatura otro, Ataque ataque) {
        this.criaturaActual.atacar(otro,ataque); //LE VOY A PASAR EL ATAQUE A LA CLASE CRIATURA - funcion atacarCriatura POR PARAMETRO PARA HACERLE DAÑO CON EL ATAQUE CORRESPONDIENTE
    }
}
