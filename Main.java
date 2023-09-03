//Scanner user for user inputs
import java.util.Scanner;
//InputMismatchException used for user inputs that don't correspond to what it is expected
import java.util.InputMismatchException;


class Main {
  public static void main(String[] args) {

    //initialize objects
    Scanner sc = new Scanner(System.in);
    User user = new User();
    
    System.out.println("\n        YOUR HEALTH PLAN\n");
    System.out.println("-----------------------------------\n");
    System.out.print("Please enter your first name: ");
    user.setName(sc.nextLine());
    System.out.print("Please enter your last name: ");
    user.setLastName(sc.nextLine());
    //user inputs age with try-catch
    boolean validAge = false;
      while (!validAge) {
        try {
            System.out.print("Please enter your age: ");
            user.setAge(sc.nextInt());
            validAge = true;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for age.");
            sc.nextLine(); 
        }
    }
    //user inputs gender with if-else
    boolean validGender = false;
    while (!validGender) {
      System.out.print("Please enter your gender, 'm' for male and 'f' for female: ");
      String genderInput = sc.next();
      //string that user types should be length 1, I used a string and then charAt since there is no "nextChar()"
      if (genderInput.length() == 1) {
        char genderChar = genderInput.charAt(0);
        if (genderChar == 'm' || genderChar == 'f') {
          user.setGender(genderChar);
          validGender = true;
        } else {
          System.out.println("Invalid input. Please enter 'm' for male or 'f' for female.");
        }
      } else {
        System.out.println("Invalid input. Please enter a single character: 'm' for male or 'f' for female.");
      }
    }
    //user inputs measurementUnit with if-else
    boolean validMeasurementUnit = false;
    while (!validMeasurementUnit) {
      System.out.print("Would you like to enter the info in 'Standard' (Pound/Inch) or 'Metric' (Centimeter/Kilogram)? ");
      String unitInput = sc.next();
      //notice that it's equalsIgnoreCase, user could enter sTanDarD or Standard and it's still correct
      if (unitInput.equalsIgnoreCase("standard") || unitInput.equalsIgnoreCase("metric")) {
        user.setMeasurementUnit(unitInput);
        validMeasurementUnit = true;
      } else {
        System.out.println("Invalid input. Please enter 'Standard' or 'Metric'.");
      }
    }
    //user inputs weight with try-catch
    boolean validInput = false;
    while (!validInput) {
      try {
        System.out.print("Please enter your weight: ");
        user.setWeight(sc.nextDouble());
        validInput = true;
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter a valid weight.");
        sc.nextLine();
      }
    }
    //user inputs height with try-catch
    validInput = false;
    while (!validInput) {
    try {
        System.out.print("Please enter your height");
        if (user.getMeasurementUnit().equalsIgnoreCase("standard")) {
            System.out.print(" (feet): ");
            double feet = sc.nextDouble();
            System.out.print("Please enter your height (inches): ");
            double inches = sc.nextDouble();
            //inches are converted into centimeters (it's easier for me to work with centimeters and kilograms)
            double totalInches = (feet * 12) + inches;
            user.setHeight(totalInches);
        } else if (user.getMeasurementUnit().equalsIgnoreCase("metric")) {
            System.out.print(": ");
            user.setHeight(sc.nextDouble());
        }
        validInput = true;
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter a valid height.");
        sc.nextLine(); 
      }
    }
    //call BMICalculator with user inputs
    double userBmi = user.BMICalculator(user.getMeasurementUnit(), user.getHeight(), user.getWeight()); 
    System.out.println("\n-----------------------------------\n");
    System.out.println("Based on the information you provided: \nYour BMI is " + userBmi + ", that means your physical state is '" + user.BMIMeaning() + "'");
    System.out.println("\nHere are 2 different plans on how you can improve your health");
    int factor = 0;
    boolean input = false;
    //user inputs activity factor
    while (!input) {
      try {
        System.out.print("Rank your physical activity on a scale from 1 to 10: ");
        factor = sc.nextInt();
        //factor should be between 1 and 10 (inclusive)
        if (factor >= 1 && factor <= 10) {
            input = true;
        } else {
            System.out.println("Invalid input. Please enter a value between 1 and 10.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
        sc.nextLine();
      }
    }
    //activity factor is converted from a 1 - 10 scale into a 1 - 2 scale (proportionally)
    double newFactor = (factor - 1) * (1.0 / 9.0) + 1;
    System.out.println("\n-----------------------------------\n\nPlans");
    System.out.println("\n1st Plan: With this plan you will be able to mantain your weight");
    System.out.print("2nd Plan (Recommended): With this plan you will be able to ");
    //depending on the userBMI it is determined which type of diet would best fit for him
    //example: if userBMI is under 18.5, means that he is underweight, so he probably woulf like to be in a healthier weight, so that it's why I recomend to "gain weight and potentially muscle if he/she traind more than three times per week"
    if (userBmi <= 18.5){
      System.out.print("gain weight (potentially muscle if you train +3 times per week\n");
    } else if (userBmi <= 24.9){
      System.out.print("recompose your body (gain muscle and lose body fat if you train +3 times per week\n");
    } else {
      System.out.print("lose body fat and potentially build muscle if you train +3 times per week\n");
    } 

    //user chooses plan of best fit for him/her, if he/she chooses the 2nd plan, the activity factor will be set into 1.5 if it is lower than 1.5 (1.5 represents a person that train at least 3 times per week), if the person has activity factor of 2, this means that he already train every day and there is no reason to lower his/her activity factor
    int n = 0;
    boolean valid = false;
    while (!valid) {
        try {
            System.out.print("\nWhich plan best fits your goals? Enter '1' or '2': ");
            n = sc.nextInt();
            //user must choose between '1' or '2'
            if (n == 1 || n == 2) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter '1' or '2'.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter either '1' or '2'.");
            sc.nextLine();
        }
    }
    if (n == 2 && newFactor <= 1.5) {
      newFactor = 1.5;
    }
    //extraCals are the calories to make a caloric surplus/deficit
    //if user is in healthy weight or decides to choose plan number 1, he will be assigned a maintenance diet
    /*
    Brief Summary
    eat more calories than you burn -> gain weight
    eat less calories than you burn -> lose weight
    eat same calories than you burn -> maintain weight
    eat more calories than you burn and train -> gain weight but high percentage of that weight you gain will be muscle
    eat less calories than you burn and train -> lose weight but low percentage of the weight you lose will be muscle
    eat same calories than you burn and train -> body recomposition lose fat gain weight (not as much as you gain gain in a caloric surplus)
    */
    int extraCals = 0;
    if (n == 2) {
    //+-300 calories is usually a recommended deviation to have from the person maintenance calories, but it depends of the person 
      if (userBmi < 18.5) {
        extraCals += 300;
      } else if (userBmi > 24.9) {
        extraCals -= 300;
      }
    }

    //create plan object
    Plan userPlan = new Plan(user.BMRCalories(user.getWeight(), user.getHeight(), user.getAge(), user.getGender()), n, newFactor, extraCals);
    //create plan file
    userPlan.makePlan(user);
  }
}
