import java.util.*;
/**Account Class For System Implements Account Interface*/
public class Account implements Account_i{
    private String mail,password;
    /**Sets Mail Address */
    @Override
    public void setMail(String mail){ this.mail=mail; }
    /**Sets Password */
    @Override
    public void setPassword(String password){ this.password=password; }
    /**Returns Mail Address */
    @Override
    public String getMail(){ return this.mail; }
    /**Returns Password */
    @Override
    public String getPassword(){ return this.password; }
    /**Constructor For Account */
    Account(){
        String temp;
        Scanner scan = new Scanner(System.in);
        System.out.print("E-mail Address: ");
        temp = scan.nextLine();
        this.setMail(temp);
        
        System.out.print("Password: ");
        temp= scan.nextLine();
        this.setPassword(temp);
    }
    /**Constructor For Account */
    Account(String mail,String password){
        this.setMail(mail);
        this.setPassword(password);
    }
    /**Constructor For Account */
    Account(Account a){
        this.setMail(a.getMail());
        this.setPassword(a.getPassword());
    }
}
