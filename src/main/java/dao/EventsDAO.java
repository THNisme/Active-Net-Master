/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Event;
import model.TicketCategories;
import utils.DBContext;

/**
 *
 * @author Hieu
 */
public class EventsDAO extends DBContext {

    public EventsDAO() {
        super();
    }

    public List<Event> getAll() {
        String sql = "select * from[dbo].[events]";
        List<Event> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("description");
                Date date = rs.getDate("date");
                String location = rs.getString("location");
                Date created = rs.getDate("create_at");
                Event events = new Event(id, name, des, date, location, created);

                list.add(events);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String name = rs.getString("name");
                String des = rs.getString("description");
                Date date = rs.getDate("date");
                String location = rs.getString("location");
                Date created = rs.getDate("created_at");
                return new Event(id, name, des, date, location, created);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addEvent(String name, String description, Date date, String location, Date createdAt) {
        String sql = "INSERT INTO events (name, description, date, location, created_at) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDate(3, (java.sql.Date) date);
            ps.setString(4, location);
            ps.setDate(5, (java.sql.Date) createdAt);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
