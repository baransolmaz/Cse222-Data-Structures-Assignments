/**Interface For Customer Class*/
public interface Customer_i{
    /**Returns Customer Id */
    public int getId();
    /**Return Total Customer Number */
    public int getCustomerNumber();
    /**Returns Order Amount */
    public int getOrderAmount();
    /**Adds to Order Array
     * @param type
     * @param model
     * @param color
     * @param amount
     */
    public void buyFurniture(String type, String model,String color,int amount);
    /**
     * Returns Customer Account
    */
    public Account getAccount();
    /**Asks Address and Phone */
    public void setAdressPhone();
    /**Prints Customer's Order*/
    public void showOrders();

}