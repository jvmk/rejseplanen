package com.varmarken.rejseplanen.afgangstavlen.network;

import java.io.InputStream;

/**
 * This interface may be used by a client to declare that it will attempt to
 * parse the contents of an {@link InputStream} to an instance of a given type.
 * 
 * @author Janus Varmarken
 * 
 * @param <RESULT>
 */
public interface IInputStreamParser<RESULT> {

	/**
	 * 
	 * @param stream
	 *            An {@link InputStream} that should be parsed checking if it
	 *            contains an instance of the type parameter.
	 * @return The parsed instance.
	 * @throws Exception
	 *             If an error occurs while reading from {@code stream} or if
	 *             the contents of the {@code stream} cannot be converted to an
	 *             instance of the given type parameter.
	 */
	RESULT parseStream(InputStream stream) throws Exception;

}
