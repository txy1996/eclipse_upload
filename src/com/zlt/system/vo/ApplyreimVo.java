  package com.zlt.system.vo;

import java.util.Date;
import java.util.List;

import com.zlt.system.entity.Department;
import com.zlt.system.entity.Employee;
import com.zlt.system.entity.Finance;
import com.zlt.system.entity.ReimTypeTab;
import com.zlt.system.entity.Reimnumtab;

/**刘俊泽 知了堂 java三组
 * 2018年1月22日 19：54
 * 用于操报销申请审批多表查询
 * 
 */
public class ApplyreimVo  {
	/**number_id单号 
	 * emp_name查询报销人
	 * department_name查询部门 
	 * reim_type查询报销类型
	 * Finance_money查询报销总金额
	 */
	 
	 private String app_abb;	
	 
	 private String emp_name;
	 
	 private String department_name;
	 
	 private String reim_type;
	 
	 private int money 	;
	 
	 private Date time;
	 
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getApp_abb() {
		return app_abb;
	}

	public void setApp_abb(String app_abb) {
		this.app_abb = app_abb;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getReim_type() {
		return reim_type;
	}

	public void setReim_type(String reim_type) {
		this.reim_type = reim_type;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ApplyreimVo(String app_abb, String emp_name, String department_name, String reim_type, int money,
			Date time) {
		super();
		this.app_abb = app_abb;
		this.emp_name = emp_name;
		this.department_name = department_name;
		this.reim_type = reim_type;
		this.money = money;
		this.time = time;
	}

	public ApplyreimVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ApplyreimVo [app_abb=" + app_abb + ", emp_name=" + emp_name + ", department_name=" + department_name
				+ ", reim_type=" + reim_type + ", money=" + money + ", time=" + time + "]";
	}

	

	
}
