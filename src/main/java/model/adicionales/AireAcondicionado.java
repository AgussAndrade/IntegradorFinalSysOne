package model.adicionales;

public class AireAcondicionado implements Adicional {
    private double precioBase;
    private Integer id;
    public AireAcondicionado(){
        this.precioBase =20000;
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
