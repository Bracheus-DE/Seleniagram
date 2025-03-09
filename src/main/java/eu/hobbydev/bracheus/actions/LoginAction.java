package eu.hobbydev.bracheus.actions;


import eu.hobbydev.bracheus.Seleniagram;
import eu.hobbydev.bracheus.exceptions.SeleniagramNoSuchElementException;
import eu.hobbydev.bracheus.interfaces.Actions;
import eu.hobbydev.bracheus.interfaces.ConfigurationHolder;
import eu.hobbydev.bracheus.manager.SeleniumManager;
import eu.hobbydev.bracheus.utils.HumanizerTools;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C) 2025 Bracheus
 * <p>
 * All rights reserved. Unauthorized copying, distribution, or modification
 * of this code without express permission from Bracheus is
 * strictly prohibited.
 * <p>
 * This project is the result of personal development and innovation by Bracheus.
 * By using or modifying this code, you acknowledge the ownership of Bracheus
 * and agree to comply with the terms outlined in the project’s
 * licensing agreement.
 * <p>
 * Created by: Bracheus (GitHub: https://github.com/Bracheus-DE)
 * <p>
 * For inquiries, collaborations, or licensing information, contact:
 * bracheus@hobbydev.eu
 */

/**
 * The {@code LoginAction} class implements the {@link Actions} and {@link HumanizerTools} interfaces
 * to automate the login process on Instagram using Selenium.
 * <p>
 * This action attempts to log in with the provided credentials, handling cookie pop-ups
 * and checking for login errors. If the login fails, it stops further actions.
 * </p>
 */
public class LoginAction implements Actions, HumanizerTools {

    Logger logger = LoggerFactory.getLogger(name);

    private static final String name = "LoginAction";

    /**
     * Executes the login action by navigating to Instagram, handling cookies,
     * filling out the login form, and verifying if the login was successful.
     * <p>
     * If the login fails due to incorrect credentials, the bot is stopped.
     * </p>
     */
    @Override
    public void handleAction() {
        openingLogin();
        checkForCookies();
        if (!fillingInputForm()) {
            logger.error("Can't login with given information!");
            Seleniagram.actionThreadManager.registerActions(new StoppingSeleniagramAction());
        }
        logger.info("Logged in successfully!");
    }

    /**
     * Fills in the login form with the stored credentials and submits it.
     * <p>
     * The method attempts to locate the username, password, and login button elements.
     * If any of them are missing or if an incorrect password is detected, the login fails.
     * </p>
     *
     * @return {@code true} if the login was successful, {@code false} otherwise.
     */
    private boolean fillingInputForm() {
        WebElement username = null;
        WebElement password = null;
        WebElement login = null;

        try {
            username = getSeleniumManager().findInputByName("username");
        } catch (SeleniagramNoSuchElementException e) {
            logger.info("Can't find username input field!");
            return false;
        }

        try {
            password = getSeleniumManager().findInputByName("password");
        } catch (SeleniagramNoSuchElementException e) {
            logger.info("Can't find password input field!");
            return false;
        }

        try {
            login = getSeleniumManager().findButtonByText("Log in");
        } catch (SeleniagramNoSuchElementException e) {
            logger.info("Can't find login button!");
            return false;
        }

        username.click();
        username.sendKeys(getConfigurationHolder().getUsername());
        inputDelay();
        password.click();
        password.sendKeys(getConfigurationHolder().getPassword());
        inputDelay();
        login.click();
        siteDelay();

        WebElement errorMessage = null;
        try {
            errorMessage = getSeleniumManager().findDivByText("Sorry, your password was incorrect. Please double-check your password.");
        } catch (SeleniagramNoSuchElementException e) {
            logger.info("No login error found");
        }

        if (errorMessage != null) {
            logger.info("Login credentials are wrong!");
            return false;
        }

        if (getSeleniumManager().getUrl().equals("https://instagram.com/")) {
            logger.info("An unknown error occurred.");
            return false;
        }

        return true;
    }

    /**
     * Checks for a cookie consent popup and accepts all cookies if the popup is found.
     * <p>
     * If the cookie consent button is not found, it is assumed that cookies have already been accepted.
     * </p>
     */
    private void checkForCookies() {
        WebElement cookieButton = null;
        try {
            cookieButton = getSeleniumManager().findButtonByText("Allow all cookies");
        } catch (SeleniagramNoSuchElementException e) {
            logger.error(e.getMessage());
        }
        if (cookieButton != null) {
            cookieButton.click();
            logger.info("Accepted cookies!");
            inputDelay();
        } else {
            logger.info("Cookies must be already accepted! No cookie popup found!");
        }
    }

    /**
     * Opens the Instagram login page and applies a delay to ensure page load completion.
     */
    private void openingLogin() {
        siteDelay();
        Seleniagram.actionThreadManager.getSeleniumManager().open("https://instagram.com/");
        siteDelay();
    }

    /**
     * Returns the name of this action.
     *
     * @return The name of this action as a {@code String}.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the {@link SeleniumManager} instance used for handling Selenium operations.
     *
     * @return The SeleniumManager instance.
     */
    private SeleniumManager getSeleniumManager() {
        return Seleniagram.actionThreadManager.getSeleniumManager();
    }

    /**
     * Retrieves the {@link ConfigurationHolder} instance that contains the stored login credentials.
     *
     * @return The ConfigurationHolder instance.
     */
    private ConfigurationHolder getConfigurationHolder() {
        return Seleniagram.configurationHolder;
    }
}