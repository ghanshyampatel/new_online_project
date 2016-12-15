/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ghanshyam Patel
 */
public class reg extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=request.getParameter("login_name");
        String surname=request.getParameter("login_surname");
        String phone=request.getParameter("login_number");
        String email=request.getParameter("login_email");        
        String username=request.getParameter("login_user");
        String pass=request.getParameter("login_pwd");
        String confpass=request.getParameter("login_cnfpwd");
       
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
         if(pass.equals(confpass))
        {  
            try
            {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reg","root","tiger");
                String query="insert into registration values(?,?,?,?,?,?)";
                PreparedStatement pst =con.prepareStatement(query );  

                pst.setString(1,name);  
                pst.setString(2,surname);        
                pst.setString(3,phone);
                pst.setString(4,email);
                pst.setString(5,username);
                pst.setString(6,pass);
                          
                int i = pst.executeUpdate();
                String msg=" ";
                 if(i>0){  
                    msg="Record has been inserted";
                 out.println("<font size='6' color=blue><center>" + msg + "</font>");
                 out.println("<a href=\"login.html\">LOGIN</a><center>");


                }  
                 else{  
                    msg="failed to insert the data";
                    out.println("<font size='6' color=blue>" + msg + "</font>");
                    out.println("<a href=\"reg.html\">Try Again</a><center>");
                }  
                pst.close();
       
            }
            catch(Exception e)
            {
              out.println(e);  
            }
                
        }
        else
        {
            response.sendRedirect("login.html");
        }
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet reg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reg at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
