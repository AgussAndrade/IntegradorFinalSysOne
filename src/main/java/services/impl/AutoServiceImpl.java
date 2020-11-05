package services.impl;

import dao.AutoDAO;
import dao.impl.AutoDAOImpl;
import dtos.AdicionalDTO;
import dtos.AutoDTO;

import exceptions.ServiceException;
import model.adicionales.Adicional;
import model.autos.Auto;
import services.AutoService;

import java.util.ArrayList;
import java.util.List;


public class AutoServiceImpl implements AutoService {
    AutoDAO autoDAO;
    Converter converter;

    public AutoServiceImpl(){
        autoDAO = new AutoDAOImpl();
        converter = new Converter();
    }

    @Override
    public void ingresarAuto(AutoDTO autoDTO) throws ServiceException {
        try{
        precioFinal(autoDTO);
        autoDAO.insert(converter.dtoToAuto(autoDTO));
        } catch (Exception e) {
            throw new ServiceException("error en service auto" + e.getMessage());
        }
    }

    @Override
    public AutoDTO consultarAuto(Integer id) throws ServiceException {
        try{return converter.autoToDTO(autoDAO.queryId(id));
        } catch (Exception e) {
            throw new ServiceException("error en service auto" + e.getMessage());
        }
    }

    @Override
    public void modificarAuto(AutoDTO autoDTO) throws ServiceException {
        try{autoDAO.update(converter.dtoToAuto(autoDTO));
        } catch (Exception e) {
            throw new ServiceException("error en service auto" + e.getMessage());
        }
    }

    @Override
    public void eliminarAuto(Integer id) throws ServiceException {
        try{autoDAO.delete(id);

        } catch (Exception e) {
            throw new ServiceException("error en service auto" + e.getMessage());
        }
    }

    @Override
    public void precioFinal(AutoDTO autoDTO) {
        double toReturn =0;
        Auto auto = converter.dtoToAuto(autoDTO);
        for(Adicional adicional : auto.getAdicionales()){
            toReturn += adicional.getPrecioBase();
        }
        toReturn += auto.getPrecioBase();
        autoDTO.setPrecioFinal(toReturn);
        autoDTO.setPrecioBase(auto.getPrecioBase());
    }

    @Override
    public List<AutoDTO> consultarAutos() throws ServiceException {
        try{List<Auto> autos = autoDAO.query();
        List<AutoDTO> autoDTOS = new ArrayList<>(autos.size());
        for(Auto auto : autos){
            autoDTOS.add(converter.autoToDTO(auto));
        }
        return autoDTOS;
    } catch (Exception e) {
            throw new ServiceException("error en service auto" + e.getMessage());
        }
    }

}
