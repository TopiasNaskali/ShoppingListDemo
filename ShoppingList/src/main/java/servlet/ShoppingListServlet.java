package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/shoppinglist")
public class ShoppingListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// DAO OLION LUONTI, JOTTA PYSTYTÄÄN KUTSUMAAN FUNKTIOTA GETALLITEMS
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		System.out.println("Shopping list contents:");

		List<ShoppingListItem> items = dao.getAllItems();

		System.out.println("Itemit " + items);

		// lähetetään aika merkkijono JSP-sivulle attribuuttina
		req.setAttribute("shoppingitems", items);

		// lähetä request edelleen index.jsp sivulle
		req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
	}
}
