/**Interface For Admin Class */
public interface Admin_i {
    /**Returns Company Of Admin */
    public Company getCompany();
    /**Sets The Admin's Company*/
    public void setCompany(Company c);
    /**Adds Employee To Company */
    public void addWorker(Employee e);
    /**Removes Employee From Company */
    public void removeWorker(String name,String surname);
    /**Adds Customer To Company*/
    public void addCustomer(Customer c);
    /**Returns Admin's Account*/
    public Account getAccount();
    /**Adds One Branch To Company */
    public void addBranch();
    /**Removes One Branch From Company */
    public void removeBranch(int id);
    /**Accepts The Requests From Customers  */
    public void acceptRequests();
    /**Shows All Requests From Customers */
    public void seeRequests();
    
}
