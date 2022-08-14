/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.OrderDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Tri Thinh
 */
public class saveShoppingCartServlet extends HttpServlet {

    private static String path = "viewcart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        System.out.println(email);
        
        if(session != null){
            Account loginUser = (Account)session.getAttribute("LOGIN_USER");
            HashMap<Integer,Integer> cart = (HashMap<Integer,Integer>) session.getAttribute("CART");
            if(cart != null && !cart.isEmpty()){
                if(loginUser == null){
                    request.setAttribute("warning","You must login to finish the shopping !!");
                }else{
                    boolean result = OrderDAO.insertOrder(email, cart);
                    if(result){
                        session.setAttribute("CART", null);
                        request.setAttribute("msg", "Save your cart successfully");
                    }else{
                        request.setAttribute("warning", "Fail to save your cart");
                    }
                }
            }else{
                //
            }
        }else{
            path = "index.jsp";
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
