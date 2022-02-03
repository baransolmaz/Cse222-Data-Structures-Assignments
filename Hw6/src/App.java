import java.io.*;
import java.util.*;

public class App {
    private File mainFile;
    private File products;
    protected Hashtable<Integer, Customer> customers = new Hashtable<Integer, Customer>();
    protected LinkedList<Trader> traders = new LinkedList<Trader>();
    protected Tree<String> categories=new Tree<String>("#");
    /**
     * Reads the File and initializes fields
     * @param fileName
     */
    public App(String fileName) {
        File folder = new File("Users");
        folder.mkdir();
        createDriverUser();
        mainFile = new File(fileName);
        products = new File("Products.txt");
        try {
            if (products.exists())
                products.delete();
            products.createNewFile();
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(mainFile));
            String data = myReader.readLine();
            BufferedWriter fWriter = new BufferedWriter(new FileWriter(products));
            while ((data = myReader.readLine()) != null) {
                String[] data2 = data.split(";", 0);
                fWriter.write("Id: " + data2[0] + "\n");
                fWriter.write("Name: " + data2[1] + "\n");
                String temp=data2[2].replace("\"", "").replace("[", "").replace("]", "");
                categories.add(temp);
                fWriter.write("Category: " + temp + "\n");
                fWriter.write("Price: " + data2[3] + "\n");
                fWriter.write("Discounted: " + data2[4] + "\n");
                fWriter.write("Description: " + data2[5] + "\n");
                fWriter.write("Trader: " + data2[6] + "\n\n");
                temp = data2[6].replace("/", "-");
                File trad = new File("Users//" + temp + ".txt");
                if (trad.createNewFile()) {
                    addTrader(new Trader(temp));
                }
                BufferedWriter traderWriter = new BufferedWriter(new FileWriter(trad, true));
                traderWriter.write(data2[0] + "\n");
                traderWriter.close();
            }
            fWriter.close();
            myReader.close();
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }
    }
    /**Creates Users for driver code */
    private void createDriverUser() {
        Customer admin = new Customer("admin");
        admin.setId(-1);
        customers.put(-1, admin);
        Trader trad = new Trader("admin");
        trad.setId(-2);
        traders.add(trad);
        System.out.println("\n-Test Users Added-");
    }
    /**Generates Customer Id*/
    private int customerIdGenerator() {
        Random rand = new Random();
        int id = rand.nextInt(89999999) + 10000000;

        while (customers.containsKey(id)) {
            id = rand.nextInt(89999999) + 10000000;
        }
        System.out.println("Your Unique Id: " + id);
        return id;
    }
    /**
     * Adds new customer 
     * @param customer Custumer
     */
    public void addCustomer(Customer customer) {
        int id = customerIdGenerator();
        customer.setId(id);
        customers.put(id, customer);
        System.out.println("\n-Customer Added-");
    }
    /**
     * Adds new Trader
     * @param trader
     */
    public void addTrader(Trader trader) {
        int id = traderIdGenerator();
        //System.out.println("Your Id: "+id);
        trader.setId(id);
        traders.add(trader);
        System.out.println("\n-Trader Added-");

    }
    /**
     * Generates Trader Id
     * @return int
     */
    private int traderIdGenerator() {
        Random rand = new Random();
        int id = 10000000;
        Iterator<Trader> iter = traders.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                id = rand.nextInt(89999999) + 10000000;
                iter = traders.iterator();
            }
        }
        return id;
    }
    /**Prints All Products */
    public void showProducts() {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(products));
            String data = myReader.readLine();
            while ((data = myReader.readLine()) != null) {
                if (!data.contains("Category: ") && !data.contains("Description: ")) {
                    System.out.println(data);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Prints Product Information
     * @param id String
     */
    public void getProductInformation(String id) {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(products));
            String data = myReader.readLine();
            while ((data = myReader.readLine()) != null) {
                if (data.contains("Id: " + id)) {
                    System.out.println("\n"+data);
                    for (int i = 0; i < 6; i++) {
                        data = myReader.readLine();
                        System.out.println(data);
                    }
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Prints Traders
     * @param index int
     */
    public void showTraders(String index) {
        Scanner scan = new Scanner(System.in);
        int counter = 1;
        if (index.equals("0")) {
            for (Trader trad : traders) {
                String name = trad.getUserFile().getName();
                String[] temp = name.split("\\.");
                System.out.println(counter + ") " + temp[0]);
                counter++;
            }
            System.out.print("Trader Index: ");
            index = scan.nextLine();
        }
        Trader trad = traders.get(Integer.parseInt(index) - 1);
        try {
            BufferedReader trReader = new BufferedReader(new FileReader(trad.getUserFile()));
            String data;
            while ((data = trReader.readLine()) != null)
                getProductInformation(data);

            trReader.close();
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }

    }
    /**
     * Orders Product 
     * @param custom Customer
     * @param id String
     */
    public void orderProduct(Customer custom, String id) {
        Scanner scan = new Scanner(System.in);
        if (id.length() < 15) {
            System.out.print("Product Id: ");
            id = scan.nextLine();
        }
        try {
            BufferedReader trReader = new BufferedReader(new FileReader(products));
            String data;
            BufferedWriter customWriter = new BufferedWriter(new FileWriter(custom.getUserFile(), true));
            while ((data = trReader.readLine()) != null) {
                if (data.contains("Id: " + id)) {
                    customWriter.write(id + "\n");
                    customWriter.close();
                    trReader.close();
                    System.out.println("\n-Product Ordered-");
                    return;
                }
            }
            customWriter.close();
            trReader.close();
            System.err.println("\n-Id Does Not Exist!! ");
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Adds New Product To Trader and File
     * @param trad Trader
     * @param name String
     * @param cat String
     * @param price String
     * @param discounted String
     * @param desc String
     */
    public void addProduct(Trader trad, String name, String cat, String price, String discounted, String desc) {
        Scanner scan = new Scanner(System.in);
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(products, true));
            if (name == null) {
                System.out.print("Name: ");
                name = scan.nextLine();
                System.out.print("Category: ");
                cat = scan.nextLine();
                System.out.print("Price: ");
                price = scan.nextLine();
                System.out.print("Discounted Price: ");
                discounted = scan.nextLine();
                System.out.print("Description: ");
                desc = scan.nextLine();
            }
            String id = productIdGenerator();
            bWriter.write("Id: " + id + "\n");
            bWriter.write("Name: " + name + "\n");
            bWriter.write("Category: " + cat + "\n");
            bWriter.write("Price: " + price + "\n");
            bWriter.write("Discounted: " + discounted + "\n");
            bWriter.write("Description: " + desc + "\n");
            bWriter.write("Trader: " + trad.getName() + "\n\n");
            trad.addProduct(id);
            bWriter.close();
            System.out.println("\n-Product Added-");
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Removes Traders Product
     * @param trader Trader
     * @param id Product Id
     */
    public void removeProduct(Trader trader, String id) {
        Scanner scan = new Scanner(System.in);
        if (id.length() < 16) {
            System.out.print("Id: ");
            id = scan.nextLine();
        }
        if (!checkProductId(trader, id)) {
            System.err.println("You Do Not Have Product");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            File newFile = new File("new.txt");
            newFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.contains("Id: " + id)) {
                    trader.removeProduct(id);
                    for (int i = 0; i < 7; i++) {
                        reader.readLine();
                    }
                } else
                    writer.write(data + "\n");
            }
            reader.close();
            writer.close();
            products.delete();
            newFile.renameTo(new File("Products.txt"));
            System.out.println("\n-Product Removed-");
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Checks Traders Products 
     * @param trader Trader
     * @param id String
     * @return Boolen
     */
    private boolean checkProductId(Trader trader,String id) {
        try {
            BufferedReader readUser = new BufferedReader(new FileReader(trader.getUserFile()));
            String data;
            while ((data = readUser.readLine()) != null)
                if (data.contains(id)){
                    readUser.close();
                    return true;
                } 
            readUser.close();
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }
        return false;
    }
    /**
     * Updates Product information
     * @param trader Trader
     * @param id String
     * @param index String
     * @param newInfo String
     */
    public void editProduct(Trader trader, String id,String index,String newInfo){
        Scanner scan = new Scanner(System.in);
        if (id.length() < 16) {
            System.out.print("Id: ");id = scan.nextLine();
        }
        if (!checkProductId(trader, id)) {
            System.err.println("You Do Not Have Product");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            File newFile = new File("new.txt");
            newFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.contains("Id: " + id)) {
                    ArrayList<String> datas=new ArrayList<String>();
                    writer.write(data + "\n");
                    for (int i = 0; i < 5; i++)
                        datas.add(reader.readLine());
                    switch (index) {
                        case "1":datas.set(0,"Name: "+newInfo);
                            break;
                        case "2":datas.set(2,"Price: "+newInfo);
                            break;
                        case "3":datas.set(3,"Discounted: "+newInfo);
                            break;
                        case "4":datas.set(4,"Description: "+newInfo);
                            break;
                        default:System.out.println("Wrong Index");
                            return;
                    }
                    for (int i = 0; i < 5; i++) 
                        writer.write(datas.get(i) + "\n");    
                }else
                    writer.write(data + "\n");
            }
            reader.close();
            writer.close();
            products.delete();
            newFile.renameTo(new File("Products.txt"));
            System.out.println("\n-Product Edited-");
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }
    }
    /**
     * Generates Product Id
     * @return String
     */
    private String productIdGenerator() {
        StringBuilder id = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        int length = 16;
        while (!checkId(id.toString())) {
            id = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                id.append(randomChar);
            }
        }
        return id.toString();
    }
    /**
     * Checks products id for unique id 
     * @param id String
     * @return boolen
     */
    private boolean checkId(String id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.contains("Id: " + id)) {
                    reader.close();
                    return false;
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.   " + e.getMessage());
        }
        return true;
    }
    /**
     * Prints Categories
     * @param rootNode Tree.Node<E>
     */
    public void seeCategories(Tree.Node<String> rootNode){
        Scanner scan=new Scanner(System.in);
        System.out.println(rootNode);
        System.out.println("0) Exit\n");
        System.out.print("Index: ");
        
        int index=Integer.parseInt(scan.nextLine());
        if (index==0)
            return;
        seeCategories(rootNode.nodes.get(index-1));

    }
    /**
     * Search For Key Then Sorts
     * @param key String
     * @param sort String
     * @return ArrayList<Order>
     */
    public ArrayList<Order> searchKey(String key,String sort) {
        ArrayList<Order> temp= new ArrayList<Order>();
        Scanner scan=new Scanner(System.in);
        if (sort.equals("0")) {
            System.out.println("Sort By ? (Default Order: Decreasing)");
            System.out.println("For Increasing Order Enter Minus Number (Ex:For Name: -1 )\n");
            System.out.println("1) Name");    
            System.out.println("2) Price");
            System.out.println("3) Percentage of Discount");
            System.out.print("Index: "); sort=scan.nextLine();
        }
        try {
            BufferedReader read = new BufferedReader(new FileReader(products));
            String data;
            while ((data = read.readLine()) != null) {
                if (data.contains("Id: ")) {
                    String[] name=read.readLine().split(": ");
                    String[] category=read.readLine().split(": ");
                    String[] price=read.readLine().split(": ");
                    String[] discounted=read.readLine().split(": ");
                    String[] desc=read.readLine().split(": ");
                    String[] trader=read.readLine().split(": ");
                    if (name[1].contains(key)||desc[1].contains(key)) {
                        temp.add(new Order(data, name[1],category[1], price[1], discounted[1], desc[1], trader[1]));
                    }
                }
            }
            read.close();
            sort(temp,Integer.parseInt(sort));
        } catch (Exception e) {
            System.err.println("An error occurred.   " + e.getMessage());
        }
        return temp;
    }
    /**
     * Redirects Sorting
     * @param temp  ArrayList<Order>
     * @param sort int
     */
    private void sort(ArrayList<Order> temp, int sort){
        switch (sort) {
            case -1:
            case  1:
                    insertionSort(temp,sort);
                break;
            case -2:
            case  2:
                    selectionSort(temp,sort);
                break;
            case -3:
            case  3:
                    bubbleSort(temp,sort);
                break;
        
            default:
                break;
        }
    }
    /**
     * Bubble Sort 
     * @param temp ArrayList<Order>
     * @param sort int
     */
    private void bubbleSort(ArrayList<Order> temp, int sort) {
        int n = temp.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (temp.get(j).getPercentage() > temp.get(j+1).getPercentage())
                {
                    // swap arr[j+1] and arr[j]
                    Order tempProduct =  temp.get(i);
                    temp.set(j, temp.get(j+1));
                    temp.set(j+1, tempProduct);
                }
        
        switch (sort) {
            case 3:
                    for (int i = temp.size()-1; i >=0 ; i--) 
                        System.out.println(temp.get(i)+"\n");
                break;
            case -3:
                    for (int i = 0; i < temp.size(); i++) 
                        System.out.println(temp.get(i)+"\n");
                break;
            default:System.err.println("Error");
                break;
        }

    }
    /**
     * Selection Sort 
     * @param temp ArrayList<Order>
     * @param sort int
     */
    private void selectionSort(ArrayList<Order> temp, int sort) { 
        int min_idx; 
        for (int i = 0; i < temp.size()-1; i++) 
        { 
            min_idx = i; 
            for (int j = i+1; j < temp.size(); j++) 
            if (Integer.parseInt(temp.get(j).getPrice())<Integer.parseInt(temp.get(min_idx).getPrice()) ) 
                min_idx = j; 
     
            Order tempProduct =  temp.get(i);
            temp.set(i, temp.get(min_idx));
            temp.set(min_idx, tempProduct);
        }
        switch (sort) {
            case 2:
                    for (int i = temp.size()-1; i >=0 ; i--) 
                        System.out.println(temp.get(i)+"\n");
                break;
            case -2:
                    for (int i = 0; i < temp.size(); i++) 
                        System.out.println(temp.get(i)+"\n");
                break;
            default:System.err.println("Error");
                break;
        } 
    }
    /**
     * Insertion Sort 
     * @param temp ArrayList<Order>
     * @param sort int
     */
    private void insertionSort(ArrayList<Order> temp,int sort) {
        int j;
        Order product;
        for (int i = 1; i < temp.size(); i++)
        {
            product = temp.get(i);
            j = i-1;
            while (j >= 0 && (temp.get(j).getName().compareTo(product.getName())>=0))
            {

                temp.set(j+1, temp.get(j));
                j = j-1;
            }
            temp.set(j+1, product);
        }
        switch (sort) {
            case 1:
                    for (int i = temp.size()-1; i >=0 ; i--) 
                        System.out.println(temp.get(i)+"\n");
                break;
            case -1:
                    for (int i = 0; i < temp.size(); i++) 
                        System.out.println(temp.get(i)+"\n");
                break;
            default:System.err.println("Error");
                break;
        }
    }
    /**
     * Filters Search Results
     * @param search ArrayList<Order>
     * @param category String
     * @param min int
     * @param max int
     */
    public void filterSearch(ArrayList<Order> search, String category, int min, int max) {
        if (search.size()==0) {
            System.err.println("No Product");
            return;
        }
        for (int i = 0; i < search.size(); i++)
            if((min<= Integer.parseInt(search.get(i).getPrice()))&&(Integer.parseInt(search.get(i).getPrice())<=max))
                if(search.get(i).getCategory().contains(category))
                    System.out.println("\n"+search.get(i).toString());

    }
}