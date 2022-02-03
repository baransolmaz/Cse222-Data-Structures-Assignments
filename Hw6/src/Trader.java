import java.io.*;
import java.util.*;

public class Trader extends Userfile implements Trader_i{
    private String name;
    private int id;
    private String pass;
    Trader(String pass){
        this.name=pass;
        this.pass=pass;
        setFile(new File("Users//"+pass+".txt"));
        try {
            getUserFile().createNewFile();
        } catch (Exception e) {
            System.out.println("An error occurred.   "+e.getMessage());
        }
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean checkUser(int id ,String pass) {
        if (this.id==id && this.pass.equals(pass))
            return true;
        return false;
    }
    @Override
    public void removeProduct(String id){
        try {
            BufferedReader reader=new BufferedReader(new FileReader(getUserFile()));
            File newFile = new File("Users//temp.txt");
            newFile.createNewFile();
            BufferedWriter writer= new BufferedWriter(new FileWriter(newFile));
            String data;
            while ((data=reader.readLine())!=null){
                if(!data.contains(id))
                    writer.write(data+"\n");
            }
            reader.close();
            writer.close();
            getUserFile().delete();
            newFile.renameTo(getUserFile());
            System.out.println("Product Deleted");
        } catch (Exception e) {
            System.out.println("An error occurred.   "+e.getMessage());
        }
    }
    @Override
    public void seeOrders(Hashtable<Integer, Customer> customers,int id){
        Queue<Order> temp=new LinkedList<Order>();
        ArrayList<String> codes=new ArrayList<String>();
        try {
            BufferedReader csReader = new BufferedReader(new FileReader(customers.get(id).getUserFile()));
            String data,data2;
            while ((data=csReader.readLine())!=null){
                BufferedReader trReader = new BufferedReader(new FileReader(getUserFile()));
                while ((data2=trReader.readLine())!=null)
                    if (data2.contains(data))
                        codes.add(data);     

                trReader.close();                         
            } 
            csReader.close();
            for (int i = 0; i < codes.size(); i++) {
                BufferedReader prReader = new BufferedReader(new FileReader("Products.txt"));
                while ((data2=prReader.readLine())!=null){
                    if (data2.contains("Id: "+codes.get(i))){
                        String[] name=prReader.readLine().split(": ");
                        String[] cat=prReader.readLine().split(": ");
                        String[] price=prReader.readLine().split(": ");
                        String[] discounted=prReader.readLine().split(": ");
                        String[] desc=prReader.readLine().split(": ");
                        String[] trader=prReader.readLine().split(": ");
                        temp.add(new Order(codes.get(i),name[1],cat[1],price[1],discounted[1],desc[1],trader[1]));   
                    }
                }
                prReader.close();
            }
            while (!temp.isEmpty()){
                Order order=temp.poll();
                System.out.println(order);           
            }
        }catch (Exception e) {
            System.out.println("An error occurred. "+e.getMessage() );
        }
     
    }
    @Override
    public void editOrders(Hashtable<Integer, Customer> customers, int customerId,String code){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customers.get(customerId).getUserFile()));
            File newFile = new File("Users//new.txt");
            newFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            String data;
            while ((data = reader.readLine()) != null) {
                if (!data.contains(code))
                    writer.write(data + "\n");
            }
            reader.close();
            writer.close();
            newFile.renameTo(customers.get(customerId).getUserFile());
            System.out.println("Order Canceled\n");
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }    
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void addProduct(String id){
        try {
            BufferedWriter bWriter=new BufferedWriter(new FileWriter(getUserFile(),true));
            bWriter.write(id+"\n");
            bWriter.close();
            System.out.println("Product Added");
        } catch (IOException e) {
            System.out.println("An error occurred.   "+e.getMessage());
        }
    }
    @Override
    public void seeProducts(){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(getUserFile()));
            String data,data2;
            while ((data=myReader.readLine())!=null){
                BufferedReader trReader = new BufferedReader(new FileReader("Products.txt"));
                while ((data2=trReader.readLine())!=null)
                    if (data2.contains("Id: "+data)){
                        System.out.println("\n"+data2);
                        for (int i = 0; i < 6; i++) 
                            System.out.println(trReader.readLine());         
                    }
                trReader.close();                         
            } 
            myReader.close();
        }catch (Exception e) {
            System.out.println("An error occurred. "+e.getMessage() );
        }
    }

}