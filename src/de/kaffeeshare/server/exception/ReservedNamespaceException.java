package de.kaffeeshare.server.exception;

public class ReservedNamespaceException extends RuntimeException {

	private static final long serialVersionUID = 2826030527124116551L;
	
	public ReservedNamespaceException() {
		super("Trying to use a reserved namespace!");
	}

}
