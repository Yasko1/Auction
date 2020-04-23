package by.epam.lab.service.factory;

import by.epam.lab.service.command.*;
import by.epam.lab.service.command.admin.BanUserCommand;
import by.epam.lab.service.command.admin.ConfirmLotCommand;
import by.epam.lab.service.command.admin.LotManagementCommand;
import by.epam.lab.service.command.admin.RefuseLotCommand;
import by.epam.lab.service.command.admin.UnbanUserCommand;
import by.epam.lab.service.command.admin.UserManagementCommand;

/**
 * Designed to build an object of type {@link Command}.
 */
public class CommandFactory {

    /**
     * Designed to build an object of type {@link Command} depends on command name.
     *
     * @param command a {@link String} object that contains builder name
     * @return an object of type {@link Command}.
     */
    public static Command create(String command) {

        command = command.toUpperCase();
        CommandEnum commandEnum = CommandEnum.valueOf(command);

        Command resultCommand;
        switch (commandEnum) {
            case LOGIN:
                resultCommand = new LoginCommand();
                break;
            case OFFERALOTPAGE:
                resultCommand = new OfferALotPageCommand();
                break;
            case MAIN:
                resultCommand = new MainCommand();
                break;
            case PROFILE:
                resultCommand = new ProfileCommand();
                break;
            case LOTINFO:
                resultCommand = new LotInfoCommand();
                break;
            case USERLOTS:
                resultCommand = new LotsCommand();
                break;
            case SIGNOUT:
                resultCommand = new SingOutCommand();
                break;
            case LOTMANAGEMENT:
                resultCommand = new LotManagementCommand();
                break;
            case USERMANAGEMENT:
                resultCommand = new UserManagementCommand();
                break;
            case FINDLOTS:
                resultCommand = new FindLotsCommand();
                break;
            case OFFERALOT:
                resultCommand = new OfferALotCommand();
                break;
            case REFUSELOT:
                resultCommand = new RefuseLotCommand();
                break;
            case CONFIRMLOT:
                resultCommand = new ConfirmLotCommand();
                break;
            case BANUSER:
                resultCommand = new BanUserCommand();
                break;
            case UNBANUSER:
                resultCommand = new UnbanUserCommand();
                break;
            case TOPUPBALANCE:
                resultCommand = new TopUpBalanceCommand();
                break;
            case PAYLOT:
                resultCommand = new PayLotCommand();
                break;
            case BID:
                resultCommand = new BidCommand();
                break;
            case LANGUAGE:
                resultCommand = new LanguageCommand();
                break;
            default:
                throw new IllegalArgumentException("Invalid command" + commandEnum);
        }
        return resultCommand;
    }
}