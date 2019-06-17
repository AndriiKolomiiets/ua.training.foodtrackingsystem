package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

public class ChangeLanguage implements Command {
    private static final Logger log = Logger.getLogger(ChangeLanguage.class);
    @Override
    public String execute(HttpServletRequest request) {
        String LANG_PAGE_REGEX = "/fts/[A-Za-z]{2,}";
        String localeFromPage = request.getParameter(Attributes.REQUEST_LANGUAGE);
        String reqPage = request.getParameter(Attributes.REQUEST_CURRENT_PAGE);
        String[] languageAndCountry = localeFromPage.split("_");
        Locale locale = new Locale(languageAndCountry[0], languageAndCountry[1]);

        String respPage = reqPage.matches(LANG_PAGE_REGEX) ?
                reqPage.replace(Attributes.PAGE_PATH, "")
                : Attributes.COMMAND_MAIN_PAGE;
        request.getSession().setAttribute(Attributes.REQUEST_LOCALE_LANGUAGE, locale);
        log.warn(LogMessages.LOG_LANGUAGE_CHANGED + "[New language: "+ Arrays.toString(languageAndCountry) +"]");
        return PagePath.REDIRECT + respPage;
    }
}
