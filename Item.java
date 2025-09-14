import java.util.ArrayList;

public class Item {
    String name;
    String description;
    ArrayList<String> status = new ArrayList<String>();

    public Item() {
    };

    public Item(String n, String d) {
        name = n;
        description = d;
    }

    public String getDesc() {
        return description;
    }
}
