package dao;

import exceptions.DAOException;
import model.adicionales.Adicional;

public interface AdicionalDAO  {
    public Integer insert(Adicional object) throws DAOException;
    public void update(Adicional object) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public Adicional queryId(Integer id) throws DAOException;
}
