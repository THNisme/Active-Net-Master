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
import model.Message;
import model.Tickets;
import model.User;
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
        String sql = "SELECT * FROM [dbo].[events]";
        List<Event> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            TicketsDAO ticketsDAO = new TicketsDAO(); // Khởi tạo 1 lần ngoài vòng lặp

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("description");
                Date date = rs.getDate("date");
                String location = rs.getString("location");
                Date created = rs.getDate("created_at");

                // Tạo event
                Event event = new Event(id, name, des, date, location, created);

                // Lấy danh sách vé theo event_id SAI ROIF BAN HIEU OWI
                List<Tickets> tickets = ticketsDAO.getTicketsByEventId(id);
                event.setTickets(tickets); // Gán vé vào sự kiện

                list.add(event);
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

    public void create(String name, String description, Date date, String location, Date createdAt) {
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

    public void update(int id, String name, String description, Date date, String location, Date created_at) {
        String sql = "UPDATE [dbo].[events] SET [name] = ?, [description] = ?, [date] = ?, [location] = ?, [create_at] = ? WHERE [id] = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDate(3, new java.sql.Date(date.getTime()));
            ps.setString(4, location);
            ps.setDate(5, new java.sql.Date(created_at.getTime()));
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public List<Tickets> loadTicketsByEventId(int eventID) {
//        String sql = "SELECT * FROM messages \n"
//                + "WHERE users_id = ?";
//
//        List<Message> list = new ArrayList<>();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, userId);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                int idDB = rs.getInt("id");
//                int userIdDB = rs.getInt("users_id");
//                String content = rs.getString("content");
//                boolean readed = rs.getBoolean("readed");
//                java.sql.Date realeaseDate = rs.getDate("release_date");
//
//                UserDAO userDao = new UserDAO();
//                User userById = userDao.getUserById(userId);
//
//                Message message = new Message(idDB, userById, content, readed, realeaseDate);
//
//                list.add(message);
//
//                return list;
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

    public static void main(String[] args) {
        EventsDAO dao = new EventsDAO();
        for (Event e : dao.getAll()) {
            System.out.println(e.getId() + "  " + e.getName());
            for (Tickets t : e.getTickets()) {
                System.out.println(t.getId() + "  ");
            }
        }
    }
}
