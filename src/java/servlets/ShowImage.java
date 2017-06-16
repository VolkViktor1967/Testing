/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Book;
import beans.User;
import controllers.SearchController;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "ShowImage", urlPatterns = {"/ShowImage"})
public class ShowImage extends HttpServlet {
    int  i=0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //@Inject
    //SearchController searchControler1;
    /*@Inject
    NumberGenerator numberGenerator;*/
	
   /* @PostConstruct
    private void PostConstruct(){
		System.out.println(numberGenerator.generateNumber());
   }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        
        String ind = request.getParameter("index");
        System.err.println("ShowImage INDEX = "+ind);
        
        try {
            int id =Integer.valueOf(request.getParameter("id"));
            
           /*
            SearchController searchControler = (SearchController) request.getSession().getAttribute("searchController");
            byte[] image =  searchControler.GetImage(id);
            response.setContentLength(image.length);
            out.write(image);
            */
            
            ArrayList<Book> list = (ArrayList<Book>)request.getSession(false).getAttribute("currentBookList");
            Book book;
          
            int i=0;
            do{
              book = list.get(i);
              i++;
            }while (book.getId()!=id);
           
            book.fillImageContent();
            if(book.getImage()!=null){
              response.setContentLength(book.getImage().length);
              out.write(book.getImage());
            }
            
            
            
            
        }catch(Exception e){
            System.out.println("servlets.ShowImage.processRequest()");
        }
        finally{
            out.close();
        }                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
