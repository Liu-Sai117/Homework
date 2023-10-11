public class Person {
    private int age;
    private double height;
    private double weight;
    private String name;
    private String gender;
    public Person(int age, double height, double weight, String name, String gender){
        age = this.age;
        height = this.height;
        weight = this.weight;
        name = this.name;
        gender = this.gender;
    }
    public int getAge(){
        return age;
    }
    public double getHeight(){
        return height;
    }
    public double getWeight(){
        return weight;
    }
    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public void setAge(int num){
        age = num;
    }
    public void setHeight(int num){
        height = num;
    }
    public void setWeight(double num){
        weight = num;
    }
    public void setName(String str){
        name = str;
    }
    public void setGender(String str){
        gender = str;
    }
    public String toString(){
        return "Name: "+getName() +" Gender: "+getGender()+ "Age: "+getAge()+" Height: "+getHeight()+" Weight: "+getWeight();
    }
}
