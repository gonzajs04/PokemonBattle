import greenfoot.*;
public class Ataque extends Actor
{

    private int dañoMin;
        private int dañoMax;

    private String tipo;
    private int cantEnergiaNecesaria;
    public Ataque(int dañoMin,int dañoMax,String tipo, int cantEnergiaNecesaria){
        this.dañoMin = dañoMin;
        this.dañoMax = dañoMax;
        this.tipo = tipo;
        this.cantEnergiaNecesaria = cantEnergiaNecesaria;
    }

    public int getDañoMin(){
        return this.dañoMin;
    }
    public int getDañoMax(){
        return this.dañoMax;
    }
    public int getEnergiaNecesaria(){
        return this.cantEnergiaNecesaria;
    }
    public String getTipo(){
        return tipo;
    }
    


}
