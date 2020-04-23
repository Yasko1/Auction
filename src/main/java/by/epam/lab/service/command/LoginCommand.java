package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.RoleEnum;
import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.UserService;

import java.util.Optional;

/**
 * Designed to perform a login process.
 */
public class LoginCommand implements Command {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String ERROR_LOGIN_MESSAGE = "errorLoginMessage";
    private static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed!";
    private static final String ACCOUNT_HAS_BEEN_BANNED_MESSAGE = "Your account has been banned!";
    private static final String COMMAND_MAIN = "controller?command=main";

    /**
     * Process the request, login and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserService userService = new UserService();
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Optional<User> user = userService.login(login, password);

        if (!user.isPresent()) {
            request.setAttribute(ERROR_LOGIN_MESSAGE, AUTHENTICATION_FAILED_MESSAGE);
            return new CommandResult(LOGIN_PAGE, false);
        }

        User userEntity = user.get();
        if (userEntity.isBanned()) {
            request.setAttribute(ERROR_LOGIN_MESSAGE, ACCOUNT_HAS_BEEN_BANNED_MESSAGE);
            return new CommandResult(LOGIN_PAGE, false);
        }

        long id = userEntity.getId();
        RoleEnum role = userEntity.getRole();

        HttpSession session = request.getSession();
        session.setAttribute(ID, id);
        session.setAttribute(ROLE, role);
        
        return new CommandResult(COMMAND_MAIN, true);
    }

}
