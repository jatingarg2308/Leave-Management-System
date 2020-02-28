import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Operations {
    Scanner sc=new Scanner(System.in);
    public void requestLeave(int id, int days) {
        GetEmployeeInfo e=new GetEmployeeInfo();
        int lb=e.getLeaveBalance(id);
        if (lb-days>=0)
        {
            System.out.println("Leave Approved");
            lb-=days;
            Update_Leave_Balance ulb=new Update_Leave_Balance();
            ulb.update(id, lb);
        }
        else
        {
            System.out.println("Leave Not Approved!!");
            System.out.println("Leaves requested exceed the leave quota available");
        }
    }
    public void Register(){
        System.out.print("Please Enter the credention of new Employee ");
        GetEmployeeInfo e=new GetEmployeeInfo();
        int id = e.getLastID();
        System.out.println("Last ID registered: "+ id);
        id+=1;
        System.out.print("Name: ");
        String name=sc.nextLine();
        System.out.print("Age: ");
        int age=sc.nextInt();
        System.out.print("password: ");
        int pin=sc.nextInt();
        Registration r=new Registration();
        r.register(id, name,20,pin,age);
        System.out.println("Registered Successfully with id: "+ id);

    }
    public void login(){
        System.out.print("Please Enter id: ");
        int id= sc.nextInt();
        System.out.print("Enter Password: ");
        int pin=sc.nextInt();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id,password FROM employee_info1 ;" );
            HashMap<Integer, Integer> user=new HashMap<Integer, Integer>();
            while ( rs.next() ) {
                int xid = rs.getInt("id");
                int xpin=rs.getInt("password");
                user.put(xid,xpin);
            }
            if (pin==user.get(id)){
                System.out.println("Logged In!!");
                System.out.print("Please enter the days you want to take leave: ");
                int dy=sc.nextInt();
                requestLeave(id,dy);
            }
            else
            {
                System.out.println("Please Enter Valid id and password");
                login();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
