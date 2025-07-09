/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItems;
import utils.DBContext;

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
                java.sql.Date createdAt = rs.getDate("created_at");
                java.sql.Date updatedAt = rs.getDate("updated_at");

                Cart cart = new Cart(id, userId, createdAt, updatedAt);

                CartItemsDAO itemsDAO = new CartItemsDAO();
                List<CartItems> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);

                return cart;
            }
        } catch (Exception e) {
            System.out.println("getCartByUserId: " + e.getMessage());
        }
        return null;
    }

    public int createCart(int userId) {
        String sql = "INSERT INTO carts (user_id, created_at, updated_at) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (Exception e) {
            System.out.println("createCart: " + e.getMessage());
        }
        return -1;
    }
    
    public List<Cart> getAllCarts() {
        String sql = "SELECT * FROM carts";
        List<Cart> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int uid = rs.getInt("user_id");
                java.sql.Date createdAt = rs.getDate("created_at");
                java.sql.Date updatedAt = rs.getDate("updated_at");

                Cart cart = new Cart(id, uid, createdAt, updatedAt);

                CartItemsDAO itemsDAO = new CartItemsDAO();
                List<CartItems> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);

                list.add(cart);
            }
        } catch (Exception e) {
            System.out.println("getAllCarts: " + e.getMessage());
        }
        return list;
    }
}