package by.epam.lab.service.command;

import java.math.BigDecimal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotStatusEnum;
import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotService;
import by.epam.lab.service.UserService;

/**
 * Designed to complete pay a lot process.
 */
public class PayLotCommand implements Command {

    private static final String ID = "id";
    private static final String LOT_ID = "lotId";
    private static final String PAYMENT_MESSAGE = "paymentMessage";
    private static final String ERROR_PAYMENT_MESSAGE = "Not enough money to pay!";
    private static final String CONTROLLER_COMMAND_LOTS = "controller?command=userLots";
    private static final String USER_LOTS_PAGE  = "/WEB-INF/userLots.jsp";
    private static final int EQUAL_OR_GREATER_VALUE = 0;

    /**
     * Process the request, pay a lot and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String lotIdString = request.getParameter(LOT_ID);
        long lotId = Long.valueOf(lotIdString);

        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(lotId);

        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(ID);

        UserService userService = new UserService();
        Optional<User> user = userService.findById(userId);

        CommandResult commandResult = new CommandResult();
        if (lot.isPresent() && user.isPresent()) {
            Lot aLot = lot.get();
            User aUser = user.get();

            BigDecimal paymentAmount = aLot.getPrice();
            BigDecimal userBalance = aUser.getBalance();

            if (userBalance.compareTo(paymentAmount) >= EQUAL_OR_GREATER_VALUE) {
                BigDecimal residualBalance = userBalance.subtract(paymentAmount);
                aUser.setBalance(residualBalance);
                aLot.setStatus(LotStatusEnum.PAID);

                lotService.save(aLot);
                userService.save(aUser);

                commandResult.setPage(CONTROLLER_COMMAND_LOTS);
                commandResult.setRedirect(true);
            } else {
                commandResult.setPage(USER_LOTS_PAGE);
                request.setAttribute(PAYMENT_MESSAGE, ERROR_PAYMENT_MESSAGE);
            }
        }

        return commandResult;
    }
}
