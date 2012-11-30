package org.gliderwiki.rest;

import java.io.StringReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.gliderwiki.rest.bean.Employee;
import org.gliderwiki.rest.bean.EmployeeList;
import org.gliderwiki.rest.ds.EmployeeDS;
import org.gliderwiki.rest.util.AtomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.sun.syndication.feed.atom.Feed;

@Controller
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmployeeDS employeeDS;
	
	public void setEmployeeDS(EmployeeDS ds) {
		this.employeeDS = ds;
	}
	
	private Jaxb2Marshaller jaxb2Mashaller;
	
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}

	private static final String XML_VIEW_NAME = "employees";
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/employee/{id}")
	@ResponseBody
	public Employee getEmployee(@PathVariable String id) {
		logger.debug("@@@@@ employee : " + id); 
		Employee e = employeeDS.get(Long.parseLong(id));
		
		return e;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employee/{id}")
	public ModelAndView updateEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		employeeDS.update(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee")
	public ModelAndView addEmployee(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		employeeDS.add(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee/input/{id}")
	@ResponseBody
	public String addPostId(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("id : " + id);
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		logger.debug("name : " + name);
		logger.debug("password : " + password);
		return id;
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
	public ModelAndView removeEmployee(@PathVariable String id) {
		employeeDS.remove(Long.parseLong(id));
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/employees")
	public ModelAndView getEmployees() {
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return new ModelAndView(XML_VIEW_NAME, "employees", list);
	}
	
	////////////////////////// @ResponseBody ////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/emp/{id}", headers="Accept=application/xml, application/json")
	public @ResponseBody Employee getEmp(@PathVariable String id) {
		Employee e = employeeDS.get(Long.parseLong(id));
		return e;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/emps", headers="Accept=application/xml, application/json")
	public @ResponseBody EmployeeList getAllEmp() {
		logger.debug("#emps start!");
		List<Employee> employees = employeeDS.getAll();
		logger.debug("#employees : " + employees.toString());
		EmployeeList list = new EmployeeList(employees);
		return list;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/emps", headers="Accept=application/atom+xml")
	public @ResponseBody Feed getEmpFeed() {
		logger.debug("#getEmpFeed Start");
		
		List<Employee> employees = employeeDS.getAll();
		return AtomUtil.employeeFeed(employees, jaxb2Mashaller);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/emp")
	public @ResponseBody Employee addEmp(@RequestBody Employee e) {
		employeeDS.add(e);
		return e;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/emp/{id}")
	public @ResponseBody Employee updateEmp(@RequestBody Employee e, @PathVariable String id) {
		employeeDS.update(e);
		return e;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/emp/{id}")
	public @ResponseBody void removeEmp(@PathVariable String id) {
		employeeDS.remove(Long.parseLong(id));
	}
	
}
