import org.json.*;


import java.io.StringWriter;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String userName;
    private String email;
    private int id;


    static int _ID = 0;

    public User()
    {
        firstName = "";
        lastName = "";
        password = "";
        userName = "";
        email = "";
        id = ++this._ID;

    }

    public User(String firstName, String lastName, String email, String userName, String password, int id)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.id = id;
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



    public String  toJSON(){
        String temp = "{\"user\":"+ "{\"id\":" + this.id + ",\"first\":" + this.firstName + ",";
        temp += "\"last\":" + this.lastName + ",\"email\"" + this.email + ",";
        temp += "\"username\":" + this.userName + ",\"password\":" + this.password + "}";

        return temp;
    }

    public User parseJSON(String JSON){ //given a json string, return a new user with those parameters
        User newUser = new User();

        try {

            JSONObject obj = new JSONObject(JSON); //make json object, passing json string

            //getting parameters from objects given the keys
            String username = obj.getString("username");
            String password = obj.getString("password");
            String id = obj.getString("id");
            String firstname = obj.getString("first");
            String lastname = obj.getString("last");
            String email = obj.getString("email");

            //creating the user given the parameters parsed
            newUser = new User(firstname,lastname,email,username,password,Integer.parseInt(id));

        }
        catch (JSONException jex){
            System.out.println("JSON is not correct");
        }

        return newUser;

    }

}