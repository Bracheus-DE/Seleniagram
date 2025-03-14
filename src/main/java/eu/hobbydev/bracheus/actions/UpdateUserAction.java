package eu.hobbydev.bracheus.actions;


import eu.hobbydev.bracheus.Seleniagram;
import eu.hobbydev.bracheus.classes.InstaUser;
import eu.hobbydev.bracheus.interfaces.Actions;
import eu.hobbydev.bracheus.manager.SeleniumManager;
import eu.hobbydev.bracheus.manager.UserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
 * The {@code UpdateUserAction} class is responsible for updating user data for both regular Instagram users
 * and the Seleniagram user. It handles the process of searching for the user, retrieving their data,
 * and updating the relevant user information in the system.
 * <p>
 * This class extends the {@code SearchAction} class and implements the {@code Actions} interface. It provides
 * functionality to update either a regular Instagram user or the Seleniagram user based on the input parameters.
 * </p>
 *
 * @see SearchAction
 * @see Actions
 */
public class UpdateUserAction extends SearchAction implements Actions {

    private Logger logger = LoggerFactory.getLogger(UpdateUserAction.class);
    private final boolean seleniagramUser;
    private final String user;

    /**
     * Constructs an {@code UpdateUserAction} for the specified user with the default flag for Seleniagram user as false.
     *
     * @param user The username of the user whose data needs to be updated.
     */
    public UpdateUserAction(String user) {
        this(user, false);
    }

    /**
     * Constructs an {@code UpdateUserAction} for the specified user, with an option to specify whether the user is
     * a Seleniagram user.
     *
     * @param user            The username of the user whose data needs to be updated.
     * @param seleniagramUser A flag indicating whether the user is a Seleniagram user (true) or not (false).
     */
    public UpdateUserAction(String user, boolean seleniagramUser) {
        super(user);
        this.user = user;
        this.seleniagramUser = seleniagramUser;
    }

    /**
     * Handles the action of updating the user's data. It performs the following steps:
     * <ul>
     * <li>Performs a direct search for the user.</li>
     * <li>Retrieves the user's data.</li>
     * <li>If the user is a regular Instagram user, updates the user information in the system.</li>
     * <li>If the user is a Seleniagram user, updates the Seleniagram user information in the system.</li>
     * </ul>
     * <p>
     * If the data is not found or is incomplete, an error is logged.
     */
    @Override
    public void handleAction() {
        directSearch();
        List<String> data = getUserData();

        if (data == null) {
            logger.error("Data not found. Try again later!");
            return;
        }

        if (seleniagramUser) {
            updateSeleniagramUser(data);
        } else {
            updateUser(data);
        }
    }

    /**
     * Retrieves the user data from the web page. It extracts the user's posts, followers, and following information.
     * If the required data is not found or is incomplete, an error is logged.
     *
     * @return A list of strings containing the user's data (posts, followers, following), or {@code null} if
     * an error occurs or the data is incomplete.
     */
    private List<String> getUserData() {
        List<String> data = new ArrayList<>();

        try {
            List<WebElement> stats = getSeleniumManager().findElementsByXpath("//ul[contains(@class, 'x78zum5')]/li");

            if (stats.size() == 3) {
                for (int i = 0; i < 3; i++) {
                    String xpath = ".//div/a/span/span[contains(@class, 'x5n08af')]/span";
                    if (i == 0) {
                        xpath = ".//div/span/span[contains(@class, 'x5n08af')]/span";
                    }
                    WebElement span = stats.get(i).findElement(By.xpath(xpath));
                    String text = span.getText();
                    if (text != null && !text.isEmpty()) {
                        data.add(text);
                        System.out.println(text);
                    } else {
                        logger.warn("Empty text found for span element!");
                    }
                }
            } else {
                logger.warn("Can't find all infos! Expected: 3, Found: " + stats.size());
            }
        } catch (NoSuchElementException e) {
            logger.error("Problem getting userinfo: " + e.getMessage(), e);
            return null;
        }

        return data;
    }

    /**
     * Updates the data for a regular Instagram user. The user's posts, subscribers, and subscriptions are updated.
     * If the user is already known, their data is updated, and they are re-registered. If the user is not known, a new
     * user is created and registered.
     *
     * @param data The list of data containing the user's posts, subscribers, and subscriptions.
     */
    private void updateUser(List<String> data) {
        if (data.size() != 3) {
            logger.error("Data is not complete. Try again later!");
            return;
        }
        if (getUserManager().isKnownInstaUser(user)) {
            InstaUser instaUser = getUserManager().getKnownInstaUser(user);
            boolean followed = false;

            if (getUserManager().isFollowedInstaUser(instaUser)) {
                followed = true;
                getUserManager().unregisterFollowedInstaUser(instaUser);
            }
            getUserManager().unregisterInstaUser(instaUser);
            instaUser.setPosts(Integer.parseInt(data.getFirst()));
            logger.info("Found {} posts!", data.getFirst());
            instaUser.setSubs(Integer.parseInt(data.get(1)));
            logger.info("Found {} subs!", data.get(1));
            instaUser.setSubbed(Integer.parseInt(data.get(2)));
            logger.info("Found {} subbed accounts!", data.get(2));
            logger.info("Updated InstaUser {}!", instaUser.getUsername());
            getUserManager().registerInstaUser(instaUser);
            logger.info("Registered InstaUser {}!", instaUser.getUsername());
            if (followed) {
                logger.info("Registered followed InstaUser {}!", instaUser.getUsername());
            }
            return;
        }
        InstaUser instaUser = new InstaUser(user);
        instaUser.setPosts(Integer.parseInt(data.getFirst()));
        logger.info("Found {} posts!", data.getFirst());
        instaUser.setSubs(Integer.parseInt(data.get(1)));
        logger.info("Found {} subs!", data.get(1));
        instaUser.setSubbed(Integer.parseInt(data.getLast()));
        logger.info("Found {} subbed accounts!", data.getLast());
        logger.info("Updated InstaUser {}!", instaUser.getUsername());
        getUserManager().registerInstaUser(instaUser);
        logger.info("Registered InstaUser {}!", instaUser.getUsername());
    }

    /**
     * Updates the data for the Seleniagram user. The user's posts, subscribers, and subscriptions are updated.
     *
     * @param data The list of data containing the Seleniagram user's posts, subscribers, and subscriptions.
     */
    private void updateSeleniagramUser(List<String> data) {
        if (data.size() == 3) {
            getUserManager().getSeleniagramUser().setPosts(Integer.parseInt(data.getFirst()));
            logger.info("Found {} posts!", data.getFirst());
            getUserManager().getSeleniagramUser().setSubs(Integer.parseInt(data.get(1)));
            logger.info("Found {} subs!", data.get(1));
            getUserManager().getSeleniagramUser().setSubbed(Integer.parseInt(data.getLast()));
            logger.info("Found {} subbed accounts!", data.getLast());
            logger.info("Updated SeleniagramUser {}!", getUserManager().getSeleniagramUser().getUsername());
            return;
        }
        logger.error("Data is not complete. Try again later! Data size: {}", data.size());
    }

    /**
     * Returns the name of the action. This name is used for logging and tracking purposes.
     *
     * @return The name of the action as a string ("UpdateUserAction").
     */
    @Override
    public String getName() {
        return "UpdateUserAction";
    }

    /**
     * Retrieves the {@link SeleniumManager} instance associated with this action.
     *
     * @return The {@link SeleniumManager} instance.
     */
    private SeleniumManager getSeleniumManager() {
        return Seleniagram.actionThreadManager.getSeleniumManager();
    }

    /**
     * Retrieves the {@link UserManager} instance used for managing user data.
     *
     * @return The {@link UserManager} instance.
     */
    private UserManager getUserManager() {
        return Seleniagram.userManager;
    }
}