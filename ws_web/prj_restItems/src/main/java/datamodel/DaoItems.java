package datamodel;

import java.util.HashMap;
import java.util.Map;

public enum DaoItems {
	instance;

	private Map<String, Item> contentProvider = new HashMap<String, Item>();

	private DaoItems() {

		// EJERCICIO: Crear algunos items por defecto para las pruebas.
		Item item = new Item("ITEM_01", "Item 1", 10);
		contentProvider.put(item.getId(), item);
		item = new Item("ITEM_02", "Item 2", 20);
		contentProvider.put(item.getId(), item);
		item = new Item("ITEM_03", "Item 3", 30);
		contentProvider.put(item.getId(), item);
		item = new Item("ITEM_04", "Item 4", 40);
		contentProvider.put(item.getId(), item);
		item = new Item("ITEM_05", "Item 5", 50);
		contentProvider.put(item.getId(), item);
	}

	public Map<String, Item> getModel() {
		return contentProvider;
	}
}
