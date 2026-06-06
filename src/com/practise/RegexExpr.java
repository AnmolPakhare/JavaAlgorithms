package com.practise;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* ^: This symbol asserts the start of the string. It ensures that the string begins with the pattern defined afterward.

[_A-Za-z0-9-\\+]+: This part matches the local part of the email address. Let's break it down:

[_A-Za-z0-9-]: Matches any uppercase letter, lowercase letter, digit, underscore, or hyphen.
\\+: Matches the plus sign, which is allowed in email addresses according to the specification.
+: Indicates that one or more of the preceding characters should be matched.
(\\.[_A-Za-z0-9-]+)*: This part allows for periods followed by more characters (local parts). Let's break it down:

\\.: Matches a period character.
[_A-Za-z0-9-]+: Matches any uppercase letter, lowercase letter, digit, underscore, or hyphen after the period.
*: Indicates that the preceding group (period and characters) can occur zero or more times. This allows for multiple subdomains in the email address.
@: Matches the "@" symbol, separating the local part from the domain part of the email address.

[A-Za-z0-9-]+: This part matches the domain name. Let's break it down:

[A-Za-z0-9-]: Matches any uppercase letter, lowercase letter, digit, or hyphen.
+: Indicates that one or more of the preceding characters should be matched.
(\\.[A-Za-z0-9]+)*: This allows for periods followed by more characters (subdomains). Let's break it down:

\\.: Matches a period character.
[A-Za-z0-9]+: Matches any uppercase letter, lowercase letter, or digit after the period.
*: Indicates that the preceding group (period and characters) can occur zero or more times. This allows for multiple subdomains in the domain name.
(\\.[A-Za-z]{2,}): This matches the top-level domain (TLD) part of the email address. Let's break it down:

\\.: Matches a period character.
[A-Za-z]{2,}: Matches any uppercase or lowercase letter occurring at least twice after the period. This ensures that the TLD consists of at least two characters.
$: This symbol asserts the end of the string. It ensures that the string ends with the pattern defined before it.
* */

public class RegexExpr {

    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] emails = {
                "example@example.com",
                "test.email@example.com",
                "user@domain.co.in",
                "user123@gmail.com",
                "user.name@example.co.jp",
                "invalid.email.com",
                "invalid@.com",
                "invalid@domain."
        };

        for (String email : emails) {
            System.out.println(email + " is valid? " + isValidEmail(email));
        }
    }
}
