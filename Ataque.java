import greenfoot.*;
public class Ataque extends Actor
{
    private int dañoMin;
    private int dañoMax;
    private String tipo;
    private int cantEnergiaNecesaria;
    private String nombre;
    private int probabilidadGolpeCritico;
    public Ataque(String nombre,int dañoMin,int dañoMax,String tipo, int cantEnergiaNecesaria, int probabilidadGolpeCritico){
        this.dañoMin = dañoMin;
        this.dañoMax = dañoMax;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantEnergiaNecesaria = cantEnergiaNecesaria;
        this.probabilidadGolpeCritico = probabilidadGolpeCritico;
    }

    public int getDañoMin(){
        return this.dañoMin;
    }
    public int getProbabilidadGolpeCritico(){
        return this.probabilidadGolpeCritico;
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
    public String getNombre(){
        return this.nombre;
    }
    


}
