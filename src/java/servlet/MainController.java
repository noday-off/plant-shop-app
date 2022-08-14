/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tri Thinh
 */
public class MainController extends HttpServlet {

    private String path = "errorpage.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            
            switch(action){
                case "Login": path = "loginServlet"; break;
                case "Register": path = "registerServlet"; break;
                case "Search": path = "searchServlet"; break;
                case "Logout": path = "logoutServlet"; break;
                case "Addtocart": path = "addToCartServlet"; break;
                case "Viewcart": path = "viewcart.jsp"; break;
                case "Update": path = "updateCartServlet"; break;
                case "Delete": path = "deleteCartServlet"; break;
                case "SaveOrder": path = "saveShoppingCartServlet"; break;
                case "ViewOrder": path = "viewOrderServlet"; break;
                case "CancelOrder": path = "cancelOrderServlet"; break;
                case "ViewOrderDetails": path = "viewOrderDetailsServlet"; break;
                default: path = "index.jsp"; break;
            }
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
