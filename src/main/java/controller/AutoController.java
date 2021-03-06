package controller;

import dtos.AdicionalDTO;
import dtos.AutoDTO;
import exceptions.ControllerException;
import exceptions.ServiceException;
import services.AdicionalService;
import services.AutoService;
import services.impl.AdicionalServiceImpl;
import services.impl.AutoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoController extends HttpServlet {
    AutoService autoService;
    AdicionalService adicionalService;

    public AutoController(){
        super();
        autoService = new AutoServiceImpl();
        adicionalService = new AdicionalServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            AutoDTO autoDTO = new AutoDTO();
            autoDTO.setModelo(req.getParameter("modelo"));
            autoDTO.setPrecioBase( Integer.parseInt( req.getParameter("precioBase") ) );

            addAdicional(req, autoDTO);

            autoService.ingresarAuto(autoDTO);
        }        catch (Exception e){
            throw new ControllerException("error al post auto" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            String id = req.getParameter("id");
            if(id != null){

                AutoDTO autoDTO = autoService.consultarAuto(Integer.parseInt(id));
                printAuto(res,req,autoDTO);
            }
            else {
                for(AutoDTO autoDTO : autoService.consultarAutos()){
                    printAuto(res,req,autoDTO);
                }
            }
        }catch (Exception e){
            throw new ControllerException("error al get auto" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            autoService.eliminarAuto(Integer.parseInt(req.getParameter("id")));
        }catch (Exception e){
            throw new ControllerException("error al delete adicional" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            AutoDTO autoDTO = new AutoDTO();
            autoDTO.setModelo(req.getParameter("modelo"));
            autoDTO.setPrecioBase( Integer.parseInt( req.getParameter("precioBase") ) );
            autoDTO.setPrecioFinal( Integer.parseInt( req.getParameter("precioFinal") ) );
            addAdicional(req, autoDTO);
            autoService.modificarAuto(autoDTO);
        }
        catch (Exception e){
            throw new ControllerException("error al put auto" + e.getMessage());
        }
    }

    private void printAuto(HttpServletResponse res, HttpServletRequest req, AutoDTO autoDTO) throws ServiceException, ControllerException {
        PrintWriter pw = null;
        try {
            pw = res.getWriter();
        } catch (IOException e) {
            throw new ControllerException("error en obtener pw" + e.getMessage());
        }

            String json = "{ \n" +
                    "  precioBase : " + String.valueOf(autoDTO.getPrecioBase()) + "\n" +
                    "  modelo : " + autoDTO.getModelo() + "\n" +
                    "  precioFinal : " + autoDTO.getPrecioFinal() + "\n"
                    + "}"; // no agrego los adicionales porque si agrego el objeto como tal va a quedar desprolijo, y los dtos no guardan ids
            pw.write(json);

    }
    private void addAdicional(HttpServletRequest req, AutoDTO autoDTO) throws ServiceException {
        String query = req.getParameter("adicional");

        if(query != null){
            List<String> ids = Arrays.asList(query.split(","));
            List<AdicionalDTO> adicionals = new ArrayList<>();
            for(String idAdicional : ids){
                adicionals.add( adicionalService.consultarAdicional(Integer.parseInt(idAdicional) ));
            }
            autoDTO.setAdicionales(adicionals);
        }
    }
}
