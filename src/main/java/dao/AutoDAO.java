package dao;

import exceptions.DAOException;
import model.autos.Auto;

import java.util.List;

public interface AutoDAO {
    //que este dao use el de los model.adicionales no toques otra cosa
    public void insert(Auto object) throws DAOException;
    public void update(Auto object) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public Auto queryId(Integer id) throws DAOException;
    public List<Auto> query() throws DAOException;
}
