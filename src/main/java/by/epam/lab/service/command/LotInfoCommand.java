package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.entity.LotDto;
import by.epam.lab.entity.User;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotDtoService;
import by.epam.lab.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Designed to find of information about the lot.
 */
public class LotInfoCommand implements Command {

    private static final String LOT_ID = "lotId";
    private static final String LOT_DTO = "lotDto";
    private static final String LOT_INFO_PAGE = "/WEB-INF/lotInfo.jsp";
    private static final String BIDDERS_LIST = "biddersList";


    /**
     * Process the request, finds lot info and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idString = request.getParameter(LOT_ID);
        long lotId = Long.valueOf(idString);
        LotDtoService lotDTOService = new LotDtoService();
        Optional<LotDto> lotDTO = lotDTOService.findById(lotId);
        lotDTO.ifPresent(aDTOLot -> request.setAttribute(LOT_DTO, aDTOLot));

        UserService userService = new UserService();
        List<User> bidders = userService.findLotBidders(lotId);
        request.setAttribute(BIDDERS_LIST, bidders);

        return new CommandResult(LOT_INFO_PAGE, false);
    }

}
