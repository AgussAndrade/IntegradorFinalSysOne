package model.adicionales;

public class Airbag implements Adicional {
    private double precioBase;
    private Integer id;
    public Airbag(){
        precioBase = 7000;
    }


    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
