package com.cms.util.biz;


/**
 * 
 * 
 * 按照数据库表中的对应常量归类
 * 
 * @author qgn
 *
 */
public class ConstantsUtil {
	/**
	 * GOODS_BILL_APPLY
	 */
	//状态status:  N临时  ,F作废 , C审核  ,S发送 , A接收 , O出库, D入库
	public static final String  APPLY_STATUS_N="N";
	public static final String  APPLY_STATUS_F="F";
	public static final String  APPLY_STATUS_C="C";
	public static final String  APPLY_STATUS_S="S";
	public static final String  APPLY_STATUS_A="A";
	public static final String  APPLY_STATUS_O="O";
	public static final String  APPLY_STATUS_D="D";
	//单据类型:  KSSQ 科室申请, KSTK 科室退库
	public static final String  APPLY_TYPE_KSSQ="KSSQ";
	public static final String  APPLY_TYPE_KSSQ_TEXT="科室申请";
	public static final String  APPLY_TYPE_KSTK="KSTK";
	public static final String  APPLY_TYPE_KSTK_TEXT="科室退库";
	
	/**
	 * GOODS_BILL_IN
	 */
	//状态status:N临时,F作废,C已审核,D已处理
	public static final String  IN_STATUS_N="N";
	public static final String  IN_STATUS_F="F";
	public static final String  IN_STATUS_C="C";
	public static final String  IN_STATUS_D="D";
	//单据类型:CGRK  采购入库   CGTH 采购退货
	public static final String  IN_TYPE_CGRK="CGRK";
	public static final String  IN_TYPE_CGRK_TEXT="采购入库";
	public static final String  IN_TYPE_CGTH="CGTH";
	public static final String  IN_TYPE_CGTH_TEXT="采购退货";
	/**
	 * GOODS_BILL_PLAN
	 */
	//状态:	N临时,C已审核,D已生效,F作废
	public static final String  PLAN_STATUS_N="N";
	public static final String  PLAN_STATUS_F="F";
	public static final String  PLAN_STATUS_C="C";
	public static final String  PLAN_STATUS_D="D";
	/**
	 * GOODS_STOCK_CHECK
	 */
	//N临时  F作废(取消) C已审  D已完成
	public static final String  STOCK_CHECK_STATUS_N="N";
	public static final String  STOCK_CHECK_STATUS_F="F";
	public static final String  STOCK_CHECK_STATUS_C="C";
	public static final String  STOCK_CHECK_STATUS_D="D";
	
	public static final String  STOCK_CHECK_TYPE_KFPD="KFPD";
	public static final String  STOCK_CHECK_TYPE_KFPD_TEXT="库房盘点";
	
	
	/**
	 * IO过程中使用到的表名称,类型,备注
	 */
	public static final String  TABLENAME_GOODS_BILL_APPLY_DTL="GOODS_BILL_APPLY_DTL";
	public static final String  TABLENAME_GOODS_BILL_IN_DTL="GOODS_BILL_IN_DTL";
	public static final String  TABLENAME_GOODS_STOCK_CHECK_DTL="GOODS_STOCK_CHECK_DTL";
	
	
}
