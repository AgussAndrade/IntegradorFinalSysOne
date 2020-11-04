package dtos;

import model.adicionales.Adicional;

import java.util.List;

public class AutoDTO {
    private double precioBase;
    private double precioFinal;
    private List<AdicionalDTO> adicionales;
    private String modelo;

    public List<AdicionalDTO> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<AdicionalDTO> adicionales) {
        this.adicionales = adicionales;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
