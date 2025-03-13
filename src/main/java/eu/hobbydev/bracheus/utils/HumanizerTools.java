package eu.hobbydev.bracheus.utils;

import eu.hobbydev.bracheus.Seleniagram;
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
 * The `HumanizerTools` interface defines methods for introducing delays between actions
 * and for checking the status of the action queue in the Seleniagram bot.
 *
 * This interface is intended to simulate human-like pauses during automation tasks to avoid
 * detection and to make the bot's behavior more natural. The delays are introduced between
 * actions (e.g., input delays and site delays) to mimic human interaction speeds.
 */
public interface HumanizerTools {

    /**
     * Introduces a delay to simulate human-like input timing.
     * The delay lasts for 500 milliseconds.
     *
     * This method is called to add a short pause before or after user input actions,
     * simulating a human's typing delay.
     */
    default void inputDelay(){
        try {
            Thread.sleep(500);  // 500 milliseconds delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);  // Handle interruption during sleep
        }
    }

    /**
     * Introduces a delay between site interactions.
     * The delay lasts for 5000 milliseconds (5 seconds).
     *
     * This method is typically used to add a longer pause between actions on different pages
     * or sections of a site, simulating a more natural browsing experience.
     */
    default void siteDelay(){
        try {
            Thread.sleep(5000);  // 5000 milliseconds (5 seconds) delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);  // Handle interruption during sleep
        }
    }

    /**
     * Checks whether the action queue is currently being handled.
     *
     * @return `true` if the action queue is being processed, `false` otherwise.
     *
     * This method is used to monitor the state of the action thread and determine
     * if the bot is in the middle of executing a set of actions.
     */
    default boolean checkingActionQueue(){
        return (Seleniagram.actionThreadManager.getIsHandeling());
    }

}
