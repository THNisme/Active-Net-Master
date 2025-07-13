/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.ManualPayment;
import model.Order;
import utils.DBContext;

/**
 *
 * @author Thinh
 */
public class ManualPaymentDAO extends DBContext {

    public ManualPaymentDAO() {
        super();
    }

    // Lấy tất cả thanh toán thủ công
    public List<ManualPayment> getAll() {
        List<ManualPayment> list = new ArrayList<>();
        String sql = "SELECT * FROM manual_payments";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                int orderId = rs.getInt("order_id");
                String content = rs.getString("transfer_content");
                boolean confirmed = rs.getBoolean("confirmed_by_admin");
                Timestamp confirmedAt = rs.getTimestamp("confirmed_at");
                
                // Tạo đối tượng Order chỉ với ID
                Order order = new Order();
                order.setId(orderId);
                
                list.add(new ManualPayment(
                    id, 
                    order, 
                    content, 
                    confirmed, 
                    confirmedAt != null ? new Date(confirmedAt.getTime()) : null
                ));
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    // Lấy thanh toán theo ID
    public ManualPayment getManualPaymentByOrderId(int id) {
        String sql = "SELECT * FROM manual_payments WHERE id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                String content = rs.getString("transfer_content");
                boolean confirmed = rs.getBoolean("confirmed_by_admin");
                Timestamp confirmedAt = rs.getTimestamp("confirmed_at");
                
                // Tạo đối tượng Order chỉ với ID
                Order order = new Order();
                order.setId(orderId);
                
                return new ManualPayment(
                    id, 
                    order, 
                    content, 
                    confirmed, 
                    confirmedAt != null ? new Date(confirmedAt.getTime()) : null
                );
            }
        } catch (Exception e) {
            System.out.println("getById: " + e.getMessage());
        }
        return null;
    }

    // Tạo mới thanh toán thủ công
    public void create(ManualPayment payment) {
        String sql = "INSERT INTO manual_payments (order_id, transfer_content, confirmed_by_admin, confirmed_at) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, payment.getOrder().getId());
            ps.setString(2, payment.getTransferContent());
            ps.setBoolean(3, payment.isConfirmedByAdmin());
            ps.setTimestamp(4, payment.getConfirmedAt() != null ? 
                new Timestamp(payment.getConfirmedAt().getTime()) : null);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    // Cập nhật thanh toán thủ công
    public void update(ManualPayment payment) {
        String sql = "UPDATE manual_payments SET transfer_content = ?, confirmed_by_admin = ?, confirmed_at = ? WHERE id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, payment.getTransferContent());
            ps.setBoolean(2, payment.isConfirmedByAdmin());
            ps.setTimestamp(3, payment.getConfirmedAt() != null ? 
                new Timestamp(payment.getConfirmedAt().getTime()) : null);
            ps.setInt(4, payment.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    // Xóa thanh toán thủ công
    public void delete(int id) {
        String sql = "DELETE FROM manual_payments WHERE id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

    // Test
    public static void main(String[] args) {
        ManualPaymentDAO dao = new ManualPaymentDAO();
        
        // Test create
        Order order = new Order();
        order.setId(1); // Giả sử có đơn hàng ID 1
        
        ManualPayment payment = new ManualPayment(
            0, // ID mới
            order, 
            "ABC123", 
            false, 
            null
        );
        dao.create(payment);
        
        // Test getAll
        System.out.println("All payments:");
        for (ManualPayment mp : dao.getAll()) {
            System.out.println(mp.getId() + " - Order: " + mp.getOrder().getId());
        }
        
//        // Test update
//        payment.setTransferContent("XYZ789");
//        payment.setConfirmedByAdmin(true);
//        payment.setConfirmedAt(new Date());
//        dao.update(payment);
//        
        // Test getById
        ManualPayment updated = dao.getManualPaymentByOrderId(1);
        System.out.println("Updated payment: " + updated.getTransferContent());
        
        // Test delete
        dao.delete(2);
    }
}
