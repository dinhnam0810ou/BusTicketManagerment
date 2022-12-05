/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndn.service;

import com.ndn.config.JdbcUtils;
import com.ndn.pojo.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Dinh Nam
 */
public class CategoryService {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try(Connection connection = JdbcUtils.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from category");
            while(resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                categories.add(category);
            }
        } catch (Exception e) {
        }
        return categories;
    }
}
