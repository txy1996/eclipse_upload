package com.zlt.system.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlt.system.entity.Employee;
import com.zlt.system.service.EmployeeService;
import com.zlt.system.service.impl.EmployeeServiceImpl;
import com.zlt.system.vo.Line;

import javafx.scene.chart.PieChart.Data;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
/**
 * 个人信息设置界面
 * @author 晓煜
 * @date2018年1月25日上午9:18:31
 * @version
 * @explain
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		switch (type) {
		case "update":
			Update(request, response);
			break;
		case "Check":
			CheckPasswoed(request, response);
			break;
		case "shiver":
			EmployeeFindById(request, response);
			break;
		case "ok":
			password(request, response);
			break;
			
		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	
	}
	public void  EmployeeFindById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		String  emp_name=(String) session.getAttribute("username");
		EmployeeService employeeService=new EmployeeServiceImpl();
		String flag=null;
		Employee employee=new Employee(emp_name);
		Line line=employeeService.EmployeeServiceFindById(employee);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(line);
		flag=JSON.toJSONString(jsonArray);
		response.getWriter().write(jsonArray.toString());
		System.out.println(flag);
	}
	public void CheckPasswoed(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		String  emp_name=(String) session.getAttribute("username");
		EmployeeService employeeService=new EmployeeServiceImpl();
		String flag=null;
		String password=request.getParameter("password");
		Employee employee=new Employee(emp_name,password);
		Employee employee2=new Employee();
		employee2=employeeService.EmployeeServiceCheckPasswoed(employee);
		String string="不通过";
		if (employee2!=null) {
			System.out.println(employee2.toString());
			string="通过";
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ok", string);
		flag=JSON.toJSONString(jsonObject);
		response.getWriter().write(jsonObject.toString());
		System.out.println(flag);
		
	}
	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date emp_hiredate=null;Date emp_birth=null;
		try {
			emp_hiredate=format1.parse(request.getParameter("hiredate"));
			 emp_birth = format1.parse(request.getParameter("BirthDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmployeeService employeeService=new EmployeeServiceImpl();
		String emp_id=request.getParameter("JobCode");
		String emp_name=request.getParameter("username");
		String emp_tel=request.getParameter("phone");
		String emp_email=request.getParameter("email");
		String emp_bankcard=request.getParameter("BankAmericard");
		String emp_address=request.getParameter("address");
		String emp_sex=request.getParameter("sex");
		Employee employee=new Employee(emp_id, emp_name, emp_sex, emp_birth, emp_hiredate, emp_tel, emp_address, emp_email, emp_bankcard);
		System.out.println(employee.toString());
		employeeService.EmployeeServiceUpdate(employee);
		
	}
	public void password(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		String new1=request.getParameter("new1");
		HttpSession session=request.getSession();
		String  emp_name=(String) session.getAttribute("username");
		Employee employee=new Employee(emp_name, new1);
		EmployeeService employeeService=new EmployeeServiceImpl();
		boolean flag=false;
		flag=employeeService.EmployeeServiceChangePassword(employee);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("emmm", flag);
		JSON.toJSONString(jsonObject);
		response.getWriter().write(jsonObject.toString());
		
	}
}
