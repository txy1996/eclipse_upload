package com.zlt.system.vo;

/**
 * 用于接受历史查询需要的对应类
 * 
 * @author `'`灯半盏
 */
public class History {
	/**
	 * 根据员工表emp_id查询的员工姓名
	 */
	private String emp_name;
	/**
	 * 根据其它类型表ClaimForm_id查询创建日期
	 */
	private String other_time;
	/**
	 * 根据报销单号表bills_id查询: 报销编号:已改在其它类型表
	 */
	private String app_abb;
	/**
	 * 根据报销类型表 Reimbursementtype_id 查询报销类型
	 */
	private String reimbursementtype_type;
	/**
	 * 根据部门表查询所属部门 department_id
	 */
	private String department_name;
	/**
	 * 根据其它类型表ClaimForm_id 查询 所属项目 报账总额 事由 备注
	 */
	// 所属项目
	private String other_title;
	// 报账总金额
	private int other_money;
	// 报账事由
	private String other_reason;
	// 备注
	private String remarks;

	public History() {
		super();
	}

	public History(String emp_name, String other_time, String app_abb, String reimbursementtype_type,
			String department_name, String other_title, int other_money, String other_reason, String remarks) {
		super();
		this.emp_name = emp_name;
		this.other_time = other_time;
		this.app_abb = app_abb;
		this.reimbursementtype_type = reimbursementtype_type;
		this.department_name = department_name;
		this.other_title = other_title;
		this.other_money = other_money;
		this.other_reason = other_reason;
		this.remarks = remarks;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getOther_time() {
		return other_time;
	}

	public void setOther_time(String other_time) {
		this.other_time = other_time;
	}

	public String getApp_abb() {
		return app_abb;
	}

	public void setApp_abb(String app_abb) {
		this.app_abb = app_abb;
	}

	public String getReimbursementtype_type() {
		return reimbursementtype_type;
	}

	public void setReimbursementtype_type(String reimbursementtype_type) {
		this.reimbursementtype_type = reimbursementtype_type;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getOther_title() {
		return other_title;
	}

	public void setOther_title(String other_title) {
		this.other_title = other_title;
	}

	public int getOther_money() {
		return other_money;
	}

	public void setOther_money(int other_money) {
		this.other_money = other_money;
	}

	public String getOther_reason() {
		return other_reason;
	}

	public void setOther_reason(String other_reason) {
		this.other_reason = other_reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "History [emp_name=" + emp_name + ", other_time=" + other_time + ", app_abb=" + app_abb
				+ ", reimbursementtype_type=" + reimbursementtype_type + ", department_name=" + department_name
				+ ", other_title=" + other_title + ", other_money=" + other_money + ", other_reason=" + other_reason
				+ ", remarks=" + remarks + "]";
	}

	/*
	 * 对应数据库的查询全部语句 SELECT emp.emp_name,other_time,
	 * other_project,other_money,other_reason,other_remarks
	 * ,department_name,(SELECT app_id FROM bills WHERE app_id=1)'报销单号' FROM
	 * emp,other,department,bills WHERE emp.emp_name=other.emp_name AND
	 * emp.emp_name=department_name
	 */
}
