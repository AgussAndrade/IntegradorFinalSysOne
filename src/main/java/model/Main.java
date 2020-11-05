package model;

import dao.AutoDAO;
import dao.impl.AutoDAOImpl;
import dtos.AdicionalDTO;
import dtos.AutoDTO;
import exceptions.DAOException;
import model.adicionales.Airbag;
import model.adicionales.AireAcondicionado;
import model.autos.Auto;
import model.autos.Coupe;
import model.autos.Sedan;
import services.AutoService;
import services.impl.AutoServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws DAOException {
        AutoService autoService = new AutoServiceImpl();
        try{
           // System.out.print(autoService.consultarAuto(1).getModelo());
//            autoService.eliminarAuto(5);
//            AutoDTO autoDTO = new AutoDTO();
//            autoDTO.setModelo("Coupe");
//            List<AdicionalDTO> adicionalDTOS = new ArrayList<>();
//            AdicionalDTO adicionalDTO = new AdicionalDTO();
//            adicionalDTO.setTipo("Airbag");
//            adicionalDTOS.add(adicionalDTO);
//            adicionalDTO = new AdicionalDTO();
//            adicionalDTO.setTipo("LlantasDeAleacion");
//            adicionalDTOS.add(adicionalDTO);
//            autoDTO.setAdicionales(adicionalDTOS);
//            autoService.ingresarAuto(autoDTO);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
