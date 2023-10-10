import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class UIAtaques extends Actor {
    Criatura criaturaActual;
    Texto tituloAtaques;
    Boton b1, b2, b3, b4;
    Criatura[] criaturas;
    Boton botonSeleccionado;
    Parrafo descripcion;
    Runnable ataque = null;
    Criatura ataqueObjetivo = null;
    Criatura hoverObjetivo = null;

    public UIAtaques(Criatura[] criaturas) {
        this.criaturas = criaturas;
        b1 = new Boton("", null, 25, Color.BLACK, 320, 30);
        b2 = new Boton("", null, 25, Color.BLACK, 320, 30);
        b3 = new Boton("", null, 25, Color.BLACK, 320, 30);
        b4 = new Boton("", null, 30, Color.BLACK, 320, 36);
        descripcion = new Parrafo("Ataque", 20, Color.BLACK, 344, 192);
        tituloAtaques = new Texto("Ataques", 30, Color.BLACK, null);
    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(tituloAtaques, 170, 225);
        getWorld().addObject(b1, 176, 265);
        getWorld().addObject(b2, 176, 300);
        getWorld().addObject(b3, 176, 335);
        getWorld().addObject(b4, 176, 373);
        getWorld().addObject(descripcion, 352 + 344 / 2, 300);
    }

    public void asignarCriaturaActual(Criatura criaturaActual) {
        this.criaturaActual = criaturaActual;

        tituloAtaques.actualizarTexto(criaturaActual.toString());
        descripcion.setText("");
        botonSeleccionado = null;
        ataque = null;
        resetColorBotones();
        criaturaActual.setVisualSeleccionado(true);

        dibujarFondo();

        Boton[] botones = { b1, b2, b3, b4 };

        for (int i = 0; i < botones.length; i++) {
            new BotonReactor(botones[i], this, criaturaActual, i + 1); //LE PASO LA CRIATURA ACTUAL PARA OBTENER LUEGO LOS ATAQUES
        }
    }

    public void click(Criatura c) {
        ataqueObjetivo = c;
        if (botonSeleccionado == null) {
            descripcion.setText(c.getStats());
        }
        if (ataque != null) {
            ataque.run();
        }
    }

    public void hover(Criatura c) {
        if (hoverObjetivo == c) {
            return;
        }

        hoverObjetivo = c;
        if (botonSeleccionado == null) {
            // descripcion.actualizarTexto(c.toString());
        }
    }

    void resetColorBotones() {
        b1.actualizar(Color.LIGHT_GRAY);
        b2.actualizar(Color.LIGHT_GRAY);
        b3.actualizar(Color.LIGHT_GRAY);
        b4.actualizar(Color.LIGHT_GRAY);
    }

    private void dibujarFondo() {
        GreenfootImage imagenBarra = new GreenfootImage(700, 200);
        imagenBarra.setColor(Color.BLACK);
        imagenBarra.fill();
        imagenBarra.setColor(criaturaActual.esEquipo1() ? Color.RED : Color.BLUE);
        imagenBarra.fillRect(4, 4, 344, 192);
        imagenBarra.setColor(Color.LIGHT_GRAY);
        imagenBarra.fillRect(352, 4, 344, 192);
        setImage(imagenBarra);
    }

}
