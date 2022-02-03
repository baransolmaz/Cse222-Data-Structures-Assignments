import java.util.Scanner;
/**Admin Class Extends Human Class */
public class Admin extends Human implements Admin_i{
    private Human admin;
    private Account account;
    private Company company;
    /**Basic Constructor */
    Admin(){
        super();
        account=new Account();
    }
    /**Advanced Constructor */
    Admin(String name,String surname,String mail,String password){
        super(name,surname);
        account=new Account(mail,password);
    }
    /**Returns Company Of Admin */
    @Override
    public Company getCompany(){
        return this.company;
    }
    /**Sets The Admin's Company*/
    public void setCompany(Company c){
        c.makeAdmin(this);
        this.company=c;
    }
    /**Adds Employee To Company */
    @Override
    public void addWorker(Employee e){
        company.addWorker(e);
        System.out.println("\nEmployee Added!\n");
    }
    /**Removes Employee From Company */
    @Override
    public void removeWorker(){
        Scanner scan=new Scanner(System.in);
        System.out.print("Employee Name: ");
        String name=scan.nextLine();
        System.out.print("Employee Surname: ");
        String surname=scan.nextLine();
        try {
            company.removeWorker(name,surname);
            System.out.println("\nEmployee Removed!\n");    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    /**Adds Customer To Company*/
    @Override
    public void addCustomer(Customer c){
        company.addCustomer(c);
        System.out.println("\nCustomer Added!\n");
    }
    /**Returns Admin's Account*/
    @Override
    public Account getAccount(){
        return account;
    }
     /**Adds One Branch To Company */
    @Override
    public void addBranch(){
        company.addBranch();
        System.out.println("\nBranch Added!\n");
    }
     /**Removes One Branch From Company */
     @Override
    public void removeBranch(){
        company.removeBranch();
        System.out.println("\nBranch Removed!\n");
    }
    /**Accepts The Requests From Customers  */
    @Override
    public void acceptRequests(){
        company.acceptRequests();
    }
    /**Shows All Requests From Customers */
    @Override
    public void seeRequests(){
        company.seeRequests();
    }
}