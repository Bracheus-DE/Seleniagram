package eu.hobbydev.bracheus.manager;


import eu.hobbydev.bracheus.interfaces.Listener;
import eu.hobbydev.bracheus.utils.HumanizerTools;
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
 * The ListenerThreadManager class is responsible for managing and executing listeners in a separate thread.
 * This class extends the Thread class and continuously checks each registered listener at regular intervals.
 * The listeners perform specific checks and actions as defined by their implementation.
 *
 * <p>The thread can be stopped gracefully by calling the stopManager method, which halts the thread's execution.</p>
 */
public class ListenerThreadManager extends Thread implements HumanizerTools {
    private Logger logger = LoggerFactory.getLogger(ListenerThreadManager.class);
    private List<Listener> listeners;
    private volatile boolean running = true;
    private SeleniumManager seleniumManager;

    /**
     * Constructs a new ListenerThreadManager with the specified SeleniumManager.
     * This manager will handle the execution of the listeners.
     *
     * @param seleniumManager the SeleniumManager instance that may be used for actions within the listeners.
     */
    public ListenerThreadManager(SeleniumManager seleniumManager) {
        this.seleniumManager = seleniumManager;
        this.listeners = new ArrayList<>();
    }

    /**
     * Adds a listener to the list of listeners. This method is synchronized to ensure thread-safety when adding listeners.
     *
     * @param listener the listener to be added to the list.
     */
    public synchronized void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * Stops the listener thread by setting the running flag to false and interrupting the thread.
     * This will terminate the execution of the listener checks.
     */
    public void stopManager() {
        logger.info("Stopping ListenerThreadManager!");
        running = false;
        this.interrupt();
    }

    /**
     * Runs the thread that continuously checks each registered listener.
     * It invokes the {@link Listener#runCheckup()} method for each listener and waits for a defined period before repeating.
     * If no listeners are registered, the thread will wait for 30 seconds before checking again.
     */
    @Override
    public void run() {
        while (running) {
            if (!this.listeners.isEmpty()) {
                if(checkingActionQueue()) {
                    for (Listener listener : this.listeners) {
                        logger.info("Running Checkup for Listener: {}", listener.getName());
                        listener.runCheckup();
                        try {
                            // Sleep for 2 second between checks
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    logger.info("After hard work, programm is getting sleepy for a few secs");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.info("Humanizer was blocking the Queue. Mostly happens if Actions are up!");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                try {
                    logger.info("No Listener found. Getting sleepyyy!");
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Returns the SeleniumManager instance associated with this ListenerThreadManager.
     *
     * @return the SeleniumManager instance used for handling listener.
     */
    public SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }
}