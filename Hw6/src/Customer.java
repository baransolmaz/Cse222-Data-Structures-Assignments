import java.util.*;
import java.io.*;
public class Customer extends Userfile implements Customer_i{
    private int id;
    private String pass;
    /**
     * Constuructor
     * @param pass String
     */
    Customer(String pass){
        this.pass=pass;
    }
    @Override
    public void setId(int id) {
        this.id = id;
        setFile(new File("Users//"+id+".txt")); 
        try {
            getUserFile().createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public Queue<Order> getOrders(){
        Queue<Order> temp=new LinkedList<Order>();
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(getUserFile()));
            String data,data2;
            while ((data=myReader.readLine())!=null){
                BufferedReader trReader = new BufferedReader(new FileReader("Products.txt"));
                while ((data2=trReader.readLine())!=null){
                    if (data2.contains("Id: "+data)){
                        String[] name=trReader.readLine().split(": ");
                        String[] cat=trReader.readLine().split(": ");
                        String[] price=trReader.readLine().split(": ");
                        String[] discounted=trReader.readLine().split(": ");
                        String[] desc=trReader.readLine().split(": ");
                        String[] trader=trReader.readLine().split(": ");
                        temp.add(new Order(data,name[1],cat[1],price[1],discounted[1],desc[1],trader[1]));     
                    }
                }
                trReader.close();                         
            } 
            myReader.close();
        }catch (Exception e) {
            System.out.println("An error occurred. "+e.getMessage() );
        }
        return temp;
    }
    @Override
    public boolean checkUser(int id ,String pass) {
        if (this.id==id && this.pass.equals(pass))
            return true;
        return false;
    }
    @Override
    public void seePrevious() {
        Queue<Order> temp=getOrders();
        while (!temp.isEmpty()){
            Order order=temp.poll();
            System.out.println("\n"+order);           
        }
    }
}
