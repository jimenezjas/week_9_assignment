package com.example.week_9_assignment;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.FocusListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * This class is used for the user input, and validation of said information.
 * Once all information is validated, the submit button will be enabled, and the user will be allowed access to the user UI.
 */


public class HelloController extends HelloApplication{
    @FXML
    public TextField firstNameInput;
    boolean fnValid = false;
    @FXML
    protected TextField lastNameInput;
    boolean lnValid = false;
    @FXML
    protected TextField emailInput;
    boolean emailValid = false;
    @FXML
    protected TextField dobInput;
    boolean dobValid = false;
    @FXML
    protected TextField zipInput;
    boolean zipValid = false;

    @FXML
    protected Label firstNameInfo;
    @FXML
    protected Label lastNameInfo;
    @FXML
    protected Label emailInfo;
    @FXML
    protected Label dobInfo;
    @FXML
    protected Label zipInfo;

    @FXML
    protected Button submitButton;

    /**
     * This method creates patterns that are used in the email and date of birth validation process.
     * This method also adds the focus listeners that allow for the validation process to begin.
     */
    @FXML
    public void initialize() throws IOException {

        //sets up the patterns for the validation process.
        Pattern emailPattern = Pattern.compile("@farmingdale.edu", Pattern.CASE_INSENSITIVE);
        Pattern dobPattern = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$");
        submitButton.setDisable(true);

        //sets up the listeners for validation
        firstNameInput.focusedProperty().addListener((observableValue, wasFocused, isNowFocused) -> validateFirstName());
        lastNameInput.focusedProperty().addListener((observableValue, wasFocused, isNowFocused) -> validateLastName());
        emailInput.focusedProperty().addListener((observableValue, wasFocused, isNowFocused) -> validateEmail(emailPattern));
        dobInput.focusedProperty().addListener((observableValue, wasFocused, isNowFocused) -> validateDob(dobPattern));
        zipInput.focusedProperty().addListener((observableValue, wasFocused, isNowFocused) -> validateZip());
    }

    /**
     * Validates the first name user input.
     * Checks the length of the string, seeing if the string is either below 2 or above 26 characters.
     * It will update the label with feedback depending on the validity.
     */
    public void validateFirstName(){
        boolean fnValid = firstNameInput.getText().length() > 2 && firstNameInput.getText().length() < 26;
        updateFeedback(firstNameInfo, fnValid, "Thank you!", "Keep it between 2-25 characters!");
        validCheck();
    }

    /**
     * Validates the last name user input.
     * Checks the length of the string, seeing if the string is either below 2 or above 26 characters.
     * It will update the label with feedback depending on the validity.
     */
    public void validateLastName(){
        boolean lnValid = lastNameInput.getText().length() > 2 && lastNameInput.getText().length() < 26;
        updateFeedback(lastNameInfo, lnValid, "Thank you!", "Keep it between 2-25 characters!");
        validCheck();
    }

    /**
     * Validates the email user input.
     * It will update the label with feedback depending on the validity.
     *
     * @param emailPattern is the pattern used for validating the email format.
     *                     It will check if the user input contains @farmingdale.edu.
     */
    public void validateEmail(Pattern emailPattern){
        Matcher emailMatcher = emailPattern.matcher(emailInput.getText());
        emailValid = emailMatcher.find();
        updateFeedback(emailInfo, emailValid, "Thank you!", "Insert a valid farmingdale email!");
        validCheck();
    }

    /**
     * Validates the date of birth user input.
     * It will update the label with feedback depending on the validity.
     *
     * @param dobPattern is the pattern used for validating the date of birth format.
     *                   It will check that the user inputted numbers in MM/DD/YYYY format.
     */
    public void validateDob(Pattern dobPattern){
        Matcher dobMatcher = dobPattern.matcher(dobInput.getText());
        dobValid = dobMatcher.find();
        updateFeedback(dobInfo, dobValid, "Thank you!", "Keep it in MM/DD/YYYY format!");
        validCheck();
    }

    /**
     * Validates the zip code input.
     * Checks if the user inputted a zipcode that is exactly 5 digits long.
     * It will update the label with feedback depending on the validity.
     */
    public void validateZip(){
        zipValid = zipInput.getText().length() == 5 && zipInput.getText().matches("[0-9]+");
        updateFeedback(zipInfo, zipValid, "Thank you!", "Insert a 5 digit zipcode!");
        validCheck();
    }

    /**
     *
     * @param infoLabel is the label that will be updated.
     * @param validCheck is the boolean that indicates if the user input was valid.
     * @param validMessage is the message that is shown when valid.
     * @param invalidMessage is the message shown when invalid.
     */
    public void updateFeedback(Label infoLabel, boolean validCheck, String validMessage, String invalidMessage){
        if(validCheck){
            infoLabel.setText(validMessage);
        }
        else{
            infoLabel.setText(invalidMessage);
        }
        infoLabel.setOpacity(1.0);
    }

    /**
     * This checks the validity of each user input and enables or disables the submit button depending on the outcome.
     * This is checked each time the user inputs anything into any of the text fields.
     */
    public void validCheck(){
        boolean shouldEnable = fnValid && lnValid && emailValid && dobValid && zipValid;
        submitButton.setDisable(!shouldEnable);
    }

    /**
     * This method allows the user to enter the new user UI after inputting valid information into the form.
     * @throws IOException
     */
    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
        ((Stage) submitButton.getScene().getWindow()).setScene(scene2);
    }

}

