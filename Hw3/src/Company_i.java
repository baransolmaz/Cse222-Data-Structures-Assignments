/**Interface For Company Class */
public interface Company_i {
    /** Keeps Information About Admin*/
    public void makeAdmin(Human h);
    /**Adds New Branch */
    public void addBranch();
    /**Removes Existing Branch*/
    public void removeBranch(int id)throws Exception;
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
    /**
     * To request from admin for employee
     * @param brancId
     * @param type
     * @param model
     * @param color
     * @param amount
     */
    public void makeRequests(int brancId,String type,String model,String color,int amount);
    /**Shows Requests To Admin */
    public void seeRequests();
    /**Sells Furniture to Customer By Employee*/
    public void sellFurniture(int branchId,int customerId,String tType,String tModel,String tColor,int amount);
    /**Shows Customer's Previous Orders By Customer Id */
    public void customerInformation(int id)throws Exception;
    /**Shows All Furnitures */
    public void seeFurnitures(int branchId);
    /**
     * Customer Can Order Furniture By Logging In To System 
     * @param customer the person that buy
     * @param tType type of furniture
     * @param tModel
     * @param tColor
     * @param amount
     */
    public void orderFurnitures(int branchId,Customer customer,String tType,String tModel,String tColor,int amount);
    /**Search for keyword */
    public void searchFurniture(String keyword);
}

