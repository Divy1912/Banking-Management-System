package bankingManager;

import java.sql.*;
import java.util.Scanner;

public class User {
    private Connection con;
    private Scanner sc;
    User(Connection con, Scanner sc){
        this.con = con;
        this.sc = sc;
    }

    public void register(){
        sc.nextLine();
        System.out.print("Full Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if(user_exist(email)){
            System.out.println("User already exist for this email. Please login!");
            return;
        }
        try{
            String query = "insert into user(full_name,email,password) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            int rowAffected = ps.executeUpdate();
            if (rowAffected>0){
                System.out.println("User registered successfully!");
            }
            else {
                System.out.println("User registration failed");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String login(){
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        try{
            String query = "select * from user where email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()){
                return email;
            }else return null;
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean user_exist(String email){
        try{
            String query = "select * from user where email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


}
