
public class GameState {
    Location currentLocation;
    Location q1;
    Location q2;
    Location q3;
    Location q4;
    Location exit;

    Item currentItem;
    Item closet;
    Item table;
    Item tv;
    Item remote;
    Item picture;
    Item safe;
    Item bookcase;
    Item door;
    Item code;

    Entities anna;
    Entities michael;
    Entities rachel;

    CommandSystem commandSystem;

    public static int DISPLAY_WIDTH = 100;

    public GameState() {
        commandSystem = new CommandSystem(this);

        currentLocation = new Location();
        currentLocation.name = "entrance";
        currentLocation.description = "You are at the entrance, you could choose to go to q1, q2, q3, or q4 of the room.";
        commandSystem.addNoun("entrance");
        
        // these are locations
        q1 = new Location();
        q1.name = "q1";
        q1.description = "You chose to walk into q1 of the room. You can see an old, wooden table.";
        commandSystem.addNoun("q1");
        
        q2 = new Location();
        q2.name = "q2";
        q2.description = "You chose to walk into q2 of the room. You can see a half-opened wooden closet that looks a bit old.";
        commandSystem.addNoun("q2");

        q3 = new Location();
        q3.name = "q3";
        q3.description = "You chose to walk into q3 of the room. You can see an old TV and a small safe.";
        commandSystem.addNoun("q3");

        q4 = new Location();
        q4.name = "q4";
        q4.description = "You chose to walk into q4 of the room. You can see an old wooden bookcase.";
        commandSystem.addNoun("q4");

        exit = new Location();
        exit.name = "exit";
        exit.description = "You chose to walk towards the exit, you can see the door from which you can exit the room.";
        commandSystem.addNoun("exit");
    

        // these are items
        currentItem = new Item();
        currentItem.name = "current";
        currentItem.description = "description of item";
        commandSystem.addNoun(currentItem.name);

        closet = new Item();
        closet.name = "closet";
        closet.description = "You looked in the closet and saw a few pictures scattered within it. You cannot quite see what is in the pictures, but you can try to take a closer look.";
        commandSystem.addNoun(closet.name);

        picture = new Item();
        picture.name = "picture";
        picture.description = "You took a closer look at the pictures and you can see that they were all taken in 1927. flipping through the pictures, one of them caught your eye. You cannot really tell who is in the picture, but they seem quite familiar, like you have seen their face before. You can ask for help from a friend here!";
        commandSystem.addNoun(picture.name);

        table = new Item();
        table.name = "table";
        table.description = "You looked under the table and found a crumpled piece of paper with a drawing of lightning and a sketch of the letter H. You can ask one of your friends for help here!";
        commandSystem.addNoun(table.name);

        tv = new Item();
        tv.name = "tv";
        tv.description = "You looked at the TV and saw that it is off.";
        commandSystem.addNoun(tv.name);

        remote = new Item();
        remote.name = "remote";
        remote.description = "You used the remote and the tv turned on.";
        commandSystem.addNoun(remote.name);

        safe = new Item();
        safe.name = "safe";
        safe.description = "You looked at the safe and saw a keypad that can be used to open it.";
        commandSystem.addNoun(safe.name);

        bookcase = new Item();
        bookcase.name = "bookcase";
        bookcase.description = " Looking at the bookcase, you can see many different books of many different sizes and genres placed in alphabetical order.";
        commandSystem.addNoun(bookcase.name);

        door = new Item();
        door.name = "door";
        door.description = "You looked at the door, and see the keypad to enter the code.";
        commandSystem.addNoun(door.name);

        code = new Item();
        code.name = "name";
        code.description = "Enter the code you would like to use: ";
        commandSystem.addNoun(code.name);


        // these are entities (friends)
        anna = new Entities();
        anna.name = "anna";
        anna.phrase = "The man in the picture is Philo Fransworth, we learned about him in History class together! He invented the TV!";
        commandSystem.addNoun(anna.name);

        michael = new Entities();
        michael.name = "michael";
        michael.phrase = "Think of a super famous book and TV series in which the main character has 2 great best friends, a special connection to lightnings and his name starts with the letter H";
        commandSystem.addNoun(michael.name);

        rachel= new Entities();
        rachel.name = "rachel";
        rachel.phrase = "Maybe we need to rearrange the numbers from smallest to largest or largest to smallest";
        commandSystem.addNoun(anna.name);
    }
}
