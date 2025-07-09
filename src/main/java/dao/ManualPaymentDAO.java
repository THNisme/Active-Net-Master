/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import model.ManualPayment;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class ManualPaymentDAO extends DBContext {

    public ManualPayment getManualPaymentByOrderId(int orderId) {
        String sql = "SELECT * FROM manual_payments WHERE order_id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ManualPayment(
                        rs.getInt("id"),
                        null, // sẽ gán Order từ OrderDAO
                        rs.getString("transfer_content"),
                        rs.getBoolean("confirmed_by_admin"),
                        rs.getTimestamp("confirmed_at") != null
                        ? new Date(rs.getTimestamp("confirmed_at").getTime())
                        : null
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ManualPayment getManualPaymentById(int id) {
        String sql = "SELECT * FROM manual_payments WHERE id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ManualPayment(
                        id,
                        null,
                        rs.getString("transfer_content"),
                        rs.getBoolean("confirmed_by_admin"),
                        rs.getTimestamp("confirmed_at") != null
                        ? new Date(rs.getTimestamp("confirmed_at").getTime())
                        : null
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertManualPayment(ManualPayment mp) {
        String sql = "INSERT INTO manual_payments (order_id, transfer_content, confirmed_by_admin, confirmed_at) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mp.getOrder().getId());
            ps.setString(2, mp.getTransferContent());
            ps.setBoolean(3, mp.isConfirmedByAdmin());
            ps.setTimestamp(4, mp.getConfirmedAt() != null
                    ? new Timestamp(mp.getConfirmedAt().getTime())
                    : null
            );
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateManualPayment(ManualPayment mp) {
        String sql = "UPDATE manual_payments SET transfer_content = ?, confirmed_by_admin = ?, confirmed_at = ? WHERE id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mp.getTransferContent());
            ps.setBoolean(2, mp.isConfirmedByAdmin());
            ps.setTimestamp(3, mp.getConfirmedAt() != null
                    ? new Timestamp(mp.getConfirmedAt().getTime())
                    : null
            );
            ps.setInt(4, mp.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteManualPayment(int id) {
        String sql = "DELETE FROM manual_payments WHERE id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
