public class User{

  //User attributes
  private String name;
  private String lastName;
  private String measurementUnit;
  private int age;
  private char gender;
  private double height;
  private double weight;
  private double userBMI;

  //No constructor

  //Calculates Body Mass Index
  public double BMICalculator(String measurementUnit, double height, double weight){
    double bmi = 0;
    //If user choses standard as measurment unit
    if (measurementUnit.equalsIgnoreCase("standard")){
      //then, convert lbs -> kg and in -> m, since all my calculations are made in kilograms and meters or centimiters
      weight /= 2.205;
      height *= 2.54;
      setWeight(weight);
      setHeight(height);
    }
    //BMI formula = kg/m^2
    //https://www.nhlbi.nih.gov/health/educational/lose_wt/BMI/bmicalc.htm
    bmi = weight/Math.pow((height/100),2);
    userBMI = Math.round(bmi*100.0)/100.0;
    return userBMI;
  }

  //Interpret BMI based on ranges https://www.cdc.gov/healthyweight/assessing/index.html
  public String BMIMeaning(){
    if (userBMI <= 18.5){
      return "Underweight";
    } else if (userBMI <= 24.9){
      return "Normal Weight";
    } else if (userBMI <= 29.9){
      return "Overweight";
    } else {
      return "Obesity";
    }
  }

  //Calculate Basal Metabolic Rate https://www.calculator.net/bmr-calculator.html
  //BMR is "the number of calories you burn as your body performs basic (basal) life-sustaining function."
  public int BMRCalories(double weight, double height, int age, char gender) {
    int cals = 0;
    if (gender == 'm') {
        cals = (int) Math.round((10 * weight) + (6.25 * height) - (5 * age) + 5);
    } else if (gender == 'f') {
        cals = (int) Math.round((10 * weight) + (6.25 * height) - (5 * age) - 161);
    } else {
        System.out.println("Invalid Gender");
    }
    return cals;
  }

  //Getters
  public double getBMI(){
    return userBMI;
  }
  
  public String getName(){
    return name;
  }
  
  public String getLastName(){
    return lastName;
  }

  public String getMeasurementUnit(){
    return measurementUnit;
  }

  public int getAge(){
    return age;
  }

  public char getGender(){
    return gender;
  }

  public double getWeight(){
    return weight;
  }

  public double getHeight(){
    return height;
  }

  //Setters
  public void setName(String Name){
    name = Name;
  }

  public void setLastName(String LastName){
    lastName = LastName;
  }

  public void setMeasurementUnit(String MeasurementUnit){
    measurementUnit = MeasurementUnit;
  }

  public void setAge(int Age){
    age = Age;
  }

  public void setGender(char Gender){
    gender = Gender;
  }
  public void setWeight(double Weight){
    weight = Weight;
  }

  public void setHeight(double Height){
    height = Height;
  }
  
}
