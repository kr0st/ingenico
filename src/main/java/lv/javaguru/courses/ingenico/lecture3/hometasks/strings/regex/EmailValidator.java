package lv.javaguru.courses.ingenico.lecture3.hometasks.strings.regex;

public class EmailValidator {

    /*
    * If email not valid throw IllegalArgumentException
    *
    * useful methods: String#split, String#matches
    *
    * email = local-part "@" domain
    *
    * Local-part:
    * The local-part of the email address may use any of these ASCII characters:
    *  - uppercase and lowercase Latin letters A to Z and a to z;
    *  - digits 0 to 9;
    *  - special characters ._- (provided that it is not the first or last character.)
    *  - max length 100
    *
    * Domain:
    * The domain name part of an email address has to conform to strict guidelines:
    * it must match the requirements for a hostname, a list of dot-separated DNS labels,
    * each label being limited to a length of 63 characters and consisting of:
    *  - uppercase and lowercase Latin letters A to Z and a to z;
    *  - digits 0 to 9
    *  - hyphen - (provided that it is not the first or last character)
    *  - max length 100
    * */ 

    public void validate(String email) {
        throw new IllegalArgumentException("email not valid : " + email);
    }

}
