package com.example.rest;

import java.io.StringWriter;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.XmlReader;

@Path("/atom")
public class AtomFeedResource {

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getRecentFeed() {
		SyndFeed feed = null;
		final StringWriter stringWriter = new StringWriter();

		try {

			URL feedUrl = new URL(
					"http://feeds.bbci.co.uk/news/magazine/rss.xml");

			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(new XmlReader(feedUrl));
			
			
			feed.setTitle("Test Feed created from bbc feed");

			WireFeed wireFeed = feed.createWireFeed();
			WireFeedOutput output = new WireFeedOutput();
			
			output.output(wireFeed, stringWriter);

			
		} catch (Exception e) {
			throw new WebApplicationException();
		}
		
		return Response.ok(stringWriter.toString())
				.type(MediaType.APPLICATION_ATOM_XML).header("Cache-Control","max-age=3600").build();
	}
}
