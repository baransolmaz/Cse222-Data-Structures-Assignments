import java.util.*;
/**Customer Class Extends Human Class Implements Customer Interface */
public class Customer extends Human implements Customer_i {
    private Human customer;
    private int id, orderAmount=0;
    private Account account;
    private static int customerNumber=1;
    private String adress, phone;
    protected HybridList<Product> order= new HybridList<Product>();
    /**Simple Constructor */
    Customer() {
        super();
        this.setId(customerNumber);customerNumber++;
        account= new Account();
        System.out.println("Your Id: "+this.getId());
        order=new HybridList<Product>();
    }
    /**
     * Constuctor
     * @param name
     * @param surname
     */
    Customer(String name,String surname) {
        super(name,surname);
        this.setId(customerNumber);customerNumber++;
        account= new Account();
        System.out.println("Your Id: "+this.getId());
        order=new HybridList<Product>();//type-model-color-amount
    }
    /**
     * Advanced Constructor
     * @param name
     * @param surname
     * @param mail
     * @param passw
     */
    Customer(String name, String surname,String mail,String passw) {
        super(name, surname);
        account=new Account(mail,passw);
        this.setId(getCustomerNumber());
        customerNumber++;
        order=new HybridList<Product>();
    }
    /**Constructor For Copying*/
    Customer(Customer c) {
        super(c.getName(),c.getSurname());
        account=new Account(c.getAccount());
        setId(c.getId());
        try{
            order=new HybridList<Product>();
            for (int i = 0; i < c.getOrderAmount(); i++)
                order.add(c.order.get(i));
            orderAmount=c.getOrderAmount();
        }catch(Exception e){
            order=new HybridList<Product>();
        }

    }
    /** Sets Customer Id
     * @param id
     */
    private void setId(int id){ this.id=id; }
    /**Returns Customer Id */
    @Override
    public int getId(){ return this.id; }
    /**Returns Total Customer Number */
    @Override
    public int getCustomerNumber(){return customerNumber;}
    /**Returns Customer's Account */
    @Override
    public Account getAccount(){  return account; }
    /**Returns Customer's Total Order Amount */
    @Override
    public int getOrderAmount(){  return orderAmount; }
    /**Adds to Order Array
     * @param type
     * @param model
     * @param color
     * @param amount
     */
    @Override
    public void buyFurniture(String type, String model,String color){
        try{
            order.add(new Product(type, model, color));
            orderAmount++;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**Prints Customer's Order*/
    @Override
    public void showOrders(){
        System.out.println("Previous Orders:");
        try {
            for (int i = 0; i < getOrderAmount(); i++) {
                    System.out.print(order.get(i).toString());      
                System.out.println();
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**Asks Address and Phone */
    @Override
    public void setAdressPhone(){
        Scanner scan=new Scanner(System.in);
        System.out.print("Adress: ");
        adress=scan.nextLine();
        System.out.print("Phone: ");
        phone=scan.nextLine();
    }
}
