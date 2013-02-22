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

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.kaffeeshare.server.datastore.DatastoreManager;
import de.kaffeeshare.server.datastore.Item;
import de.kaffeeshare.server.exception.SystemErrorException;

/**
 * Plugin to handle youtube pages.
 */
public class Youtube extends BasePlugin {

	@Override
	public boolean match(URL url) {
		String str = url.toString();
		return (str.startsWith("http://www.youtube.com/") || str.startsWith("https://www.youtube.com/"));
	}

	@Override
	public Item createItem(URL url) {
		log.info("Running Youtube plugin!");

		Document doc;
		try {
			doc = Jsoup.parse(url, 10000);
		} catch (IOException e1) {
			throw new SystemErrorException();
		}

		String videoId = null;
		try {
			Elements elements = doc.getElementsByTag("link");
			for (Element el: elements) {
				if (el.hasAttr("rel")) {
					if (el.attr("rel").equalsIgnoreCase("canonical")) {
						videoId = el.attr("href").replace("/watch?v=", "");
					}
				}
			}
		} catch (Exception e) {
		}

		String caption = null;

		try {
			caption = getProperty(doc, "og:title");
		} catch (Exception e) {
		}

		if (caption == null) {
			try {
				caption = doc.select("title").first().text();
			} catch (Exception e) {
				caption = "";
			}
		}

		log.info("caption: " + caption);

		String description = null;

		try {
			description = getProperty(doc, "og:description");
		} catch (Exception e) {
		}

		if (description == null) {
			try {
				description = doc
						.getElementsByAttributeValue("name", "description")
						.first().attr("content");
			} catch (Exception e) {
				description = "";
			}
		}

		if (videoId != null) {
			description += "<br /><br /><br /><iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/"
					+ videoId
					+ "\" frameborder=\"0\" allowfullscreen></iframe>";
		}

		log.info("desc: " + description);

		String imageUrl = "";
		
		String urlString = null;
		try {
			urlString = getProperty(doc, "og:url");
		} catch (Exception e) {
			urlString = url.toString();
		}

		return DatastoreManager.getDatastore().createItem(caption,urlString, description, imageUrl);
	}

}
