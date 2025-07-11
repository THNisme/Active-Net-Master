/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductCategory;
import utils.DBContext;

/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class ProductDAO extends DBContext {

    public ProductDAO() {
        super();
    }

    public List<Product> getAll() {
        String sql = "SELECT   products.*, product_categories.name AS category_name, product_categories.description AS category_des \n"
                + "FROM     products INNER JOIN product_categories ON products.category_id = product_categories.id";
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int catId = rs.getInt("category_id");
                String catName = rs.getString("category_name");
                String catDes = rs.getString("category_des");

                ProductCategory cate = new ProductCategory(catId, catName, catDes);

                int proId = rs.getInt("id");
                String proName = rs.getString("name");
                String proDes = rs.getString("description");
                long proPrice = rs.getLong("price");
                int proQuan = rs.getInt("stock");
                String proImgUrl = rs.getString("image_url");

                list.add(new Product(proId, proName, proDes, proPrice, proQuan, proImgUrl, cate));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void create(String pName, String pDes, long pPrice, int pQuantity, String pImgUrl, int cateID) {
        String sql = "INSERT INTO [dbo].[products] ([name],[description],[price],[stock],[image_url],[category_id]) \n"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

//            int nextId = getNextId();
//            
//            ps.setInt(1, nextId);
            ps.setString(1, pName);
            ps.setString(2, pDes);
            ps.setLong(3, pPrice);
            ps.setInt(4, pQuantity);
            ps.setString(5, pImgUrl);
            ps.setInt(6, cateID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Product getProductById(int id) {
        String sql = "SELECT   products.*, product_categories.name AS category_name, product_categories.description AS category_des \n"
                + "FROM     products INNER JOIN product_categories ON products.category_id = product_categories.id \n"
                + "Where [products].[id] = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int catId = rs.getInt("category_id");
                String catName = rs.getString("category_name");
                String catDes = rs.getString("category_des");

                ProductCategory cate = new ProductCategory(catId, catName, catDes);

                int proId = rs.getInt("id");
                String proName = rs.getString("name");
                String proDes = rs.getString("description");
                long proPrice = rs.getLong("price");
                int proQuan = rs.getInt("stock");
                String proImgUrl = rs.getString("image_url");

                return new Product(proId, proName, proDes, proPrice, proQuan, proImgUrl, cate);
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
    
    public void update(int pID, String pName, String pDes, long pPrice, int pQuantity, String pImgUrl, int cateID) {
        String sql = "UPDATE [dbo].[products] SET [name] = ?, [description] = ?, [price] = ?, [stock] = ?, [image_url] = ?, [category_id] = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, pName);
            ps.setString(2, pDes);
            ps.setLong(3, pPrice);
            ps.setInt(4, pQuantity);
            ps.setString(5, pImgUrl);
            ps.setInt(6, cateID);
            ps.setInt(7, pID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        dao.update(1, "Iphone", "Apple", 12000000, 10, "images/iphone", 2);
        
        List<Product> list = dao.getAll();

        for (Product p : list) {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
            System.out.println(p.getPrice());
            System.out.println(p.getStock());
            System.out.println(p.getImageUrl());
            System.out.println(p.getCategory().getName());
            System.out.println("");
        }

    }

}
