package eu.hobbydev.bracheus.actions;


import eu.hobbydev.bracheus.Seleniagram;
import eu.hobbydev.bracheus.interfaces.Actions;

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
 * The {@code StoppingSeleniagramAction} class implements the {@link Actions} interface and provides
 * functionality to stop the running threads within the Seleniagram system. This action stops both
 * the listener and action threads when executed.
 * <p>
 * The class defines the name of the action as "Stoppingaction" and implements the {@code handleAction}
 * method to invoke the stop functionality of the respective threads.
 * </p>
 */
public class StoppingSeleniagramAction implements Actions {

    private static final String name = "Stoppingaction";

    /**
     * Executes the stopping action by halting the listener thread and the action thread.
     * This method will stop the threads by calling the stop methods of
     * {@link Seleniagram#listenerThreadManager} and {@link Seleniagram#actionThreadManager}.
     */
    @Override
    public void handleAction() {
        Seleniagram.listenerThreadManager.stopManager();
        Seleniagram.actionThreadManager.stopThread();
    }

    /**
     * Returns the name of this action.
     *
     * @return the name of the action as a String.
     */
    @Override
    public String getName() {
        return name;
    }
}
