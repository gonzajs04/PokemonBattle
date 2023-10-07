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

    public void run() {
        // PantallaDuelo pantallaDuelo = ((PantallaDuelo) uiAtaques.getWorld());

        if (uiAtaques.botonSeleccionado == boton) {
        	// Si clickeo lo que está seleccionado, lo des-selecciona
            uiAtaques.ataque = null;
            uiAtaques.resetColorBotones();
            uiAtaques.descripcion.setText("");
            uiAtaques.botonSeleccionado = null;
        } else {
        	// Si clickeo algo NO seleccionado, lo selecciona y prepara el posible ataque
            uiAtaques.ataque = () -> {
                if (puedeAtacarlo(uiAtaques.ataqueObjetivo)) {
                    atacar(uiAtaques.ataqueObjetivo);
                    // pantallaDuelo.turno();
                }
            };
            uiAtaques.resetColorBotones();
            boton.actualizar(Color.YELLOW);
            uiAtaques.descripcion.setText(getDetalleAtaque(criaturaActual));
            uiAtaques.botonSeleccionado = boton;
        }
    }

    public String getNombreAtaque() {
        return this.criaturaActual.getNombresAtaque()[this.numeroAtaque - 1];
    }

    public String getDetalleAtaque(Criatura c) {
        return this.criaturaActual.getDetallesAtaque()[this.numeroAtaque - 1];
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

    public void atacar(Criatura otro) {
        switch (this.numeroAtaque) {
		case 1:
		    this.criaturaActual.atacar1(otro);
		    break;
		case 2:
		    this.criaturaActual.atacar2(otro);
		    break;
		case 3:
		    this.criaturaActual.atacar3(otro);
		    break;
		case 4:
		    this.criaturaActual.atacar4(otro);
		    break;
		}
    }
}
