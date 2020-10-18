package Servlets;

import Classes.Product;
import dao.Dao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static Servlets.LoginServlet.list;
import static Servlets.LoginServlet.cartlist;

@WebServlet(name = "Servlets.ProductsControllerServlet")
public class ProductsControllerServlet extends HttpServlet {
    PrintWriter out;
    HttpSession session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        session = request.getSession();
        if (session.getAttribute("list") == null) {
            Dao dao = new Dao();
            list = dao.getAllProducts();
            request.setAttribute("list", list);
        } else {
            redirect(request, response, page);
        }

        if (page.equals("furniture") || page.equals("sport") || page.equals("gadgets")) {
            redirect(request, response, page);
        }

        if (page.equals("showcart")) {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (page.equals("price-sort")) {
            String price = request.getParameter("sort");
            String action = request.getParameter("action");
            Product p = new Product();
            if (price.equals("low-to-high")) {
                list = p.lowToHigh(list);
            } else {
                list = p.highToLow(list);
            }
            request.removeAttribute("list");
            session.setAttribute("list", list);
            redirect(request, response, action);
        }

        if (page.equals("remove")) {
            String id = request.getParameter("id");
            Product p = new Product();
            cartlist = p.removeFromCartlist(cartlist, id);

            session = request.getSession();
            session.setAttribute("cartlist", cartlist);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        String page = request.getParameter("page");

        if (page.equals("addToCart")) {
            String action = request.getParameter("action"); //category
            String id = request.getParameter("id");
            int amount = Integer.parseInt(request.getParameter("amount"));
            Product p = new Product();
            boolean check = p.checkId(cartlist, id);

            if (check) {
                out.println("Product is already added to Cart");
                out.println("<center><a href='productsController?page=" + action + "'>Return</a></center>");
            } else {
                cartlist.put(id, amount);
                if (session.getAttribute("list") == null) {
                    Dao dao = new Dao();
                    list = dao.getAllProducts();
                    request.setAttribute("list", list);
                }
                redirect(request, response, action);
            }
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String category) throws ServletException, IOException {
        if (category.equals("furniture")) {
            request.getRequestDispatcher("furniture.jsp").forward(request, response);
        }
        if (category.equals("sport")) {
            request.getRequestDispatcher("sport.jsp").forward(request, response);
        }
        if (category.equals("gadgets")) {
            request.getRequestDispatcher("gadgets.jsp").forward(request, response);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Browser thinks you're on " + getServletName() + " page");
    }

}
