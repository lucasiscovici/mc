package services.utils;

import java.io.IOException;

/**
 * Interface de nos services
 */
public interface ServiceKoko {
	/**
	 * Méthode appelé pour chaque service heritant de Service ( y mettre notre service)
	 * @throws IOException 
	 */
	public void koko() throws IOException;
}
