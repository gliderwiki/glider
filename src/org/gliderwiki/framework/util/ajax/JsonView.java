package org.gliderwiki.framework.util.ajax;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.view.AbstractView;

public class JsonView extends AbstractView{

	protected static final String DEFAULT_ENCODING = "utf-8";

	public JsonView() {
		setContentType("text/plain; charset=utf-8");
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// param 으로 정의된 객체만 꺼내온다. 		
		Object localObject = map.get("param");
		
		JSONObject localJSONObject = null;
		if (localObject != null) {
			localJSONObject = JSONObject.fromObject(localObject);
		} else {	
			Iterator localIterator = map.keySet().iterator();
			String str = null;
			while (localIterator.hasNext()) {
				str = (String) localIterator.next();
				if (!(str.startsWith("org.springframework.validation.BindingResult")))
					continue;
				map.remove(str);
			}
			localJSONObject = JSONObject.fromObject(map);
		}
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().print(localJSONObject.toString());
		
	}
	
}
