package by.epam.lab.controller.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.entity.RoleEnum;

/**
 * Designed to perform a user filter operation.
 */
public class UserFilter implements Filter {

	private static final String ROLE = "role";
	private static final String COMMAND = "command";
	private static final String LOGIN_COMMAND = "login";
	private static final String LOT_MANAGEMENT_COMMAND = "lotManagement";
	private static final String USER_MANAGEMENT_COMMAND = "userManagement";
	private static final String USER_LOTS_COMMAND = "userLots";

	private static final String LOGIN_PAGE = "login.jsp";
	private static final String LOGIN_PAGE_PATH = "/login.jsp";
	private static final String LOGIN_PAGE_STYLE_PATH = "/style/loginStyle.css";

	private static final String ERROR_ACCESS_MESSAGE = "errorAccessMessage";
	private static final String AUTHENTICATION_MESSAGE = "You should login to view this page";
	private static final String ACCESS_USER_MESSAGE = "You should login as admin to view this page";
	private static final String ACCESS_ADMIN_MESSAGE = "You should login as user to view this page";
	private static final String LANGUAGE = "language";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * Method of the Filter is called by the container each time a request/response
	 * pair is passed through the chain due to a client request for a resource at
	 * the end of the chain, checks user on authorization and access to the
	 * requested page. In the case of non authorization or not access user is
	 * forward to login page with appropriate message.
	 *
	 * @param servletRequest  an {@link ServletRequest} object that contains client
	 *                        request
	 * @param servletResponse an {@link ServletResponse} object that contains the
	 *                        response the servlet
	 * @param filterChain     an {@link FilterChain} object that allows the Filter
	 *                        to pass on the request and response to the next entity
	 *                        in the chain.
	 * @throws IOException      Signals that an I/O exception of some sort has
	 *                          occurred.
	 * @throws ServletException General exception a servlet can throw when it
	 *                          encounters difficulty.
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String servletPath = request.getServletPath();
		checkForAuthorization(servletPath, request, response);
		checkForAccess(request, response);

		filterChain.doFilter(request, response);
	}

	/**
	 * The method designed to check user access.
	 *
	 * @param request  an {@link HttpServletRequest} object that contains client
	 *                 request
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet
	 * @throws IOException      Signals that an I/O exception of some sort has
	 *                          occurred.
	 * @throws ServletException General exception a servlet can throw when it
	 *                          encounters difficulty.
	 */

	private void checkForAccess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		RoleEnum role = (RoleEnum) session.getAttribute(ROLE);

		if (role != null) {
			String command = request.getParameter(COMMAND);

			if (role.equals(RoleEnum.USER)
					&& (LOT_MANAGEMENT_COMMAND.equals(command) || USER_MANAGEMENT_COMMAND.equals(command))) {

				forwardToLoginWithMessage(request, response, ACCESS_USER_MESSAGE);
			}

			if (role.equals(RoleEnum.ADMIN) && USER_LOTS_COMMAND.equals(command)) {

				forwardToLoginWithMessage(request, response, ACCESS_ADMIN_MESSAGE);
			}
		}
	}

	/**
	 * The method designed to check user access.
	 *
	 * @param request  an {@link HttpServletRequest} object that contains client
	 *                 request
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet
	 * @throws IOException      Signals that an I/O exception of some sort has
	 *                          occurred.
	 * @throws ServletException General exception a servlet can throw when it
	 *                          encounters difficulty.
	 */

	private void checkForAuthorization(String servletPath, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String command = request.getParameter(COMMAND);

		if (!servletPath.endsWith(LOGIN_PAGE) && !servletPath.endsWith(LOGIN_PAGE_STYLE_PATH)
				&& !LOGIN_COMMAND.equals(command) && !LANGUAGE.equals(command)) {

			HttpSession session = request.getSession();
			RoleEnum role = (RoleEnum) session.getAttribute(ROLE);

			if (role == null) {
				forwardToLoginWithMessage(request, response, AUTHENTICATION_MESSAGE);
			}
		}
	}

	/**
	 * The method designed to forward user on login page with appropriate message.
	 *
	 * @param request  an {@link HttpServletRequest} object that contains client
	 *                 request
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet
	 * @throws IOException      Signals that an I/O exception of some sort has
	 *                          occurred.
	 * @throws ServletException General exception a servlet can throw when it
	 *                          encounters difficulty.
	 */

	private void forwardToLoginWithMessage(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException, ServletException {
		request.setAttribute(ERROR_ACCESS_MESSAGE, message);
		ServletContext servletContext = request.getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher(LOGIN_PAGE_PATH);
		dispatcher.forward(request, response);
	}

	@Override
	public void destroy() {
	}
}
