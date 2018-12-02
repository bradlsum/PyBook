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

public class Signup {
    ArrayList<User> userInfo = new ArrayList<User>();

    public static void main(String [] args){
        int PORT;
        BufferedReader fromServer;
        PrintStream SendServer;
        Scanner serverInput = new Scanner(System.in);
        System.out.println("Enter the port number to connect to server.");
        PORT = serverInput.nextInt();
        try{
            Socket client = new Socket("127.0.0.1", PORT);
            System.out.println("Connection established.");
            Scanner input = new Scanner(System.in);
            String firstName, lastName, password, Cpassword, email, userName, DateOfBirth;
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
            User user = new User(firstName, lastName, email, userName, password, Cpassword, DateOfBirth);
            SendServer = new PrintStream(client.getOutputStream());
            SendServer.println(firstName);
            SendServer.println(lastName);
            SendServer.println(email);
            SendServer.println(userName);
            SendServer.println(password);
            SendServer.println(Cpassword);
            SendServer.println(DateOfBirth);


            System.out.println("Welcome " + userName + " you are now signed in!");
            Scanner userChoice = new Scanner(System.in);
            int choice;
            do {
                System.out.println("What would you like to do?");
                System.out.println("1. Make a post.");
                System.out.println("2. Comment on another user's post.");
                System.out.println("3. Log out of your account.");
                choice = userChoice.nextInt();
                if(choice == 1)
                {
                    String post;
                    System.out.println("What is on your mind?");
                    post = userChoice.nextLine();
                    SendServer.println(post);
                }
                else if(choice == 2)
                {
                    String userPicking, comment;
                    System.out.println("Who's post would you like to comment on?");
                    userPicking = userChoice.nextLine();
                    SendServer.println(userPicking);
                    System.out.println("Write a comment.");
                    comment = userChoice.nextLine();
                    SendServer.println(comment);
                }
                else if(choice == 3)
                {
                    System.out.println("Logging you out of your account.");
                }
                else
                {
                    System.out.println("Invalid choice.");
                }
            }while(choice != 3);

            Date currentDate = new Date();
            System.out.println(currentDate);



        }catch(Exception e)
        {
            System.out.println("Unable to establish a connection to the server.");
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

