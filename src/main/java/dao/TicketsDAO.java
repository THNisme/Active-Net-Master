/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.TicketCategories;
import model.Tickets;
import utils.DBContext;

/**
 *
 * @author Hieu
 */
public class TicketsDAO extends DBContext {

    public TicketsDAO() {
        super();
    }

    public List<Tickets> getAll() {
        String sql = "select * from[dbo].[tickets]";
        List<Tickets> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int tikId = rs.getInt("id");
                int eventId = rs.getInt("event_id");
                EventsDAO eventDao = new EventsDAO();
                Event e = eventDao.getEventById(eventId);
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String img = rs.getString("image_url");
                int cateId = rs.getInt("category_id");
                TicketsCategoriesDAO ticketDao = new TicketsCategoriesDAO();
                TicketCategories t = ticketDao.getTicketCategoryById(cateId);
                Tickets ticket = new Tickets(tikId, e, name, price, quantity, img, t);

                list.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
