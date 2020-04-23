package by.epam.lab.service.command;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotPhoto;
import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotPhotoService;
import by.epam.lab.service.LotService;
import by.epam.lab.service.upload.FileUploader;
import by.epam.lab.service.util.DateTimeParser;

/**
 * Designed to complete offer a lot process.
 */
public class OfferALotCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OfferALotCommand.class.getName());
    private static final String ID = "id";

    private static final int HOUR_IN_MILLIS = 3600000;
    private static final String JAVAX_SERVLET_CONTEXT_TEMPDIR = "javax.servlet.context.tempdir";
    private static final String COMMAND_OFFER_ALOT_PAGE = "controller?command=offerALotPage";

    /**
     * Process the request, offer a lot and generates a result of processing in the form of
     * {@link command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Lot lot = new Lot();
        List<String> lotPhotosPaths = new ArrayList<>();

        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(ID);
        lot.setOwnerId(id);

        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletContext servletContext = request.getServletContext();
            File repository = (File) servletContext.getAttribute(JAVAX_SERVLET_CONTEXT_TEMPDIR);
            fileItemFactory.setRepository(repository);

            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> items = servletFileUpload.parseRequest(request);

            FileUploader fileUploader = FileUploader.getInstance();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    buildLotParams(item, lot);
                } else {
                    String fileName = fileUploader.upload(item);
                    lotPhotosPaths.add(fileName);
                }
            }
        } catch (FileUploadException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }

        LotService lotService = new LotService();
        long idLot = lotService.save(lot);

        List<LotPhoto> lotPhotos = buildLotPhotos(lotPhotosPaths, idLot);

        LotPhotoService lotPhotoService = new LotPhotoService();
        for (LotPhoto lotPhoto : lotPhotos) {
            lotPhotoService.saveLotPhoto(lotPhoto);
        }

        return new CommandResult(COMMAND_OFFER_ALOT_PAGE, true);
    }

    private void buildLotParams(FileItem item, Lot lot) {

        String paramName = item.getFieldName();
        String paramValue = item.getString();

        switch (paramName) {
            case Lot.PRICE:
                BigDecimal price = new BigDecimal(paramValue);
                lot.setPrice(price);
                break;
            case Lot.DATE_OF_START:
                Date dateOfStart = DateTimeParser.parse(paramValue);
                lot.setDateOfStart(dateOfStart);

                long dateOfStartMillis = dateOfStart.getTime();
                long dateOfEndMillis = dateOfStartMillis + HOUR_IN_MILLIS;
                Date dateOfEnd = new Date(dateOfEndMillis);
                lot.setDateOfEnd(dateOfEnd);
                break;
		
            default:
                throw new IllegalArgumentException("Unknown type of parameter!");
        }
    }

    private List<LotPhoto> buildLotPhotos(List<String> photosPaths, long lotId) {
        List<LotPhoto> lotPhotos = new ArrayList<>();
        for (String photoPath : photosPaths) {
            LotPhoto lotPhoto = new LotPhoto();

            lotPhoto.setIdLot(lotId);
            lotPhoto.setUrl(photoPath);

            lotPhotos.add(lotPhoto);
        }

        return lotPhotos;
    }

}
