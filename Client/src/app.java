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

    public static void main(String [] args) throws FileNotFoundException, IOException {
        System.out.print("Welcome to PyBook!\nPlease choose an option\n" +
                "1. Log in\n" +
                "2. Create an account\n" +
                "Input: ");
        int selection = sc.nextInt();
        sc.nextLine();

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        String req = "";

        client = new Socket("127.0.0.1", PORT);
        System.out.println("Connection established.");

        switch (selection) {

            case 1: {
                String res = "";

                do {
                    System.out.print("Username: ");
                    String userName = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    send(client, auth(userName, password));

                    res = recieve(client);

                    if (res.equals("true"))
                        menu(client, userName);
                    else
                        System.out.println("User does not exist.");

                }while(res.equals("false"));
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

                    menu(client, username);
                }catch (Exception e) {
                    System.out.println("Unable to establish a connection.");
                }
            }
            default:
                //System.out.println("Invalid");
        }
    }

    public static void menu (Socket client, String userName){
        try {
            SendServer = new PrintStream(client.getOutputStream());
            System.out.println("Welcome " + userName + " you are now signed in!");

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("What would you like to do?\n" +
                        "1. Print posts.\n" +
                        "2. Create a post.\n" +
                        "3. Like a post.\n" +
                        "4. Comment on a post\n" +
                        "5. Log out of your account.\n" +
                        "Input: ");

                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    send(client, getPosts);
                    System.out.println(recieve(client));
                } else if (choice == 2) {
                    System.out.print("Enter your post:");
                    String text = sc.nextLine();

                    send(client, addPost(client, userName, text));
                }  else if (choice == 3) {
                    String id;

                    send(client, getPosts);
                    System.out.println(recieve(client));

                    System.out.println("Who's post would you like to like?");
                    id = sc.nextLine();

                    send(client, addLike(client, id, userName));
                } else if (choice == 4){
                    String id, comment;

                    send(client, getPosts);
                    System.out.println(recieve(client));

                    System.out.println("Who's post would you like to comment on?");
                    id = sc.nextLine();

                    System.out.println("What would you like to say?");
                    comment = sc.nextLine();

                    send(client, addComment(client, id, userName, comment));
                } else if (choice == 5) {
                    send(client, exit);
                    client.close();

                    System.out.println("Closesing client...");

                    return;
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
        System.out.println("Sending messages...");
    }

    public static String recieve(Socket client) throws  IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        String res = in.readLine();
        //in.close();
        return res;
    }

    public static String auth(String username, String password){
        return ("{" +
                "  \"action\":\"auth\"," +
                "  \"username\":\"" + username + "\"," +
                "  \"password\":\"" + password +"\"" +
                "}");
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

    public static String addLike(Socket client, String id, String username){
        return ("{" +
                "  \"action\":\"addLike\"," +
                "  \"username\":\"" + username + "\"," +
                "  \"pid\": \"" + id + "\"" +
                "}");
    }

    public static String removeUser(String username) {
        return ("{" +
                "   \"action\":\"removeUser\"," +
                "   \"username\":\"" + username + "\"" +
                "}");
    }

    public static String removePost(String username, String pid) {
        return ("{" +
                "  \"action\":\"removePost\"," +
                "  \"pid\":\"" + pid + "\"," +
                "  \"username\":\"" + username + "\"," +
                "}");
    }

    public static String removeComment(String username, String pid, String cid) {
        return ("{" +
                "  \"action\":\"removeComment\"," +
                "  \"pid\":\"" + pid + "\"," +
                "  \"cid\":\"" + cid + "\"," +
                "  \"username\":\"" + username + "\"," +
                "}");
    }

    public static String removeLike(String username, String pid) {
        return ("{" +
                "  \"action\":\"removeLike\"," +
                "  \"pid\":\"" + pid + "\"," +
                "  \"username\":\"" + username + "\"," +
                "}");
    }
}

