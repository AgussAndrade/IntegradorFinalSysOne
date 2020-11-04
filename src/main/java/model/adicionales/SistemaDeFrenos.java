package model.adicionales;

public class SistemaDeFrenos implements Adicional{
    private double precioBase;
    private Integer id;
    public SistemaDeFrenos(){
        precioBase = 14000;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}
