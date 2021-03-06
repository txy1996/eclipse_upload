 package com.zlt.system.service;

import java.util.List;

import com.zlt.system.entity.Reimnumtab;
import com.zlt.system.entity.Status;
import com.zlt.system.vo.ApplyreimVo;
import com.zlt.system.vo.HotelInfoVo;
import com.zlt.system.vo.OtherInfoVo;
import com.zlt.system.vo.TrafficInfoVo;
import com.zlt.system.vo.TravelInfoVo;

/**
 * 会计审计
 * @author 任义平
 *
 */
public interface AccountingService {
	/**.
	 * 根据报销编号查询
	 * @return 
	 */
	public ApplyreimVo FindById(Reimnumtab reimnumtab);
	/**
	 * 报销信息全部显示
	 * @param <T>
	 * 
	 */
	public List<ApplyreimVo> Reimbursement();
	/**
	 * 查看差旅报销的详细信息
	 * @return差旅
	 */
	public TravelInfoVo ReimbursementTravel(String app_abb);
	/**
	 * 交通详情
	 * @param app_abb
	 * @return交通
	 */
	public TrafficInfoVo ReimbursementTraffic(String app_abb);
	/**
	 * 其他详情
	 * @param app_abb
	 * @return其他类型
	 */
	public OtherInfoVo ReimbursementOther(String app_abb);
	/**
	 * 住宿补助详情
	 * @param app_abb
	 * @return住宿补助
	 */
	public HotelInfoVo ReimbursementHotel(String app_abb);
	/**
	 * 审批通过后更新状态和意见
	 * @param status状态
	 */
	public void UpdateState(Status status);
	/**
	 * 提交驳回原因改变状态
	 * @param status原因
	 */
	public void InsertReson(Status status);
	/**
	 * 审批通过之后的插入信息用于付款
	 * @param apply
	 */
	public void InsertFinance(ApplyreimVo apply);
}
