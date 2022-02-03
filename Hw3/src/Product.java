public class Product {
    private String type;
    private String model;
    private String color;
    /**
     * Constructor
     * @param name
     * @param modelNo
     * @param colorNo
     */
    Product(String name,String modelNo,String colorNo){
        type=name;
        model=modelNo;
        color=colorNo;
    }
    /**Returns Furniture Color */
    public String getColor() {
        return color;
    }
    /**Returns Furniture Model */
    public String getModel() {
        return model;
    }
    /**Returns Furniture Type */
    public String getType() {
        return type;
    }
    /**To String Override */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(type+" ").append(model+" ").append(color+" ");
        return str.toString();
    }

}
