import java.io.FileWriter;
import java.io.IOException;

public class Plan{

  //Plan attributes
  private int calories;
  private int planNumber;
  private double activityFactor;
  private int extraCals;

  //Plan Constructor
  public Plan(int cals, int num, double factor, int moreCals){
    calories = cals;
    planNumber = num;
    //activityFactor represent the multiplier of the BMR based on physical activity on a single day
    //https://www.google.com/search?sca_esv=561214195&sxsrf=AB5stBgQtMII2ttJUzupC8AiAI1ZHRVxMA:1693370155779&q=activity+factor&tbm=isch&source=lnms&sa=X&ved=2ahUKEwiE2e60x4OBAxWWIEQIHaGXBKYQ0pQJegQIChAB&biw=1920&bih=923&dpr=1#imgrc=NCfaXhW-ckCIhM
    //it's usually represented in a scale from 1 - 2
    //when user enters factor it's in a scale from 1 - 10, since it's easier for the user to think about a number, then it's converted proportionally to a scale from 1 - 2
    activityFactor = factor;
    //extraCals represent the calories added or subtracted in order to make a caloric surplus/deficit
    extraCals = moreCals;
  }

  //makePlan makes plan based on specific user inputs
  public void makePlan(User user){
    try{
      //creates file with user name and last name
      //from here... (look down)
      FileWriter file = new FileWriter(user.getName().toUpperCase() + "_" + user.getLastName().toUpperCase() + ".txt");
      file.write("DIET PLAN FOR " + user.getName().toUpperCase() + " " + user.getLastName().toUpperCase() + "\n");
      file.write("Age = " + user.getAge() + "\n");
      String genderString;
      if (user.getGender() == 'm') {
          genderString = "Male";
      } else if (user.getGender() == 'f') {
          genderString = "Female";
      } else {
          genderString = "Invalid";
      }
      file.write("Gender: " + genderString + "\n");
      file.write("Measurement Unit: " + user.getMeasurementUnit() + "\n");
      if (user.getMeasurementUnit().equalsIgnoreCase("standard")) {
        double weightInLbs = user.getWeight() * 2.205;
        double totalCM = user.getHeight();
        double feet = Math.floor(totalCM / 30.48);
        double inches = Math.round((totalCM % 30.48) / 2.54);
        file.write("Weight: " + weightInLbs + " lbs\n");
        file.write("Height: " + feet + " feet " + inches + " inches\n");
      } else if (user.getMeasurementUnit().equalsIgnoreCase("metric")) {
        file.write("Weight: " + user.getWeight() + " kg\n");
        file.write("Height: " + user.getHeight() + " cm\n");
      }
      file.write("BMI: " + user.getBMI() + " (" + user.BMIMeaning() + ")\n");
      file.write("-----------------------------------\n");
      file.write("Plan Number: " + planNumber + "\n");
      file.write("-----------------------------------\n");
      //until here, it is just the same user inputs
      //the following information 
      file.write("Plan Details:\n");
      file.write("Activity Factor: " + Math.round(activityFactor*10)/10.0 + " (in a scale from 1 to 2)\n");
      //the ideal calories a human being should consume is the bmr (calories) times the person activity factor +- an 'x' amount of calories depending of the goal the person has
      int totalCals = (int) (calories * activityFactor + extraCals);
      //this section calculates the ideal carbohydrates, protein and fats grams the person should eat in a single day
      double carbsRatio, proteinRatio, fatRatio;
      if (planNumber == 1) {
          carbsRatio = 0.50;
          proteinRatio = 0.25;
          fatRatio = 0.25;
      } else {
          carbsRatio = 0.50;
          proteinRatio = 0.30;
          fatRatio = 0.20;
      }
      //totalCals multiplied by the ratio is the amount of calories that are carbohydrates, if we divide it by 4, it gives us the amount of grams we should eat per day
      //https://www.google.com/search?q=carbohydrates+proteins+and+fats+calories&tbm=isch&ved=2ahUKEwju-b_vyYOBAxVqhu4BHZZJDHcQ2-cCegQIABAA&oq=carbohydrates+proteins+and+fats+calories&gs_lcp=CgNpbWcQAzoECCMQJzoGCAAQBxAeOgcIABATEIAEOgYIABAeEBM6CAgAEAUQHhATOggIABAIEB4QEzoGCAAQCBAeUNECWP4KYMULaABwAHgAgAFXiAHyBJIBAjEwmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=v8nuZK6eJeqMur8PlpOxuAc&bih=923&biw=1920#imgrc=xW6Rh3GN-trevM
      double carbsGrams = (totalCals * carbsRatio) / 4;
      double proteinGrams = (totalCals * proteinRatio) / 4;
      double fatGrams = (totalCals * fatRatio) / 9;
      file.write("Calories Per Day: " + totalCals + " kcal\n");
      file.write("Carbohydrates Grams Per Day: " + (int) carbsGrams + " g\n");
      file.write("Protein Grams Per Day: " + (int) proteinGrams + " g\n");
      file.write("Fat Grams Per Day: " + (int) fatGrams + " g\n");
      file.close();
      System.out.println("Understood! Please check Files>" + user.getName() + "_" + user.getLastName() + ".txt");
      //catch in case there is any error while creating the file
    } catch (IOException e) {
      System.out.println("Error occured while creating file");
      e.printStackTrace();
    }
    //NOTE: THERE IS NO CALORIE CALCULATOR THAT IS 100% EXACT, EACH PERSON IS UNIQUE AND THE METABOLISM IT'S DIFFERENT           //HOWEVER, CALORIE CALCULATORS PROVIDE A GOOD APPROXIMATION
    //NOTE ALSO THAT BMI IT'S NOT ALWAYS A GOOD PARAMETER TO DETERMINE IF A PERSON IS REALLY 'OVERWEIGHT', ALMOST EVERY BODYBUILDER HAS A BMI THAT IS CONSIDERED 'OBESE'. SOMETIMES BEING OBESE != BEING 'FAT'
  }  
}
