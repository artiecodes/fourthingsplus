package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemMapper {
    static List<Item> getAllItems(ConnectionPool connectionPool) {
        String sql = "SELECT * from item";

        List<Item> itemList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("item_id");
                    String name = resultSet.getString("name");
                    boolean done = resultSet.getBoolean("done");
                    String username = resultSet.getString("username");
                    Timestamp created = resultSet.getTimestamp("created");

                    itemList.add(new Item(itemId,name,done,username,created));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public static void addItem(String new_item, String username, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Item item;
        String sql = "insert into item (name, username) values (?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, new_item);
                ps.setString(2, username);
                int rowsAffected = ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert item into database");
        }
    }
}
