/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.IO;
import model.ManualPayment;
import model.Message;
import model.Order;
import model.OrderItem;
import model.User;
import utils.DBContext;

/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class UserDAO extends DBContext {

    public UserDAO() {
        super();
    }

    public User login(String email, String pass) {

        String sql = "select * from users where email = ? and password_hash = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, IO.hashMD5(pass));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(id, name, email_db, phone, pass_db, role, createdAt);
                u.setMessages(loadMessageByUserId(id));
                u.setOrders(loadOrderByUserId(id));
                return u;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> getAll() {
        String sql = "select * from users";

        List<User> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(id, name, email_db, phone, pass_db, role, createdAt);

                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void create(String name, String email, String phone, String password_hash, int role) {
        String sql = "INSERT INTO [dbo].[users] ([name], [email], [phone], [password_hash], [role], [created_at]) \n"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //            int nextId = getNextId();
//            
//            ps.setInt(1, nextId);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, IO.hashMD5(password_hash));
            ps.setInt(5, role);
            ps.setDate(6, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users \n"
                + "WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_db = rs.getInt("id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(id_db, name, email_db, phone, pass_db, role, createdAt);

                return u;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    private int getNextId() {
//        String sql = "SELECT MAX(id) AS maxID FROM product";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("maxID") + 1;
//            } else {
//                return 1;
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return -1;
//    }
    public void update(int id, String name, String email, String phone, String password_hash, int role, String created_at) {
        String sql = "UPDATE [dbo].[users] SET [name] = ?, [email] = ?, [phone] = ?, [password_hash] = ?, [role] = ?, [created_at] = ? \n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, IO.hashMD5(password_hash));
            ps.setInt(5, role);
            ps.setDate(6, Date.valueOf(created_at));
            ps.setInt(7, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Message> loadMessageByUserId(int userId) {
        String sql = "SELECT * FROM messages \n"
                + "WHERE users_id = ?";

        List<Message> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idDB = rs.getInt("id");
                int userIdDB = rs.getInt("users_id");
                String content = rs.getString("content");
                boolean readed = rs.getBoolean("readed");
                Date realeaseDate = rs.getDate("release_date");

//                UserDAO userDao = new UserDAO();
                User userById = getUserById(userId);

                Message message = new Message(idDB, userById, content, readed, realeaseDate);
                list.add(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> loadOrderByUserId(int userId) {
        String sql = "SELECT orders.id, orders.user_id, orders.total_amount, orders.status, orders.bank_transfer_note, orders.created_at, manual_payments.id AS payment_id, manual_payments.transfer_content, manual_payments.confirmed_by_admin, manual_payments.confirmed_at \n"
                + "FROM   orders INNER JOIN \n"
                + "manual_payments ON orders.id = manual_payments.order_id \n"
                + "WHERE user_id = ?";

        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("id");
                int userIDDB = rs.getInt("user_id");
                long totalAmount = rs.getLong("total_amount");
                String orderStatus = rs.getString("status");
                String bankNote = rs.getString("bank_transfer_note");
                Date orderCreateDate = rs.getDate("created_at");

                int paymentID = rs.getInt("payment_id");
                String transferContent = rs.getString("transfer_content");
                boolean adminConfirmed = rs.getBoolean("confirmed_by_admin");
                Date paymentConfirmedDate = rs.getDate("confirmed_at");

                User u = getUserById(userIDDB);

                List<OrderItem> oItemList = loadOrderItemsByOrderId(orderID);

                Order oForP = getOrderById(orderID);

                ManualPayment payment = new ManualPayment(paymentID, oForP, transferContent, adminConfirmed, paymentConfirmedDate);

                Order o = new Order(orderID, u, totalAmount, orderStatus, bankNote, orderCreateDate, oItemList, payment);

                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

//    RELATION FUNCTION
    public List<OrderItem> loadOrderItemsByOrderId(int orderId) {
        String sql = "SELECT * FROM order_items \n"
                + "WHERE order_id = ?";

        List<OrderItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String itemType = rs.getString("item_type");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");

                OrderItem orderItem = new OrderItem(id, getOrderById(orderId), itemType, itemId, quantity, unitPrice);

                list.add(orderItem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT    orders.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         users INNER JOIN\n"
                + "                      orders ON users.id = orders.user_id where orders.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pass = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(userId, name, email, phone, pass, role, userCreatedAt);

                Order o = new Order(orderId, u, totalAmount, status, bankTransferNote, createdAt);
                return o;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        User u = dao.login("a@example.com", "123456");

//        System.out.println(u.getId());
//        System.out.println(u.getName());
//        System.out.println(u.getEmail());
//        System.out.println(u.getPhone());
//        System.out.println(u.getPasswordHash());
//        System.out.println(u.getRole());
//        System.out.println(u.getCreatedAt());

        List<Order> orderList = u.getOrders();

        for (Order m : orderList) {
            System.out.println("OID: " + m.getId() + "");
            System.out.println("UNAME :" + m.getUser().getName());
            System.out.println("Tong: " + IO.formatCurrency(String.valueOf(m.getTotalAmount())));
            System.out.println("Status: " + m.getStatus());
            System.out.println("Bank note:" + m.getBankTransferNote());
            System.out.println("Created: " + m.getCreateAt().toString());

            System.out.println("---------------");
        }

//        System.out.println(dao.login("admin@gmail.com", "123456").getId());
//        System.out.println(dao.login("admin@gmail.com", "123456").getName());
//        System.out.println(dao.login("admin@gmail.com", "123456").getEmail());
//        System.out.println(dao.login("admin@gmail.com", "123456").getPhone());
//        System.out.println(dao.login("admin@gmail.com", "123456").getPasswordHash());
//        System.out.println(dao.login("admin@gmail.com", "123456").getRole() + "");
//        System.out.println(dao.login("admin@gmail.com", "123456").getCreatedAt());
//        dao.delete(9);
//        List<User> list = dao.getAll();
//        for (User u : list) {
//            System.out.println(u.getId());
//            System.out.println(u.getName());
//            System.out.println(u.getEmail());
//            System.out.println(u.getPhone());
//            System.out.println(u.getPasswordHash());
//            System.out.println(u.getRole());
//            System.out.println(u.getCreatedAt());
//
//            System.out.println("-----------------");
//        }
//        User u = dao.getUserById(7);
//
//        System.out.println(u.getId());
//        System.out.println(u.getName());
//        System.out.println(u.getEmail());
//        System.out.println(u.getPhone());
//        System.out.println(u.getPasswordHash());
//        System.out.println(u.getRole());
//        System.out.println(u.getCreatedAt());
    }

}
