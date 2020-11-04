package dao;

import exceptions.DAOException;
import model.adicionales.Adicional;

import java.util.List;

public interface AutoAdicionalDAO {
    public void insert(Integer idAdicional, Integer idAuto) throws DAOException;
    public void delete(Integer idAuto) throws DAOException;
    public List<Integer> queryForAuto(Integer idAuto) throws DAOException;

    void update(Integer idAdicional, Integer id) throws DAOException;
}
