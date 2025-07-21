/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lê Hữu Tính - CE190016
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import utils.DBContext;

public class CartItemDAO extends DBContext {

    public CartItemDAO() {
        super();
    }

    public List<CartItem> getAll() {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT ci.*, p.name AS product_name \n"
                + "FROM cart_items ci JOIN products p ON ci.item_id = p.id;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(rs.getInt("cart_id"));
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));
                item.setProductName(rs.getString("product_name"));
                list.add(item);
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    public List<CartItem> getItemsByCartId(int cartId) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(cartId);
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));

                list.add(item);
            }
        } catch (Exception e) {
            System.out.println("getItemsByCartId: " + e.getMessage());
        }
        return list;
    }

    public void create(CartItem item) {
        String sql = "INSERT INTO cart_items (cart_id, item_type, item_id, quantity, unit_price, added_at) "
                + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getCart().getId());
            ps.setString(2, item.getItemType());
            ps.setInt(3, item.getItemId());
            ps.setInt(4, item.getQuantity());
            ps.setInt(5, item.getUnitPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    public CartItem getById(int id) {
        String sql = "SELECT * FROM cart_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(rs.getInt("cart_id"));
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));

                return item;
            }
        } catch (Exception e) {
            System.out.println("getById: " + e.getMessage());
        }
        return null;
    }

    public void update(CartItem item) {
        String sql = "UPDATE cart_items SET quantity = ?, unit_price = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getQuantity());
            ps.setInt(2, item.getUnitPrice());
            ps.setInt(3, item.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        CartItemDAO dao = new CartItemDAO();
//        dao.delete(8);
//        List<CartItem> list = dao.getAll();
//        for (CartItem item : list) {
//            System.out.println("ID: " + item.getId());
//            System.out.println("Cart ID: " + item.getCart().getId());
//            System.out.println("Type: " + item.getItemType());
//            System.out.println("ItemID: " + item.getItemId());
//            System.out.println("Quantity: " + item.getQuantity());
//            System.out.println("Unit Price: " + item.getUnitPrice());
//            System.out.println("Added At: " + item.getAddedAt());
//            System.out.println("-----");
//        }
//
//        Cart cart = new Cart();
//        cart.setId(1); 
//        CartItem newItem = new CartItem();
//        newItem.setCart(cart);
//        newItem.setItemType("product");
//        newItem.setItemId(999);
//        newItem.setQuantity(1);
//        newItem.setUnitPrice(123456);
//        dao.create(newItem);
//        System.out.println("Thêm mới thành công");
//
//        CartItem testItem = dao.getById(1);
//        if (testItem != null) {
//            System.out.println("Lấy item ID = 1, Qty = " + testItem.getQuantity());
//        }
//
//        if (testItem != null) {
//            testItem.setQuantity(testItem.getQuantity() + 10);
//            dao.update(testItem);
//            System.out.println("Đã cập nhật quantity");
//        }
//    }
}
