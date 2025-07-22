/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TicketCategories;
import utils.DBContext;

/**
 *
 * @author Thinh
 */
public class TicketCategoriesDAO extends DBContext {

    public TicketCategoriesDAO() {
        super();
    }

    // Lấy tất cả vé
    public List<TicketCategories> getAll() {
        List<TicketCategories> list = new ArrayList<>();
        String sql = "SELECT * FROM ticket_categories";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                list.add(new TicketCategories(id, name, description));
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    // Lấy danh mục vé theo ID
    public TicketCategories getTicketCategoryById(int id) {
        String sql = "SELECT * FROM ticket_categories WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");

                return new TicketCategories(id, name, description);
            }
        } catch (Exception e) {
            System.out.println("getById: " + e.getMessage());
        }
        return null;
    }

    // Tạo
    public void create(String name, String description) {
        String sql = "INSERT INTO ticket_categories (name, description) VALUES (?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    // Cập nhật
    public void update(int id, String name, String description) {
        String sql = "UPDATE ticket_categories SET [name] = ?, [description] = ? WHERE id = ?;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    // Xóa danh
    public void delete(int id) {
        String sql = "DELETE FROM ticket_categories WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

    public void deleteAllData(int id) {
        String deleteCartItems = "DELETE FROM cart_items WHERE item_type = 'ticket' and item_id IN (SELECT id FROM tickets WHERE category_id = ? )";
        String deleteTicket = "DELETE FROM tickets WHERE category_id = ?";
        String deleteTicketCategories = "DELETE FROM ticket_categories WHERE id = ?";

        try {
            conn.setAutoCommit(false); // Bắt đầu transaction

            PreparedStatement ps1 = conn.prepareStatement(deleteCartItems);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(deleteTicket);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            PreparedStatement ps3 = conn.prepareStatement(deleteTicketCategories);
            ps3.setInt(1, id);
            ps3.executeUpdate();

            conn.commit(); // Thành công → commit

        } catch (Exception e) {
            try {
                conn.rollback(); // Lỗi → rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Trả lại trạng thái mặc định
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Test
    public static void main(String[] args) {
        TicketCategoriesDAO dao = new TicketCategoriesDAO();

        // Test create
        dao.create("VIP", "VIP Tickets");

        // Test getAll
        System.out.println("All categories:");
        for (TicketCategories tc : dao.getAll()) {
            System.out.println(tc.getId() + " - " + tc.getName());
        }

        // Test update
        dao.update(1, "Standard Updated", "Standard tickets");

        // Test getById
        TicketCategories category = dao.getTicketCategoryById(1);
        System.out.println("Updated category: " + category.getName());

        // Test delete
        dao.delete(2);
    }
}
