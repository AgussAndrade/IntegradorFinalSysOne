package dao.impl;

import exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLManager {

    public void closeConnection(Connection conn) throws DAOException {

        try {
            conn.close();
        }catch (Exception ex){

            throw new DAOException(ex.getMessage());
        }
    }
    public Integer executeWithGeneratedKeys(String str) throws DAOException {
        Connection conn = this.getConnection();
        ResultSet rs = null;
        Integer id =0;
        try{

            Statement sentencia = conn.createStatement();
            sentencia.executeUpdate(str, Statement.RETURN_GENERATED_KEYS );
            rs = sentencia.getGeneratedKeys();


            if (rs.next()) {
                id = rs.getInt(1);
            }

        }catch (Exception ex){

            throw new DAOException(ex.getMessage());
        }finally {

            closeConnection(conn);
            return id;
        }

    }
    public void execute(String str) throws DAOException {


        Connection conn = this.getConnection();

        try{

            Statement sentencia = conn.createStatement();
            sentencia.execute(str);

        }catch (Exception ex) {

            throw new DAOException(ex.getMessage());
        }finally {

            closeConnection(conn);
        }


    }
    public Connection getConnection() throws DAOException {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/fabrica_de_autos";
            String usuario = "root";
            String clave = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, clave);

        }catch (Exception ex){

            throw new DAOException(ex.getMessage());
        }

        return connection;
//        Connection conn = this.getConnection();
//        ResultSet rs = null;
//        try{
//
//
//            Statement sentencia = conn.createStatement();
//            sentencia.execute(str);
//
//            rs = sentencia.getResultSet();
//
//        }catch (Exception ex){
//
//            ex.printStackTrace();
//        }finally {
//
//            closeConnection(conn);
//
//            return rs;
//        }

    }
}
