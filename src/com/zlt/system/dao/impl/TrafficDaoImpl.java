package com.zlt.system.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zlt.system.dao.TrafficDao;
import com.zlt.system.entity.Employee;
import com.zlt.system.entity.Traffic;
import com.zlt.system.util.DBUtil;
import com.zlt.system.vo.TrafficInfoVo;

/**
 * 调用交通表信息的实现类
 * @author 米邓勇
 *
 */
public class TrafficDaoImpl extends CurrencyDaoImpl<Traffic> implements TrafficDao{
	/**
	 * test方法
	 * @param args
	 */
	/*public static void main(String[] args) {
		TrafficDaoImpl traffic=new TrafficDaoImpl();
		System.out.println(traffic.FindByType("JT10000", "交通报销"));
	}*/
	@Override
	public List<?> TrafficFindById(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @author 任义平
	 * 用于交通报销的详情查询
	 */
	@Override
	public TrafficInfoVo FindByType(String app_abb) {
		// TODO Auto-generated method stub
		String sql="SELECT traffic_address,traffic_start,traffic_end,traffic_type,traffic_money,traffic_remark,status1_opinion \r\n" + 
				"FROM traffic,reimtypetab,STATUS \r\n" + 
				"WHERE reimtypetab.`reim_id`=traffic.`reim_id` AND status.`app_abb`=traffic.app_abb AND traffic.app_abb=?";
		QueryRunner runner=new QueryRunner();
		TrafficInfoVo traffic=null;
		try {
			traffic=runner.query(DBUtil.getConnection(), sql, new BeanHandler<TrafficInfoVo>(TrafficInfoVo.class),app_abb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pre, rs);
		}
		return traffic;
	}
	/**
	 * 交通类型报销
	 */
	@Override
	public TrafficInfoVo FindByElse(String app_abb) {
		// TODO Auto-generated method stub
		String sql = "SELECT traffic_address,traffic_start,traffic_end,traffic_type,traffic_money,traffic_remark,status1_opinion\r\n" + 
				"FROM traffic,reimtypetab,STATUS\r\n" + 
				"WHERE reimtypetab.`reim_id`=traffic.`reim_id` AND status.`app_abb`=traffic.app_abb=?";
		QueryRunner runner=new QueryRunner();
		TrafficInfoVo traffic=null;
		try {
			traffic=runner.query(DBUtil.getConnection(), sql, new BeanHandler<TrafficInfoVo>(TrafficInfoVo.class),app_abb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return traffic;
	}

}
