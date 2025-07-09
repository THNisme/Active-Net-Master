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
import model.Order;
import model.Users;
import utils.DBContext;

/**
 *
 * @author Admin
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

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                UserDAO uDao = new UserDAO();
                Users u = uDao.getUserById(userId);
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                Order o = new Order(id, u, totalAmount, status, bankTransferNote, createdAt);
                list.add(o);
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

            while (rs.next()) {
                int id = rs.getInt("id");
                UserDAO uDao = new UserDAO();
                Users u = uDao.getUserById(userId);
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                Order o = new Order(id, u, totalAmount, status, bankTransferNote, createdAt);
                list.add(o);
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

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                UserDAO uDao = new UserDAO();
                Users u = uDao.getUserById(userId);
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                return new Order(orderId, u, totalAmount, status, bankTransferNote, createdAt);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createOrder(int userId, int totalAmount, String bankTransferNote) {
        String sql = "INSERT INTO orders (user_id, total_amount, status, bank_transfer_note, created_at) VALUES (?,?,?,?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(1, totalAmount);
            ps.setString(1, "pending");
            ps.setString(1, bankTransferNote);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void paidOrder(int orderId) {
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
            System.out.println(o.getId() + " " + o.getStatus() + " " + o.getBankTransferNote());
        }

        dao.paidOrder(1);
    }
}
