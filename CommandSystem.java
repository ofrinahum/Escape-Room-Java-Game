
import java.util.*;
import javax.lang.model.util.ElementScanner14;

public class CommandSystem {
    private GameState state;
    Scanner in = new Scanner(System.in);

    private List<String> verbs = new ArrayList<String>();
    private List<String> verbDescription = new ArrayList<String>();
    private List<String> nouns = new ArrayList<String>();

    public CommandSystem(GameState state) {
        this.state = state;
        addVerb("walk", "Walk to a certain location in the room.");
        addVerb("look", "Look at a clue or an object more closely");
        addVerb("ask", "Ask one of your friends a question.");
        addVerb("use key", "Use one of your clues, objects, or codes.");
        addVerb("use code door", "Use one of your clues, objects, or codes.");
        addVerb("quit", "Quit the game.");
        addVerb("help", "Open the help menu again");
    }
    
    public void executeVerb(String verb) {
        switch (verb) {
            case "walk":
            {
             System.out.println("What part of the room would you want to walk to? enter q1, q2, q3, and q4 ");
             String walkTo = in.next();
             walkTo(walkTo);
             break;
            }
            case "look":
                {
                System.out.println("You look around.");
                System.out.println(state.currentLocation.description);
                break;
                }

            case "ask":
            {
                System.out.println("Who would you like to ask for help or a question? enter Anna, Michael, or Rachel");
                String askFriend = in.next();
            }
            case "help":
            {
                this.printHelp();
                break;
            }
            case "quit":
            {
                System.out.println("You chose to quit the game. See you next time!");
                System.exit(0);
            }
        }
    }

    public void executeVerbNoun(String verb, String noun) {
        String resultString = "";
        switch (verb) {
            case "walk":
            {
                resultString = walkTo(noun);
                break;
            }
            case "look":
            {
                resultString = lookAt(noun);
                break;
            }
            case "ask":
            {
                resultString = askFriend(state.currentItem.name, noun);
                break;
            }
            case "use":
            {
                if(noun.equalsIgnoreCase("remote"))
                {
                    resultString = state.remote.description;
                }
                else
                {
                   resultString = "You cannot use this item here or just yet, try again later.";
                }
            }
        }
        System.out.println(resultString);
    }

    public String walkTo(String location)
    {
        String descript = "";
        switch(location)
        {
            case "q1":
            {
                state.currentLocation.name = state.q1.name;
                descript = state.q1.description;
                break;
            }
            case "q2":
            {
                state.currentLocation.name = state.q2.name;
                descript = state.q2.description;
                break;
            }
            case "q3":
            {
                state.currentLocation.name = state.q3.name;
                descript = state.q3.description;
                break;
            }
            case "q4":
            {
                state.currentLocation.name = state.q4.name;
                descript = state.q4.description;
                break;
            }
            case "exit":
            {
                state.currentLocation.name = state.exit.name;
                descript = state.exit.description;
                break;
            }
            default:
            {
                System.out.println("I do not recognize this location, please try again");
                break;
            }
        }
        return descript;
    }

    public String askFriend(String item, String name)
    {
        String phrase = "";
        if (item.equalsIgnoreCase("table") && name.equalsIgnoreCase("michael"))
        {
            phrase = state.michael.phrase;
        }
       else if (item.equalsIgnoreCase("picture") && name.equalsIgnoreCase("anna"))
        {
            phrase = state.anna.phrase;
        }
        else if (item.equalsIgnoreCase("door") && name.equalsIgnoreCase("rachel"))
        {
            phrase = state.rachel.phrase;
        }
        else
        {
            phrase = "You cannot ask for this friend's help here! Consider reaching out to another friend";
        }
        return phrase;
    }

    public String lookAt(String noun) {
        String resultString = "";
        switch(noun)
        {
            case "closet":
            {
                state.currentItem.name = state.closet.name;
                resultString = state.closet.description;
                break;
            }
            case "table":
            {
                state.currentItem.name = state.table.name;
                resultString = state.table.description;
                break;
            }
            case "tv":
            {
                state.currentItem.name = state.tv.name;
                resultString = state.tv.description;
                break;
            }
            case "safe":
            {
                state.currentItem.name = state.safe.name;
                resultString = state.safe.description;
                break;
            }
            case "bookcase":
            {
                state.currentItem.name = state.bookcase.name;
                resultString = state.bookcase.description;
                break;
            }
        }
        return resultString;
    }

    public void executeVerbNounNoun(String verb, String item1, String item2)
    {
        String res = "";
        switch(verb)
        {
            case "use":
        }

    }
           
    public void printHelp() {
        int DISPLAY_WIDTH = GameState.DISPLAY_WIDTH;
        String s1 = "";
        while (s1.length() < DISPLAY_WIDTH)
            s1 += "-";

        String s2 = "";
        while (s2.length() < DISPLAY_WIDTH) {
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) {
                s2 += " Commands ";
            } else {
                s2 += " ";
            }
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
        for (String v : verbs) {
            System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
        }
    }

    public String formatMenuString(String longString) {
        String result = "";
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            charLength += next.length();
            result += next + " ";
            if (charLength >= (GameState.DISPLAY_WIDTH - 30)) {
                result += "\n          ";
                charLength = 0;
            }
        }
        chop.close();
        return result + "\n\n";
    }


    public void gameOutput(String longstring) {
        gameOutput(longstring, true, true);
    }

    public void gameOutput(String longstring, boolean addBrackets, boolean formatWidth) {
        if (addBrackets) {
            longstring = addNounBrackets(longstring);
        }
        if (formatWidth) {
            longstring = formatWidth(longstring);
        }

        System.out.println(longstring);
    }

    // formats a string to DISPLAY_WIDTH character width.
    // Used when getting descriptions from items/locations and printing them to the
    // screen.
    // You can also add [nl] for a newline in a string in a description etc.
    public String formatWidth(String longString) {

        Scanner chop = new Scanner(longString);
        String result = "";
        int charLength = 0;
        boolean addSpace = true;

        while (chop.hasNext()) {

            // Get our next word in the string.
            String next = chop.next();

            // Add the legnth to our charLength.
            charLength += next.length() + 1;

            // Find and replace any special newline characters [nl] with \n.
            if (next.contains("[nl]")) {
                // Find the index after our [nl] characters.
                int secondHalf = next.indexOf("[nl]") + 4;

                // Set charLength to the number of characters after the [nl],
                // because that will be the beginnig of a new line.
                if (secondHalf < next.length()) {
                    charLength = secondHalf;
                } else {
                    charLength = 0;
                    addSpace = false; // Do not add space after if this ended with a newline character.
                }

                // Now actually replace the [nl] with the newline character
                next = next.replace("[nl]", "\n");

            }

            // Add the word to the result.
            result += next;

            // Only add a space if our special case did not happen.
            if (addSpace)
                result += " ";

            // Normally we add a space after a word, prepare for that.
            addSpace = true;

            if (charLength >= GameState.DISPLAY_WIDTH) {
                result += "\n";
                charLength = 0;
            }
        }
        chop.close();
        return result;
    }

    /**
     * Adds brackets around whole words that are included in the `nouns` list,
     * ignoring case, and also deals with any that have punctuation after them.
     *
     * @param longString the string to check for nouns
     * @return the modified string with brackets around the nouns
     */
    public String addNounBrackets(String longString) {
        String[] words = longString.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("\\p{Punct}+$", "");
            String punct = words[i].substring(word.length());
            for (String noun : nouns) {
                if (word.equalsIgnoreCase(noun)) {
                    words[i] = "[" + word + "]" + punct;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    // Adds a noun to the noun list
    // lets the command system know this is something you can interact with.
    public void addNoun(String string) {
        if (!nouns.contains(string.toLowerCase()))
            nouns.add(string.toLowerCase());
    }

    // Adds a verb to the verb list and its description to the parallel description
    // list.
    // This method should be used to register new commands with the command system.
    public void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description.toLowerCase());
    }

    // Allows the client code to check to see if a verb is in the game.
    public boolean hasVerb(String string) {
        return verbs.contains(string);
    }

    // Allows the client code to check to see if a noun is in the game.
    public boolean hasNoun(String string) {
        return nouns.contains(string);
    }

}
