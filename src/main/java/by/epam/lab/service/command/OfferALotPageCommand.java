package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Designed to display offer a lot page.
 */
public class OfferALotPageCommand implements Command {

    private static final String OFFER_A_LOT_PAGE = "/WEB-INF/offerALot.jsp";

    /**
     * Generates a result in the form of {@link command.CommandResult} object with
     * offer a lot page.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(OFFER_A_LOT_PAGE, false);
    }
}