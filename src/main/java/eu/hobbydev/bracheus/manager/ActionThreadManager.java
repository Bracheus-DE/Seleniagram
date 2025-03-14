package eu.hobbydev.bracheus.manager;

import eu.hobbydev.bracheus.interfaces.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
 * The ActionThreadManager class is responsible for managing and executing actions in a separate thread.
 * It processes actions from a queue and handles them using the provided SeleniumManager instance.
 * This class extends the Thread class to run in the background, processing actions asynchronously.
 *
 * <p>It supports action registration and ensures thread-safe interaction with the action queue.
 * The thread can be stopped by invoking the stopThread method, which safely terminates the running thread.</p>
 */
public class ActionThreadManager extends Thread {

    private Logger logger = LoggerFactory.getLogger(ActionThreadManager.class);
    private final BlockingQueue<Actions> actions = new LinkedBlockingQueue<>();
    private boolean running = true;
    private SeleniumManager seleniumManager;

    /**
     * Constructs a new ActionThreadManager with the given SeleniumManager.
     *
     * @param seleniumManager the SeleniumManager instance that will be used to handle actions.
     */
    public ActionThreadManager(SeleniumManager seleniumManager) {
        this.seleniumManager = seleniumManager;
    }

    /**
     * Runs the thread that continuously processes actions from the action queue.
     * Each action is handled by calling its {@link Actions#handleAction()} method.
     * The thread continues to run until the stopThread method is called.
     */
    @Override
    public void run() {
        while (running) {
            try {
                isHandeling = false;
                Actions action = this.actions.take();
                isHandeling = true;
                logger.info("Handeling action: {}", action.getName());
                action.handleAction();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Stops the thread from running by setting the running flag to false and interrupting the thread.
     */
    public void stopThread() {
        logger.info("Stopping ActionThreadManager!");
        running = false;
        this.interrupt();
    }

    /**
     * Registers an action to be processed by the thread. The action is added to the action queue.
     * If the thread is interrupted while adding the action to the queue, the interrupt status is preserved.
     *
     * @param action the action to be registered.
     */
    public void registerActions(Actions action) {
        try {
            this.actions.put(action);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Returns the SeleniumManager instance associated with this ActionThreadManager.
     *
     * @return the SeleniumManager instance used for handling actions.
     */
    public SeleniumManager getSeleniumManager() {
        return seleniumManager;
    }

    /**
     * Returns the size() of actions
     *
     * @return size() of actions
     */
    public int size() {
        return actions.size();
    }

    private boolean isHandeling = false;

    public boolean getIsHandeling() {
        return isHandeling;
    }
}