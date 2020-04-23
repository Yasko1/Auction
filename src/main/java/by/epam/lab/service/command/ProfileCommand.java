package by.epam.lab.service.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.RoleEnum;
import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.UserService;

/**
 * Designed to display profile page.
 */
public class ProfileCommand implements Command {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ADMIN_PROFILE_PAGE = "/WEB-INF/adminProfile.jsp";
    private static final String USER_PROFILE_PAGE = "/WEB-INF/userProfile.jsp";

    /**
     * Generates a result of request processing in the form of {@link ccommand.CommandResult} object
     * with profile  page.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(ID);

        UserService userService = new UserService();
        Optional<User> user = userService.findById(id);
        user.ifPresent(aUser -> request.setAttribute(USER, aUser));

        RoleEnum role = (RoleEnum) session.getAttribute(ROLE);
        return RoleEnum.ADMIN.equals(role) ?
                new CommandResult(ADMIN_PROFILE_PAGE, false) :
                new CommandResult(USER_PROFILE_PAGE, false);
    }
}
