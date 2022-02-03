/**Employee Class Extends Human Implements Employee Interface */
public class Employee extends Human implements Employee_i{
    private Human employee;
    private int id;
    private int branchId;
    private static int employeeNumber=0;
    private Account account;
    /**Default Constructor */
    Employee() {
        super();
        account=new Account();
        setId(getEmployeeNumber());
        employeeNumber++;
    }
    /**
     * Advanced Constructor
     * @param name
     * @param surname
     * @param mail
     * @param passw
     */
    Employee(String name, String surname,String mail,String passw) {
        super(name, surname);
        account=new Account(mail,passw);
        setId(getEmployeeNumber());
        employeeNumber++;
    }
    /**Constructor For Copying*/
    Employee(Employee e) {
        super(e.getName(),e.getSurname());
        account=new Account((e.getAccount()).getMail(),(e.getAccount()).getPassword());
        setId(e.getId());
    }
    /**Returns Employee's Account*/
    @Override
    public Account getAccount(){ return account;  }
    /**
     * Sets Employee's Id
     * @param id
     */
    private void setId(int id){ this.id=id; }
    /**Returns Employee's Id */
    @Override
    public int getId(){ return this.id; }
    /**Return Total Employee Number */
    @Override
    public int getEmployeeNumber(){ return employeeNumber; }

    @Override
    public int getBranchId(){ return this.branchId; }

    @Override
    public void setBranchId(int branchId){ this.branchId=branchId; }
}
