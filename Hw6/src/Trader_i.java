import java.util.*;

public interface Trader_i {
    /**
     * Sets Id
     * @param id int
     */
    public void setId(int id);
    /**
     * Returns Name
     * @return String
     */
    public String getName() ;
    /**
     * Compares parameters with user id and password
     * @param id   int
     * @param pass String
     * @return Boolen
     */
    public boolean checkUser(int id ,String pass) ;
    /**
     * Removes Product From Files
     * @param id String
     */
    public void removeProduct(String id);
    /**
     * Prints Products of Trader That Customer Ordered
     * @param customers Hashtable<Integer, Customer>
     * @param id int
     */
    public void seeOrders(Hashtable<Integer, Customer> customers,int id);
    /**
     * Denies Order of Customer
     * @param customers Hashtable<Integer, Customer>
     * @param customerId int
     * @param code String
     */
    public void editOrders(Hashtable<Integer, Customer> customers, int customerId,String code);
    /**
     * Returns Trader Id
     * @return int
     */
    public int getId() ;
    /**
     * Adds New Products To File
     * @param id String
     */
    public void addProduct(String id);
    /**Prints Traders Products */
    public void seeProducts();

}
