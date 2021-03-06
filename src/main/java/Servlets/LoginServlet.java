package Servlets;

import Classes.BadLoginException;
import Classes.EmailValidator;
import Classes.Product;
import Classes.User;
import dao.Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlets.LoginServlet")
public class LoginServlet extends HttpServlet {
    static ArrayList<Product> list = new ArrayList<>();
    static Map<String, Integer> cartlist = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter pw = response.getWriter();

        if (!EmailValidator.validate(email)) {
            pw.println("Regex checking...");
            pw.println("Email must contain (@) and (.) signs! Try again");
        } else {
            if (email.equals("stupidAdmin@gmail.com") ||
                    email.equals("stupidAdmin@mail.ru") ||
                    email.equals("stupidAdmin@astanait.edu.kz")) {
                try {
                    throw new BadLoginException("Don't say bad words to admin, please((");
                } catch (BadLoginException e) {
                    pw.println("This is a custom exception: " + e.getClass());
                    pw.println(e.getMessage());
                }
            } else {
                Dao dao = new Dao();

                if (dao.checkLogin(email, password)) {
                    Cookie websiteVisitCounter = new Cookie("counter", "0");
                    websiteVisitCounter.setMaxAge(3600);
                    response.addCookie(websiteVisitCounter);

                    User user = new User();

                    HttpSession session = request.getSession();
                    session.setAttribute("cartlist", list);
                    session.setAttribute("cartlist", cartlist);

                    user.setEmail(email);
                    user.setPassword(password);

                    user = dao.setUserValues(user);
                    setUserSessions(user, session);

                    request.setAttribute("user", user);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/id-check?id=" + getServletContext().getInitParameter("correctId"));
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    private void setUserSessions(User user, HttpSession session) {
        session.setAttribute("id", user.getId());
        session.setAttribute("first_name", user.getFirstName());
        session.setAttribute("last_name", user.getLastName());
        session.setAttribute("job", user.getJob());
        session.setAttribute("email", user.getEmail());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Browser thinks you're on " + getServletName() + " page");
    }
}
