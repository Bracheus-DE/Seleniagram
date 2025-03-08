package eu.hobbydev.bracheus;


import eu.hobbydev.bracheus.manager.SeleniumManager;

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
 * The Seleniagram class is the entry point of the application.
 * It initializes a SeleniumManager and starts its execution.
 * This class serves as the main class to launch the program, where it initiates the necessary setup and begins the Selenium tasks.
 */
public class Seleniagram {

    /**
     * The main method that serves as the entry point to the application.
     * It creates an instance of SeleniumManager and starts its operation.
     *
     * @param args command-line arguments (unused in this implementation).
     */
    public static void main(String[] args) {
        SeleniumManager seleniumManager = new SeleniumManager();
        seleniumManager.start();
    }
}

