import java.util.*;
class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String choice="0";

        Company company= new Company("Gtu",4);System.out.println("-Company Created-");
        
        Admin admin=new Admin("Baran","Solmaz","admin","admin");System.out.println("-Admin Created-");
        admin.setCompany(company);

        Employee worker1=new Employee("worker1","worker1","mail1","password1");
        admin.addWorker(worker1);

        Customer customer1=new Customer("customer1","customer1","mail1","password1");
        admin.getCompany().addCustomer(customer1);

        do{
            System.out.println("\n(1) Show the information of the company");
            System.out.println("(2) Log in as Admin");
            System.out.println("(3) Log in as Employee");
            System.out.println("(4) Log in as Customer");
            System.out.println("(5) Sign in as New Customer");
            System.out.println("(0) Exit\n");
            System.out.println("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                    {
                        case "1":companyInformation(admin.getCompany());
                            break;
                        case "2":loginAdmin(admin);
                            break;
                        case "3":loginEmployee(admin.getCompany());
                            break;
                        case "4":loginCustomer(admin.getCompany());
                            break;
                        case "5":newCustomer(admin.getCompany());
                            break;
                        case "0":System.out.println("Exiting.");
                            break;
                        default:System.out.println("Wrong choice! Try again!");
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (!choice.equals("0"));
        
    }
    private static void newCustomer(Company c){
        Customer custom = new Customer(); 
        c.addCustomer(custom);    
    }
    private static void companyInformation(Company c){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nCompany Name: "+c.getCompanyName());
        System.out.println("Admin : "+c.getAdminName());
        System.out.println("Total Branch Number: "+c.getBranchNumber());
        System.out.println("Total Employee Number: "+c.getEmployeeNumber());
        System.out.println("Total Customer Number: "+c.getCustomerNumber());
        System.out.println("Total Furniture Number: "+c.getTotalFurnitureNumber());
        System.out.print("\nEnter Something to Go Main Menu:"); scan.nextLine();
    }
    private static void loginAdmin(Admin admin)throws Exception{
        Scanner scan = new Scanner(System.in);    
        String tempMail, tempPass;
        System.out.print("Mail: ");
        tempMail= scan.nextLine();                   
        System.out.print("Password: ");
        tempPass= scan.nextLine();
        if (tempMail.equals(admin.getAccount().getMail())&&tempPass.equals(admin.getAccount().getPassword()) ){
            adminMenu(admin);
        }else
            throw new Exception("Wrong Mail or Password!");
    }
    private static void adminMenu(Admin admin){
        Scanner scan = new Scanner(System.in);
        String choice="0";
        System.out.println("\n\nWelcome!");
        do {
            System.out.println("\n(1) Add Employee");
            System.out.println("(2) Remove Employee");
            System.out.println("(3) Add Branch");
            System.out.println("(4) Remove Branch");
            System.out.println("(5) See Requests");
            System.out.println("(6) Accept Requests");

            System.out.println("(0) Log Out and Return Main Menu\n");
            System.out.println("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                {
                    case "1":admin.addWorker(new Employee());
                        break;
                    case "2":admin.removeWorker();
                        break;
                    case "3":admin.addBranch();
                        break;
                    case "4":admin.removeBranch();
                        break;
                    case "5":admin.seeRequests();
                        break;
                    case "6":admin.acceptRequests();
                        break;
                    case "0":System.out.println("Logging Out!\n");
                        break;
                    default:System.out.println("Wrong choice! Try again!");
                        break;
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (!choice.equals("0"));
        
    }
    private static void loginEmployee(Company comp)throws Exception{
        Scanner scan = new Scanner(System.in);
        String tempMail, tempPass;
        System.out.print("Mail: ");
        tempMail= scan.nextLine();                   
        System.out.print("Password: ");
        tempPass= scan.nextLine();
        for(int i=0;i<comp.getEmployeeNumber();i++)
            if (tempMail.equals(comp.workers[i].getAccount().getMail())&&tempPass.equals(comp.workers[i].getAccount().getPassword()) ) {
                employeeMenu(comp);
                return;
            }
        throw new Exception("Wrong Mail or Password!");
    }
    private static void employeeMenu(Company company){
        Scanner scan = new Scanner(System.in);
        String choice="0";
        System.out.println("\nWelcome!");
        do {
            try {
                System.out.println("\n(1) See Stocks");
                System.out.println("(2) Sell Furniture");
                System.out.println("(3) Request Furniture");
                System.out.println("(4) Customer Information");
                System.out.println("(5) Add New Customer");

                System.out.println("(0) Log Out and Return Main Menu\n");
                System.out.println("Choice: ");
                choice=scan.nextLine();
                switch(choice)
                {
                    case "1":company.seeFurnitures();
                        break;
                    case "2":company.sellFurniture();
                        break;
                    case "3":company.makeRequests();
                        break;
                    case "4":company.customerInformation();
                        break;
                    case "5":company.addCustomer(new Customer());
                        break; 
                    case "0":System.out.println("Logging Out!\n");
                        break;
                    default:System.out.println("Wrong choice! Try again!");
                        break;
                }    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!choice.equals("0"));  
    }
    private static void loginCustomer(Company comp)throws Exception{
        Scanner scan=new Scanner(System.in);
        String tempMail, tempPass;
        System.out.print("Mail: ");
        tempMail= scan.nextLine();                   
        System.out.print("Password: ");
        tempPass= scan.nextLine();
        for(int i=0;i<comp.getCustomerNumber();i++)
            if (tempMail.equals(comp.customers[i].getAccount().getMail())&&tempPass.equals(comp.customers[i].getAccount().getPassword()) ) {
                customerMenu(comp,comp.customers[i]);
                return;
            } 
        throw new Exception("Wrong Mail or Password!");
    }
    private static void customerMenu(Company company,Customer customer){
        Scanner scan=new Scanner(System.in);
        String choice="0";
        System.out.println("\nWelcome!");
        do{
            System.out.println("\n(1) See Furnitures");
            System.out.println("(2) Search Furniture");
            System.out.println("(3) Order Furniture");
            System.out.println("(4) See Previous Orders");

            System.out.println("(0) Log Out and Return Main Menu\n");
            System.out.println("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                {
                    case "1":company.seeFurnitures();
                        break;
                    case "2":company.searchFurniture();
                        break;
                    case "3":company.orderFurnitures(customer);
                        break;
                    case "4":customer.showOrders();
                        break;                    
                    case "0":System.out.println("Logging Out!\n");
                        break;
                    default:System.out.println("Wrong choice! Try again!");
                        break;
                } 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!choice.equals("0"));  
    }
    
}