package org.autenticador;

import java.security.PrivilegedActionException;

public abstract class AutenticacaoException extends Exception {
    protected String login;
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public AutenticacaoException() {

    }

    public String getLogin() {
        return login;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AutenticacaoException(String message, String login) {
        super(message);
        this.login = login;
    }


}
