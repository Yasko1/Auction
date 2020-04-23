package by.epam.lab.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import by.epam.lab.exception.ServiceException;
import by.epam.lab.service.LotService;
import by.epam.lab.service.UserService;
import by.epam.lab.service.util.DateTimeParser;


public class BiddingCloser extends Thread {
	
	private static final String DATE_OF_END_FROM = "date_of_end_from";
	private static final String STATUS = "status";
	
	public void run() {
		
		checkLotsForClosing();
		try {
			TimeUnit.MINUTES.sleep(5);
		}catch(InterruptedException e) { 
			throw new IllegalArgumentException();
		}
	}
	
	private void checkLotsForClosing() {
		List<Lot> lots = null;
		try {
			lots = findAllLotsForClosing();
		} catch (ServiceException e) {
			throw new IllegalArgumentException();
		}
		
		if(lots != null)
			closeBiddingForTheLots(lots);
	}
	
	private List<Lot> findAllLotsForClosing() throws ServiceException {
		Date currentDate = new Date();
		String currentDateString = DateTimeParser.parse(currentDate);
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put(STATUS, LotStatusEnum.CONFIRMED.getValue());
		
		parameters.put(DATE_OF_END_FROM, currentDateString);
		
		LotService lotService = new LotService();
		
		return lotService.findByParameters(parameters);
	}
	
	private void closeBiddingForTheLots(List<Lot> lots) {
        for (Lot lot : lots) {

            long lotId = lot.getId();
            UserService userService = new UserService();

            List<User> bidders = null;
            try {
                bidders = userService.findLotBidders(lotId);
            } catch (ServiceException e) {
            	throw new IllegalArgumentException();
            	
            }

            if (bidders != null) {
                lot.setStatus(LotStatusEnum.PAYMENTWAITING);

                int size = bidders.size();
                
                if (size != 0) {
                    int indexOfLastBidder = size - 1;
                    User lastBidder = bidders.get(indexOfLastBidder);
                    long idLastBidder = lastBidder.getId();
                    lot.setOwnerId(idLastBidder);
                }

            } else {
                lot.setStatus(LotStatusEnum.NOTPURCHASED);
            }

            LotService lotService = new LotService();
            try {
                lotService.save(lot);
            } catch (ServiceException e) {
            	throw new IllegalArgumentException();
            }
        }


	}
}
