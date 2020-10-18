package Classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL.matcher(emailStr);
        return matcher.find();
    }
}
