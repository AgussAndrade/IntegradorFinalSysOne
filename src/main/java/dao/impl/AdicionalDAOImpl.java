package dao.impl;

import exceptions.DAOException;
import model.adicionales.*;
import dao.AdicionalDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdicionalDAOImpl implements AdicionalDAO {
    SQLManager sqlManager;

    public AdicionalDAOImpl(){
        sqlManager = new SQLManager();
    }
    @Override
    public Integer insert(Adicional object) throws DAOException {
        String query = "insert into adicional (tipo,precio) values ( '" +
                                                object.getClass().getSimpleName() + "','"
                                                + object.getPrecioBase() +
                                                "')";
        return sqlManager.executeWithGeneratedKeys(query);

    }

    @Override
    public void update(Adicional object) throws DAOException {
        String query = "update adicional "
                        + " set tipo = " + object.getClass().getSimpleName() +
                        ", set precio = " + object.getPrecioBase()
                        + "where id = " +  object.getId();

        sqlManager.getConnection();
    }


    @Override
    public void delete(Integer id) throws DAOException {
        String query = "delete * from adicional where id =" + id;
        sqlManager.execute(query);
    }

    @Override
    public Adicional queryId(Integer id) throws DAOException {
        String query = "select * from adicional where id=" + id;
        Connection conn = sqlManager.getConnection();
        Adicional adicional = null;
        try{
            ResultSet rs = null;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            rs = sentencia.getResultSet();

            if(rs.next()){
                adicional = getAdicional(rs.getString("tipo")) ;
                adicional.setId(rs.getInt("id"));
                adicional.setPrecioBase(rs.getDouble("precio"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        sqlManager.closeConnection(conn);
        return adicional;
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
}
