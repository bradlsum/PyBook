import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class app {
    static int PORT = 65432;
    static BufferedReader fromServer;
    static PrintStream SendServer;
    static Socket client = null;
    static Scanner sc = new Scanner(System.in);

    static String getUsers = "{\"action\":\"getUsers\"}";
    static String getPosts = "{\"action\":\"getPosts\"}";
    static String exit = "{\"action\":\"exit\"}";

    public static void main(String [] args) throws FileNotFoundException {
        System.out.println("Welcome to PyBook!\nPlease choose an option");
        System.out.println("1. Log in");
        System.out.println("2. Create an account");
        int selection = sc.nextInt();

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        String req = "";

        switch (selection) {

            case 1: {
                sc.nextLine();
                System.out.print("Username: ");
                String userName = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                menu(client, userName);

                // }
                //else
                //{
                //   System.out.println("Wrong username or password.");
                //}

                //User theUser = client.logIn(username, password); //the user should have the correct user given credentials

                break;
            }
            case 2: {
                try {
                    client = new Socket("127.0.0.1", PORT);
                    System.out.println("Connection established.");
                    Scanner sc = new Scanner(System.in);

                    System.out.print("Enter your name:");
                    String first = sc.nextLine();
                    System.out.print("Enter your last name:");
                    String last = sc.nextLine();
                    System.out.print("Enter your email:");
                    String email = sc.nextLine();
                    System.out.print("Enter your username:");
                    String username = sc.nextLine();
                    System.out.print("Enter your password:");
                    String pass = sc.nextLine();
                    send(client, addUser(first, last, email, username, pass));
                    System.out.println(recieve(client));
                    client.close();

                    System.out.println("Welcome " + username + " you are now signed in!");
                    menu(client, username);
                }catch (Exception e) {
                    System.out.println("Unable to establish a connection.");
                }
            }
            default:
                System.out.println("Invalid");
        }
    }

    public static void menu (Socket client, String userName){
        try {
            client = new Socket("127.0.0.1", PORT);
            System.out.println("Connection established.");

            SendServer = new PrintStream(client.getOutputStream());
            System.out.println("Welcome " + userName + " you are now signed in!");

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("What would you like to do?\n" +
                        "1. Make a post.\n" +
                        "2. Comment on another user's post.\n" +
                        "3. Like a post or comment.\n" +
                        "4. Log out of your account.");

                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    send(client, getPosts);
                    System.out.println(recieve(client));

                    System.out.print("Enter your post:");
                    String text = sc.nextLine();

                    send(client, addPost(client, userName, text));

                } else if (choice == 2) {
                    send(client, getPosts);
                    System.out.println(recieve(client));

                    String id, comment;
                    System.out.println("Who's post would you like to comment on?");
                    id = sc.nextLine();

                    System.out.println("What would you like to say?");
                    comment = sc.nextLine();

                    send(client, addComment(client, id, userName, comment));

                } // else if (choice == 3) {
//                                int CommentPost;
//                                System.out.println("1. Like a post.");
//                                System.out.println("2. Like a comment.");
//                                CommentPost = sc.nextInt();
//                                SendServer.println(CommentPost);
//                                if (CommentPost == 1) {
//                                    int postPick;
//                                    System.out.println("Choose a post to like.");
//                                    postPick = input.nextInt();
//                                    SendServer.println(postPick);
//                                    System.out.println(userName + " like this post.");
//                                    SendServer.println(userName + " like this post.");
//                                } else if (CommentPost == 2) {
//                                    int commentPick;
//                                    System.out.println("Choose a comment to like.");
//                                    commentPick = input.nextInt();
//                                    SendServer.println(commentPick);
//                                    System.out.println(userName + " like this comment.");
//                                    SendServer.println(userName + " like this comment.");
//                                } else {
//                                    System.out.println("Error!! Invalid choice.");
//                                }
                /*}*/
                else if (choice == 4) {
                    send(client, exit);
                    client.close();
                } else {
                    System.out.println("Invalid choice.");
                }
            } while (choice != 4);

        } catch (Exception e) {
            System.out.println("Unable to establish a connection.");
        }
    }

    public static void send(Socket client, String req) throws IOException {
        // Send through socket
        OutputStreamWriter temp = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
        temp.append(req);
        temp.flush();
        //temp.close();
        System.out.println("Output flushed...");
    }

    public static String recieve(Socket client) throws  IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        String res = in.readLine();
        //in.close();
        return res;
    }

    public static String addUser(String first, String last, String email, String username, String password){
        return ("{" +
                "  \"action\":\"addUser\"," +
                "  \"first_name\":\"" + first + "\"," +
                "  \"last_name\":\"" + last + "\"," +
                "  \"email\": \"" + email +"\"," +
                "  \"username\":\"" + username + "\"," +
                "  \"password\":\"" + password +"\"" +
                "}");
    }

    public static String addComment(Socket client, String id, String username, String text){
        return ("{" +
                "  \"action\":\"addComment\"," +
                "  \"pid\":\"" + id + "\"," +
                "  \"username\":\"" + username + "\"," +
                "  \"text\": \"" + text + "\"" +
                "}");
    }

    public static String addPost(Socket client, String username, String text){
        return ("{" +
                "  \"action\":\"addPost\"," +
                "  \"username\":\"" + username + "\"," +
                "  \"text\": \"" + text + "\"" +
                "}");
    }
}

