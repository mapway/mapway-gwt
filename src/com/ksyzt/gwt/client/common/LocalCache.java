package com.ksyzt.gwt.client.common;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

public class LocalCache {
	private final static Storage localStorage =Storage.getLocalStorageIfSupported();

	public static StorageMap getCache() {
		if (localStorage == null) {
			return null;
		} else {
			return new StorageMap(localStorage);
		}
	}
}
