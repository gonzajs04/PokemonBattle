import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Texto extends Actor {
    String texto;
    int tam;
    Color colorFrente;
    Color colorFondo;
    Color colorBorde;

    public Texto(String texto, int tam, Color colorFrente, Color colorFondo) {
        this(texto, tam, colorFrente, colorFondo, null);
    }

    public Texto(String texto, int tam, Color colorFrente, Color colorFondo, Color colorBorde) {
        this.texto = texto;
        this.tam = tam;
        this.colorFrente = colorFrente;
        this.colorFondo = colorFondo;
        this.colorBorde = colorBorde;

        render();
    }

    public void actualizarTexto(String texto) {
        this.texto = texto;

        render();
    }

    private void render() {
        GreenfootImage txtImg = new GreenfootImage(" " + texto + " ", tam, colorFrente, colorFondo, colorBorde);

        setImage(txtImg);
    }
}
