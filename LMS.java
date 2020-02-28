import java.util.Scanner;

public class LMS {

    public static void main(String[] args){
        int x=1;
        Scanner sc = new Scanner(System.in);
        while(x==1) {

            System.out.println("Wlcome to Leave Management System");
            System.out.println("Type 1: Register");
            System.out.println("Type 2: Login");
            System.out.println("Type 3: Exit");
            System.out.print("Choice: ");
            int sel = sc.nextInt();
            if (sel==1){
                Operations o=new Operations();
                o.Register();
            }
            else if (sel==2){
                Operations o=new Operations();
                o.login();;
            }
            else if(sel==3){
                x=2;
            }
        }
    }
}
