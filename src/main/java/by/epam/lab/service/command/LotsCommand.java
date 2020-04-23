package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.LotDto;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotDtoService;

import java.util.List;

/**
 * Designed to find user lots.
 */
public class LotsCommand implements Command {

    private static final String ID = "id";
    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String USER_LOTS_PAGE = "/WEB-INF/userLots.jsp";

    /**
     * Process the request, finds user lots and generates a result of processing in the form of
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

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findAllByUserId(id);
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return new CommandResult(USER_LOTS_PAGE, false);
    }
}
