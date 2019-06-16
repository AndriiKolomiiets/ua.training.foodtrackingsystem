package ua.training.finalproject.foodtrackingsystem.constants;

public interface LogMessages {
    String LOG_DB_PROPERTIES = "\n\tProblem to read file 'db.properties'";
    String LOG_NOT_WRITE_CUSTOM_TAG = "\n\tProblem to write custom tag";
    String LOG_NOT_ACCESSIBLE_ENTITY_FIELDS = "\n\tProblem to get entity fields";

    String LOG_USER_DOUBLE_AUTH = "Double authorization";
    String LOG_ALL_USERS_NULL= "Servlet context has no logged users (allUsers== null). Created new HashSet.";
    String LOG_USER_LOGGED = "User logged";
    String LOG_USER_LOGGED_OUT = "User logged out";
    String LOG_USER_REGISTERED = "User registered";
    String LOG_USER_UPDATE_PARAMETERS = "User update parameters";
    String LOG_USER_GET_BY_ID = "\n\tProblem to get user by id";
    String LOG_USER_HTTP_NOT_EXTRACT = "\n\tProblem to extract user from HttpServletRequest";
    String LOG_USER_GO_ADMIN_URL = "User tries to go by admin urls";
    String LOG_USER_GO_USER_URL = "User tries to go by user urls. User role";
    String LOG_USER_GO_NON_REGISTERED_URL = "\n\tTries to go by user(non registered) urls";

    String LOG_DISH_GET_BY_ID = "\n\tProblem to get dish by id";
    String LOG_DISH_HTTP_NOT_EXTRACT = "\n\tProblem to extract dish from HttpServletRequest";

    String LOG_DAY_RATION_GET_BY_ID = "\n\tProblem to get day ration by id";
    String LOG_DAY_RATION_GET_BY_DATE_AND_USER = "\n\tProblem to get day ration by date and user";
    String LOG_DAY_RATION_HTTP_NOT_EXTRACT = "\n\tProblem to extract day ration from HttpServletRequest";

    String LOG_RATION_COMPOSITION_GET_BY_ID = "\n\tProblem to get composition by id";
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
}
