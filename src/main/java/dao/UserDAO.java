/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.Users;
import utils.DBContext;

/**
 *
 * @author Admin
 */
class UserDAO extends DBContext{

    public UserDAO() {
    super();
    }

    public Users getUserById(int userId) {
        Users u = new Users();
        String sql = "Select * from users where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pass = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");
                
                u = new Users(id, name, email, phone, pass, role, createdAt);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return u;
    }
    
    
}
