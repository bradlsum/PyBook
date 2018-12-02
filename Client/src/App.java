import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class App {
    ArrayList<User> userInfo = new ArrayList<User>();

    public static void main(String [] args){
        int PORT = 65432;
        Client client = new Client(PORT); //streams and connections are setup in client constructor, need to terminate connections using client.terminateConnections()

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to PyBook!\nPlease choose an option");
        System.out.println("1.-Log in");
        System.out.println("2.-Create an account");
        int selection = input.nextInt();
        switch (selection){

            case 1:
                System.out.print("Username: ");
                String username = input.nextLine();
                System.out.print("Password: ");
                String password = input.nextLine();

                User theUser = client.logIn(username,password); //the user should have the correct user given credentials

                break;


            case 2:

                try{

                    String firstName, lastName, Cpassword, email, userName, DateOfBirth;
                    System.out.println("You are now in the sign up page!");
                    System.out.println("Please enter the following information below ");
                    System.out.print("First Name: ");
                    firstName = input.nextLine();
                    System.out.print("\nLast Name: ");
                    lastName = input.nextLine();
                    System.out.print("\nEmail: ");
                    email = input.nextLine();
                    System.out.print("\nUsername: ");
                    userName = input.nextLine();
                    System.out.print("\nPassword: ");
                    password = input.nextLine();
                    System.out.print("\nConfirm Password: ");
                    Cpassword = input.nextLine();
                    System.out.print("\nDate Of Birth: ");
                    DateOfBirth = input.nextLine();


                    while(!validatePassword(password, Cpassword))
                    {
                        System.out.println("The information you entered is incorrect, please enter your correct information below.");
                        System.out.print("First Name: ");
                        firstName = input.nextLine();
                        System.out.print("\nLast Name: ");
                        lastName = input.nextLine();
                        System.out.print("\nEmail: ");
                        email = input.nextLine();
                        System.out.print("\nUsername: ");
                        userName = input.nextLine();
                        System.out.print("\nPassword: ");
                        password = input.nextLine();
                        System.out.print("\nConfirm Password: ");
                        Cpassword = input.nextLine();
                        System.out.print("\nDate Of Birth: ");
                        DateOfBirth = input.nextLine();
                    }
                    User newUser = new User(firstName, lastName, email, userName, password, Cpassword, DateOfBirth);

                    client.addNewUserToServer(newUser); //send to server

                    System.out.println("Welcome " + newUser.getUserName() + " you are signed in!");

                    Date currentDate = new Date();
                    System.out.println(currentDate);


                }catch(Exception e)
                {
                    System.out.println("Unable to establish a connection to the server.");
                }

                break;
        }
    }







    public static boolean validatePassword (String password, String Cpassword)
    {
        Pattern hasUpperCase = Pattern.compile("[A-Z]");
        Pattern hasLowerCase = Pattern.compile("[a-z]");
        Pattern hasNumber = Pattern.compile("[0-9]");
        Pattern hasSpecial = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        boolean isGood = true;
        if(!password.equals(Cpassword))
        {
            System.out.println("Error: Your password and confirm passwords do not match.");
            isGood = false;
        }
        if(password.length() < 8)
        {
            System.out.println("Error: Password has to be at least 8 characters long.");
            isGood = false;
        }
        if(!hasUpperCase.matcher(password).find())
        {
            System.out.println("Error: Your password needs to contain a minimum of 1 upper case letter.");
            isGood = false;
        }
        if(!hasLowerCase.matcher(password).find())
        {
            System.out.println("Error: Your password needs to contain a minimum of 1 lower case letter.");
            isGood = false;
        }
        if(!hasNumber.matcher(password).find())
        {
            System.out.println("Error: Your password needs to contain a minimum of 1 number.");
            isGood = false;
        }
        if(hasSpecial.matcher(password).find())
        {
            System.out.println("Error: Your password contains a special character. No special characters allowed in password.");
            isGood = false;
        }
        return isGood;
    }

}