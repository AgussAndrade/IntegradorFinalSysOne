package model.adicionales;

public class TechoCorredizo implements Adicional {
    private double precioBase;
    private Integer id;
    public TechoCorredizo(){
        precioBase = 12000;
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
