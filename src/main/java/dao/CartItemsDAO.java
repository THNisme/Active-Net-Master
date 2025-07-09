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

public class CartItemsDAO extends DBContext {

    public CartItemsDAO() {
        super();
    }

    public List<CartItems> getItemsByCartId(int cartId) {
        List<CartItems> list = new ArrayList<>();
        String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItems items = new CartItems();
                items.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(cartId);
                items.setCart(cart);

                items.setItemType(rs.getString("item_type"));
                items.setItemId(rs.getInt("item_id"));
                items.setQuantity(rs.getInt("quantity"));
                items.setUnitPrice(rs.getInt("unit_price"));
                items.setAddedAt(rs.getDate("added_at"));

                list.add(items);
            }
        } catch (Exception e) {
            System.out.println("getItemsByCartId: " + e.getMessage());
        }
        return list;
    }


    public void addItem(CartItems item) {
        String sql = "INSERT INTO cart_items (cart_id, item_type, item_id, quantity, unit_price, added_at) " +
                     "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getCart().getId());  
            ps.setString(2, item.getItemType());
            ps.setInt(3, item.getItemId());
            ps.setInt(4, item.getQuantity());
            ps.setInt(5, item.getUnitPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addItem: " + e.getMessage());
        }
    }

    public void deleteItemsByCartId(int cartId) {
        String sql = "DELETE FROM cart_items WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteItemsByCartId: " + e.getMessage());
        }
    }
}