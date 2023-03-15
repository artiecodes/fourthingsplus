package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddItem", value = "/additem")
public class AddItem extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hent alle værdier som skala gemmes i DB
        String newItem = request.getParameter("newitem");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        try {
            // Gem data
            ItemFacade.additem(newItem, user.getUsername(), connectionPool);
            // Hent alle items fra DB igen
            List<Item> itemList = ItemFacade.getAllItems(connectionPool);
            request.setAttribute("itemList", itemList);
            // Forward tilbage til welcome listen
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}
