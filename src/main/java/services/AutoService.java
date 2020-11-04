package services;

import dtos.AutoDTO;
import exceptions.ServiceException;
import model.autos.Auto;

import java.util.List;

public interface AutoService {
    public void ingresarAuto(AutoDTO autoDTO) throws ServiceException;
    public AutoDTO consultarAuto(Integer id) throws ServiceException;
    public void modificarAuto(AutoDTO autoDTO) throws ServiceException;
    public void eliminarAuto (Integer id) throws ServiceException;
    public void precioFinal(AutoDTO autoDTO);
    public List<AutoDTO> consultarAutos() throws ServiceException;
}
