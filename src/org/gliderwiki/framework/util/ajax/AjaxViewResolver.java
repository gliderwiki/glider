package org.gliderwiki.framework.util.ajax;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class AjaxViewResolver extends AbstractCachingViewResolver{
	
	private String ajaxPrefix;
	private View ajaxView;

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		View view = null;
		if(viewName.startsWith(this.ajaxPrefix)){
			view =  ajaxView;
		}
		return view;
	}

	public String getAjaxPrefix() {
		return ajaxPrefix;
	}

	public void setAjaxPrefix(String ajaxPrefix) {
		this.ajaxPrefix = ajaxPrefix;
	}

	public View getAjaxView() {
		return ajaxView;
	}

	public void setAjaxView(View ajaxView) {
		this.ajaxView = ajaxView;
	}
	
}
