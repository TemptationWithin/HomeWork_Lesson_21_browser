package com.homework_21.servlet.city_table.update;

import com.homework_21.jdbc.JDBC_CRUD_Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update")
public class Servlet_Update_Manager extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        final String updateIndex =request.getParameter("userResponse");
        response.sendRedirect("/HomeWork_Lesson_21_war_exploded/update/" + updateIndex);
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 9; i++) {
            pw.println("<br>");
        }
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th><h2 align=\"center\"> <u> City update form </u></h2>");
        pw.println("<h3 align=\"center\"> Choose update mode: </h3>");
        pw.println("<form align=\"center\"  method = \"post\" >");
        pw.println("   <select name = \"userResponse\">");
        pw.println("<h2> <option  value= \"1\" selected> SET NAME BY OLD NAME             </option>");
        pw.println("     <option  value= \"2\">          SET COUNTRY_CODE BY CITY NAME    </option>");
        pw.println("     <option  value= \"3\">          SET DISTRICT BY CITY NAME        </option>");
        pw.println("     <option  value= \"4\">          SET POPULATION BY CITY NAME </option>");
        pw.println("   </select>");
        pw.println("                                <input type=\"submit\" value=\"OK\" />");
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
