/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.OrderDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Order;
import model.User;

/**
 *
 * @author Hieu
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        
        
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login");
//            return;
//        }
//        User user = (User) session.getAttribute("user");
//        int userId = user.getId();
//
//        UserDAO userDAO = new UserDAO();
//        Cart cart = userDAO.loadCartByUserId(userId);
//
//        if (cart == null) {
//            response.getWriter().println("Không có giỏ hàng cho user ID: " + userId);
//            return;
//        }
//
//        List<CartItem> items = userDAO.getItemsByCartId(cart.getId());
//
//        // BankTransferNote = cartId_username
//        String bankTransferNote = "Cart" + cart.getId() + "_" + user.getName();
//
//        request.setAttribute("cart", cart);
//        request.setAttribute("items", items);
//        request.setAttribute("bankTransferNote", bankTransferNote); // Truyền sang JSP
//        request.getRequestDispatcher("cart.jsp").forward(request, response);

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
//        String action = request.getParameter("action");
//        if ("checkout".equalsIgnoreCase(action)) {
//            HttpSession session = request.getSession();
//            User user = (User) session.getAttribute("user");
//
//            int userId = user.getId();
//
//            CartDAO cartDAO = new CartDAO();
//
//            OrderDAO orderDAO = new OrderDAO();
//            int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
//            String bankTransferNote = request.getParameter("bankTransferNote");
//
//            orderDAO.create(userId, totalAmount, bankTransferNote);
//
//            cartDAO.delete(userId);
//
//            // 3. Chuyển hướng sau khi thanh toán
//            request.getRequestDispatcher("order-success.jsp").forward(request, response);
//        }

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
