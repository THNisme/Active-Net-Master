/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartItemDAO;
import dao.ManualPaymentDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CartItem;
import model.User;

/**
 *
 * @author BACH YEN
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

//      LOGIN SUCCESS
        if (session.getAttribute("user") != null) {
            request.getRequestDispatcher("order-success.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
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
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if ("checkout".equalsIgnoreCase(action)) {
            try {
                int uid = Integer.parseInt(request.getParameter("uid"));
                int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
                String bankNote = request.getParameter("bankTransferNote");
                

                OrderDAO orderDAO = new OrderDAO();
                CartItemDAO cartItemDAO = new CartItemDAO();
                ManualPaymentDAO paymentDAO = new ManualPaymentDAO();

                // 1. Tạo order
                orderDAO.create(uid, totalAmount, bankNote);
                int orderId = orderDAO.createTurnInt(uid, totalAmount, bankNote);
                
                // 2. Lấy cart items và insert vào order_items
                List<CartItem> items = cartItemDAO.getItemsByCartId(user.getCart().getId());
                for (CartItem item : items) {
                    orderDAO.addItemToOrder(orderId, item);
                }

                // 3. Tạo manual payment
                paymentDAO.create(orderId, bankNote);

                // 4. Xoá cart nếu muốn (tùy logic)
                cartItemDAO.deleteByCartId(user.getCart().getId());
                response.sendRedirect("order-success.jsp"); // hoặc trang nào em muốn

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("cart");
        }
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
