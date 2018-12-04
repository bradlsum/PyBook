import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Comment  {
    private String postText;
    static private int _ID = 0;
    private int id;
    private String date;
    private String time;
    private String userName;

    public Comment()
    {
        postText = "";
        date = "";
        id = ++this._ID;
        this.time = "";
        this.userName = "";

    }

    public Comment(String userName,String date, String time, String text){
        this.userName = userName;
        this.date = date;
        this.time = time;
        this.postText = text;
    }
    public void setPostText(String iPostText)
    {
        postText = iPostText;
    }
    public String getPostText()
    {
        return postText;
    }
    public void setDate(String Date)
    {

        date = Date;
    }
    public String getDate()
    {
        return date.toString();
    }
    public void addLike()
    {

    }
    public void removeLike()
    {

    }

    public void setUserName(String userName){this.userName = userName;}
    public String getUserName(){return this.userName;}

    public String toJSON(){
        String temp = "{\"id\":" + this.id + ",\"text\":" + this.postText + ",";
        temp += "\"date\":" + this.date.toString() + "\"time\":" + this.time + ",";
        temp += "\"username\":" + this.userName + "}";

        return  temp;
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
