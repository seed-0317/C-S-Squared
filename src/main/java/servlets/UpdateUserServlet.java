package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nof191 on 4/25/17.
 */
@WebServlet(name = "/updateUser")
public class UpdateUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getParameters("firstName")

//        User user = new User(all my new data)

//        businessLogic.updateUser(user)

        //go somewhere

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ASSERT: username of logged in user is in the session under key "user"

        //request.getSession.getAttribute("user")

        //User userInfo = businessLogic.getUserInfo(username)

        request.setAttribute("userInfo", userInfo);

        //getReqDispatcher("updateUser.html").forward

    }
}
