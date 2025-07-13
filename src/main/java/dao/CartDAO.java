/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import utils.DBContext;

/**
 * @author Lê Hữu Tính
 */
public class CartDAO extends DBContext {
    public CartDAO() {
        super();
    }

    public Cart getCartByUserId(int userId) {
        String sql = "SELECT * FROM carts WHERE user_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Cart cart = new Cart(id, userId, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);
                return cart;
            }
        } catch (Exception e) {
            System.out.println("getCartByUserId: " + e.getMessage());
        }
        return null;
    }

    public List<Cart> getAll() {
        String sql = "SELECT * FROM carts";
        List<Cart> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int uid = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Cart cart = new Cart(id, uid, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);

                list.add(cart);
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    public void create(int userId) {
        String sql = "INSERT INTO carts (user_id, created_at, updated_at) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM carts WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

    public Cart getCartById(int id) {
        String sql = "SELECT * FROM carts WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Cart cart = new Cart(id, uid, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);
                return cart;
            }
        } catch (Exception e) {
            System.out.println("getCartById: " + e.getMessage());
        }
        return null;
    }

    public void updateTimestamps(int id) {
        String sql = "UPDATE carts SET updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateTimestamps: " + e.getMessage());
        }
    }

    
}
