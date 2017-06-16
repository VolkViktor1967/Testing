/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.NumberGenerator;
import controllers.SearchController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
//@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
@WebServlet(name = "ShowImage2", urlPatterns = {"/ShowImage2"})
public class MainServlet extends HttpServlet {
	@Inject
	NumberGenerator numberGenerator;
        /*@Inject
        SearchController searchControler1;*/
	
	private static final long serialVersionUID = 1L;
       
	public MainServlet() {
        super();
    }
	
	@PostConstruct
	private void PostConstruct(){
		System.out.println(numberGenerator.generateNumber());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Волк В В!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Волк В В!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //byte[] image =  searchControler1.GetImage(1);
                System.out.println("Номер "+numberGenerator.generateNumber());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
 
}

































/*

         response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        
        String ind = request.getParameter("index");
        System.err.println("ShowImage INDEX = "+ind);
        
        try {
            int index =Integer.valueOf(request.getParameter("index"));
    

            System.err.println("GETIMAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! = ");
                        
            byte[] image =  searchControler1.GetImage(index);
            
            System.err.println("GETIMAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! = ");
            
        
            response.setContentLength(image.length);
            out.write(image);
            
        }catch(Exception e){
            System.out.println("servlets.ShowImage.processRequest()");
        }
        finally{
            out.close();
        }                

*/

