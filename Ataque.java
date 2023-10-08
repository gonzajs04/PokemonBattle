import greenfoot.*;
public class Ataque extends Actor
{

    private int daño;
    private String tipo;
    private int cantEnergiaNecesaria;
    public Ataque(int daño,String tipo, int cantEnergiaNecesaria){
        this.daño = daño;
        this.tipo = tipo;
        this.cantEnergiaNecesaria = cantEnergiaNecesaria;
    }

    public int getDaño(){
        return this.daño;
    }
    public int getEnergiaNecesaria(){
        return this.cantEnergiaNecesaria;
    }
    public String getTipo(){
        return tipo;
    }
    


}
