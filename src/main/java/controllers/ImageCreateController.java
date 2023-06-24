package controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.AttributeConst;


@WebServlet("/ImageCreateController")
@MultipartConfig

public class ImageCreateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ImageCreateController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/images/_form.jsp");

        rd.forward(request, response);

   }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        String imgAdd = request.getParameter(AttributeConst.IMG_ADDRESS.getValue());

        Part part = request.getPart(imgAdd);

        String fileName = part.getSubmittedFileName();

        String path = getServletContext().getRealPath("/upload");
        System.out.println(path);

        part.write(path + File.separator + fileName);


        doGet(request, response);
    }

}
