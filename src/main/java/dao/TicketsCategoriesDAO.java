/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.TicketCategories;
import utils.DBContext;

/**
 *
 * @author Hieu
 */
public class TicketsCategoriesDAO extends DBContext {

    public TicketsCategoriesDAO() {
        super();
    }

    public TicketCategories getTicketCategoryById(int id) {
        String sql = "SELECT * FROM [dbo].[ticket_categories] WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String name = rs.getString("name");
                String des = rs.getString("description");

                return new TicketCategories(id, name, des);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
