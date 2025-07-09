/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderItem;
import utils.DBContext;

/**
 *
 * @author Ngan
 */
public class OrderItemsDAO extends DBContext {

    public OrderItemsDAO() {
        super();
    }

    public List<OrderItem> getItemsByOrderId(int orderId) {
        List list = new ArrayList();
        String sql = "SELECT order_items.id, cart_items.item_type, cart_items.item_id, cart_items.quantity, cart_items.unit_price\n"
                + "FROM         order_items INNER JOIN\n"
                + "                      orders ON order_items.order_id = orders.id INNER JOIN\n"
                + "                      users ON orders.user_id = users.id INNER JOIN\n"
                + "                      carts ON users.id = carts.user_id INNER JOIN\n"
                + "                      cart_items ON carts.id = cart_items.cart_id"
                + "					  where orders.id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            OrderDAO orderDao = new OrderDAO();
            while (rs.next()) {
                int id = rs.getInt("order_items.id");
                Order o = orderDao.getOrderById(orderId);
                String itemType = rs.getString("item_type");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");
                OrderItem orderItems = new OrderItem(id, o, itemType, itemId, quantity, unitPrice);
                list.add(orderItems);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
    
    public void createOrderItem(int orderId, String itemType, int itemId, int quantity, int unitPrice){
        String sql = "INSERT INTO order_items (order_id, item_type, item_id, qquantity, unit_price) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderId);
            ps.setString(1, itemType);
            ps.setInt(1, itemId);
            ps.setInt(1, quantity);
            ps.setInt(1, unitPrice);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteItemsByOrderItemId(int orderItemId) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItemId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
