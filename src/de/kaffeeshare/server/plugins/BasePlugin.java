/*******************************************************************************
 * Copyright 2013 Jens Breitbart, Daniel Klemm, Dennis Obermann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.kaffeeshare.server.plugins;

import java.net.URL;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;

import de.kaffeeshare.server.datastore.Item;

/**
 * Abstract base class for all plugins.
 */
public abstract class BasePlugin {

	protected static final Logger log = Logger.getLogger(BasePlugin.class.getName());
	
	/**
	 * Check URL for a matching.
	 * @param url URL
	 * @return true, if plugin match otherwise false
	 */
	public abstract boolean match(URL url);
	
	/**
	 * Create an item.
	 * @param url URL
	 * @return Item
	 */
	public abstract Item createItem(URL url);

	/**
	 * Get the property content.
	 * @param doc Document
	 * @param prop Property
	 * @return Caption
	 */
	protected String getProperty(Document doc, String prop) {
		String caption;
		caption = doc.getElementsByAttributeValue("property", prop)
		             .first().attr("content");
		return caption;
	}	
}
