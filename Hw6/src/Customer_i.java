import java.util.*;
/**Interface For Customer Class*/
public interface Customer_i{
    /**Returns Customer Id */
    public int getId();
    /**Reads Customer's Order*/
    public Queue<Order> getOrders();
    /**
     * Sets Id
     * @param id int
     */
    public void setId(int id);
    /**
     * Compares parameters with user id and password
     * @param id   int
     * @param pass String
     * @return Boolen
     */
    public boolean checkUser(int id ,String pass);
    /**Prints Previous Orders */
    public void seePrevious();
}