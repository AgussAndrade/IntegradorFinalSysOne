package services;

import dtos.AdicionalDTO;
import exceptions.ServiceException;

public interface AdicionalService {
    void ingresarAdicional(AdicionalDTO adicionalDTO) throws ServiceException;
    AdicionalDTO consultarAdicional(Integer id) throws ServiceException;
    void modificarAdicional(AdicionalDTO adicionalDTO) throws ServiceException;
    void eliminarAdicional (Integer id);


}
