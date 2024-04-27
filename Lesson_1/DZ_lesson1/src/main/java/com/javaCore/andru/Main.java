package com.javaCore.andru;

import com.javaCore.andru.madel.Employee;
import com.javaCore.andru.service.DbService;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {

        Thread.sleep(30000);

        String url = "jdbc:mysql://employeeMysql-db:3306";
        String userName = "root";
        String pass = "password";
        String nameDb = "employeesDb";
        String tableName = "employees";


        Random random = new Random();
        DbService dbService = new DbService();
        Connection connection = dbService.connectionDbMySQL(url, userName, pass);
        dbService.useDataBase(connection, nameDb);
        dbService.createTabl(connection);

        int count = random.nextInt(1, 10);
        for (int i = 0; i < count; i++) {
            dbService.addEmployeeInDb(connection, tableName, Employee.create());
        }
        List<Employee> employees = dbService.getAllEmployee(connection, tableName);
        for (Employee employee: employees){
            System.out.println(employee);
        }
        connection.close();

    }
}


