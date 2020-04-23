package by.epam.lab.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotDto;
import by.epam.lab.entity.LotStatusEnum;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotDtoService;
import by.epam.lab.service.util.DateTimeParser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Designed to performance lot finding process.
 */
public class FindLotsCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";
    private static final String LOT_DTO_LIST = "lotDtoList";

    private static final String DATE_OF_START_FROM = "date_of_start_from";
    private static final String DATE_OF_END_TO = "date_of_end_to";
    private static final String PRICE_FROM = "price_from";
    private static final String PRICE_TO = "price_to";

    /**
     * Process the request, find lots with given parameters and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Map<String, String> parameters = findLotsParameters(request);

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findByParameters(parameters);

        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return new CommandResult(MAIN_PAGE, false);
    }

    private Map<String, String> findLotsParameters(HttpServletRequest request) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.CONFIRMED.getValue());

        Date currentDate = new Date();
        String currentDateString = DateTimeParser.parse(currentDate);
        parameters.put(DATE_OF_START_FROM, currentDateString);
        parameters.put(DATE_OF_END_TO, currentDateString);

        String priceFrom = request.getParameter(PRICE_FROM);
        String priceTo = request.getParameter(PRICE_TO);
        parameters.put(PRICE_FROM, priceFrom);
        parameters.put(PRICE_TO, priceTo);

        return parameters;
    }
}
