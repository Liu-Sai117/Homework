import java.io.*;
import java.util.Arrays;
public class PersonDataManager {
    private static Person people[];
    private static int size;
    public PersonDataManager(){
        size = 0;
    }
    //I have big trouble doing this buildFromFile method, it assign value to people array but in my PersonManager class
    //it just won't read the file and assign anything to the people array in PersonDataManager class.
    public static void buildFromFile(String location) throws IllegalArgumentException {
        File file = new File(location);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Invalid file location: " + location);
        }
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while (br.readLine() != null) {
                count++;
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Person temp[] = new Person[count];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                int element = 0;
                String[] data = line.split(",");
                String name = data[0].trim().replaceAll("\"","").replaceAll(" ","");
                String gender = data[1].trim().replaceAll("\"", "").replaceAll(" ","");
                int age = Integer.parseInt(data[2].trim().replaceAll("\"", "").replaceAll(" ",""));
                double height = Double.parseDouble(data[3].trim().replaceAll("\"", "").replaceAll(" ",""));
                double weight = Double.parseDouble(data[4].trim().replaceAll("\"", "").replaceAll(" ",""));
                Person person = new Person(age, height, weight, name, gender);
                temp[element] = person;
                element++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading the file: " + e.getMessage());
        }
        people = temp;
        size = count;
        System.out.println(temp[1].getName());

    }
    public void addPerson (Person newPerson) throws PersonAlreadyExistsException{
        //check if person exist
        for(int i = 0; i<people.length;i++){
            if(people[i].equals(newPerson)){
                throw new PersonAlreadyExistsException("This person already exist!");
            }
        }
        //if the size is equal to people.length, which means it's full, increase capacity
        if(size == people.length){
            increaseCapacity();
        }
        //check for the index to add person with correct order
        int index=0;
        while (index<people.length && people[index].getName().compareTo(newPerson.getName())<0){
            index++;
        }
        //add person to that index
        System.arraycopy(people, index, people, index+1, size-index);
        people[index] = newPerson;
        size++;
    }
    //increase the Capacity of array
    private static void increaseCapacity() {
        int newCapacity = (people.length +1)* 2;
        people = Arrays.copyOf(people, newCapacity);
    }
    public void getPerson(String name) throws PersonDoesNotExistsException{
        boolean ifExist=false;
        //check if the person exist
        for(int i = 0; i<people.length; i++){
            if(people[i].getName().equals(name)){
                ifExist = true;
            }
        }
        if(ifExist == false){
            throw new PersonDoesNotExistsException("This person don't exist!");
        }
        //find the person's name and print all it's informaton
        for(int i = 0; i<people.length; i++){
            if(people[i].getName().equals(name)){
                System.out.println("Person found!");
                System.out.println("Name: "+people[i].getName());
                System.out.println("Gender: "+people[i].getGender());
                System.out.println("Age: "+people[i].getAge());
                System.out.println("Height: "+people[i].getHeight());
                System.out.println("Weight: "+people[i].getWeight());
                break;
            }
        }
    }
    public void removePerson(String name) throws PersonDoesNotExistsException{
        boolean ifExist = false;
        //check if the person exist and if exist then move the person to last index and nullify that.
        for(int i = 0; i<people.length; i++){
            if(people[i].getName().equals(name)){
                System.arraycopy(people, i + 1, people, i, size - i - 1);
                people[size - 1] = null;
                size--;
                ifExist = true;
                break;
            }
        }
        if(ifExist == false){
            throw new PersonDoesNotExistsException("This person does not exist!");
        }
    }
    //print the inforation in table format
    public void printTable(){
        System.out.println("-------------------------------------------------");
        System.out.println("  Name  |  Gender  |  Age  |  Height  |  Weight  ");
        System.out.println("-------------------------------------------------");
        for(int i= 0; i<people.length; i++){
            if(people[i] !=null){
                System.out.println("  "+people[i].getName()+"  |  "+people[i].getGender()+"  |  "+people[i].getAge()+"  |  "+people[i].getHeight()+"  |  "+people[i].getWeight());
            }  
        } 
        System.out.println("-------------------------------------------------");
    }
}
