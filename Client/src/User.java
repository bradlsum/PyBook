public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String Cpassword;
    private String userName;
    private String email;
    private String DateOfBirth;
    private int id;


    static int _ID = 0;

    public User()
    {
        firstName = "";
        lastName = "";
        password = "";
        Cpassword = "";
        userName = "";
        email = "";
        DateOfBirth = "";
        id = ++this._ID;

    }

    public User(String firstName, String lastName, String email, String userName, String password, String Cpassword, String DateOfBirth)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.Cpassword = Cpassword;
        this.DateOfBirth = DateOfBirth;

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



    public String  toJSON(){
        String temp = "{\"id\":" + this.id + ",\"first\":" + this.firstName + ",";
        temp += "\"last\":" + this.lastName + ",\"email\"" + this.email + ",";
        temp += "\"username\":" + this.userName + ",\"password\":" + this.password + "}";

        return temp;
    }

}