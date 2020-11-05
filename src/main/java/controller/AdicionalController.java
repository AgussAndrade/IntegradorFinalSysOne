package controller;

import dtos.AdicionalDTO;
import exceptions.ControllerException;
import services.AdicionalService;
import services.impl.AdicionalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdicionalController extends HttpServlet {
    AdicionalService adicionalService;

    public AdicionalController(){
        super();
        adicionalService = new AdicionalServiceImpl();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setTipo(req.getParameter("tipo"));
        adicionalDTO.setPrecioBase(Integer.parseInt(req.getParameter("precioBase")));
        try {
            adicionalService.ingresarAdicional(adicionalDTO);

        }
        catch (Exception e){
            throw new ControllerException("error al post adicional" + e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            AdicionalDTO adicionalDTO = adicionalService.consultarAdicional( Integer.parseInt( req.getParameter("id") ) );
            PrintWriter pw = resp.getWriter();

            String json = "{ \n" +
                    "  precioBase : " + String.valueOf(adicionalDTO.getPrecioBase()) + "\n" +
                    "  tipo : " + adicionalDTO.getTipo() + "\n" +
                    "}";
            pw.write(json);
        }        catch (Exception e){
            throw new ControllerException("error al get adicional" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{adicionalService.eliminarAdicional(Integer.parseInt(req.getParameter("id")));
        }        catch (Exception e){
            throw new ControllerException("error al delete adicional" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setTipo(req.getParameter("tipo"));
        adicionalDTO.setPrecioBase( Integer.parseInt( req.getParameter("precioBase") ) );
        adicionalService.modificarAdicional(adicionalDTO);
        }        catch (Exception e){
            throw new ControllerException("error al put adicional" + e.getMessage());
        }
    }
}
