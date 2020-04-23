package by.epam.lab.service.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.entity.LotDto;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotDtoService;

/**
 * Designed to prepare main page.
 */
public class MainCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    /**
     * Process the request, prepare main page and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findAllActive();
        request.setAttribute(LOT_DTO_LIST, lotDtoList);
        return new CommandResult(MAIN_PAGE, false);

    }
}
