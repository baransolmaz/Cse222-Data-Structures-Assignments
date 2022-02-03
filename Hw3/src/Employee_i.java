/**Interface For Employee Class */
public interface Employee_i{
    /**Returns Employee Id */
    public int getId();
    /**Return Total Employee Number */
    public int getEmployeeNumber();
    /**Returns Employee's Account*/
    public Account getAccount();
    /**Returns Branch Id That Employee Work */
    public int getBranchId();
    /**
     * Sets Branch Id Of Employee
     * @param branchId Branch Id That Employee
     */
    public void setBranchId(int branchId);

}
