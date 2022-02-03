import java.util.*;
/**Abstract Class For Admin,Employee,Customer*/
public class Human implements Human_i {
    private String name;
    private String surname;
    
    /**Constructor For Human */
    Human() {
        String temp;
        Scanner scan = new Scanner(System.in);            
        System.out.print("Name: ");
        temp= scan.nextLine();
        this.setName(temp);
                
        System.out.print("Surname:");
        temp= scan.nextLine();
        this.setSurname(temp);
    }
    /**Constructor For Human
     * @param name
     * @param surmame
     */
    Human(String name,String surname) {
        this.setName(name);
        this.setSurname(surname);
    }
    /**Sets Name 
     * @param name
    */
    @Override
    public void setName(String name){  this.name=name; }
    /**Sets Surname
     * @param surname
     */
    @Override
    public void setSurname(String surname){  this.surname=surname; }
    /**Returns Name */
    @Override
    public String getName(){  return this.name; }
    /**Returns Surname */
    @Override
    public String getSurname(){  return this.surname; }
}
