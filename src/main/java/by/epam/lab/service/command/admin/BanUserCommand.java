package by.epam.lab.service.command.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.UserService;
import by.epam.lab.service.command.Command;
import by.epam.lab.service.command.CommandResult;

/**
 * Designed to perform a user lockout process.
 */
public class BanUserCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String COMMAND_USER_MANAGEMENT = "controller?command=userManagement";

    /**
     * Process the request, locks the user and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		 String userIdString = request.getParameter(USER_ID);
	        long userId = Long.valueOf(userIdString);

	        UserService userService = new UserService();
	        Optional<User> user = userService.findById(userId);

	        if (user.isPresent()) {
	            User userItem = user.get();
	            userItem.setBanned(true);
	            userService.save(userItem);
	        }

	        return new CommandResult(COMMAND_USER_MANAGEMENT, true);
	}
}
