package com.homework_21.servlet.city_table.read;

import com.homework_21.jdbc.JDBC_CRUD_Manager;
import com.homework_21.json.JSON_City_table_Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/read/1")
public class Servlet_Read_1_FullTable extends HttpServlet {
    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();
    JSON_City_table_Parser parser = new JSON_City_table_Parser();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        ArrayList<String> cityList = manager.readFullTable();

        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        pw.println("<table align=\"center\" border=\"1\" >");
        pw.println("<th> ID </th>");
        pw.println("<th> Name </th>");
        pw.println("<th> District </th>");
        pw.println("<th> Country code </th>");
        pw.println("<th> Population </th>");
        pw.println("</tr>");
        for (String s: cityList) {
            pw.println("<tr>" +
                            "<td align=\"center\">" + parser.parseID(s)          + "</td>" +
                            "<td align=\"center\">" + parser.parseName(s)        + "</td>" +
                            "<td align=\"center\">" + parser.parseDistrict(s)    + "</td>" +
                            "<td align=\"center\">" + parser.parseCountryCode(s) + "</td>" +
                            "<td align=\"center\">" + parser.parsePopulation(s)  + "</td>" +
                      "</tr>");
        }
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/read\">");
        pw.println("<button>Cancel</button>");
        pw.println("</form>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
}
