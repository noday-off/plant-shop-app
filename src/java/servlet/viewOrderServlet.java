/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.OrderDAO;
import dto.Account;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tri Thinh
 */
public class viewOrderServlet extends HttpServlet {

   private static String path = "index.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account loginUser = (Account)session.getAttribute("LOGIN_USER");
        int status = Integer.parseInt(request.getParameter("status"));
        if(loginUser != null){
            ArrayList<Order> orderList = OrderDAO.getOrders(loginUser.getAccID(),status);
            System.out.println(orderList == null?"orderList null":"orderList not null");
            System.out.println(orderList.isEmpty()?"orderList empty":"orderList not empty");
            if(orderList != null){
                session.setAttribute("ORDER_LIST", orderList);
                path = "vieworder.jsp";
            }else{
                request.setAttribute("msg","Order list not found");
                path="vieworder.jsp";
            }
        }else{
            request.setAttribute("warning","You must log in to view your orders");
        }
        request.getRequestDispatcher(path).forward(request, response);
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
