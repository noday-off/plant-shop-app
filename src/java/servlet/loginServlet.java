/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tri Thinh
 */
public class loginServlet extends HttpServlet {

    private static String path = "";
    private static final String personalPagePath = "personalPage.jsp";
    private static final String adminPagePath = "admin.jsp";
    private static final String loginPath = "login.jsp";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try {
                //get session
                HttpSession session = request.getSession();                
                
                //get parameter from client
                String email= request.getParameter("txtEmail");
                String password= request.getParameter("txtPassword");
                
                Account acc = null;
                    
                acc = AccountDAO.getAccount(email,password);

                if(acc!=null){
                    session.setAttribute("LOGIN_USER", acc);
                    if(acc.getRole() == 1){
                        path = adminPagePath;
                    }else{
                        path = personalPagePath;
                    }
                }else{
                    request.setAttribute("warning","Login fail! Incorrect Email or Password");
                    path = loginPath;
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                request.getRequestDispatcher(path).forward(request, response);
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
