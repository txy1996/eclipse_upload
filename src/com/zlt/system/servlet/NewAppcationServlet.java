package com.zlt.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zlt.system.dao.ReimTypeTabDao;
import com.zlt.system.dao.ReimnumtabDao;
import com.zlt.system.dao.impl.ReimTypeTabDaoImpl;
import com.zlt.system.dao.impl.ReimnumtabDaoImpl;
import com.zlt.system.entity.Hotel;
import com.zlt.system.entity.Other;
import com.zlt.system.entity.ReimTypeTab;
import com.zlt.system.entity.Reimnumtab;
import com.zlt.system.entity.Status;
import com.zlt.system.entity.Traffic;
import com.zlt.system.entity.Travel;
import com.zlt.system.service.NewAppcationService;
import com.zlt.system.service.ReportFormService;
import com.zlt.system.service.impl.NewApplicationServiceImpl;
import com.zlt.system.service.impl.ReportFormServiceImpl;
import com.zlt.system.vo.DepartNameVo;
import com.zlt.system.vo.GetMaxMoneyVo;

/**
 * 该类用来获取从页面传来的报销信息，并进行存储
 * Servlet implementation class NewAppcationServlet
 * @author 米邓勇
 */
@WebServlet("/NewAppcationServlet")
public class NewAppcationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimnumtabDao rd = new ReimnumtabDaoImpl();
	ReimTypeTabDao rt = new ReimTypeTabDaoImpl();
	NewAppcationService na = new NewApplicationServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAppcationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println(type);
		if("other".equals(type)){
			addOther(request, response);
		}else if("hotel".equals(type)){
			addHotel(request, response);
		}else if("travel".equals(type)){
			addTravel(request, response);
		}else if("traffic".equals(type)){
			addTraffic(request, response);
		}else if("find".equals(type)){
			finddepartment(request, response);
		}else if("money".equals(type)){
			findMaxMoney(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 通过员工姓名获取到员工所能申请得到最大报销金额
	 * @2018年1月27日
	 * @void
	 * @findMaxMoney
	 */
	public void findMaxMoney(HttpServletRequest request, HttpServletResponse response){
		String emp = request.getParameter("emp");
		GetMaxMoneyVo money = na.findMaxMoney(emp);
		JSONObject json = new JSONObject();
		json.put("money1", money.getMoney());
		try {
			PrintWriter out = response.getWriter();
			out.print(json.toJSONString());
			System.out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 通过员工信息获取到员工所在的部门信息
	 * @2018年1月27日
	 * @void
	 * @finddepartment
	 */
	public void finddepartment(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		ReportFormService rs = new ReportFormServiceImpl();
		String user = (String) request.getSession().getAttribute("username");
		System.out.println(user);
		DepartNameVo depart = rs.findDepart(user);
		System.out.println(depart.getDepart());
		JSONObject json = new JSONObject();
		json.put("name", user);
		json.put("department", depart.getDepart());
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json.toJSONString());
			System.out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 实现对于差旅报销类型数据的添加纪录
	 * @2018年1月25日
	 * @void
	 * @addTravel
	 */
	public void addTravel(HttpServletRequest request, HttpServletResponse response){
		//差旅表信息插入  ---1
		Travel t = new Travel();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null,date1=null,date2= new Date();
		String date3 = format1.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		try {
			date = format1.parse(request.getParameter("startdate"));
			date1 = format1.parse(request.getParameter("enddate"));
			date2 = format1.parse(date3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//t.setTravel_id(111);
		t.setReim_id(1);
		t.setEmp_name(request.getParameter("emp"));
 		t.setTravel_time(date2);
		t.setTravel_address(request.getParameter("address"));
		t.setTravel_start(date);
		t.setTravel_end(date1);
		t.setTravel_date(Integer.parseInt(request.getParameter("days")));
		t.setTravel_target(request.getParameter("target"));
		t.setTravel_result(request.getParameter("result"));
		t.setTravel_money(Integer.parseInt(request.getParameter("money")));
		t.setTravel_remark(request.getParameter("content"));
		t.setTravel_title(request.getParameter("project"));
		t.setDepartment_name(request.getParameter("department"));
		t.setApp_abb("cc");
		boolean flag1 = na.add(t);
		int status = 0;
		int travel_id = 0 ;
		if(flag1){
			//获取最近插入的报销信息 ---2
			Travel t1 = new Travel();
			t1 = na.findMaxtravel();
			
			//获取该报销信息的具体报销类型 ---3 共同使用
			ReimTypeTab rt = new ReimTypeTab();
			ReimTypeTab rt1 = new ReimTypeTab();
			rt.setReim_id(t1.getReim_id());
			System.out.println(rt);
			rt1 = na.findrei(rt);
			if(t1 != null && rt1 != null){
				//向新增了数据的数据表中修改凭借后的字段名 ---4
				Travel t2 = new Travel();
				t2.setApp_abb(rt1.getReim_abb()+t1.getTravel_id());
				t2.setTravel_id(t1.getTravel_id());
				boolean fl = na.update(t2);

				//设置报销编号表中的报销编号数据 ---4
				Reimnumtab rm = new Reimnumtab();
				rm.setApp_id(t1.getTravel_id());
				rm.setApp_abb( rt1.getReim_abb()+rm.getApp_id());
				boolean flag = na.add(rm);
				
				//向状态表中进行数据添加
				Status status1 = new Status();
				status1.setApp_abb(rt1.getReim_abb()+rm.getApp_id());
				status1.setStatus1("审核中");
				status1.setStatus2("审核中");
				status1.setStatus_result("审核中");
				boolean flag3 = na.addStatus(status1);
				
				if(fl == true && flag == true && flag3 == true ){
					status = 1;
					travel_id = t1.getTravel_id();
				}else{
					System.out.println("指定字段更新失败！");
				}
			}else{
				System.out.println("数据查询失败！");
			}
		}else{
			System.out.println("数据插入错误！");
		}
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("id", travel_id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 实现对与交通类型数据的添加
	 * @2018年1月25日
	 * @void
	 * @addTraffic
	 */
	public void addTraffic(HttpServletRequest request, HttpServletResponse response){
		//交通报销表的数据添加
		Traffic tf = new Traffic();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null,date1=null,date2= null;
		String date3 = format1.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		try {
			date = format1.parse(request.getParameter("startdate"));
			date1 = format1.parse(request.getParameter("enddate"));
			date2 = format1.parse(date3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date2);
		tf.setTraffic_titel(request.getParameter("project"));
		tf.setDepartment_name(request.getParameter("department"));
		tf.setEmp_name(request.getParameter("emp"));
		tf.setApp_abb("cc");
		tf.setReim_id(2);
		tf.setTraffic_address(request.getParameter("address"));
		tf.setTraffic_start(date);
		tf.setTraffic_end(date1);
		tf.setTraffic_money(Integer.parseInt(request.getParameter("money")));
		tf.setTraffic_remark(request.getParameter("content"));
		tf.setTraffic_time(date2);
		tf.setTraffic_type(request.getParameter("travel"));
		boolean flag = na.add(tf);
		int status = 0;
		int traffic_id = 0 ;
		if(flag ==true){
			//获取当前表最近插入的数据
			Traffic tf1 = new Traffic();
			tf1 = na.findMaxtraffic();
			
			//获取该报销信息的具体报销类型 ---3 共同使用
			ReimTypeTab rt = new ReimTypeTab();
			rt.setReim_id(tf1.getReim_id());
			rt = na.findrei(rt);

			if(tf1 != null && rt != null){
				//进行修改交通费用表
				Traffic tf2 = new Traffic();
				tf2.setApp_abb(rt.getReim_abb()+tf1.getTraffic_id());
				tf2.setTraffic_id(tf1.getTraffic_id());
				boolean flag1 = na.update(tf2);
				
				//进行修改报销编号表
				Reimnumtab rm = new Reimnumtab();
				rm.setApp_abb(rt.getReim_abb()+tf1.getTraffic_id());
				rm.setApp_id(tf1.getTraffic_id());
				boolean flag2 = na.add(rm);
				
				//向状态表中进行数据添加
				Status status1 = new Status();
				status1.setApp_abb(rt.getReim_abb()+tf1.getTraffic_id());
				status1.setStatus1("审核中");
				status1.setStatus2("审核中");
				status1.setStatus_result("审核中");
				boolean flag3 = na.addStatus(status1);
				
				if(flag1 == true && flag2 == true && flag3 == true){
					status = 1;
					traffic_id = tf1.getTraffic_id();
				}else{
					System.out.println("指定字段更新失败！");
				}
			}else{
				System.out.println("数据查询失败！");
			}
		}else{
			System.out.println("数据插入错误！");
		}
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("id", traffic_id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 实现对于住宿报销类型数据的添加
	 * @2018年1月25日
	 * @void
	 * @addHotel
	 */
	public void addHotel(HttpServletRequest request, HttpServletResponse response){
		//住宿表信息的添加
		Hotel hotel = new Hotel();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null,date1=null,date2= null;
		String date3 = format1.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		try {
			date = format1.parse(request.getParameter("startdate"));
			date1 = format1.parse(request.getParameter("enddate"));
			date2 = format1.parse(date3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hotel.setDepartment_name(request.getParameter("department"));
		hotel.setEmp_name(request.getParameter("emp"));
		hotel.setHotel_days(Integer.parseInt(request.getParameter("days")));
		hotel.setHotel_first(date);
		hotel.setHotel_finish(date1);
		hotel.setHotel_Other(request.getParameter("other"));
		hotel.setHotel_people(request.getParameter("otherpeople"));
		hotel.setHotel_quarterage(Integer.parseInt(request.getParameter("money")));
		hotel.setHotel_remarks(request.getParameter("content"));
		hotel.setHotel_time(date2);
		hotel.setHotel_site(request.getParameter("address"));
		hotel.setApp_abb("cc");
		hotel.setReim_id(3);
		hotel.setHotel_title(request.getParameter("project"));
		boolean flag = na.add(hotel);
		int status = 0;
		int hotel_id = 0 ;
		if(flag == true){
			//获取当前表中最近插入的数据
			Hotel hotel1 = new Hotel();
			hotel1 = na.findMaxhotel();
			
			//查询当前表的报销类型缩写
			ReimTypeTab type = new ReimTypeTab();
			type.setReim_id(hotel1.getReim_id());
			type = na.findrei(type);

			if(hotel1 != null && type != null){
				//更新报销编号表中的数据
				Reimnumtab rm = new Reimnumtab();
				rm.setApp_id(hotel1.getHotel_id());
				rm.setApp_abb(type.getReim_abb()+hotel1.getHotel_id());
				boolean flag1 = na.add(rm);
				
				//更新当前表中拼接字段的值
				Hotel hotel2 = new Hotel();
				hotel2.setHotel_id(hotel1.getHotel_id());
				hotel2.setApp_abb(type.getReim_abb()+hotel1.getHotel_id());
				boolean flag2 = na.update(hotel2);
				
				//向状态表中进行数据添加
				Status status1 = new Status();
				status1.setApp_abb(type.getReim_abb()+hotel1.getHotel_id());
				status1.setStatus1("审核中");
				status1.setStatus2("审核中");
				status1.setStatus_result("审核中");
				boolean flag3 = na.addStatus(status1);
				
				if(flag1 == true && flag2 == true && flag3 == true){
					status = 1;
					hotel_id = hotel1.getHotel_id();
				}else{
					System.out.println("指定字段更新失败！");
				}
			}else{
				System.out.println("数据查询失败！");
			}
		}else{
			System.out.println("数据插入错误！");
}
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("id", hotel_id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	/**
	 * 实现对于其他类型报销数据的添加
	 * @2018年1月25日
	 * @void
	 * @addOther
	 */
	public void addOther(HttpServletRequest request, HttpServletResponse response){
		Other other = new Other();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String date1 = format1.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		try {
			date = format1.parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		other.setReim_id(Integer.parseInt(request.getParameter("select")));
		other.setDepartment_name(request.getParameter("department"));
		other.setEmp_name(request.getParameter("emp"));
		other.setApp_abb("cc");
		other.setOther_money(Integer.parseInt(request.getParameter("money")));
		other.setOther_reason(request.getParameter("reason"));
		other.setOther_remarks(request.getParameter("content"));
		other.setOther_time(date);
		other.setOther_title(request.getParameter("project"));
		boolean flag = na.add(other);
		int status = 0;
		int other_id = 0 ;
		
		if(flag == true){
			//查询当前表最近出入的数据的信息
			Other other1 = new Other();
			other1 = na.findMaxother();
			
			//通过查询到的当前表信息获取报销类型缩写
			ReimTypeTab type = new ReimTypeTab();
			type.setReim_id(other1.getReim_id());
			type = na.findrei(type);
			
			if(other1 != null && type != null){
				//设置报销编号的的拼接字段
				Reimnumtab rm = new Reimnumtab();
				rm.setApp_id(other1.getOther_id());
				rm.setApp_abb(type.getReim_abb()+other1.getOther_id());
				boolean flag1 = na.add(rm);
				
				//设置当前表的拼接字段信息
				Other other2 = new Other();
				other2.setOther_id(other1.getOther_id());
				other2.setApp_abb(type.getReim_abb()+other1.getOther_id());
				boolean flag2 = na.update(other2);
				
				//向状态表中进行数据添加
				Status status1 = new Status();
				status1.setApp_abb(type.getReim_abb()+other1.getOther_id());
				status1.setStatus1("审核中");
				status1.setStatus2("审核中");
				status1.setStatus_result("审核中");
				boolean flag3 = na.addStatus(status1);
				
				if(flag1 == true && flag2 == true && flag3 == true){
					status = 1;
					other_id = other1.getOther_id();
				}else{
					System.out.println("指定字段更新失败！");
				}
			}else{
				System.out.println("数据查询失败！");
			}
		}else{
			System.out.println("数据插入错误！");
}
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("id", other_id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
