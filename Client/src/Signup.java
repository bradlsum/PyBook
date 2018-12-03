import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class Signup {
    ArrayList<User> userInfo = new ArrayList<User>();

    public static void main(String [] args) throws FileNotFoundException {
        int PORT = 65432;
        BufferedReader fromServer;
        PrintStream SendServer;
        Socket client = null;
        Scanner serverInput = new Scanner(System.in);
        Date currentDate = new Date();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to PyBook!\nPlease choose an option");
        System.out.println("1. Log in");
        System.out.println("2. Create an account");
        int selection = input.nextInt();
        switch (selection) {

            case 1: {
                // This will get the credintal from server and will be used to validate with the user inputs
                /*Scanner file = new Scanner(new File("Gets credentials from server."));
                String serverUsername = file.nextLine(); // Place holder but will be used to search server for user credintal
                String serverPassword = file.nextLine(); // Place holder but will be used to search server for user credintal
*/              input.nextLine();
                System.out.print("Username: ");
                String userName = input.nextLine();
                System.out.print("Password: ");
                String password = input.nextLine();
                //if(userName.equals(serverUsername) && password.equals(serverPassword)) {
                    try {
                        client = new Socket("127.0.0.1", PORT);
                        System.out.println("Connection established.");

                        SendServer = new PrintStream(client.getOutputStream());
                        System.out.println("Welcome " + userName + " you are now signed in!");
                        Scanner userChoice = new Scanner(System.in);
                        int choice;
                        do {
                            System.out.println("What would you like to do?");
                            System.out.println("1. Make a post.");
                            System.out.println("2. Comment on another user's post.");
                            System.out.println("3. Like a post or comment.");
                            System.out.println("4. Log out of your account.");
                            choice = userChoice.nextInt();
                            if (choice == 1) {
                                String post;
                                System.out.println("What is on your mind?");
                                post = userChoice.nextLine();
                                SendServer.println(post);
                                SendServer.println(currentDate);
                                Post posting = new Post(post);
                            } else if (choice == 2) {
                                String userPicking, comment;
                                System.out.println("Who's post would you like to comment on?");
                                userPicking = userChoice.nextLine();
                                SendServer.println(userPicking);
                                System.out.println("Write a comment.");
                                comment = userChoice.nextLine();
                                SendServer.println(comment);
                                SendServer.println(currentDate);
                                Comment commenting = new Comment(comment);
                            } else if (choice == 3) {
                                int CommentPost;
                                System.out.println("1. Like a post.");
                                System.out.println("2. Like a comment.");
                                CommentPost = userChoice.nextInt();
                                SendServer.println(CommentPost);
                                if (CommentPost == 1) {
                                    int postPick;
                                    System.out.println("Choose a post to like.");
                                    postPick = input.nextInt();
                                    SendServer.println(postPick);
                                    System.out.println(userName + " like this post.");
                                    SendServer.println(userName + " like this post.");
                                } else if (CommentPost == 2) {
                                    int commentPick;
                                    System.out.println("Choose a comment to like.");
                                    commentPick = input.nextInt();
                                    SendServer.println(commentPick);
                                    System.out.println(userName + " like this comment.");
                                    SendServer.println(userName + " like this comment.");
                                } else {
                                    System.out.println("Error!! Invalid choice.");
                                }
                            } else if (choice == 4) {
                                System.out.println("Logging you out of your account.");
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        } while (choice != 4);

                    } catch (Exception e) {
                        System.out.println("Unable to establish a connection.");
                    }
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
                    //Scanner input = new Scanner(System.in);
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

                    // Validates that the password meets all requirements.
                    // Check to make sure all user information is not null
                    while (!validation(password, Cpassword, firstName, lastName, email, userName, DateOfBirth)) {
                        System.out.println("The information you entered does not meet requirements. Please make sure your information meets requirements.");
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
                        System.out.println("3. Like a post or comment.");
                        System.out.println("4. Log out of your account.");
                        choice = userChoice.nextInt();
                        if (choice == 1) {
                            String post;
                            System.out.println("What is on your mind?");
                            post = userChoice.nextLine();
                            SendServer.println(post);
                            SendServer.println(currentDate);
                            Post posting = new Post(post);
                        } else if (choice == 2) {
                            String userPicking, comment;
                            System.out.println("Who's post would you like to comment on?");
                            userPicking = userChoice.nextLine();
                            SendServer.println(userPicking);
                            System.out.println("Write a comment.");
                            comment = userChoice.nextLine();
                            SendServer.println(comment);
                            SendServer.println(currentDate);
                            Comment commenting = new Comment(comment);
                        } else if (choice == 3) {
                            int CommentPost;
                            System.out.println("1. Like a post.");
                            System.out.println("2. Like a comment.");
                            CommentPost = userChoice.nextInt();
                            SendServer.println(CommentPost);
                            if (CommentPost == 1) {
                                int postPick;
                                System.out.println("Choose a post to like.");
                                postPick = input.nextInt();
                                SendServer.println(postPick);
                                System.out.println(userName + " like this post.");
                                SendServer.println(userName + " like this post.");
                            } else if (CommentPost == 2) {
                                int commentPick;
                                System.out.println("Choose a comment to like.");
                                commentPick = input.nextInt();
                                SendServer.println(commentPick);
                                System.out.println(userName + " like this comment.");
                                SendServer.println(userName + " like this comment.");
                            } else {
                                System.out.println("Error!! Invalid choice.");
                            }
                        } else if (choice == 4) {
                            System.out.println("Logging you out of your account.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } while (choice != 4);

                } catch (Exception e) {
                    System.out.println("Unable to establish a connection to the server.");
                }
                break;
            }
            case 3:
            {
                System.out.println("Not a valid option.");
            }
        }
    }
    public static boolean validation(String password, String Cpassword, String firstName, String lastName, String email, String userName, String DateOfBirth)
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
        if(firstName.isEmpty())
        {
            System.out.println("Required field!! First name cannot be left blank.");
            isGood = false;
        }
        if(lastName.isEmpty())
        {
            System.out.println("Required field!! Last name cannot be left blank.");
            isGood = false;
        }
        if(!hasSpecial.matcher(email).find())
        {
            System.out.println("Error, your email needs to have the '@' sign.");
            isGood = false;
        }
        if(userName.isEmpty())
        {
            System.out.println("Required field!! Username cannot be left blank.");
            isGood = false;
        }
        if(DateOfBirth.isEmpty())
        {
            System.out.println("Required field!! Date of Birth cannot be left blank.");
            isGood = false;
        }
        return isGood;
    }

}

