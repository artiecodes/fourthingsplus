package dat.backend.model.entities;

import java.sql.Timestamp;

public class Item {
    private int itemId;
    private String name;
    private boolean done;
    private String username;
    private Timestamp created;

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Item(int itemId, String name, boolean done, String username, Timestamp created) {
        this.itemId = itemId;
        this.name = name;
        this.done = done;
        this.username = username;
        this.created = created;


    }
}
