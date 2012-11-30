package org.gliderwiki.framework.util.ajax;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class JsonViewResolver extends AbstractCachingViewResolver{
	
	private String jsonPrefix;
	private View jsonView;

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		View view = null;
		if(viewName.startsWith(this.jsonPrefix)){
			view =  jsonView;
		}
		return view;
	}

	public String getJsonPrefix() {
		return jsonPrefix;
	}

	public void setJsonPrefix(String jsonPrefix) {
		this.jsonPrefix = jsonPrefix;
	}

	public View getJsonView() {
		return jsonView;
	}

	public void setJsonView(View jsonView) {
		this.jsonView = jsonView;
	}	
}
