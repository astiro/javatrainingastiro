/*
 * Login servlet will check username and password introduced in login.jsp
 * and opens main.jsp is user is valid and stay in Login.jsp page if not. 
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login servlet will check username and password introduced in login.jsp
 * and opens main.jsp is user is valid and stay in Login.jsp page if not.
 * @author gheor
 */
public class Login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        // test that the button Login was pushed
        if(null != request.getParameter("personas_login_login")){
            // reads values introduced by the user in the 2 fields username and password
            String un = request.getParameter("personas_login_username");
            String pas = request.getParameter("personas_login_password");
            // connects to Database
            String user = "test";
            String password = "test";
            String url = "jdbc:derby://localhost:1527/persoane;create=true";
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            // checks if the account with username and password is in the database
            try{
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
                String query = "SELECT USERS.USERNAME,USERS.PASSWORD,USERS.ROLE \n" +
                                "FROM USERS, USERS_ROLES\n" +
                                "WHERE USERS.ROLE=USERS_ROLES.ROLE AND USERNAME='"+un+"' AND PASSWORD='"+pas+"'";
                resultSet = statement.executeQuery(query);
                boolean hasRows = false;
                if (resultSet!=null) // if resultset has rows ==> user is already a registered one
                {
                    while(resultSet.next()){
                        hasRows = true;
                        String username = resultSet.getString(1);
                        String role = resultSet.getString(3);
                        // set a variable in session to keep it for a user as logged
                        // set another variable to keep the role of actual user for loading the menu for an admin or for a user
                        // if user is a registered one open the main.jsp page after
                        request.getSession().setAttribute("actualUserRole", role); // this will be used to display the menu in main.jsp page
                        request.getSession().setAttribute("actual_user", username); // this will be used if needed in other page
                        request.getSession().setAttribute("validUser", true); // this will be test by all other pages to test if a user is logged and valid
                        // open main.jsp
                        //request.getRequestDispatcher("./main.jsp").forward(request, response);
                        // for unitary tests we are open here an existing jsp that will be replaced with main.jsp we do not have now
                        //request.getRequestDispatcher("./getallpersons.jsp").forward(request, response);
                        request.getRequestDispatcher("./main.jsp").forward(request, response);
                    }
                    if(hasRows == false){
                        request.getSession().setAttribute("validUser", false); // user laready inserted wrong credentials (username, password)
                        // else stay in login.jsp page
                        request.getRequestDispatcher("./index.jsp").forward(request, response);
                    }
                }
                else
                {
                    request.getSession().setAttribute("validUser", false); // user laready inserted wrong credentials (username, password)
                    // else stay in login.jsp page
                    request.getRequestDispatcher("./index.jsp").forward(request, response);
                }
            }                
            catch (ClassNotFoundException | SQLException ex)
            {
                ex.getMessage();
            }
            finally
            {
                if (resultSet != null)
                {
                    try
                    {
                        resultSet.close();
                    }
                    catch (SQLException ex){
                        ex.getMessage();
                    }
                }
                if (statement != null)
                {
                    try
                    {
                        statement.close();
                    }
                    catch (SQLException ex){
                        ex.getMessage();
                    }
                }	
                if (connection != null)
                {
                    try
                    {
                        connection.close();
                    }
                    catch (SQLException ex){
                        ex.getMessage();
                    }
                }
            }
        }// if Login button was pushed
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
        return "Login servlet.";
    }// </editor-fold>
}
