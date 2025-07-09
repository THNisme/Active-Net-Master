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
import model.CartItems;
import model.CartItems;
import utils.DBContext;

public class CartItemsDAO extends DBContext {

    public CartItemsDAO() {
        super();
    }
    public List<CartItems> getItemsByCartId(int cartId) {
        String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
        List<CartItems> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String itemType = rs.getString("item_type");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");
                Date addedAt = rs.getDate("added_at");

                CartItems item = new CartItems(id, cartId, itemType, itemId, quantity, unitPrice, addedAt);
                list.add(item);
            }
        } catch (Exception e) {
            System.out.println("getItemsByCartId: " + e.getMessage());
        }
        return list;
    }

    // Thêm item vào cart
    public void addItem(CartItems items) {
        String sql = "INSERT INTO cart_items (cart_id, item_type, item_id, quantity, unit_price, added_at) " +
                     "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, items.getCartId());
            ps.setString(2, items.getItemType());
            ps.setInt(3, items.getItemId());
            ps.setInt(4, items.getQuantity());
            ps.setInt(5, items.getUnitPrice());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addItem: " + e.getMessage());
        }
    }

    // Xóa tất cả item trong giỏ (theo cart_id)
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
   public List<CartItems> getCartItemsByCartId(int cartId) {
    List<CartItems> list = new ArrayList<>();
    String sql = "SELECT * FROM cart_items WHERE cart_id = ?";

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cartId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            CartItems items = new CartItems();
            items.setId(rs.getInt("id"));
            items.setCartId(rs.getInt("cart_id"));
            items.setItemType(rs.getString("item_type"));
            items.setItemId(rs.getInt("item_id"));
            items.setQuantity(rs.getInt("quantity"));
            items.setUnitPrice(rs.getInt("unit_price"));
            items.setAddedAt(rs.getDate("added_at CartItems items = new CartItems();"));

            list.add(items);
        }
    } catch (Exception e) {
        System.out.println("getCartItemsByCartId: " + e.getMessage());
    }

    return list;
}
 

    // Test
//    public static void main(String[] args) {
//        CartItemsDAO dao = new CartItemsDAO();
//        List<CartItems> list = dao.getItemsByCartId(1);
//        for (CartItems i : list) {
//            System.out.println("Item ID: " + i.getItemId());
//            System.out.println("Qty: " + i.getQuantity());
//        }
//    }
}