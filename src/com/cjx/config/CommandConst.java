package com.cjx.config;

/**
 * 保存命令常量，用于命令转发
 * 
 * @author cjx
 *
 */
public class CommandConst {

	/**
	 * 测试
	 */
	public static final int TEST = 10000;
	
	/**	技术生长率	*/
	public static final int technicalGrowthRate = 10001;
	/**	技术成熟系数	*/
	public static final int technicalMatureRate = 10002;
	
	
	/**	根据关键词获得聚类数据	*/
	public static final int ClusterAnalysis = 20001;
	public static final int ClusterSearchResult = 20003;	//聚类分析查询，所有结果专利
	public static final int ClusterItemSearch = 20002;//聚类专利查询
	
	
	/**	专利查询	*/
	public static final int PatentSearch = 30001;
	
}
