package com.homework_21.servlet;

import com.homework_21.jdbc.JDBC_CRUD_Manager;
import com.homework_21.json.JSON_Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("")
public class Servlet_CreateOrConnectDatabase extends HttpServlet {
    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        PrintWriter pw = resp.getWriter();

        final String name = req.getParameter("name");
        final int responseIndex = Integer.parseInt(req.getParameter("userResponse"));
        manager.createOrConnectDatabase(responseIndex, name);
        resp.sendRedirect("/HomeWork_Lesson_21_war_exploded/operation");

        pw.close();
    }
//<table border="1">
//   <tr>
//    <th>Ячейка 1</th>
//    <th>Ячейка 2</th>
//   </tr>
//   <tr>
//    <td>Ячейка 3</td>
//    <td>Ячейка 4</td>
//  </tr>
// </table>
// </body>
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        pw.println("<br>");
        pw.println("<br>");
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th> <h1> <u> <p align=\"center\" style=\"color:#0000ff\"> Welcome to the management system! </u></h1>");
        pw.println("<p align=\"center\"><h2>  To work with the database, please select one of the following options: ");
        pw.println("<p align=\"justify\"> - CONNECT - I already have a database.");
        pw.println("<p align=\"justify\"> - CREATE - I want to create a new database.</h2>");
        pw.println("<p align=\"justify\"> <h3> (The creation of a new database is accompanied by the creation of a new City table) </h3>");
        pw.println("<p align=\"justify\"> <h3> (If a database with the same name already exists, it automatically connects to this database.) </h3> </th>");
        pw.println("</tr>");
        pw.println("</h1>");
        pw.println("</table>");

        pw.println("<table align=\"center\" border=\"20\" >");
        pw.println("<tr>");
        pw.println("<th> <form align=\"center\"  method = \"post\" >");
        pw.println("<br>");
        pw.println("   <select name = \"userResponse\">");
        pw.println("<h2> <option  value= \"2\" selected> CONNECT </option>");
        pw.println("     <option  value= \"1\">          CREATE  </option>");
        pw.println("   </select>");
        pw.println("<p><b> Database name: </b><br>  <input type=\"text\" name = \"name\" size=" + 15 + " required pattern=\"[A-Za-z0-9]*\"> </p>");
        pw.println("                                <input type=\"submit\" value=\"OK\" /> </th>");
        pw.println("</form>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");

        pw.close();
    }
}
