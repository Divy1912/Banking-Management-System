package bankingManager;

import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection con;
    private Scanner sc;
    Accounts(Connection con, Scanner sc){
        this.con = con;
        this.sc = sc;
    }

    public long open_account(String email){
        if(!account_exist(email)){
            sc.nextLine();
            System.out.print("Enter full name: ");
            String name = sc.nextLine();
            System.out.print("Enter Initial amount: ");
            double amount = sc.nextDouble();
            System.out.print("Set pin: ");
            sc.nextLine();  // consume leftover newline
            String security_pin = sc.nextLine();

            String query = "insert into accounts(account_number,full_name,email,balance,security_pin) values(?,?,?,?,?)";
            long account_number = generateAccountNumber();
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setLong(1, account_number);
                ps.setString(2,name);
                ps.setString(3,email);
                ps.setDouble(4, amount);
                ps.setString(5,security_pin);
                int row = ps.executeUpdate();
                if (row>0){
                    return account_number;
                }
                else {
                    throw new RuntimeException("Account creation Failed");
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    throw new RuntimeException("Account already exists!!");
    }

    public long generateAccountNumber(){
        String query = "select account_number from accounts order by account_number desc limit 1";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                long number = rs.getLong("account_number");
                return number+1;
            }
            else return 10000100;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    return 10000100;
    }

    public boolean account_exist(String email){
        try{
            String query = "select account_number from accounts where email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet row = ps.executeQuery();
            if(row.next()){
                return true;
            }
            else {
                return false;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public long getAccountNumber(String email){
        String query = "select account_number from accounts where email = ?";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getLong("account_number");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Account Number Doesn't Exists");
    }
}
