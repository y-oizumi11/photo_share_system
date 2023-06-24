package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.AttributeConst;

/**
 * Servlet implementation class ImageCreateController
 */
@WebServlet("/ImageCreateController")
@MultipartConfig

public class ImageCreateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageCreateController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String imgAdd = request.getParameter(AttributeConst.IMG_ADDRESS.getValue());

        Part part = request.getPart(imgAdd);
        String filePath = "/upload/" + part.getSubmittedFileName();
        part.write(getServletContext().getRealPath(filePath));
        // System.out.println(getServletContext().getRealPath(filePath));

        doGet(request, response);
    }

}
