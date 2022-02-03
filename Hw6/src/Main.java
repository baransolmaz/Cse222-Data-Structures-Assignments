import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        App app= new App("e-commerce-samples.csv");
        String choice="0";
        do{
            System.out.println("(1) Driver");
            System.out.println("(2) Interactive");
            System.out.println("(0) Exit\n");
            System.out.print("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                    {
                        case "1":driver(app);
                            break;
                        case "2":menu(app);
                            break;
                        case "0":System.out.println("Exiting.");
                            break;
                        default:System.out.println("Wrong choice! Try again!");
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (!choice.equals("0"));        
    }
    private static void driver(App app){
        try {
            Scanner scan=new Scanner(System.in);
            String choice="0";
            System.out.println("(1) Customer Driver");
            System.out.println("(2) Trader Driver");
            System.out.print("Choice: ");
            choice=scan.nextLine();
            switch(choice)
                {
                    case "1":loginDriver(app,-1,"admin");//Admin Customer
                        break;
                    case "2":loginDriver(app,10000000,"Alisha");//Alisha 
                        break;
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void menu(App app){
        Scanner scan=new Scanner(System.in);
        String choice="0", pass;
        do{
            System.out.println("(1) Log in");
            System.out.println("(2) Sign Up");
            System.out.println("(0) Exit\n");
            System.out.print("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                    {
                        case "1":System.out.print("Id: ");String id =scan.nextLine();
                                System.out.print("Password: ");pass =scan.nextLine();
                                login(app,Integer.parseInt(id) ,pass);
                            break;
                        case "2":System.out.print("Password: ");pass =scan.nextLine();
                                if (pass.length()<6) {
                                    System.err.println("Password Must Be At Least Six Digits! ");
                                }else
                                    app.addCustomer(new Customer(pass));
                            break;
                        case "0":System.out.println("Exiting.");
                            break;
                        default:System.out.println("Wrong choice! Try again!");
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (!choice.equals("0"));
    }
    private static void loginDriver(App app,int id,String pass)throws Exception{
        if (app.customers.containsKey(id)) {
            customerDriver(app,app.customers.get(id));
            return;
        }
        for(Trader trad: app.traders )
            if (trad.checkUser(id, pass)) {
                traderDriver(app,trad);
                return;
            }
        throw new Exception("Wrong Id or Password!");
    }
    private static void login(App app,int id,String pass)throws Exception{
        if (app.customers.containsKey(id)) {
            customerMenu(app,app.customers.get(id));
            return;
        }
        for(Trader trad: app.traders )
            if (trad.checkUser(id, pass)) {
                traderMenu(app,trad);
                return;
            }
        throw new Exception("Wrong Id or Password!");
    }
    private static void traderMenu(App app,Trader trader){
        Scanner scan = new Scanner(System.in);
        String choice="0", id;
        System.out.println("\nWelcome Trader!");
        do {
            try {
                System.out.println("\n(1) See Product");
                System.out.println("(2) Add Product");
                System.out.println("(3) Remove Product");
                System.out.println("(4) Edit Product");
                System.out.println("(5) See Orders");
                System.out.println("(6) Edit Orders");

                System.out.println("(0) Log Out and Return Main Menu\n");
                System.out.print("Choice: ");
                choice=scan.nextLine();
                switch(choice)
                {
                    case "1":trader.seeProducts();
                        break;
                    case "2":app.addProduct(trader,null,"category","price","discounted","description");
                        break;
                    case "3":app.removeProduct(trader,"0");
                        break;
                    case "4":System.out.println("Which Info Do You Want to Change?");
                            System.out.println("1) Name");
                            System.out.println("2) Price");
                            System.out.println("3) Discounted Price");
                            System.out.println("4) Description");
                            System.out.print("Index: ");
                            String index = scan.nextLine();
                            System.out.print("New Data: ");
                            String newData=scan.nextLine();
                            app.editProduct(trader,"0",index,newData);
                        break;
                    case "5":System.out.println("Customer Id: ");
                            id = scan.nextLine();
                            trader.seeOrders(app.customers,Integer.parseInt(id));
                        break;
                    case "6":System.out.println("Customer Id: ");
                            id = scan.nextLine();
                            System.out.println("Customer Id: ");
                            String pro_Id = scan.nextLine();
                            trader.editOrders(app.customers,Integer.parseInt(id),pro_Id);
                        break;
                    case "0":System.out.println("Logging Out!\n");
                        break;
                    default:System.out.println("Wrong choice! Try again!");
                        break;
                }    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!choice.equals("0"));  
    }
    private static void traderDriver(App app,Trader trader){
        System.out.println("\nWelcome Trader!");
        try {
            System.out.println("\nPrinting Traders Product");
            trader.seeProducts();

            System.out.println("\nAdding New Product");    
            app.addProduct(trader,"New Product","New >> New Product","100","99","Param Olsada Ben Alsam");
            
            System.out.println("\nEditing Product");    
            app.editProduct(trader,"SRTEH2FVUHAAVH9X","1","New Name");
            app.editProduct(trader,"SRTEH2FVUHAAVH9X","2","999999999");
            app.editProduct(trader,"SRTEH2FVUHAAVH9X","3","0");
            app.editProduct(trader,"SRTEH2FVUHAAVH9X","4","New Description");
            
            System.out.println("\nNew Version Of Edited Product");
            app.getProductInformation("SRTEH2FVUHAAVH9X");
            
            System.out.println("\nDeleting Product");
            app.removeProduct(trader,"SRTEH2FVUHAAVH9X");
            
            System.out.println("\nPrinting Products That Custemer Ordered From Trader");
            trader.seeOrders(app.customers,-1);
            
            System.out.println("\nCanceling Product That Custemer Ordered From Trader");
            trader.editOrders(app.customers,-1,"SRTEH2FECMGNZJXJ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void customerMenu(App app,Customer customer){
        Scanner scan=new Scanner(System.in);
        String choice="0";
        ArrayList<Order> search=new ArrayList<Order>();
        System.out.println("\nWelcome Customer!");
        do{
            System.out.println("\n(1) See All Products");
            System.out.println("(2) See Detailed Product Information");
            System.out.println("(3) See Categories");
            System.out.println("(4) See Traders Products");
            System.out.println("(5) Order Product");
            System.out.println("(6) See Previous Orders");
            System.out.println("(7) Search Product");
            System.out.println("(0) Log Out and Return Main Menu\n");
            System.out.print("Choice: ");
            try {
                choice=scan.nextLine();
                switch(choice)
                {
                    case "1":app.showProducts();
                        break;
                    case "2":System.out.print("Id: ");
                             String id=scan.nextLine();
                             app.getProductInformation(id);
                        break;
                    case "3":app.seeCategories(app.categories.root);
                        break;
                    case "4":app.showTraders("0");
                        break;
                    case "5":app.orderProduct(customer,"0");
                        break;
                    case "6":customer.seePrevious();
                        break;
                    case "7": System.out.println("Wanted Key: ");
                            String key = scan.nextLine();
                            search=app.searchKey(key,"0");   
                        break;
                    case "8": System.out.println("Min. Price: ");
                            String min = scan.nextLine();
                            System.out.println("Max. Price: ");
                            String max = scan.nextLine();
                            System.out.println("Category: ");
                            String category = scan.nextLine();
                            app.filterSearch(search,category,Integer.parseInt(min),Integer.parseInt(max));   
                        break;
                    case "0":System.out.println("Logging Out!\n");
                        break;
                    default:System.out.println("Wrong choice! Try again!");
                        break;
                } 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!choice.equals("0"));  
    }
    private static void customerDriver(App app,Customer customer){
        ArrayList<Order> search=new ArrayList<Order>();
        System.out.println("\nWelcome Customer!");
        try {
            //System.out.println("\nPrinting All Products");
            //app.showProducts();//Too much product... 
            System.out.println("\nPrinting All Information Of One Product");
            app.getProductInformation("PWTEB7H2E4KCYUE3");
            //app.seeCategories(app.categories.root);//Interactive
            System.out.println("\nPrinting 3175.Trader's Products");
            app.showTraders("3175");
            System.out.println("Ordering Product From Alisha");
            app.orderProduct(customer,"SRTEH2FECMGNZJXJ");
            System.out.println("Ordering Product From FabHomeDecor");
            app.orderProduct(customer,"SBEEH3QGU7MFYJFY");
            System.out.println("Ordering Product From Freelance");
            app.orderProduct(customer,"BOTEGYTZ2T6WUJMM");
            System.out.println("\nPrinting Previous Orders");
            customer.seePrevious();
            System.out.println("\nSearching Key -Liquid- Sorting By Decreasing Name");
            search=app.searchKey("Liquid","1");   
            System.out.println("\nSearching Key -Liquid- Sorting By Increasing Name");
            search=app.searchKey("Liquid","-1");   
            System.out.println("\nSearching Key -Liquid- Sorting By Decreasing Price");
            search=app.searchKey("Liquid","2");   
            System.out.println("\nSearching Key -Liquid- Sorting By Increasing Price");
            search=app.searchKey("Liquid","-2");   
            System.out.println("\nSearching Key -Liquid- Sorting By Decreasing Discount Percentage");
            search=app.searchKey("Liquid","3");   
            System.out.println("\nSearching Key -Liquid- Sorting By Increasing Discount Percentage");
            search=app.searchKey("Liquid","-3");   
            System.out.println("\nFiltering Previous Search Result (Min:300 --- Max: 1700 --- Category: Sweaters & Pullovers");
            app.filterSearch(search,"Sweaters & Pullovers",0,999999);   

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}