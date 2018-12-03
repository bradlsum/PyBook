//Abyel Romero

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.*;

public class Client {

    private DataOutputStream output;
    private DataInputStream input;
    private Socket connection;
    private int port;

    public Client(int port){
        this.port = port;
        this.run();
    }

    public void addNewUserToServer(User newUser){

        //in main menu, if user creates a new account, this function will run after a new user is created
        //the new user is passed to the function and will be output to the server in a json string.
        //server will store new user

    }

    public User logIn(String username, String password){

        //in main menu, if user selects log in, app will call this function.
        // client will output a json string with username and password.
        //server will provide a json string if given username and password are correct.


        return new User();//will return the user object provided by server
    }

    public ArrayList requestPosts(){
        //this function will return an array will all posts contained in server
        //in main menu, if user correctly signs in, a timeline will be created from the app
        //the app will call this function that requests the timeline data from the server (posts)
        //the server will provide all the posts in the posts array (in json) with their associated likes
        //the app will print the posts (the comments and likes)
        //at the top of the timeline, there will be options to create a new post or edit a post so that other users can see them
        //in each post there is an option to like the post

        return  new ArrayList();
    }

//    public void addPost(Post newPost){
//       will add a new post to the server
    //the function passes a json string with the post to be added

//    }

//    public void deletePost(Post thePost){
//        thePost will be deleted from the server
            //client will output a json string to the server with the post to be deleted
//    }

    //if there are more functions to be implemented please tell me.


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
