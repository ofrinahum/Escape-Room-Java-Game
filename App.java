
import java.util.*;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        GameState state = new GameState();
        // Store the command system for easy reference in the client code.
        CommandSystem commandSystem = state.commandSystem;
        boolean gameRunning = true;
        System.out.println("");
        System.out.println(" It is Halloween today, and you and your friends Anna, Michael and Rachel decided to celebrate by going to a haunted house themed escape \n room. You are all a bit scared, but excited to try and break out of the room in time. You hand in your phones and take a quick picture \n before you walk into the room and the door gets closed behind you, and you see the timer on the wall starts counting back from 10 minutes.\n You look around the room and see that it is divided into 4 quarters to which you can go to explore the room. \n You and your friends have 10 minutes to exit the room using the items and clues around you. Look around the room and use \n the commands in the help menu (you can always type in help to view menu again) to find the clues that will help you escape. Ultimately,\n the clues should lead you to a code that unlocks the door.");
        commandSystem.printHelp();

        while (gameRunning) {

            String[] input = getCommand();

            if (input.length < 1) {
                System.out.println("Unknown command. Type help to print the command menu.");

            } else if (input[0].equalsIgnoreCase("quit")) {
                gameRunning = false;
                System.out.println("You chose to quit the game. See you again next time!");
                in.close();

                // Command has 1 word - Check if it is a valid verb and execute it.
            } else if (input.length == 1 && commandSystem.hasVerb(input[0])) {
                commandSystem.executeVerb(input[0]);

                // Command has 2 words - should be verb and noun.
            } else if (input.length == 2) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else {
                    // Run command
                    commandSystem.executeVerbNoun(input[0], input[1]);
                }

                // command has 3 words - should be verb noun noun
            } else if (input.length == 3) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else if (!commandSystem.hasNoun(input[2])) {
                    unknownCommand(input[2]);
                } else {
                    // Run command
                    commandSystem.executeVerbNounNoun(input[0], input[1], input[2]);
                }

                // Deal with any possible unknown structure/command
            } else {
                if (input.length > 1) {
                    String userInput = "";

                    for (String s : input)
                        userInput += s + " ";

                    unknownCommand(userInput);

                } else {
                    unknownCommand(input[0]);
                }
            }
        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        in = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.print("What would you like to do? >  ");
        String input = in.nextLine();
        System.out.println();
        return input.toLowerCase().split("\\s+");
    }

    // Used to let the user know that what they typed as a command is not
    // understood.
    public static void unknownCommand(String input) {
        if (Math.random() < .3) // A random chance for a silly response.
            System.out.println("Don't be silly. Everyone knows '" + input + "' is not a command! Type ? for help.");
        else
            System.out.println("I don't understand '" + input + "'. Type ? for help.");
    }

}