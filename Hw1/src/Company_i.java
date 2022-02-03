/**Interface For Company Class */
public interface Company_i {
    /** Keeps Information About Admin*/
    public void makeAdmin(Human h);
    /**Increase Branch Number By One */
    public void addBranch();
    /**Decrease Branch Number By One */
    public void removeBranch();
    /**Return Company Name*/
    public String getCompanyName();
    /**Returns Admin's Information */ 
    public String getAdminName();
    /**Returns Admin*/
    public Human getAdmin();
    /**Returns Total Branch Number */
    public int getBranchNumber();
    /**Adds Employee To Worker Array*/
    public void addWorker(Employee e);
    /**Removes Employee From Worker Array
     * If Employee Doesn't Exist Throws Exception
     * @param name
     * @param surname
     * @throws Exception
     */
    public void removeWorker(String name,String surname)throws Exception;
    /**Adds Customer To Customer Array*/
    public void addCustomer(Customer c);
    /**Returns Total Employee Number */
    public int getEmployeeNumber();
    /**Returns Total Customer Number */
    public int getCustomerNumber();
    /**Returns Total Furniture Number */
    public int getTotalFurnitureNumber();
    /**Increases Stock Number By The Requested Amount*/
    public void acceptRequests();
    /**Takes Requests From Employee and Adds The Request Array */
    public void makeRequests();
    /**
     * OverLoad of void makeRequests() 
     * @param type
     * @param amount
     */
    public void makeRequests(String type,String amount);
    /**Shows Requests To Admin */
    public void seeRequests();
    /**Sells Furniture to Customer By Employee*/
    public void sellFurniture();
    /**Shows Customer's Previous Orders By Customer Id */
    public void customerInformation()throws Exception;
    /**Shows All Furnitures */
    public void seeFurnitures();
    /**
     * Customer Can Order Furniture By Logging In To System 
     * @param customer
     */
    public void orderFurnitures(Customer customer);
    /**Search for keyword */
    public void searchFurniture();
}

