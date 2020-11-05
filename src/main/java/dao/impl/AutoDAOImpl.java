package dao.impl;

import dao.AdicionalDAO;
import dao.AutoAdicionalDAO;
import dao.AutoDAO;
import exceptions.DAOException;
import model.adicionales.*;
import model.autos.Auto;
import model.autos.Coupe;
import model.autos.Familiar;
import model.autos.Sedan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoDAOImpl implements AutoDAO {
    SQLManager sqlManager;
    AdicionalDAO adicionalDAO;
    AutoAdicionalDAO autoAdicionalDAO;
    public AutoDAOImpl(){
        adicionalDAO = new AdicionalDAOImpl();
        sqlManager = new SQLManager();
        autoAdicionalDAO = new AutoAdicionalDAOImpl();
    }
    @Override
    public void insert(Auto object) throws DAOException {
        try{
            String query ="insert into Auto (modelo,precio_base,precio_total) values('" +
                            object.getClass().getSimpleName() + "','" +
                            object.getPrecioBase() + "','" +
                            object.getPrecioFinal() + "')";
            Integer idAuto = sqlManager.executeWithGeneratedKeys(query);


            List<Adicional> adicionals = object.getAdicionales();
            for(Adicional a : adicionals){
                Integer idAdicional = adicionalDAO.insert( a);
                autoAdicionalDAO.insert(idAdicional,idAuto);

            }

        } catch (Exception e) {
            throw new DAOException("error en dao auto" + e.getMessage());
        }
    }

    @Override
    public void update(Auto object) throws DAOException {
        try {
            String query = "update Auto set modelo ='" +
                    object.getClass().getSimpleName() + "'," +
                    "set precio_base = '" + object.getPrecioBase() + "'," +
                    "set precio_total = '" + object.getPrecioFinal() +
                    "' where id = '" + object.getId() + "'";
            List<Adicional> adicionals = object.getAdicionales();
            for (Adicional a : adicionals) {
                Integer idAdicional = adicionalDAO.insert(a);
                autoAdicionalDAO.update(idAdicional, object.getId());

            }
            sqlManager.execute(query);

        } catch (Exception e) {
            throw new DAOException("error en dao auto" + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {
        System.out.println("hola");
        List<Integer> keysAdicional = autoAdicionalDAO.queryForAuto( id);
        System.out.println("como");
        autoAdicionalDAO.delete(id);
        System.out.println("holas");
        for(Integer key : keysAdicional){
            System.out.println("holasa");
            adicionalDAO.delete(key);
        }
        System.out.println("holaasdsadas");
        String queryAuto = "delete from auto where id = " + id;
        sqlManager.execute(queryAuto);
    }

    @Override
    public Auto queryId(Integer id) throws DAOException {
        String query = "select * from auto where id=" + id;
        Auto auto = null;
        try {
            Connection conn = sqlManager.getConnection();
            ResultSet rs = null;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            rs = sentencia.getResultSet();

            if (rs.next()) {
                auto = getAuto(rs.getString("modelo"));
                auto.setId(rs.getInt("id"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setPrecioFinal(rs.getDouble("precio_total"));
            }
            List<Integer> keysAdicional = autoAdicionalDAO.queryForAuto(id);
            for (Integer key : keysAdicional) {
                auto.addAdicional(adicionalDAO.queryId(key));
            }
            sqlManager.closeConnection(conn);
        }catch(Exception e){
                throw new DAOException("error en auto DAO " + e.getMessage());
            }

        return auto;
    }

    @Override
    public List<Auto> query() throws DAOException {
        String query = "select * from auto ";

        List<Auto> autos = new ArrayList<>();
        try {
            Connection conn = sqlManager.getConnection();
            ResultSet rs = null;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            rs = sentencia.getResultSet();

            Auto auto = null;
            while (rs.next()) {
                auto = getAuto(rs.getString("modelo"));
                Integer id = rs.getInt("id");
                auto.setId(id);
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setPrecioFinal(rs.getDouble("precio_total"));

                List<Integer> keysAdicional = autoAdicionalDAO.queryForAuto(id);
                for (Integer key : keysAdicional) {
                    auto.addAdicional(adicionalDAO.queryId(key));
                }
                autos.add(auto);
                sqlManager.closeConnection(conn);
            }
        } catch (Exception e) {
            throw new DAOException("error en autoDao" + e.getMessage());
        }
        return autos;
    }

    private Auto getAuto(String nameOfClass) {
        if(nameOfClass.equals("Coupe")){
            return new Coupe();
        }
        else if(nameOfClass.equals("Familiar")){
            return new Familiar();
        }
        return new Sedan();
    }
}
