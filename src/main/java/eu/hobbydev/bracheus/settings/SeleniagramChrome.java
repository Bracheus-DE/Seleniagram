package eu.hobbydev.bracheus.settings;


import org.openqa.selenium.chrome.ChromeOptions;

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
 * The SeleniagramChrome class provides a method to configure and return ChromeOptions
 * for running Google Chrome in a headless mode with additional configurations for Selenium automation.
 * These configurations help to simulate a regular browser environment and avoid detection as a bot.
 */
public class SeleniagramChrome {

    // Constants for Chrome options
    private static final String SANDBOX = "--no-sandbox"; // Disables the sandbox for running Chrome.
    private static final String DEV = "--disable-dev-shm-usage"; // Disables /dev/shm usage for certain environments.
    private static final String HIDE_BOT = "--disable-blink-features=AutomationControlled"; // Prevents detection of Selenium automation.
    private static final String USER_AGENT = "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"; // Sets the user-agent string to mimic a real user.
    private static final String CHROME_BINARY = "C:\\chromedriver\\chrome.exe"; // Path to the Chrome executable.

    private static final String REMOTE_DEBUGGING_PORT = "--remote-debugging-port=9222"; // Enables remote debugging on a specific port.
    private static final String FORCE_SCALE_FACTOR = "--force-device-scale-factor=1"; // Ensures proper scaling of UI elements.
    private static final String DISABLE_POPUP_BLOCKING = "--disable-popup-blocking"; // Disables Chrome's popup blocking feature.
    private static final String DISABLE_INFOBARS = "--disable-infobars"; // Disables Chrome's infobars (e.g., "Chrome is being controlled by automated test software").


    /**
     * Configures and returns a ChromeOptions object with the necessary arguments for running Chrome
     * in a headless mode and with additional settings to prevent bot detection.
     *
     * @return a configured ChromeOptions object.
     */
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // Adding necessary Chrome options
        chromeOptions.addArguments("--remote-allow-origins=*"); // Ensures compatibility with cross-origin requests.
        chromeOptions.addArguments(FORCE_SCALE_FACTOR);  // Prevents scaling issues in the UI.
        chromeOptions.addArguments(DISABLE_POPUP_BLOCKING);  // Ensures popups are not blocked.
        chromeOptions.addArguments(DISABLE_INFOBARS);  // Hides infobars that indicate Selenium automation.
        chromeOptions.addArguments(SANDBOX);  // Disables sandboxing (useful for certain environments).
        chromeOptions.addArguments(DEV);  // Disables shared memory usage that may cause issues in some environments.
        chromeOptions.addArguments(HIDE_BOT);  // Hides the bot signature to avoid detection.
        chromeOptions.addArguments(USER_AGENT);  // Sets the browser's user-agent to mimic a real user.
        chromeOptions.setBinary(CHROME_BINARY);  // Specifies the location of the Chrome executable.
        chromeOptions.addArguments(REMOTE_DEBUGGING_PORT);  // Enables remote debugging to allow interaction with Chrome.

        return chromeOptions; // Return the configured ChromeOptions object.
    }

}