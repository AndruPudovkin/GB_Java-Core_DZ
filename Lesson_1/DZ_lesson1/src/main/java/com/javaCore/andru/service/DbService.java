package com.javaCore.andru.service;

import com.javaCore.andru.madel.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {

    public DbService() {
    }

    //Подключение к БД
    public Connection connectionDbMySQL(String url, String userName, String pass) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, userName, pass);
        if (connection!=null) {
            System.out.println("Соединение установленно");
        }else System.out.println("Соединение не установленно");
        return connection;
    }

    public void createTabl(Connection connection) throws SQLException {
        Statement statement;
        String query = "CREATE TABLE IF NOT EXISTS employees" + "(\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    public void useDataBase(Connection connection, String nameDatabase) throws SQLException {
        Statement statement;
        String query = "USE " + nameDatabase;
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    public List<Employee> getAllEmployee(Connection connection, String tableName) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        String quary = "SELECT * FROM " + tableName;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(quary);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            employeeList.add(new Employee(id,name, age));
        }
        return employeeList;
    }

    public void addEmployeeInDb(Connection connection, String tablnName, Employee employee) throws SQLException {
        Statement statement;
        String query = String.format("INSERT INTO %s (`name`, `age`) VALUES ('%s', '%s');",
                                    tablnName, employee.getName(), employee.getAge());
        statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Новая запись внесена");
    }
}
