package dao.impl;

import dao.AutoAdicionalDAO;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoAdicionalDAOImpl implements AutoAdicionalDAO {
    SQLManager sqlManager;
    public AutoAdicionalDAOImpl() {
        sqlManager = new SQLManager();

    }
    @Override
    public void insert(Integer idAdicional, Integer idAuto) throws DAOException {
        String query = "insert into auto_adicional (id_adicional, id_auto) values ('"+
                        idAdicional + "','" + idAuto + "')";
        sqlManager.execute(query);
    }

    @Override
    public void delete(Integer id) throws DAOException {
        String query = "delete from auto_adicional where id_auto = " + id;
        sqlManager.execute(query);
    }

    @Override
    public List<Integer> queryForAuto(Integer idAuto) throws DAOException {
        String query = "select * from auto_adicional where id_auto = " + idAuto;
        List<Integer> keysAdicional = new ArrayList<>();
        Connection conn = sqlManager.getConnection();
        try{
            ResultSet rs = null;

            Statement sentencia = conn.createStatement();

            rs = sentencia.executeQuery(query);

            while(rs.next()) {

                keysAdicional.add(rs.getInt("id_adicional"));
                System.out.println(rs.getInt("id_adicional"));
            }
        }
        catch (Exception e){
            throw new DAOException("error al hacer queryforauto en autoadicional" + e.getMessage());
        }
        sqlManager.closeConnection(conn);
        return keysAdicional;
    }

    @Override
    public void update(Integer idAdicional, Integer idAuto) throws DAOException {
        String query = "update into auto_adicional set id_adicional = " + idAdicional +
                "where id_auto=" + idAuto ;
        sqlManager.execute(query);
    }

}
