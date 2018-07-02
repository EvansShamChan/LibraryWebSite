package com.softserve.edu.library.servlet;

//import com.softserve.edu.library.dao.SignInDao;
import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.entity.User;
import com.softserve.edu.library.enumForProject.EnumForSqlMethod;
import com.softserve.edu.library.enumForProject.EnumForUserEntityFields;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<EnumForUserEntityFields, String> map = new HashMap<>();

//        map.put(EnumForUserEntityFields.ID, request.getParameter("id"));
        map.put(EnumForUserEntityFields.USERNAME , request.getParameter("username"));
        map.put(EnumForUserEntityFields.PASSWORD, request.getParameter("password"));
//        map.put(EnumForUserEntityFields.FIRSTNAME, request.getParameter("firstname"));
//        map.put(EnumForUserEntityFields.LASTNAME, request.getParameter("lastname"));
//        map.put(EnumForUserEntityFields.DATEOFBIRTH, request.getParameter("dateofbirth"));
//        map.put(EnumForUserEntityFields.REGISTRATIONDATE, request.getParameter("dateofregistr"));
        System.out.println(map.size());




        UserDao userDao = new UserDao();
        User user = userDao.findUserInDBNew(EnumForSqlMethod.DELETE, map);

//        if (!(user.getUsername() == null && user.getPassword() == null)) {
//            if (user.getUsername().equals(map.get(EnumForUserEntityFields.USERNAME)) &&
//                    user.getPassword().equals(map.get(EnumForUserEntityFields.PASSWORD)) &&
//                    user.getId().equals(Long.parseLong(map.get(EnumForUserEntityFields.ID)))) {
////              URL for page after SignIN
//            }
//        } else {
//            request.setAttribute("errorStyle", "display: block");
//            request.getRequestDispatcher("pages/sign-in/SignIn.jsp").forward(request, response);
//        }
    }
}
