//Abyel Romero

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Like extends User {

    private String userName;
    private String date;
    private String time;

    public Like(){
        this.date = "";
        this.time = "";
    }

    public Like(String username,String date,String time) {
        this.userName = username;
        this.date = date;
        this.time = time;
    }


    public String toJSON(){
        String temp = "{\"date\":" + this.date.toString() + ",\"time\":" + this.time + ",";
        temp += "\"username\":" + this.userName + "}";

        return temp;
    }

    public Like parseJSON(String JSON){ //given a json string, return a new user with those parameters
        Like newLike = new Like();

        try {
            JSONObject obj = new JSONObject(JSON); //make json object, passing json string

            //getting parameters from objects given the keys
            String username = obj.getString("username");
            String date = obj.getString("date");
            String time = obj.getString("time");


            //creating the user given the parameters parsed
            newLike = new Like(username,date,time);

        }
        catch (JSONException jex){
            System.out.println("JSON is not correct");
        }

        return newLike;

    }

}
