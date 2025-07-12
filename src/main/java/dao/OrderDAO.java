/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ManualPayment;
import model.Order;
import model.OrderItem;
import model.User;
import utils.DBContext;

/**
 *
 * @author Nguyễn Đào Thu Ngân
 */
public class OrderDAO extends DBContext {

    public OrderDAO() {
        super();
    }

    public List<Order> getAll() {
        String sql = "Select * from orders";
        List<Order> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            OrderItemsDAO oItemDao = new OrderItemsDAO();
            ManualPaymentDAO manualPaymentDAO = new ManualPaymentDAO();
//            UserDAO uDao = new UserDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                
                int userId = rs.getInt("user_id");
//                User u = uDao.getUserById(userId);
                
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                List<OrderItem> listOI = oItemDao.getItemsByOrderId(id);
                ManualPayment m = manualPaymentDAO.getManualPaymentByOrderId(id);

//                Order o = new Order(id, u, totalAmount, status, bankTransferNote, createdAt);
//                o.setItems(listOI);
//                o.setManualPayment(m);
//                list.add(o);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> getOrderByUserId(int userId) {
        List<Order> list = new ArrayList<>();
        String sql = "Select * from orders where user_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            OrderItemsDAO oItemDao = new OrderItemsDAO();
            ManualPaymentDAO manualPaymentDAO = new ManualPaymentDAO();
//            UserDAO uDao = new UserDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
//                User u = uDao.getUserById(userId);
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                List<OrderItem> listOI = oItemDao.getItemsByOrderId(id);
                ManualPayment m = manualPaymentDAO.getManualPaymentByOrderId(id);

//                Order o = new Order(id, u, totalAmount, status, bankTransferNote, createdAt);
//                o.setItems(listOI);
//                o.setManualPayment(m);
//
//                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getOrderById(int orderId) {
        String sql = "Select * from orders where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            
//            UserDAO uDao = new UserDAO();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
//                User u = uDao.getUserById(userId);
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

//                Order o = new Order(orderId, u, totalAmount, status, bankTransferNote, createdAt);
//                return o;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void create(int userId, int totalAmount, String bankTransferNote) {
        String sql = "INSERT INTO orders (user_id, total_amount, status, bank_transfer_note, created_at) VALUES (?,?,?,?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, totalAmount);
            ps.setString(3, "pending");
            ps.setString(4, bankTransferNote);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int orderId, int userId, int totalAmount, String status, String bankTransferNote, Date createdAt) {
        String sql = "UPDATE orders\n"
                + "SET user_id = ?, total_amount = ?, status = ?, bank_transfer_note = ?, created_at = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, totalAmount);
            ps.setString(3, status);
            ps.setString(4, bankTransferNote);
            ps.setDate(5, new java.sql.Date(createdAt.getTime()));
            ps.setInt(6, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setPaid(int orderId) {
        String sql = "UPDATE orders\n"
                + "SET status = 'paid'\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        for (Order o : dao.getAll()) {
            List<OrderItem> list = o.getItems();
            System.out.println(o.getId());
            for (OrderItem orderItem : list) {
                System.out.println(orderItem.getId() + " " + orderItem.getItemType());
            }
        }
        

    }
}
