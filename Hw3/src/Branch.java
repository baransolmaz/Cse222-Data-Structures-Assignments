public class Branch {
    private int id;
    private static int branchNumber=0;
    protected HybridList<Product> products =new HybridList<Product>();
    protected KWArrayList<Employee> workers; 
    protected HybridList<Product> requests=new HybridList<Product>();
    /**Default Constructor */
    Branch() {
        super();
        setId(getBranchNumber());
        workers =new KWArrayList<Employee>();

        Furniture fur= new OfficeChair();
        for (String type : fur.getModels()) 
            for (String color : fur.getColors())
                products.add(new Product(fur.getType(),type, color));
        
        fur= new OfficeDesk();
        for (String type : fur.getModels()) 
            for (String color : fur.getColors())
                products.add(new Product(fur.getType(),type, color));
       
        fur= new MeetingTable();
        for (String type : fur.getModels()) 
            for (String color : fur.getColors())
                products.add(new Product(fur.getType(),type, color));
        
        fur= new Bookcase();
        for (String type : fur.getModels()) 
            for (String color : fur.getColors())
                products.add(new Product(fur.getType(),type, color));
        
        fur= new OfficeCabinet();
        for (String type : fur.getModels()) 
            for (String color : fur.getColors())
                products.add(new Product(fur.getType(),type, color));

        for (int i = 0; i < 10; i++)
            requests.add(new Product("Meeting Table", "model2", "color2"));
    
        branchNumber++;
    }

    private void setId(int id){ this.id=id; }

    public int getId(){ return this.id; }
       
    public int getBranchNumber(){ return branchNumber; }

}
