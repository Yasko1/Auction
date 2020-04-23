package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotStatusEnum;
import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotService;
import by.epam.lab.service.UserService;

import java.util.Optional;

/**
 * Designed to performance of the bid process.
 */
public class BidCommand implements Command {

    public static final String ID = "id";
    private static final String LOT_ID = "lotId";
    private static final String COMMAND_LOT_INFO_LOT_ID = "controller?command=lotInfo&lotId=";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    /**
     * Process the request, bid lot and generates a result of processing in the form of
     * {@link com.epam.auction.command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.auction.command.CommandResult} object.
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

        CommandResult commandResult = new CommandResult(MAIN_PAGE, false);
        if (lot.isPresent() && user.isPresent()) {
            Lot aLot = lot.get();
            lotService.bid(aLot);

            User aUser = user.get();
            userService.saveLotBidder(aUser, lotId);

            LotStatusEnum status = aLot.getStatus();
            if (LotStatusEnum.CONFIRMED.equals(status)) {
                commandResult = new CommandResult(COMMAND_LOT_INFO_LOT_ID + lotId, true);
            }
        }

        return commandResult;
    }
}
