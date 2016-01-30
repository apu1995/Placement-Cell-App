package com.example.sony_vaio.placementapp;

import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    // Regular Expression
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{10}";
    private static final String NAME_REGEX = "^[a-zA-Z\\s]*$";
    private static final String ROLL_REGEX = "^[0-9]*$";
    private static final String DOB_REGEX = "^[0-9-/]*$";
    private static final String SPI_REGEX = "^[0-9]*(\\.)?[0-9]+$";

    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "##########";
    private static final String NAME_MSG = "invalid name";
    private static final String Roll_MSG = "invalid ID";
    private static final String DOB_MSG = "invalid Date of Birth";
    private static final String SPI_MSG = "Invalid SPI or CPI";

    public static boolean isSpi(EditText editText, boolean required){
        return isValid(editText, SPI_REGEX, SPI_MSG, required);
    }

    public static boolean isDOB(EditText editText, boolean required) {
        return isValid(editText, DOB_REGEX, DOB_MSG, required);
    }

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }
    //call this method when you need to check name validations
    public static boolean isName(EditText editText, boolean required) {
        return isValid(editText, NAME_REGEX, NAME_MSG, required);
    }
    public static boolean isRoll(EditText editText, boolean required) {
        return isValid(editText, ROLL_REGEX, Roll_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {
        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }
}

