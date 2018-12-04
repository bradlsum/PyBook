//Abyel Romero

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Client {

    private DataOutputStream output;
    private DataInputStream input;
    private Socket connection;
    private int port;
    private ArrayList<Post> thePosts;

    public Client(int port){
        this.port = port;
        this.run();
    }

    public void addNewUserToServer(User newUser){

        //in main menu, if user creates a new account, this function will run after a new user is created
        //the new user is passed to the function and will be output to the server in a json string.
        //server will store new user

        this.sendJSON(newUser.toJSON());

    }


    private void sendJSON(String JSON){

        try{
            this.output.writeUTF(JSON);
            this.output.flush();
        }

        catch(Exception e){
            e.printStackTrace();}

    }

    public User logIn(String username, String password){

        //in main menu, if user selects log in, app will call this function.
        // client will output a json string with username and password.
        //server will provide a json string if given username and password are correct.

        String getUsernameJSON = "{\"action\":" + "\"auth\"" + ",\"username\":" + username + ",";
        getUsernameJSON += "\"password\":" + password + "}";

        this.sendJSON(getUsernameJSON);

        User user = this.recieveUser();

        return user;
    }

    private User recieveUser(){
        User theUser = new User();

        try{

            String tempJSON = this.input.readUTF();
            theUser = theUser.parseJSON(tempJSON);
        }

        catch(Exception e){
            e.printStackTrace();}
        return theUser;
    }

    private void receivePosts(){

        try {
            String JSONString = this.input.readUTF();

            JSONObject obj = new JSONObject(JSONString); //make json object, passing json string

            JSONArray jsonArray = obj.getJSONArray("posts");

            for(int k = 0; k < jsonArray.length(); k++){
                JSONObject temp2 = jsonArray.getJSONObject(k);

                String postText = temp2.getString("text");
                String date = temp2.getString("date");
                String id = temp2.getString("id");

                Post newPost = new Post(postText,date,Integer.parseInt(id));
                this.thePosts.add(newPost);
            }


        }
        catch (JSONException jex){
            System.out.println("JSON is not correct");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTimeline(){

        this.requestPosts();
        this.receivePosts();

        for (int i = 0; i < this.thePosts.size(); i++){
            System.out.println(thePosts.get(i).getPostText());
            System.out.println(thePosts.get(i).getDate());
        }

    }


    public void requestPosts(){
        String JSON = "{\"action\":" + "\"getPosts\"}";
        this.sendJSON(JSON);
    }



    private void run(){

        try {
            connectToServer();
            setStreams();
        }

        catch (EOFException eofex){
            eofex.printStackTrace();
        }
        catch (IOException ioex){
            ioex.printStackTrace();
        }

    }

    public void terminateConnection(){

        try{
            output.close();
            input.close();
            connection.close();
        }

        catch (IOException ioex){
            ioex.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        connection = new Socket("localhost",port);
    }

    private void setStreams() throws  IOException{
        output = new DataOutputStream(connection.getOutputStream());
        output.flush();
        input = new DataInputStream(connection.getInputStream());
    }


}
