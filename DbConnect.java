import java.sql.*;
public class DbConnect {
    public static void main(String[] args){
        Connection c=null;
        Statement stmt=null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "password");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Employee_Info1 " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           varchar(100)  NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " Leave_available int    Not Null," +
                    " password       int     not Null)" ;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": :+e.getMessage()");
            System.exit(0);
        }
        System.out.println("Opened Database Successfully");
    }
}