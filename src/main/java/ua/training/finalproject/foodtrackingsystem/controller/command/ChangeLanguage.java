package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLanguage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String LANG_PAGE_REGEX = "/fts/[A-Za-z]{2,}";

        String localeFromPage = request.getParameter(Attributes.REQUEST_LANGUAGE);
        String reqPage = request.getParameter(Attributes.REQUEST_CURRENT_PAGE);

        String[] languageAndCountry = localeFromPage.split("_");

        Locale locale = new Locale(languageAndCountry[0], languageAndCountry[1]);

//        boolean tempFlag = reqPage.matches(RegexExpress.LANG_PAGE);


        String respPage = reqPage.matches(LANG_PAGE_REGEX) ?
                reqPage.replace(Attributes.PAGE_PATH, "")
                : Attributes.COMMAND_MAIN_PAGE;
        request.getSession().setAttribute(Attributes.REQUEST_LOCALE_LANGUAGE, locale);

        return PagePath.REDIRECT + respPage;
    }
}
