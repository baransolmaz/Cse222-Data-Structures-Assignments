import java.util.*;

/** Company Class For System Implements Company Interface */
public class Company implements Company_i {
    private String companyName;
    private Human admin;
    protected KWLinkedList<Branch> branches;
    private int branchNumber = 0, employeeNumber = 0, customerNumber = 0;
    protected KWArrayList<Customer> customers;

    /** Constructor */
    Company(String name, int branchNo) {
        this.companyName = name;
        branches = new KWLinkedList<Branch>();
        for (int i = 0; i < branchNo; i++)
            this.addBranch();
        customers = new KWArrayList<Customer>();
    }
    /** Keeps Information About Admin */
    @Override
    public void makeAdmin(Human h) {
        this.admin = h;
    }
    /** Increase Branch Number By One */
    @Override
    public void addBranch() {
        branches.add(branchNumber, new Branch());
        this.branchNumber++;
    }
    /** Decrease Branch Number By One */
    @Override
    public void removeBranch(int id) throws Exception {
        ListIterator<Branch> itr = branches.listIterator();
        while (itr.hasNext()) 
            if (id == itr.next().getId()) {
                itr.remove();
                this.branchNumber--;
                return;
            }
        throw new Exception("No Matched Branch!");
    }

    /** Return Company Name */
    @Override
    public String getCompanyName() {
        return this.companyName;
    }

    /** Returns Admin's Information */
    @Override
    public String getAdminName() {
        return getAdmin().getName() + " " + getAdmin().getSurname();
    }

    /** Returns Admin */
    @Override
    public Human getAdmin() {
        return admin;
    }

    /** Returns Total Branch Number */
    @Override
    public int getBranchNumber() {
        return this.branchNumber;
    }

    /** Adds Employee To Worker Array */
    @Override
    public void addWorker(Employee e) {
        int index = getEmployeeNumber() % getBranchNumber();
        branches.get(index).workers.add(e);
        employeeNumber++;
    }

    /**
     * Removes Employee From Worker Array If Employee Doesn't Exist Throws Exception
     * 
     * @param name
     * @param surname
     * @throws Exception
     */
    @Override
    public void removeWorker(String name, String surname) throws Exception {
        for (Branch branch : branches) {
            for (int i = 0; i < branch.workers.size(); i++)
                if (name.equals(branch.workers.get(i).getName())
                        && surname.equals(branch.workers.get(i).getSurname())) {
                    for (int j = i + 1; j < branch.workers.size(); j++)
                        branch.workers.set(i, branch.workers.get(j));
                    employeeNumber--;
                    return;
                }
        }
        throw new Exception("No Matched Employee!");
    }

    /** Adds Customer To Customer Array */
    @Override
    public void addCustomer(Customer c) {
        customers.add(c);
        customerNumber++;
        System.out.println("Customer Added!");
    }

    /** Returns Total Employee Number */
    @Override
    public int getEmployeeNumber() {
        return this.employeeNumber;
    }

    /** Returns Total Customer Number */
    @Override
    public int getCustomerNumber() {
        return this.customerNumber;
    }

    /** Returns Total Furniture Number */
    @Override
    public int getTotalFurnitureNumber() {
        int total = 0;
        for (Branch branch : branches)
            total += branch.products.size();
        return total;
    }

    /** Increases Stock Number By The Requested Amount */
    @Override
    public void acceptRequests() {
        for (Branch branch : branches) {
            for (int i = 0; i < branch.requests.size(); i++)
                branch.products.add(branch.requests.get(i));
            branch.requests = new HybridList<Product>();
        }
        System.out.println("\nRequests Accepted! ");
    }
    /**
     * OverLoad of void makeRequests()
     * 
     * @param type
     * @param amount
     */
    @Override
    public void makeRequests(int brancId, String type, String model, String color, int amount) {
        for (Branch branch : branches)
            if (brancId == branch.getId()){
                for (int i = 0; i < amount; i++)
                    branch.requests.add(new Product(type, model, color));

                System.out.println("\nCompany Informed!\n");
            }
    }
    /** Shows Requests To Admin */
    @Override
    public void seeRequests() {
        System.out.println("\nRequests: ");
        for (Branch branch : branches)
            for (int i = 0; i < branch.requests.size(); i++) 
                System.out.println(branch.requests.get(i).toString());
    }
    /**Sells Furniture to Customer By Employee*/
    @Override
    public void sellFurniture(int branchId,int customerId,String tType,String tModel,String tColor,int amount){
        int total=0;
        for (Branch branch : branches)
            if (branchId==branch.getId()) {
                System.out.println("\nCustomer Id: "+customerId+"\nType: "
                +tType+"\nModel: "+tModel+"\nColor: "+tColor+"\nAmount: "+amount);
                for (int k=0;k<customers.size();k++)
                    if(customerId==customers.get(k).getId()){            
                        for (int i = 0; i < branch.products.size(); i++)
                            if (total != amount) {
                                Product p=branch.products.get(i);
                                if (tType.equals(p.getType()) && 
                                tModel.equals(p.getModel())&&tColor.equals(p.getColor())){
                                    total++;
                                    customers.get(k).buyFurniture(p.getType(),p.getModel(),p.getColor());
                                    branch.products.remove(i);
                                }
                            }else{
                                System.out.println("-Furniture Sent-");
                                return;    
                            }
                        System.out.println("Total Amount: "+total);
                        System.out.println("The Amount To Be Sold: "+amount);
                        makeRequests(branchId, tType, tModel, tColor, amount);
                        System.out.println("Sold: " + total + " Requested Amount: " + (amount - total));
                        return;
                    }
                System.err.println("Customer Does Not Exist!");
                return;
            }
        System.err.println("Branch Does Not Exist!");
    }

    /** Shows Customer's Previous Orders By Customer Id */
    @Override
    public void customerInformation(int id) throws Exception {
        System.out.println("Customer Id: " + id);
        for (int i = 0; i < customers.size(); i++)
            if (id == customers.get(i).getId()) {
                System.out.println("Customer Name: " + customers.get(i).getName() 
                + "\tSurname: " + customers.get(i).getSurname());
                customers.get(i).showOrders();
                return;
            }
        throw new Exception("Customer Id Couldn't Found!");
    }
    /** Shows All Furnitures */
    @Override
    public void seeFurnitures(int branchId) {
        for (Branch branch : branches)
            if (branchId == branch.getId())
                for (int i = 0; i < branch.products.size(); i++)
                    System.out.println(branch.products.get(i).toString());
    }
    /**
     * Customer Can Order Furniture By Logging In To System
     * 
     * @param customer
     */
    @Override
    public void orderFurnitures(int branchId, Customer customer, String tType, 
                                String tModel, String tColor,int amount) {
        int total = 0;
        for (Branch branch : branches)
            if (branchId == branch.getId()) {
                System.out.println("Furniture Type: " + tType);
                System.out.println("Furniture Model: " + tModel);
                System.out.println("Furniture Color: " + tColor);
                System.out.println("Wanted Amount: " + amount);
                for (int i = 0; i < branch.products.size(); i++) {
                    if (total != amount) {
                        Product p = branch.products.get(i);
                        if (tType.equals(p.getType()) && tModel.equals(p.getModel()) 
                            && tColor.equals(p.getColor())) {
                            total++;
                            customer.buyFurniture(p.getType(), p.getModel(), p.getColor());
                            branch.products.remove(i);
                            i--;
                        }
                    } else {
                        System.out.println("Sold: " + total);
                        System.out.println("-Order Completed-");
                        return;
                    }
                }
                System.out.println("\nTotal Furniture Amount: " + total);
                System.out.println("Wanted: " + amount);
                makeRequests(branchId, tType, tModel, tColor, amount);
                System.out.println("Sold: " + total + " Requested Amount: " + (amount - total));
                System.out.println("-Order Completed-");
            }
    }
    /** Search for keyword */
    @Override
    public void searchFurniture(String keyword) {
        System.out.println("Keyword: " + keyword);
        System.out.println("Search Results: ");
        for (Branch branch : branches) {
            System.out.println("--Branch " + branch.getId() + " --");
            for (int i = 0; i < branch.products.size(); i++) {
                Product p = branch.products.get(i);
                if (p.getType().contains(keyword) || p.getModel().contains(keyword)
                 || p.getColor().contains(keyword))
                    System.out.println(p.toString());
            }
        }

    }
}