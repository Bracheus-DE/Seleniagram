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
public interface HumanizerTools {

    Logger humanizer = LoggerFactory.getLogger(HumanizerTools.class);

    default void inputDelay(){
        humanizer.info("Adding input delay between form fields!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    default void siteDelay(){
        humanizer.info("Adding a short delay after visiting a new Site!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    default boolean checkingActionQueue(){
        return (Seleniagram.actionThreadManager.size() == 0);
    }

}
