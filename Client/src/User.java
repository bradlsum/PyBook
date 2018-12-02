public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String Cpassword;
    private String userName;
    private String email;
    private String DateOfBirth;

    public User()
    {
        firstName = "";
        lastName = "";
        password = "";
        Cpassword = "";
        userName = "";
        email = "";
        DateOfBirth = "";
    }

    public User(String firstName, String lastName, String email, String userName, String password, String Cpassword, String DateOfBirth)
    {

    }


    public void setFirstName(String iFirstName)
    {
        firstName = iFirstName;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String iLastName)
    {
        lastName = iLastName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setPassword(String iPassword)
    {
        password = iPassword;
    }
    public String getPassword()
    {
        return password;
    }
    public void setCPassword(String iCpassword)
    {
        Cpassword = iCpassword;
    }
    public String getCPassword()
    {
        return Cpassword;
    }
    public void setUserName(String iUserName)
    {
        userName = iUserName;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setEmail(String iEmail)
    {
        email = iEmail;
    }
    public String getEmail()
    {
        return email;
    }
    public void setDateOfBirth(String iDateOfBirth)
    {
        DateOfBirth = iDateOfBirth;
    }
    public String getDateOfBirth()
    {
        return DateOfBirth;
    }


}