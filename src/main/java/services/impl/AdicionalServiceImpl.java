package services.impl;

import dao.AdicionalDAO;
import dao.impl.AdicionalDAOImpl;
import dtos.AdicionalDTO;

import exceptions.ServiceException;
import services.AdicionalService;

public class AdicionalServiceImpl implements AdicionalService {
    AdicionalDAO adicionalDAO;
    Converter converter;

    public AdicionalServiceImpl(){
        adicionalDAO = new AdicionalDAOImpl();
        converter = new Converter();
    }

    @Override
    public void ingresarAdicional(AdicionalDTO adicionalDTO) throws ServiceException {
        try {
            adicionalDAO.insert(converter.dtoToAdicional(adicionalDTO));


        } catch (Exception e) {
            throw new ServiceException("error en servoce adicional" + e.getMessage());
        }
    }
    @Override
    public AdicionalDTO consultarAdicional(Integer id) throws ServiceException {
        try {
            return converter.adicionalToDTO(adicionalDAO.queryId(id));
        } catch (Exception e) {
            throw new ServiceException("error en servoce adicional" + e.getMessage());
        }
    }
    @Override
    public void modificarAdicional(AdicionalDTO adicionalDTO) throws ServiceException {
        try{
        adicionalDAO.update(converter.dtoToAdicional(adicionalDTO));
        } catch (Exception e) {
            throw new ServiceException("error en servoce adicional" + e.getMessage());
        }
    }

    @Override
    public void eliminarAdicional(Integer id) {
        try {
            adicionalDAO.delete(id);

        } catch (Exception e) {
            try {
                throw new ServiceException("error en servoce adicional" + e.getMessage());
            } catch (ServiceException serviceException) {
                serviceException.printStackTrace();
            }
        }
    }


}
