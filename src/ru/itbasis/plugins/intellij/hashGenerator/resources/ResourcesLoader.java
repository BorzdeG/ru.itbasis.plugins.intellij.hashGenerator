package ru.itbasis.plugins.intellij.hashGenerator.resources;

import com.intellij.openapi.diagnostic.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourcesLoader {
	private static final Logger LOG = Logger.getInstance(ResourcesLoader.class);

	private static volatile ResourceBundle _bundle;
	private static final String LOCALE_RESOURCES_PKG = "ru.itbasis.plugins.intellij.hashGenerator.resources.i18n.messages";

	private ResourcesLoader() {
	}

	public static String getString(final String key) {
		try {
			if (_bundle == null) {
				getResourceBundle();
			}
			return _bundle.getString(key);
		} catch (MissingResourceException e) {
			throw new MissingResourceException("Missing Resource: " + Locale.getDefault() + " - key: " + key +
			                                   " - resources: " + LOCALE_RESOURCES_PKG, LOCALE_RESOURCES_PKG, key);
		} catch (NullPointerException e) {
			throw new NullPointerException("No bundle set: use ResourcesLoader.getResourceBundle().getString(...)");
		}
	}

	public static ResourceBundle getResourceBundle() {
		LOG.info("Loading locale properties for '" + Locale.getDefault() + ')');

		if (_bundle != null) {
			return _bundle;
		}

		//noinspection UnusedCatchParameter
		try {
			_bundle = ResourceBundle.getBundle(LOCALE_RESOURCES_PKG, Locale.getDefault());
		} catch (MissingResourceException e) {
			throw new MissingResourceException("Missing Resource bundle: " + Locale.getDefault() + ' ',
			                                   LOCALE_RESOURCES_PKG,
			                                   "");
		}

		return _bundle;
	}
}
