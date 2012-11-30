package org.gliderwiki.rest.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;

import org.gliderwiki.rest.bean.Employee;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;


public class AtomUtil {

	public static Map<String, Object> marshallerProperties;
	
	static {
		marshallerProperties = new HashMap<String, Object>();
		marshallerProperties.put("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	}
	
	public static Feed employeeFeed(List<Employee> employees, Jaxb2Marshaller marshaller) {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("Employee Atom Feed");
		
		List<Entry> entries = new ArrayList<Entry>();
		for(Employee e : employees) {
			StreamResult result = new StreamResult(new ByteArrayOutputStream());
			
			marshaller.setMarshallerProperties(marshallerProperties);
			marshaller.marshal(e, result);
			String xml = result.getOutputStream().toString();
			
			Entry entry = new Entry();
			entry.setId(Long.valueOf(e.getId()).toString());
			entry.setTitle(e.getName());
			Content content = new Content();
			content.setType(Content.XML);
			content.setValue(xml);
			
			List<Content> contents = new ArrayList<Content>();
			contents.add(content);
			entry.setContents(contents);
			
			entries.add(entry);
		}
		feed.setEntries(entries);
		return feed;
	}
}
