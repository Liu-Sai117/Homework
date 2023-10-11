import java.util.Scanner;
import java.io.*;
public class PersonManager{
     
    public static void main(String[] args) throws PersonAlreadyExistsException, PersonDoesNotExistsException{
        PersonDataManager personManager = new PersonDataManager();
        Scanner sc = new Scanner(System.in);
        char choice;
        System.out.println("Menu Options:");
        System.out.println("(I) - Import from File");
        System.out.println("(A) - Add Person");
        System.out.println("(R) - Remove Person");
        System.out.println("(G) - Get Info on Person");
        System.out.println("(P) - Print Table");
        System.out.println("(S) - Save to File");
        System.out.println("(Q) - Quit");
        System.out.println("Enter your choice: ");
        choice = sc.nextLine().toUpperCase().charAt(0);
        //The program will end if the user choose to quit (Q), if not then the program will ask the user to make a choice again
        while(choice != 'Q'){
        switch (choice) {
            case 'I':
            System.out.println("Please enter a location");
            String location =sc.nextLine();
            PersonDataManager.buildFromFile(location);
                break;
            case 'A':
            System.out.println("Please enter the name of the person: ");
            String name = sc.nextLine();
            System.out.println("Please enter the age:");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter the gender(M or F)");
            String genderMF = sc.nextLine();
            System.out.println("Please enter the height (in inches): ");
            double height = sc.nextDouble();
            sc.nextLine();
            System.out.println("Please enter the weight (in lbs): ");
            double weight = sc.nextDouble();
            sc.nextLine();
            //after user enter all the data, make them a new Person Object and add that person by addPerson
            Person p = new Person(age, height, weight, name, genderMF);
            personManager.addPerson(p);
                break;
            case 'R':
            System.out.println("Please enter the name of the person: ");
            //Remove the person with its name
            String nameRemove = sc.nextLine();
            personManager.removePerson(nameRemove);
                break;
            case 'G':
            System.out.println("Please enter the name of the person: ");
            String nameInfo = sc.nextLine();
            personManager.getPerson(nameInfo);
                break;
            case 'P':
            personManager.printTable();
                break;
            case 'S':
                System.out.println("Please select a name for the file: ");
                //After user enter a file name, it will create a new file.
                String fileName = sc.nextLine();
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
                    System.out.println(" A file named "+fileName+" has been created!");
                }catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 'Q':
                System.out.println("Exiting the program!");
                break;
            default:
            //If user entered anything other than the choices, this message will popup.
                System.out.println("Invalid choice. Please try again.");
        }
        System.out.println("Enter your choice: ");
        choice = sc.nextLine().toUpperCase().charAt(0);
    }
    }
}