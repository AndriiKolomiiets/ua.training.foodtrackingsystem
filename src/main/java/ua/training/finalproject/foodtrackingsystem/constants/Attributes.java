package ua.training.finalproject.foodtrackingsystem.constants;

/**
 * Initializing interface.
 * Here are stored requests and response constants.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface Attributes {
    String HTML_TEXT = "text/html";
    String HTML_ENCODE = "UTF-8";
    String REGEX_REDIRECT_PAGE = ".*/fts/";

    String PAGE_PATH = "/fts/";
    String PAGE_NAME = "pageName";
    String PAGE_MAIN = "page.main";
    String PAGE_FOOD_TRACKING = "page.food.tracking";
    String PAGE_DEMONSTRATION = "page.demonstration";

    //SESSION
    String REQUEST_USER = "user";
    String REQUEST_USERS_ALL = "allUsers";
    String REQUEST_USER_ROLE = "role";
    String REQUEST_LANGUAGE = "lang";
    String REQUEST_LOCALE_LANGUAGE = "localeLang";
    String REQUEST_CURRENT_PAGE = "currPage";

    //RETURN STATEMENT
    String RETURN_STATEMENT_USER_IS_EMPTY = "userIsEmpty";
    String RETURN_STATEMENT_USER_LOGGED = "userIsLogged";
    String RETURN_STATEMENT_SUCCESS = "success";
    String RETURN_STATEMENT_USER_LOGGED_OUT = "userIsLoggedOut";
    String RETURN_STATEMENT_USER_EXISTS_IN_DB = "userExistsInDb";
    String USER_ERROR_LOGIN = "userErrorLogin";
    String USER_NOT_EXISTS = "userNotExist";
    String USER_ERROR_PASSWORD = "userPassError";

    //USER
    String REQUEST_USER_ID = "user_id";
    String REQUEST_LOGIN = "login";
    String REQUEST_EMAIL = "email";
    String REQUEST_PASSWORD = "password";
    String REQUEST_ROLE = "role";
    String REQUEST_ROLE_ID = "role_id";

    //CLIENT
    String REQUEST_CLIENT_ID = "client_id";
    String REQUEST_BIRTH_DATE = "birth_date";
    String REQUEST_HEIGHT = "height";
    String REQUEST_WEIGHT = "weight";
    String REQUEST_CALORIES_NORM = "calories_norm";
    String REQUEST_LIFE_STYLE = "life_style_coefficient";

    //FOOD
    String REQUEST_FOOD_ID = "food_id";
    String REQUEST_FOOD_NAME = "food_name";
    String REQUEST_CALORIES = "calories";
    String REQUEST_PROTEINS = "proteins";
    String REQUEST_LIPIDS = "lipids";
    String REQUEST_CARBOHYDRATES = "carbs";

    //DAY MEAL
    String REQUEST_MEAL_ID = "meal_id";
    String REQUEST_DATE_TIME = "date_time";
    String REQUEST_NUMBER = "number";
    String REQUEST_CALORIES_STATUS = "calories_status";
    String REQUEST_CALORIES_TO_NORM = "calories_to_norm";

    //TRACK STAT
    String REQUEST_TRACK_STATISTIC_ID = "track_statistic_is";

    //CLIENT TRACK
    String REQUEST_CLIENT_TRACK_ID = "client_track_id";
    String REQUEST_DATE = "date";

    //COMMANDS
    String COMMAND_SIGN_OR_REGISTER = "signInOrRegister";
    String COMMAND_SIGN_OR_REGISTER_WITH_ERROR = "signInOrRegisterWithError";
    String COMMAND_LOG_IN = "logIn";
    String COMMAND_LOG_OUT = "logOut";
    String COMMAND_REGISTER_NEW_USER = "registerNewUser";
    String COMMAND_MAIN_PAGE = "mainPage";
    String COMMAND_CHANGE_LANGUAGE = "changeLanguage";
    String COMMAND_USER_SETTINGS = "userSettings";
    String COMMAND_USER_SETTINGS_WITH_ERROR = "userSettingsWithError";
    String COMMAND_USER_UPDATE_PARAMETERS = "updateUserParameters";
    String COMMAND_USER_UPDATE_BY_ADMIN = "updateUsers";
    String COMMAND_SHOW_USERS = "showUsers";
    String COMMAND_SHOW_USERS_UPDATE_SEARCH_BY_ADMIN = "showUsersAfterUpdateOrSearch";
    String COMMAND_SHOW_USERS_BY_EMAIL_BY_ADMIN = "searchUsersByEmail";
    String COMMAND_DELETE_USERS = "deleteUsers";
    String COMMAND_DAY_RATION = "dayRation";
    String COMMAND_CREATE_DAY_RATION = "createNewRation";
    String COMMAND_LIST_HOME_PAGE = "listHomePage";
    String COMMAND_LIST_USER_DAY_RATION = "listUserDayRation";
    String COMMAND_DELETE_USER_COMPOSITION = "deleteUsersComposition";
    String COMMAND_UPDATE_USER_COMPOSITION = "updateUsersComposition";
    String COMMAND_MENU = "menu";
    String COMMAND_MENU_GENERAL_EDIT = "menuGeneralEdit";
    String COMMAND_MENU_GENERAL_EDIT_WITH_ERROR = "menuGeneralEditWithError";
    String COMMAND_MENU_GENERAL_DELETE = "deleteGeneralMenuItem";
    String COMMAND_MENU_GENERAL_UPDATE = "updateGeneralDish";
    String COMMAND_MENU_USERS_DELETE = "deleteUsersMenuItem";
    String COMMAND_MENU_USERS_UPDATE = "updateUsersDish";
    String COMMAND_MENU_USERS_EDIT = "menuUsersEdit";
    String COMMAND_MENU_USERS_EDIT_WITH_ERROR = "menuUsersEditWithError";
    String COMMAND_MENU_USERS_EDIT_AFTER_UPDATE = "menuUsersEditAfterUpdate";
    String COMMAND_MENU_ADD_DISH = "addNewDish";
    String COMMAND_MENU_LIST_DISH_PAGE = "listDishPage";
    String COMMAND_MENU_LIST_USERS_PAGE = "listUsersPage";
    String HTML_CONTENT_TYPE = "text/html;charset=UTF-8";
    String REQUEST_MEAL_LIST = "mealList";
    String REQUEST_CLIENT_LIST = "clientList";
    String REQUEST_CLIENT_TRACK_LIST = "trackList";
    String RETURN_STATEMENT_FOOD_EXISTS = "foodExists";
    String CALORIES_STATUS_EXCEEDED = "exceeded";
    String CALORIES_STATUS_OK = "normal";
    String RETURN_STATEMENT_WRONG_ID = "wrongId";

}
