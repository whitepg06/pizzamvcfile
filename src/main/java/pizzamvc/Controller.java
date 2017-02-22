package pizzamvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Controller class handles all requests coming in from the user's browser.
 *
 * @author John Phillips
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("In controller");

        // set default url
        String url = "/home.html";

        // give servlet access to the path of a file on the server
        ServletContext context = getServletContext();
        String path = context.getRealPath("/WEB-INF/PizzaOrderList.txt");

        // get current action
        String action = request.getParameter("userAction");
        System.out.println("userAction=" + action);
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            System.out.println("controller:home");
            url = "/home.html";

        } else if (action.equalsIgnoreCase("order")) {
            System.out.println("controller:createRecord");

            // get parameters passed in from the request
            String email = request.getParameter("email");
            String size = request.getParameter("size");
            String[] toppingArray = request.getParameterValues("toppings");

            // convert toppingArray to a comma separated string
            String toppings = "none";
            if (toppingArray != null && toppingArray.length > 0) {
                // we have at least 1 topping; reset toppings to ""
                toppings = "";
                if (toppingArray.length == 1) {
                    // single topping with no commas
                    toppings = toppingArray[0];
                } else if (toppingArray.length > 1) {
                    // separate multiple toppings with commas
                    for (int i = 0; i < toppingArray.length - 1; i++) {
                        toppings += toppingArray[i] + ", ";
                    }
                    // the last topping does not have a comma after
                    toppings += toppingArray[toppingArray.length - 1];
                }
            }

            // store data in an PizzaOrder object
            // the PizzaOrder class is part of the MVC model 
            // as is the DAO (data access object)
            PizzaOrder myOrder = new PizzaOrder(email, size, toppings);
            System.out.println("Controller:order:pizza=" + myOrder);

            // validate the parameters
            if (email == null || size == null || email.isEmpty() || size.isEmpty()) {
                url = "/orderError.jsp";
                System.out.println("Controller:pizza order validation error");
            } else {
                // order looks ok; have the DAO add it to the order file
                DAOPizzaOrder.addOrder(myOrder, path);
                url = "/orderSuccess.jsp";
                request.setAttribute("myOrder", myOrder);
                System.out.println("Controller:placing order");
            }

        } else if (action.equalsIgnoreCase("report")) {
            // fetch the order records here and then pass them to showRecords.jsp
            System.out.println("controller:report");
            url = "/showRecords.jsp";
            request.setAttribute("orderList", DAOPizzaOrder.getOrderList(path));
            System.out.println("controller:retrieving report");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
