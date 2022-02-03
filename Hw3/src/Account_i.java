/**Interface For Account Class */
public interface Account_i {
    /**
     * Sets Mail Address For Account
     * @param mail
     */
    public void setMail(String mail);
    /**
     * Sets Password For Account
     * @param password
     */
    public void setPassword(String password);
    /**
     * Returns Mail Address
     */
    public String getMail();
    /**
     * Returns Password
     */
    public String getPassword();
}
