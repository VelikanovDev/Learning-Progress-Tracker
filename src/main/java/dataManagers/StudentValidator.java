package dataManagers;

import java.util.regex.Pattern;

public class StudentValidator {
    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile("[a-zA-Z]+(['\\-]?[a-zA-Z]+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile("[a-zA-Z]+(['\\- ]?[ a-zA-Z]+)*", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+(?:[-.]*\\w*)*@\\w+\\.\\w+", Pattern.CASE_INSENSITIVE);
    private static final Pattern POINTS_PATTERN = Pattern.compile("\\d+ \\d+ \\d+ \\d+ \\d+");

    public static boolean validateFirstName(String firstName) {
        return FIRST_NAME_PATTERN.matcher(firstName).matches() && firstName.length() > 1;
    }

    public static boolean validateLastName(String lastName) {
        return LAST_NAME_PATTERN.matcher(lastName).matches() && lastName.length() > 1;
    }

    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validatePointsFormat(String pointsFormat) {
        return POINTS_PATTERN.matcher(pointsFormat).matches();
    }

}
