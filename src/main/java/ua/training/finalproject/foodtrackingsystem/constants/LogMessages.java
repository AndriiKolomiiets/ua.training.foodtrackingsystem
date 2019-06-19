package ua.training.finalproject.foodtrackingsystem.constants;

/**
 * Initializing interface.
 * Here are stored log constants.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface LogMessages {

    String LOG_USER_DOUBLE_AUTH = "Double authorization";
    String LOG_ALL_USERS_NULL = "Servlet context has no logged users (allUsers== null). Created new HashSet.";
    String LOG_USER_LOGGED = "User logged";
    String LOG_USER_LOGGED_OUT = "User logged out";
    String LOG_USER_REGISTERED = "User registered";
    String LOG_USER_UPDATE_PARAMETERS = "User updateAll parameters";
    String LOG_USER_HTTP_NOT_EXTRACT = "\n\tProblem to extract user from HttpServletRequest";
    String LOG_USER_GO_USER_URL = "User tries to go by user urls. User role";
    String LOG_USER_GO_NON_REGISTERED_URL = "\n\tTries to go by user(non registered) urls";

    String LOG_HTTP_USER_FIELDS_EMPTY = "User login/password/email from http is/are empty.";
    String LOG_USER_PASSWORD_ERROR = "User password from HTTP doesn't match password from DB";
    String LOG_USER_LOGIN_ERROR = "User login from HTTP doesn't match login from DB";
    String LOG_USER_HTTP_ERROR = "User from HTTP doesn't exist in DB";
    String LOG_LOGIN_OCCUPIED = "User with such login already exists in DB";
    String LOG_LANGUAGE_CHANGED = "Language has been changed";
    String LOG_USER_ACCESS_DENIED = "Attempt to use the same login twice per session";
    String LOG_PAGE_REDIRECTED = "Page redirect";
    String LOG_SEND_RESPONSE = "Send response ";
    String LOG_CONNECTION_POOL_EXCEPTION = "Connection pool exception";
    String LOG_CONNECTION_EXCEPTION = "Exception while getting connection";
    String LOG_DATABASE_EXCEPTION = "Exception while working with database";
    String LOG_USER_CREATED_IN_DB = "New user created in database";
    String LOG_USER_LOGIN_REGISTER_ERROR = "Can't register new user. Login is used";
    String LOG_USER_NOT_FOUND = "User not found in DB";
    String LOG_DAY_MEAL_LIST_CREATED = "DayMeal list created.";
    String LOG_CLIENT_CREATED_IN_DB = "Client profile was created in DB.";
    String LOG_CLIENT_UPDATED_IN_DB = "Client profile was updated in DB.";
    String LOG_DAY_MEAL_CREATED_IN_DB = "DayMeal created in DB.";
    String LOG_DAY_MEAL_ADDED_TO_DB = "New DayMeal was added to DB";
    String LOG_DAY_MEAL_ERROR = "Error while creating or getting DayMeal.";
    String LOG_USER_ROLE_MISSED = "User role is missed";
    String LOG_CLIENT_ERROR = "Client creation error";
    String LOG_DAY_MEAL_UPPDATED_IN_DB = "DayMeal updated in DB";
    String LOG_CLIENT_TRACK_CREATED_IN_DB = "ClientTrack reated in DB";
}
