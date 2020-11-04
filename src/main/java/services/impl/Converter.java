package services.impl;

import dtos.AdicionalDTO;
import dtos.AutoDTO;
import model.adicionales.*;
import model.autos.Auto;
import model.autos.Coupe;
import model.autos.Familiar;
import model.autos.Sedan;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public AdicionalDTO adicionalToDTO(Adicional adicional) {
        AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setPrecioBase(adicional.getPrecioBase());
        adicionalDTO.setTipo(adicional.getClass().getSimpleName());
        return adicionalDTO;
    }

    public Adicional dtoToAdicional(AdicionalDTO adicionalDTO) {
        Adicional adicional = getAdicional(adicionalDTO.getTipo());
        adicional.setPrecioBase(adicionalDTO.getPrecioBase());
        return adicional;
    }
    public Auto dtoToAuto(AutoDTO autoDTO){
        Auto auto = getAuto(autoDTO.getModelo());
        for(AdicionalDTO adicionalDTO : autoDTO.getAdicionales()){
            auto.addAdicional(dtoToAdicional(adicionalDTO));
        }
        auto.setPrecioBase(autoDTO.getPrecioBase());
        auto.setPrecioFinal(autoDTO.getPrecioFinal());
        return auto;
    }
    public AutoDTO autoToDTO(Auto auto){
        AutoDTO autoDTO = new AutoDTO();
        List<AdicionalDTO> adicionalDTOS = new ArrayList<>();
        for(Adicional adicional : auto.getAdicionales()){
            adicionalDTOS.add(adicionalToDTO(adicional));
        }
        autoDTO.setAdicionales(adicionalDTOS);
        autoDTO.setPrecioBase(auto.getPrecioBase());
        autoDTO.setPrecioFinal(auto.getPrecioFinal());
        autoDTO.setModelo(auto.getClass().getSimpleName());
        return autoDTO;
    }
    private Adicional getAdicional(String nameOfClass){
        if(nameOfClass.equals("Airbag")){
            return new Airbag();
        }
        else if(nameOfClass.equals("AireAcondicionado")){
            return new AireAcondicionado();
        }
        else if (nameOfClass.equals("LlantasDeAleacion")){
            return new LlantasDeAleacion();
        }
        else if (nameOfClass.equals("SistemaDeFrenos")){
            return new SistemaDeFrenos();
        }
        return new TechoCorredizo();
    }
    private Auto getAuto(String nameOfClass) {
        if(nameOfClass.equals("Coupe")){
            return new Coupe();
        }
        else if(nameOfClass.equals("Familiar")){
            return new Familiar();
        }
        return new Sedan();
    }
}
