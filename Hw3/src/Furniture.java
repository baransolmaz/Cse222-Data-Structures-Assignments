/**Abstract Class For Furnitures */
public abstract class Furniture {
    /**Returns Furniture Type */
    abstract String getType();
    /**Returns Stock Amount */
    abstract int getStockNumber();
    /**Returns Total Model Number */
    abstract int getModelNumber();
    /**Returns Model Array */
    abstract String[] getModels();
    /**Returns Color Array */
    abstract String[] getColors();
    /**Increase Stock Amount By k 
     * @param k
    */
    abstract void increaseStock(int k);
    /**Decrease Stock Amount By k, 
     * Throws Exception When k Is 
     * Greater Than Stock Amount
     * @param k
     */
    abstract void decreaseStock(int k) throws Exception;
}