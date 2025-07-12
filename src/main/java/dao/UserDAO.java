/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.IO;
import model.Message;
import model.Product;
import model.ProductCategory;
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
            if (rs.next()) {
                int idDB = rs.getInt("id");
                int userIdDB = rs.getInt("users_id");
                String content = rs.getString("content");
                boolean readed = rs.getBoolean("readed");
                Date realeaseDate = rs.getDate("release_date");

                UserDAO userDao = new UserDAO();
                User userById = userDao.getUserById(userId);
                
                Message message = new Message(idDB, userById, content, readed, realeaseDate);

                list.add(message);

                return list;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
//        System.out.println(dao.login("admin@gmail.com", "123456").getId());
//        System.out.println(dao.login("admin@gmail.com", "123456").getName());
//        System.out.println(dao.login("admin@gmail.com", "123456").getEmail());
//        System.out.println(dao.login("admin@gmail.com", "123456").getPhone());
//        System.out.println(dao.login("admin@gmail.com", "123456").getPasswordHash());
//        System.out.println(dao.login("admin@gmail.com", "123456").getRole() + "");
//        System.out.println(dao.login("admin@gmail.com", "123456").getCreatedAt());

        dao.delete(9);

        List<User> list = dao.getAll();
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

        List<Message> messList = dao.loadMessageByUserId(1);

        for (Message m : messList) {
            System.out.println(m.getId());
            System.out.println(m.getUser().getEmail());
            System.out.println(m.getContent());
            System.out.println(m.isReaded());
            System.out.println(m.getReleaseDate().toString());
            System.out.println("---------------");
        }

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
