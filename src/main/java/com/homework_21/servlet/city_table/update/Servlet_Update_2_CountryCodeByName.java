package com.homework_21.servlet.city_table.update;

import com.homework_21.jdbc.JDBC_CRUD_Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update/2")
public class Servlet_Update_2_CountryCodeByName extends HttpServlet {

    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        final String name = request.getParameter("name");
        final String newCountryCode = request.getParameter("newCountryCode");
        if (manager.isNameContainsInDatabase(name)){
            manager.updateCountryCodeByCityName(name, newCountryCode);
            response.sendRedirect("/HomeWork_Lesson_21_war_exploded/update/2");
        } else {
            pw.println("<html>");
            pw.println("<body style=\"color:Black; background-color:DarkGray\">");
            for (int i = 0; i < 10; i++) {
                pw.println("<br>");
            }
            pw.println("<table align=\"center\" border=\"35\" >");
            pw.println("<tr>");
            pw.println("<th><h1 align=\"center\" style=\"color:Maroon\"> There is no city with the same name in the database. Please try again </h1>");
            pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/update/2\">");
            pw.println("<button>Try again</button>");
            pw.println("</form>");
            pw.println("</th>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");
        }
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 7; i++) {
            pw.println("<br>");
        }
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th><h2 align=\"center\"> <u> Update form </u></h2>");
        pw.println("<form align=\"center\"  method = \"post\">");
        pw.println("<p><b> city name: </b><br>  <input type=\"text\" name = \"name\" size=" + 20 + "required pattern=\"[A-Za-z]*\">");
        pw.println("<p><b> new country code: </b><br>  <input type=\"text\" name = \"newCountryCode\" size=" + 20 + "required pattern=\"[A-Z]{3}\">");
        pw.println("<p><b>                                <input type=\"submit\" value=\"Update\" />");
        pw.println("</form>");

        pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/update\">");
        pw.println("<button>Cancel</button>");
        pw.println("</form>");
        pw.println("</th>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</body>");
        pw.println("<html>");
        pw.close();
    }
}
