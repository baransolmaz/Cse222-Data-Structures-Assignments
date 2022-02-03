/**Meeting Table Class Extends Abstract Furniture Class */
public class MeetingTable extends Furniture{
    private String name="Meeting Table";
    private String models[]={"model1","model2","model3","model4","model5","model6","model7","model8","model9","model10"};
    private String color[]={"color1","color2","color3","color4"};
    private int stockNumber=15;
    /**Returns Furniture Type */
    @Override
    public String getType(){
        return name;
    }
    /**Returns Stock Amount */
    @Override
    public int getStockNumber(){
        return stockNumber;
    }
    /**Returns Total Model Number */
    @Override
    public int getModelNumber(){
        return models.length;
    }
    /**Returns Model Array */
    @Override
    public String[] getModels(){
        return models;
    }
    /**Returns Color Array */
    @Override
    public String[] getColors(){
        return color;
    }
    /**Increase Stock Amount By k 
     * @param k
    */
    @Override
    public void increaseStock(int k){
        stockNumber+=k;
    }
    /**Decrease Stock Amount By k, 
     * Throws Exception When k Is 
     * Greater Than Stock Amount
     * @param k
     */
    @Override
    public void decreaseStock(int k) throws Exception{
        if (k<0) {
            throw new Exception("Amount Must Be Positive");
        }else if (stockNumber-k<0) {
            throw new Exception("Not Enough Stock!!");
        }else 
            stockNumber-=k;
    }
}