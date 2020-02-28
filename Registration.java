import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registration {
    public static void register(int id, String name, int leavebalance, int password, int age) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");


            String sql = "INSERT INTO Employee_Info1 VALUES (?,?,?,?,?);";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2,name);
            stmt.setInt(3,age);
            stmt.setInt(4,leavebalance);
            stmt.setInt(5,password);
            stmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}