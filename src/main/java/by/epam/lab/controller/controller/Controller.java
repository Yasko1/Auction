package by.epam.lab.controller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.lab.entity.BiddingCloser;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.command.Command;
import by.epam.lab.service.command.CommandResult;
import by.epam.lab.service.factory.CommandFactory;

/**
 *Provides a HTTP servlet class suitable for a Web site.
 */
public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private static final String COMMAND = "command";
	private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
	private BiddingCloser biddingCloser;

	@Override
	public void init() {
		biddingCloser = new BiddingCloser();
		biddingCloser.start();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public void destroy() {
		biddingCloser.interrupt();
	}

	/**
	 * Processes the request by getting a command from the
	 * {@link HttpServletRequest} object, execute this command and redirects or
	 * forwards on destination page depending on the result of the command.
	 *
	 * @param request  an {@link HttpServletRequest} object that contains client request
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet sends to the client
	 * @throws ServletException General exception a servlet can throw when it
	 *                          encounters difficulty.
	 * @throws IOException      Signals that an I/O exception of some sort has occurred.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter(COMMAND);
		Command action = CommandFactory.create(command);

		LOGGER.info("Command = " + command);

		CommandResult commandResult;
		try {
			commandResult = action.execute(request, response);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage(), e);
			commandResult = new CommandResult(ERROR_PAGE, false);
		}
		
		String page = commandResult.getPage();
		if (commandResult.isRedirect()) {
			sendRedirect(response, page);
		} else {
			dispatch(request, response, page);
		}
	}

	/**
	 * Forwards to destination page.(nujnuu)
	 *
	 * @param request  an {@link HttpServletRequest} object that contains client request.
	 * @param response an {@link HttpServletResponse} object that contains the response the
	 *  servlet sends to the client.
	 * @param page an {@link String} object that contains forwarding page.
	 * @throws ServletException General exception a servlet can throw when it encounters difficulty.
	 * @throws IOException      Signals that an I/O exception of some sort has occurred.
	 */
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext(); 
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}

	/**
	 * Redirects to destination page.
	 *
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet sends to the client.
	 * @param page     an {@link String} object that contains redirecting page.
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 */
	private void sendRedirect(HttpServletResponse response, String page) throws IOException {
		response.sendRedirect(page);
	}
}
