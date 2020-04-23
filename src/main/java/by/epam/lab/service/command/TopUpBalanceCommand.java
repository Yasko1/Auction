package by.epam.lab.service.command;

import java.math.BigDecimal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.UserService;

/**
 * Designed to perform recharge operations.
 */
public class TopUpBalanceCommand implements Command {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String TOP_UP_BALANCE = "topUpBalance";
    private static final String COMMAND_PROFILE = "controller?command=profile";

    /**
     * Process the request, top up balance and generates a result of processing in the form of
     * {@link command.CommandResult} object.
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
        if (user.isPresent()) {
            String topUpBalanceString = request.getParameter(TOP_UP_BALANCE);
            BigDecimal topUpBalance = new BigDecimal(topUpBalanceString);

            User aUser = user.get();
            BigDecimal balance = aUser.getBalance();
            BigDecimal newBalance = balance.add(topUpBalance);

            aUser.setBalance(newBalance);

            userService.save(aUser);
            request.setAttribute(USER, aUser);
        }

        return new CommandResult(COMMAND_PROFILE, true);
    }
}
