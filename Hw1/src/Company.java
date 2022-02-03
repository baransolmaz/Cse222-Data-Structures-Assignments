import java.util.*;
/**Company Class For System Implements Company Interface */
public class Company implements Company_i{
    private String companyName;
    private Human admin;
    private int branchNumber=0, employeeNumber=0, customerNumber=0;
    protected Employee workers[];
    protected Customer customers[];
    protected Furniture products[]={new OfficeChair(),new OfficeDesk(),new MeetingTable(),new Bookcase(),new OfficeCabinet()};
    private String requests[][];
    /**Constructor */
    Company(String name,int branchNo){
        this.companyName=name;
        for(int i=0;i<branchNo;i++)
            this.addBranch();
        workers =new Employee[1];
        requests=new String[1][2];
        requests[0][0]=new String("Bookcase");
        requests[0][1]=new String("100");
    }
    /** Keeps Information About Admin*/
    @Override
    public void makeAdmin(Human h){ this.admin=h; }
    /**Increase Branch Number By One */
    @Override
    public void addBranch(){ this.branchNumber++; }
    /**Decrease Branch Number By One */
    @Override
    public void removeBranch(){ this.branchNumber--; }
    /**Return Company Name*/
    @Override
    public String getCompanyName(){ return this.companyName; }
    /**Returns Admin's Information */ 
    @Override
    public String getAdminName(){  return getAdmin().getName()+" "+getAdmin().getSurname(); }
    /**Returns Admin*/
    @Override
    public Human getAdmin(){ return admin; }
    /**Returns Total Branch Number */
    @Override
    public int getBranchNumber(){ return this.branchNumber; }
    /**Adds Employee To Worker Array*/
    @Override
    public void addWorker(Employee e){
        Employee temp[]=new Employee[getEmployeeNumber()];
        for(int i=0;i<getEmployeeNumber();i++)
            temp[i]=new Employee(workers[i]);
        workers =new Employee[getEmployeeNumber()+1];
        for(int i=0;i<getEmployeeNumber();i++)
            workers[i]=new Employee(temp[i]); 
        workers[getEmployeeNumber()]= new Employee(e);
        employeeNumber++;
    }
    /**Removes Employee From Worker Array
     * If Employee Doesn't Exist Throws Exception
     * @param name
     * @param surname
     * @throws Exception
     */
    @Override
    public void removeWorker(String name,String surname)throws Exception{
        for(int i=0;i<getEmployeeNumber();i++)
            if (name.equals(workers[i].getName()) && surname.equals(workers[i].getSurname()) ){
                for (int j = i+1; j <getEmployeeNumber(); j++)
                    workers[i]=new Employee(workers[j]);
                employeeNumber--;
                return;
            }
        throw new Exception("No Matched Employee!");
    }
    /**Adds Customer To Customer Array*/
    @Override
    public void addCustomer(Customer c){
        Customer temp[]=new Customer[getCustomerNumber()];
        for(int i=0;i<getCustomerNumber();i++)
            temp[i]=new Customer(customers[i]);
        customers =new Customer[getCustomerNumber()+1];
        for(int i=0;i<getCustomerNumber();i++)
            customers[i]=new Customer(temp[i]); 
        customers[getCustomerNumber()]= new Customer(c);
        customerNumber++;
        System.out.println("Customer Added!");
    }
    /**Returns Total Employee Number */
    @Override
    public int getEmployeeNumber(){  return this.employeeNumber;    }
    /**Returns Total Customer Number */
    @Override
    public int getCustomerNumber(){ return this.customerNumber; }
    /**Returns Total Furniture Number */
    @Override
    public int getTotalFurnitureNumber(){
        int total=0;
        for (int i = 0; i < products.length; i++)
            total+=products[i].getStockNumber();
        return total;
    }
    /**Increases Stock Number By The Requested Amount*/
    @Override
    public void acceptRequests(){
        for (int i = 0; i < requests.length; i++)
            for (int j = 0; j < products.length; j++)
                if (requests[i][0].equals(products[j].getType()))
                    products[j].increaseStock(Integer.parseInt(requests[i][1]));
        requests=new String[1][2];
        System.out.println("\nRequests Accepted! ");
    }
    /**Takes Requests From Employee and Adds The Request Array */
    @Override
    public void makeRequests(){
        Scanner scan =new Scanner(System.in);
        for (int i = 0; i < products.length; i++)
            System.out.println(products[i].getType()+"\tModel No: "+products[i].getModelNumber()+"\tStock: "+products[i].getStockNumber());
        System.out.print("Requested Type: ");
        String type=scan.nextLine();
        System.out.print("Requested Amount: "); 
        String amount=scan.nextLine();

        String temp[][]=new String[requests.length][2];
        for (int i = 0; i < requests.length; i++) {
            temp[i]=new String[2];
            for (int j = 0; j < 2; j++)
                temp[i][j]= new String(requests[i][j]);               
        }
        requests=new String[temp.length+1][2];
        for (int i = 0; i <temp.length; i++) {
            requests[i]=new String[2];
            for (int j = 0; j < 2; j++)
                requests[i][j]= new String(temp[i][j]);               
        }
        requests[temp.length] =new String[2];
        requests[temp.length][0]=new String(type);
        requests[temp.length][1]=new String(amount); 
        System.out.println("\nRequested From Admin!");
    }
    /**
     * OverLoad of void makeRequests() 
     * @param type
     * @param amount
     */
    @Override
    public void makeRequests(String type,String amount){
        String temp[][]=new String[requests.length][2];
        for (int i = 0; i < requests.length; i++) {
            temp[i]=new String[2];
            for (int j = 0; j < 2; j++)
                temp[i][j]= new String(requests[i][j]);     
        }
        requests=new String[temp.length+1][2];
        for (int i = 0; i <temp.length; i++) {
            requests[i]=new String[2];
            for (int j = 0; j < 2; j++) 
                requests[i][j]= new String(temp[i][j]);               
        }
        requests[temp.length] =new String[2];
        requests[temp.length][0]=new String(type);
        requests[temp.length][1]=new String(amount); 
        System.out.println("\nCompany Informed!\n");
    }
    /**Shows Requests To Admin */
    @Override
    public void seeRequests() {
        System.out.println("\nRequests: ");
        for (int i = 0; i < requests.length; i++) {
            System.out.println(requests[i][0]+"\tAmount: "+requests[i][1]);
        } 
    }
    /**Sells Furniture to Customer By Employee*/
    @Override
    public void sellFurniture(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nType: ");
        String temp=scan.nextLine();
        for (int i = 0; i < products.length; i++)
            if (temp.equals(products[i].getType())){
                for (String model :products[i].getModels())//prints models
                    System.out.print(model+", ");
                System.out.print("\nModel: ");
                temp=scan.nextLine();
                for (String model :products[i].getModels())
                    if (temp.equals(model)){
                        for (String color : products[i].getColors())//prints colors
                            System.out.print(color+", ");
                        
                        System.out.print("\nColor: ");
                        temp=scan.nextLine();
                        for (String color :products[i].getColors())
                            if (temp.equals(color)){
                                System.out.println("Total Amount: "+products[i].getStockNumber());
                                int amount=scan.nextInt();
                                System.out.println("Customer Id: ");
                                int id=scan.nextInt();
                                for (Customer customer : customers)
                                    if(id==customer.getId()){
                                        try {
                                            products[i].decreaseStock(amount);
                                            customer.buyFurniture(products[i].getType(), model, color,amount);
                                            System.out.println("-Furniture Sent-");
                                        }catch (Exception e){
                                            System.out.println(e.getMessage());
                                            if(amount>0)//Means "Not Enough Stock"
                                                makeRequests(products[i].getType(),Integer.toString(amount));
                                        }
                                        return;
                                    }
                                System.err.println("Customer Does Not Exist!");
                            }
                    }
            }
    }
    /**Shows Customer's Previous Orders By Customer Id */
    @Override
    public void customerInformation()throws Exception{
        Scanner scan=new Scanner(System.in);
        System.out.println("Customer Id: ");
        int id=scan.nextInt();
        for (Customer customer : customers)
            if(id==customer.getId()){
                System.out.println("Customer Name: "+customer.getName()+"\tSurname: "+customer.getSurname());
                customer.showOrders();
                return;
            }
        throw new Exception("Customer Id Couldn't Found!");
    }
    /**Shows All Furnitures */
    @Override
    public void seeFurnitures(){
        for (int i = 0; i < products.length; i++)
            System.out.println(products[i].getType()+"\tModel No: "+products[i].getModelNumber()+"\tStock: "+products[i].getStockNumber());
    }
    /**
     * Customer Can Order Furniture By Logging In To System 
     * @param customer
     */
    @Override
    public void orderFurnitures(Customer customer){
        Scanner scan=new Scanner(System.in);
        System.out.print("Furniture Type: ");
        String temp=scan.nextLine();
        for (int i = 0; i < products.length; i++)
            if (temp.equals(products[i].getType())){
                for (String model :products[i].getModels())
                    System.out.print(model+", ");
                System.out.print("\nFurniture Model: ");
                temp=scan.nextLine();
                for (String model :products[i].getModels())
                    if (temp.equals(model)){
                        for (String color : products[i].getColors())
                            System.out.print(color+", ");
                        System.out.print("\nFurniture Color: ");
                        temp=scan.nextLine();
                        for (String color :products[i].getColors())
                            if (temp.equals(color)){
                                System.out.println("Total Furniture Amount: "+products[i].getStockNumber());
                                int amount=scan.nextInt();
                                customer.setAdressPhone();
                                try {
                                    products[i].decreaseStock(amount);
                                    customer.buyFurniture(products[i].getType(), model, color,amount);
                                }catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    if(amount>0)//Means "Not Enough Stock"
                                        makeRequests(products[i].getType(),Integer.toString(amount));
                                    return;
                                }
                                
                            }
                    }
            }  
    }
    /**Search for keyword */
    @Override
    public void searchFurniture() {
        Scanner scan=new Scanner(System.in);
        System.out.print("Keyword For Search: ");
        String keyword=scan.nextLine();
        System.out.println("Search Results: ");
        for (Furniture product : products) {
            if (product.getType().contains(keyword))
                System.out.println(product.getType()+"\tModel No: "+product.getModelNumber()+"\tColor No: "+product.getColors().length+"\tStock Amount: "+product.getStockNumber());
            for (String model : product.getModels()) {
                if (model.contains(keyword))
                    System.out.println(product.getType()+"\tModel: "+model+"\tColor No: "+product.getColors().length+"\tStock Amount: "+product.getStockNumber());
            }
            for (String color : product.getColors()) {
                if (color.contains(keyword))
                    System.out.println(product.getType()+"\tModel No: "+product.getModelNumber()+"\tColor: "+color+"\tStock Amount: "+product.getStockNumber());
            }  
        }
    }

}