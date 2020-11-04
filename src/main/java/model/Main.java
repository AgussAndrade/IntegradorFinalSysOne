package model;

import dao.AutoDAO;
import dao.impl.AutoDAOImpl;
import exceptions.DAOException;
import model.adicionales.Airbag;
import model.adicionales.AireAcondicionado;
import model.autos.Auto;
import model.autos.Coupe;
import model.autos.Sedan;


public class Main {
    public static void main(String[] args) throws DAOException {
        AutoDAO autoDAO = new AutoDAOImpl();
        Auto auto = new Sedan();
        auto.addAdicional(new AireAcondicionado());
        auto.addAdicional(new Airbag());
        autoDAO.insert(auto);
    }
}
