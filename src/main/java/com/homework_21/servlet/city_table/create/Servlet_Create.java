package com.homework_21.servlet.city_table.create;

import com.homework_21.jdbc.JDBC_CRUD_Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create")
public class Servlet_Create extends HttpServlet {
    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter pw = response.getWriter();
            final String name = request.getParameter("name");
            final String countryCode = request.getParameter("countryCode");
            final String district = request.getParameter("district");
            final int population = Integer.parseInt(request.getParameter("population"));
            //pw.println("name = " + name + ". CountryCode = " + countryCode + ". District = " + district + ". Population = " + population);
            manager.createCity(name, countryCode, district, population);
            response.sendRedirect("/HomeWork_Lesson_21_war_exploded/create");
            pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 5; i++) {
            pw.println("<br>");
        }
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th> <h2 align=\"center\"> <u> City create form </u></h2>");
        pw.println("<form align=\"center\"  method = \"post\">");
        pw.println("<p><b> name: </b><br>  <input type=\"text\" name = \"name\" size=" + 20 + "required pattern=\"[A-Za-z]*\">  </p>");
        pw.println("<p><b> country code(three capital english letters): </b><br>  <input type=\"text\" name = \"countryCode\" size=" + 20 + "required pattern=\"[A-Z]{3}\"> </p>");
        pw.println("<p><b> district: </b><br>  <input type=\"text\" name = \"district\" size=" + 20 + "required pattern=\"[A-Za-z]*\"> </p>");
        pw.println("<p><b> population: </b><br>  <input type=\"text\" name = \"population\" size=" + 20 + "required pattern=\"[0-9]*\"> </p>");
        pw.println("                                <input type=\"submit\" value=\"Create\" />");
        pw.println("                                <input type=\"reset\" value=\"Clear\"></p>");
        pw.println("</form>");

        pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/operation\">");
        pw.println("<button>Cancel</button>");
        pw.println("</form>");
        pw.println("</th>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
}
