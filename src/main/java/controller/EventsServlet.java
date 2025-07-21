/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.EventsDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Event;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EventsServlet", urlPatterns = {"/events"})
public class EventsServlet extends HttpServlet {

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
            out.println("<title>Servlet EventsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventsServlet at " + request.getContextPath() + "</h1>");
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
        
        if (session.getAttribute("user") != null && u.getRole() == 1) {

            String view = request.getParameter("view");

            if (view == null) {
                view = "list";
            }

            if (view.equalsIgnoreCase("list")) {
                EventsDAO eventDao = new EventsDAO();
                List<Event> eventList = eventDao.getAll();
                request.setAttribute("eventList", eventList);
                request.getRequestDispatcher("events.jsp").forward(request, response);
            }

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
        String view = request.getParameter("view");

        if (view == null) {
            view = "edit";
        }

        if (view.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("idEdit"));
            String name = request.getParameter("eventNameEdit");
            String desc = request.getParameter("descEdit");
            String date = request.getParameter("dateEdit");
            String location = request.getParameter("locationEdit");
            String dateCreateAt = request.getParameter("createAtEdit");

            EventsDAO eDao = new EventsDAO();
            eDao.updateDashboard(id, name, desc, date, location, dateCreateAt);
            response.sendRedirect("events");
        } else if (view.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("idDelete"));

            EventsDAO eDao = new EventsDAO();
            eDao.deleteAllData(id);
            response.sendRedirect("events");
        } else if (view.equalsIgnoreCase("add")) {
            String name = request.getParameter("eventNameEdit");
            String desc = request.getParameter("descEdit");
            String date = request.getParameter("dateEdit");
            String location = request.getParameter("locationEdit");

            EventsDAO eDao = new EventsDAO();
            eDao.createDashboard(name, desc, date, location);
            response.sendRedirect("events");
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
