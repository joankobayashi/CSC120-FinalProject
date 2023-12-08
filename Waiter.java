import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Waiter {
    Scanner scanner = new Scanner(System.in);
    /**
     * Player name
     */
    String name;

    /**
     * Name of the player's date
     */
    String dateName;

    /**
     * Amount of tips the player currently has
     */
    private int tips;

    /**
     * Current no. of times the player has fucked up
     */
    private int currentFuckUps;

    /**
     * No. of fuck ups left
     */
    private int fuckUpsLeft;

    /**
     * Sets maximum no. of times player can fuck up to 3
     */
    final private int maxFuckUps = 3;

    /**
     * Whether or not the player is fired
     */
    public boolean isFired = false;

    /**
     * No. of times the player has asked the kitchen for help
     */
    private int askKitchenCount = 0;


    /**
     * Constructor
     * @param name
     * @param dateName
     */

    public Waiter(String name, String dateName){
        this.name = name;
        this.dateName = dateName;
        this.tips = 0;
        this.currentFuckUps = 0;
        this.fuckUpsLeft = 3;
    }

     /**
     * Returns the current number of times the player has fucked up
     * @return currentFuckUps
     */

    public int getCurrentFuckUps(){
        return this.currentFuckUps;
    }
     /**
     * Returns the number of fucks up left
     * @return fuckUpsLeft
     */

    public int getFuckUpsLeft(){
        return this.fuckUpsLeft;
    }
    /**
     * Returns the maximum number of times the player can fuck up
     * @return maxFuckUps
     */
    public int getMaxFuckUps() {
        return maxFuckUps;
    }

    /**
     * Returns the current amount of tips the player has
     * @return tips
     */
    public double getTips() {
        return this.tips;
    }

    /**
     * Outputs a message to welcome the customer and prompt them to begin their order.
     */
    public void greet(){
        System.out.println(this.name + "Hello! My name is " + this.name + "Welcome to " + Restaurant.name + ". What can I get started for you today?");
    }

    /**
     * Allows the player to answer the customer's question, and adjusts tips, no. of times they have fucked up accordingly. Allows user to ask the kitchen, if they have chances available.
     * @param question The customer's question
     * @param amount Amount the tip must be decremented by
     */
    public void answerQuestion(String question, int amount){
        System.out.println("Game: Choose your answer: \n" + "A. Yes\n" + "B. No\n" + "C. I'm not sure, let me double check that. \n");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("C")){
            System.out.println(this.name + ": I'm not sure, let me double check that.");
            System.out.println("Game: Would you like to ask the kitchen?\n" + "A. Yes\n" + "B. No\n");
            String askKitchenChoice = scanner.nextLine();
            if(askKitchenChoice.equalsIgnoreCase("A")){
                askKitchen(question);
            }
            
            else if(askKitchenChoice.equalsIgnoreCase("B")){
                answerQuestion(question, amount);
            }

           else if (askKitchenChoice.equalsIgnoreCase("help")){
                gameHelp.help();
            } 
            
            else {
                System.out.println("Game: Choose one of the given options.");
            }
        }

        else if (answer.equalsIgnoreCase("A") || answer.equalsIgnoreCase("B")){
            if (answer.equalsIgnoreCase("A")){
                System.out.println(this.name + "Yes");
            }
            else if (answer.equalsIgnoreCase("B")){
                System.out.println(this.name + "No");
            }

            if (!(answer.equalsIgnoreCase(Restaurant.questions.get(question)))){
                System.out.println("Game: You were wrong. The customer reported you to the manager.");
                this.currentFuckUps += 1;
                this.fuckUpsLeft = this.maxFuckUps - this.currentFuckUps;
                System.out.println("Manager: How did you not know that? I'd better not get any more complaints. You only have " + fuckUpsLeft + " fuck ups left.");
                Customer.tip(amount);
            }
            else{
                System.out.println(Customer.name + ": Okay. Thank you.");
            }
        }
        
        if (answer.equalsIgnoreCase("help")){
            gameHelp.help();
        }
        else {
            System.out.println("Choose one of the given options.");
        }
    }

    /**
     * Prompts user to pick the correct order from a list of menu items
     * @param order String
     * @param customerName String
     */
    public void enterOrder(String order, String customerName){
       // how do i randomly assign A,B,C,D to the menu items, so the correct option is not always D
       System.out.println("What did " + customerName + "order?");
       Random random = new Random();
       int randomIndex = random.nextInt(Restaurant.menu.size());
       System.out.println("A." + Restaurant.menu.get(randomIndex).name);
       randomIndex = random.nextInt(Restaurant.menu.size());
       System.out.println("B. " + Restaurant.menu.get(randomIndex).name);
       randomIndex = random.nextInt(Restaurant.menu.size());
       System.out.println("C. " + Restaurant.menu.get(randomIndex).name);
       System.out.println("D. " + order);
    }

    /**
     * Checks the questions hashtable and returns the correct answer, if the player still has a chance left
     */
    private void askKitchen(String question){
        if (askKitchenCount < 1) {
            System.out.println("Game: Entering Kitchen......\n" + "Chef: What do you want " +this.name + "?\n" + this.name + ": " + question);
            System.out.println("Chef: " + Restaurant.questions.get(question));
        }

        else {
            System.out.println("Chef: Get out. You've already asked me a question, let me do my job. ");
        }
    }
    
    /**
     * Allows the player to view the menu
     */
    public void viewMenu(){
        Restaurant.printMenu();
    }


    /**
     * Serves food to the customer
     */
    public void serveFood(){
        System.out.println("The customers at this table have received their food.");
    }
}
