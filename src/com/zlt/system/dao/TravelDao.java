package com.zlt.system.dao;
import java.util.List;

import com.zlt.system.entity.Employee;
import com.zlt.system.entity.Travel;
import com.zlt.system.vo.Report;
import com.zlt.system.vo.TravelInfoVo;
/**
 * 调用差旅表数据的接口
 * @author 米邓勇
 *
 */

public interface TravelDao extends CurrencyDao<Travel>{
	/**
	 * 根据员工姓名查询对应的表的信息 :唐晓煜
	 * @param employee 传入员工表对象 具体信息：姓名
	 * @return 个人报表需要的数据vo类
	 */
	List<Report> TravelFindById(Employee employee);
	/**
	 * 用于报销详情的查看
	 * @author 任义平
	 * @param type报销类型
	 * @param app_abb报销编号
	 * @return
	 */
	public TravelInfoVo FindByType(String app_abb);
	/**
	 * @author 刘俊泽
	 * @param type
	 * @param app_abb
	 * @return
	 */
	public TravelInfoVo FindByTy(String app_abb);
}
