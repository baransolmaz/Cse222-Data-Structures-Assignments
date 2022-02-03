import java.util.*;
class main {
    public static void main(String[] args){
        Company company= new Company("Gtu",4);System.out.println("-Company Created-");
        
        Admin admin=new Admin("Baran","Solmaz","admin","admin");System.out.println("-Admin Created-");
        admin.setCompany(company);

        Employee worker1=new Employee("worker1","worker1","mail1","password1");
        admin.addWorker(worker1);

        Employee worker2=new Employee("worker2","worker2","mail2","password2");
        admin.addWorker(worker2);

        Employee worker3=new Employee("worker3","worker3","mail3","password3");
        admin.addWorker(worker3);

        Employee worker4=new Employee("worker4","worker4","mail4","password4");
        admin.addWorker(worker4);

        Customer customer1=new Customer("customer1","customer1","mail1","password1");
        admin.getCompany().addCustomer(customer1);

        System.out.println("\n-------Information of the company------");
        companyInformation(admin.getCompany());

        System.out.println("----Sign in as New Customer----");
        newCustomer(admin.getCompany(),"newC","newC","newC","newC");

        System.out.println("\n----Log in as (not exist)Customer----");
        try {
            loginCustomer(admin.getCompany(),"not","not");
        } catch (Exception e) {
            System.out.println(e);
        }
       
        System.out.println("\n----Log in as (exist)Customer----");
        try {
            loginCustomer(admin.getCompany(),"newC","newC");
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("----Log in as (not exist)Employee----");
        try {
            loginEmployee(admin.getCompany(),"not","not");
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n----Log in as (exist)Employee----");
        try {
            loginEmployee(admin.getCompany(),"mail1","password1");
        } catch (Exception e) {
            System.out.println(e);
        }
       
        System.out.println("\n----Log in as (not exist)Admin----");
        try {
            loginAdmin(admin,"not","not");
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n----Log in as (exist)Admin----");
        try {
            loginAdmin(admin,"admin","admin");
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n-------Last Version Of The Company------");
        companyInformation(admin.getCompany());
    }
    private static void companyInformation(Company c){
        System.out.println("\nCompany Name: "+c.getCompanyName());
        System.out.println("Admin : "+c.getAdminName());
        System.out.println("Total Branch Number: "+c.getBranchNumber());
        System.out.println("Total Employee Number: "+c.getEmployeeNumber());
        System.out.println("Total Customer Number: "+c.getCustomerNumber());
        System.out.println("Total Furniture Number: "+c.getTotalFurnitureNumber()+"\n");
    }
    private static void newCustomer(Company c,String name,String surname,String mail,String pass){
        Customer custom = new Customer(name,surname,mail,pass); 
        c.addCustomer(custom);    
    }
    private static void loginCustomer(Company comp,String tempMail,String tempPass)throws Exception{
        for(int i=0;i<comp.getCustomerNumber();i++)//ϴ(n)
            if (tempMail.equals(comp.customers.get(i).getAccount().getMail())&&tempPass.equals(comp.customers.get(i).getAccount().getPassword()) ) {
                System.out.println("\t!! Successful Login !!");
                System.out.println("\n----Customer Actions----\n");
                customerActions(comp,comp.customers.get(i));
                return;
            } 
        throw new Exception("Wrong Mail or Password!");
    }
    private static void customerActions(Company company,Customer customer){
        System.out.println("\n--- See Furnitures---");
        company.seeFurnitures(1);
        
        System.out.println("\n--- Order Furniture---");
        company.orderFurnitures(1,customer,"Office Chair","model2","color2",3);

        System.out.println("\n--- Order Furniture---");
        company.orderFurnitures(1,customer,"Office Chair","model2","color2",100);
        
        System.out.println("\n--- See Previous Orders---");
        customer.showOrders();
        
        System.out.println("\n---Search Furniture---");
        company.searchFurniture("Chair");    
        
        System.out.println("\n--!! Customer Actions Completed !!--\n");
    }
    private static void loginEmployee(Company comp,String tempMail,String tempPass)throws Exception{
        Iterator<Branch> itr = comp.branches.iterator();
        while(itr.hasNext()) {
            Branch temp= itr.next();
            for(int i=0;i<temp.workers.size();i++)
                if (tempMail.equals(temp.workers.get(i).getAccount().getMail())&&tempPass.equals(temp.workers.get(i).getAccount().getPassword()) ) {
                    System.out.println("---Successful Login---");
                    employeeActions(comp,temp.workers.get(i).getBranchId());
                    return;
                }
        }
        throw new Exception("Wrong Mail or Password!");
    }
    private static void employeeActions(Company company,int branchId){
        System.out.println("\n---Employee Actions---");
        
        System.out.println("\n--See Stocks in Branch--");        
        company.seeFurnitures(branchId);
        
        System.out.println("\n-- Add New Customer--");
        company.addCustomer(new Customer("ok","ok","ok","ok"));
        
        System.out.println("\n-- (not exist)Customer Information--");
        try {
            company.customerInformation(999);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n-- (exist)Customer Information--");
        try {
            company.customerInformation(2);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("\n-- Sell Furniture To (not exist)Customer--");   
        company.sellFurniture(branchId,999,"Office Cabinet","model1","No_Color",3);       
         
        System.out.println("\n-- Sell Furniture To (exist)Customer--");   
        company.sellFurniture(branchId,2,"Office Cabinet","model1","No_Color",5);  
        
        System.out.println("\n-- Request Furniture--");
        company.makeRequests(branchId,"Bookcase","model1","No_Color",15);
                    
        System.out.println("--!! Employee Actions Completed !!--");
    
    }
    private static void loginAdmin(Admin admin,String tempMail,String tempPass)throws Exception{
        if (tempMail.equals(admin.getAccount().getMail())&&tempPass.equals(admin.getAccount().getPassword()) ){
            System.out.println("---Successful Login---");
            adminActions(admin);
        }else
            throw new Exception("Wrong Mail or Password!");
    }
    private static void adminActions(Admin admin){
        System.out.println("\n--Admin Actions--");

        System.out.println("\n-- See Requests --");
        admin.seeRequests();
        
        System.out.println("\n-- Accept Requests --");
        admin.acceptRequests();

        System.out.println("\n-- Add Employee --");
        admin.addWorker(new Employee("newE","newE","newE","newE"));
        
        System.out.println("\n-- Remove (not exist)Employee --");
        admin.removeWorker("yok","yok");
        
        System.out.println("\n-- Remove (exist)Employee --");
        admin.removeWorker("newE","newE");
                
        System.out.println("\n-- Add Branch --");
        admin.addBranch();
        
        System.out.println("-- Remove (not exist)Branch --");
        admin.removeBranch(999);
                    
        System.out.println("\n-- Remove (exist)Branch --");
        admin.removeBranch(2);

        System.out.println("--!! Admin Actions Completed !!--");
    }

}