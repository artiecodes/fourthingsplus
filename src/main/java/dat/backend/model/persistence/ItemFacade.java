package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ItemFacade {
    public static List<Item> getAllItems(ConnectionPool connectionPool) {
        return ItemMapper.getAllItems(connectionPool);
    }

    public static void additem(String new_item, String username, ConnectionPool connectionPool) throws DatabaseException {
        ItemMapper.addItem(new_item, username, connectionPool);
    }
}
