import java.util.*;
/**Customer Class Extends Human Class Implements Customer Interface */
public class Customer extends Human implements Customer_i {
    private Human customer;
    private int id, orderAmount=0;
    private Account account;
    private static int customerNumber=1;
    private String adress, phone;
    protected String order[][];
    /**Simple Constructor */
    Customer() {
        super();
        this.setId(customerNumber);customerNumber++;
        account= new Account();
        System.out.println("Your Id: "+this.getId());
        order=new String[1][4];//type-model-color-amount
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
        order=new String[1][4];//type-model-color-amount
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
        order=new String[1][4];
    }
    /**Constructor For Copying*/
    Customer(Customer c) {
        super(c.getName(),c.getSurname());
        account=new Account(c.getAccount());
        setId(c.getId());
        try{
            order=new String[c.getOrderAmount()][4];
            for (int i = 0; i < c.getOrderAmount(); i++) {
                order[i]=new String[4];
                for (int j = 0; j < 4; j++)
                    order[i][j]=new String(c.order[i][j]);
            }
            orderAmount=c.getOrderAmount();
        }catch(Exception e){
            order=new String[1][4];
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
    public void buyFurniture(String type, String model,String color,int amount){
        try{
            String temp[][]=new String[getOrderAmount()+1][4];
            for (int i = 0; i < getOrderAmount(); i++) {
                temp[i]=new String[4];
                for (int j = 0; j < 4; j++)
                    temp[i][j]= new String(order[i][j]);
            }
            order=new String[this.getOrderAmount()+2][4];
            for (int i = 0; i < this.getOrderAmount(); i++) {
                order[i]=new String[4];
                for (int j = 0; j < 4; j++)
                    order[i][j]= new String(temp[i][j]);               
            }
            order[this.getOrderAmount()] =new String[4];
            for (int j = 0; j < 4; j++)
                order[this.getOrderAmount()][j]=new String();
            order[this.getOrderAmount()][0]=type; 
            order[this.getOrderAmount()][1]=model;
            order[this.getOrderAmount()][2]=color;
            order[this.getOrderAmount()][3]=Integer.toString(amount);              
            
            orderAmount++;
            System.out.println("-Order Completed-");
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
                for (int j = 0; j < 4; j++)
                    System.out.print(order[i][j]+" \t ");      
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
