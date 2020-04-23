package by.epam.lab.service.factory;

import by.epam.lab.service.builder.Builder;
import by.epam.lab.service.builder.LotBuilder;
import by.epam.lab.service.builder.UserBuilder;

/**
 * Designed to build an object of type {@link Builder}.
 */
public class BuilderFactory {

    private static final String USER = "\"user\"";
    private static final String LOT = "\"lot\"";

    /**
     * Designed to build an object of type {@link Builder} depends on builder name.
     *
     * @param builderName a {@link String} object that contains builder name
     * @return an object of type {@link Builder}.
     */
    @SuppressWarnings("rawtypes")
	public static Builder create(String builderName) {

    	System.out.println(" !!!!!BuilderName :  "+builderName);
        switch (builderName) {
            case USER:
            	System.out.println("yes?");
                return new UserBuilder();
            case LOT:
            	System.out.println("yes??");
                return new LotBuilder();
            default:
            	System.out.println("yes???");
                throw new IllegalArgumentException("Unknown Builder name!");
        }
    }

}
