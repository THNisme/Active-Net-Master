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
 * @author Nguyễn Đào Thu Ngân
 */
public class OrderItemsDAO extends DBContext {

    public OrderItemsDAO() {
        super();
    }

    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> list = new ArrayList();
        String sql = "SELECT    order_items.*\n"
                + "FROM         order_items INNER JOIN\n"
                + "                      orders ON order_items.order_id = orders.id\n"
                + "					  where order_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            OrderDAO orderDao = new OrderDAO();
            while (rs.next()) {
                int id = rs.getInt("id");
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

    public void create(int orderId, String itemType, int itemId, int quantity, int unitPrice) {
        String sql = "INSERT INTO order_items (order_id, item_type, item_id, quantity, unit_price) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderId);
            ps.setString(2, itemType);
            ps.setInt(3, itemId);
            ps.setInt(4, quantity);
            ps.setInt(5, unitPrice);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int orderItemId) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItemId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, int orderId, String itemType, int itemId, int quantity, int unitPrice) {
        String sql = "UPDATE order_items\n"
                + "SET order_id = ?, item_type = ?, item_id = ?, quantity = ?, unit_price = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setString(2, itemType);
            ps.setInt(3, itemId);
            ps.setInt(4, quantity);
            ps.setInt(5, unitPrice);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        OrderItemsDAO dao = new OrderItemsDAO();
        List<OrderItem> list = dao.getItemsByOrderId(1);
        for (OrderItem o : list) {
            System.out.println(o.getItemId() + " " + o.getItemType());
        }
    }
}
