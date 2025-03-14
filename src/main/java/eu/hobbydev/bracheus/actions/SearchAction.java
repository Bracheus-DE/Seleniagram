package eu.hobbydev.bracheus.actions;


import eu.hobbydev.bracheus.Seleniagram;
import eu.hobbydev.bracheus.classes.InstaUser;
import eu.hobbydev.bracheus.classes.LanguageHolder;
import eu.hobbydev.bracheus.exceptions.SeleniagramNoSuchElementException;
import eu.hobbydev.bracheus.interfaces.Actions;
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
 * Represents an action that performs a search operation on Instagram.
 * The action attempts to search for a user profile by navigating through the platform's UI,
 * utilizing both elements like the profile span and the search input field. If any element is not found,
 * the class attempts a direct search and handles login actions if necessary.
 *
 * This class implements the `Actions` interface, which likely defines the `handleAction()` method
 * to perform the action when invoked. It also implements the `HumanizerTools` interface, which
 * may provide additional utility methods for simulating human-like interactions (e.g., adding delays).
 */
public class SearchAction implements Actions, HumanizerTools {

    Logger logger = LoggerFactory.getLogger(getName());
    private final String user;

    /**
     * Constructs a SearchAction with the specified username to search for.
     *
     * @param user the username to search for on Instagram
     */
    public SearchAction(String user){
        this.user = user;
    }

    /**
     * Executes the search action by performing a sequence of steps:
     * - Checks if the profile span is present to ensure the user is logged in.
     * - If not, attempts to log in by registering a LoginAction.
     * - Starts the search process either via the search span or by performing a direct search.
     *
     * This method handles errors by retrying or invoking alternative methods if elements are not found.
     */
    @Override
    public void handleAction() {
        siteDelay();

        // Check if profile span exists, indicating that the user is logged in.
        if (!checkProfile()) {
            logger.error("Profile span not found. Checking again after switching site.");
            getSeleniumManager().open("https://instagram.com/");
            siteDelay();
            if (!checkProfile()) {
                logger.error("Profile span not found. Trying to login.");
                // Register the login action if the profile is still not found.
                Seleniagram.actionThreadManager.registerActions(new LoginAction());
                return;
            }
        }

        // Proceed with the search if logged in.
        startSearch();
    }

    /**
     * Initiates the search by interacting with the search span and input fields.
     * It enters the provided username character by character into the search input.
     * If elements are not found, it falls back to a direct search method.
     */
    private void startSearch() {
        WebElement searchSpan;
        try {
            // Try to find the search span and click it.
            searchSpan = getSeleniumManager().findSpanByText(getLanguageHolder().getSearchSpan());
        } catch (SeleniagramNoSuchElementException e) {
            logger.error("No span found. Starting direct search");
            directSearch();
            return;
        }
        searchSpan.click();
        inputDelay();

        WebElement searchInput;
        try {
            // Try to find the search input field and type the username.
            searchInput = getSeleniumManager().findInputByPlaceholder(getLanguageHolder().getSearchSpan());
        } catch (SeleniagramNoSuchElementException e) {
            logger.error("No input found. Starting direct search");
            directSearch();
            return;
        }

        for (char c : user.toCharArray()) {
            searchInput.sendKeys(String.valueOf(c));
            inputDelay();
        }

        siteDelay();

        WebElement userSpan;
        try {
            // Attempt to find and click on the user's profile span.
            userSpan = getSeleniumManager().findSpanByText(user);
        } catch (SeleniagramNoSuchElementException e) {
            logger.error("No user span found. Starting direct search");
            directSearch();
            return;
        }
        userSpan.click();
        inputDelay();
    }

    /**
     * Placeholder method for performing a direct search if elements cannot be found.
     * This method currently does not implement any specific functionality but can be extended.
     */
    protected void directSearch() {
        InstaUser instaUser = new InstaUser(user);
        getSeleniumManager().open(instaUser.getUserURL());
        siteDelay();
    }

    /**
     * Checks if the profile span is visible, indicating the user is logged in.
     *
     * @return true if the profile span is found, false otherwise
     */
    private boolean checkProfile() {
        WebElement profile;
        try {
            profile = getSeleniumManager().findSpanByText(getLanguageHolder().getProfileSpan());
        } catch (SeleniagramNoSuchElementException e) {
            return false;
        }
        return profile != null;
    }

    /**
     * Gets the SeleniumManager instance responsible for interacting with the browser.
     *
     * @return the SeleniumManager instance
     */
    private SeleniumManager getSeleniumManager() {
        return Seleniagram.actionThreadManager.getSeleniumManager();
    }

    /**
     * Gets the LanguageHolder instance containing language-specific labels for UI elements.
     *
     * @return the LanguageHolder instance
     */
    private LanguageHolder getLanguageHolder() {
        return Seleniagram.languageHolder;
    }

    /**
     * Gets the name of this action, used for logging and identification purposes.
     *
     * @return the name of the action, "SearchAction"
     */
    @Override
    public String getName() {
        return "SearchAction";
    }
}