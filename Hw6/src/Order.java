public class Order {
    private String name;
    private String price;
    private String id;
    private String traderName;
    private String discounted;
    private String desc;
    private String category;
    private float percentage;
    /**
     * Constuctor
     * @param id String
     * @param name String
     * @param category String
     * @param price String
     * @param discounted String
     * @param desc String
     * @param traderName String
     */
    Order(String id,String name,String category,String price,String discounted,String desc,String traderName){
        this.traderName=traderName;
        this.id=id;
        this.name=name;
        this.price=price;
        this.discounted=discounted;
        this.desc=desc;
        this.category=category;
        this.percentage=100*(Integer.parseInt(price)-Integer.parseInt(discounted))/Integer.parseInt(price) ;
    }
    /**
     * Returns Category
     * @return String
     */
    public String getCategory() {
        return category;
    }
    /**
     * Returns Discount Percentage
     * @return Float
     */
    public float getPercentage() {
        return percentage;
    }
    /**
     * Returns Id
     * @return String
     */
    public String getId() {
        return id;
    }
    /**
     * Returns The Name Of Product
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * Returns Price
     * @return String
     */
    public String getPrice() {
        return price;
    }
    /**
     * Returns Trader Name
     * @return String
     */
    public String getTraderName() {
        return traderName;
    }
    /**
     * Returns Description
     * @return String
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Returns Discounted Price
     * @return String
     */   
    public String getDiscounted() {
        return discounted;
    }
    @Override
    public String toString() {
        StringBuilder build= new StringBuilder();
        build.append(id+"\n"+name+"\nPrice: "+price+" Discounted: "+discounted+" Discount Percentage: %"+percentage);
        //build.append("\n"+desc);
        build.append("\n"+traderName);
        return build.toString();
    }
}
