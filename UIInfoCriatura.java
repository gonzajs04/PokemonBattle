import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class UIInfoCriatura extends Actor {
    private static final int MARGIN = 8;
    Criatura criatura;
    int width = 120;

    public UIInfoCriatura(Criatura criatura) {
        this.criatura = criatura;
    }

    @Override
    protected void addedToWorld(World world) {
        actualizar();
    }

    public void actualizar() {
        GreenfootImage imagenNombre = new GreenfootImage(criatura.toString(), 20, Color.BLACK, null);
        GreenfootImage imagenVida = new GreenfootImage(
                " " + criatura.getVida() + " / " + criatura.getVidaMaxima() + " ", 20, Color.BLACK, null);

        double barraWidth = width - MARGIN * 2.5;
        GreenfootImage imagenBarra = new GreenfootImage(width - MARGIN * 2, 12);
        imagenBarra.setColor(Color.RED);

        imagenBarra.fillRect(0, 0, (int) (barraWidth / 2), 11);
        imagenBarra.setColor(Color.BLACK);
        imagenBarra.drawRect(0, 0, (int) barraWidth - 1, 11);

        int height = imagenNombre.getHeight() + imagenBarra.getHeight() + imagenVida.getHeight();

        GreenfootImage imagen = new GreenfootImage(width, height);

        imagen.setColor(criatura.esEquipo1() ? Color.PINK : Color.BLUE);
        imagen.fill();

        imagen.drawImage(imagenNombre, (width - imagenNombre.getWidth()) / 2, 0);
        imagen.drawImage(imagenBarra, MARGIN, imagenNombre.getHeight());
        imagen.drawImage(imagenVida, (width - imagenVida.getWidth()) / 2,
                imagenNombre.getHeight() + imagenBarra.getHeight());

        setImage(imagen);
    }
}
