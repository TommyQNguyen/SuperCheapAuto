package packageSuperCheapAuto;

import java.util.*;

public class Clients {
	
	private static HashMap<String, Client> listeClients = new HashMap<String, Client>();

	public static HashMap<String, Client> getListe() {
		return listeClients;
	}

	public static Client getClient(String numeroClient) {
		return listeClients.get(numeroClient);
	}

	public static void ajouterClient(Client client) {
		listeClients.put(client.getNumeroClient(), client);
	}

}