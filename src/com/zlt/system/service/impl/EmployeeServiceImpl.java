package com.zlt.system.service.impl;

import com.zlt.system.dao.EmployeeDao;
import com.zlt.system.dao.impl.EmployeeDaoImpl;
import com.zlt.system.entity.Employee;
import com.zlt.system.service.EmployeeService;
import com.zlt.system.vo.Line;

/**
 * 个人信息界面的业务层实现类，继承EmployeeService接口
 * @author 晓煜
 * @date2018年1月24日上午10:09:05
 * @version 
 * @explain
 */
public class EmployeeServiceImpl implements EmployeeService{
	
	EmployeeDao Employee=new EmployeeDaoImpl();

	@Override
	public Line EmployeeServiceFindById(Employee employee) {
		// TODO Auto-generated method stub
		Line line=Employee.EmployeeFindById(employee);
		return line;
	}

	@Override
	public boolean EmployeeServiceUpdate(Employee employee) {
		// TODO Auto-generated method stub
		int state=Employee.EmployeeUpdate(employee);
		boolean flag=false;
		if (state>0) {
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean EmployeeServiceChangePassword(Employee employee) {
		// TODO Auto-generated method stub
		int state=Employee.EmployeeChangePassword(employee);
		boolean flag=false;
		if (state>0) {
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean EmployeeServicePicture(Employee employee) {
		// TODO Auto-generated method stub
		int state=Employee.EmployeePicture(employee);
		boolean flag=false;
		if (state>0) {
			flag=true;
		}
		return flag;
	}

	@Override
	public Employee EmployeeServiceCheckPasswoed(Employee employee) {
		// TODO Auto-generated method stub
		Employee employee2=Employee.login(employee);
		return employee2;
	}


	
}
