package com.cjx.core;

import com.cjx.config.CommandConst;
import com.cjx.processor.cluster.ClusterProcess;
import com.cjx.processor.indicator.IndicatorParam;
import com.cjx.processor.indicator.IndicatorProcess;
import com.cjx.processor.search.PatentSearchProcess;

import flex.messaging.io.amf.ASObject;

/**
 * 主方法，用于接受客户端对此remoteObject的调用，并进行命令转发，通知相应处理程序处理客户端命令
 * 
 * @author kitten
 *
 */
public class CommandAdaptor {

	public void doCommand(int command,ASObject value)
	{
		
		switch(command)
		{
			case CommandConst.TEST:
				System.out.println(value);
				break;
			
			case CommandConst.technicalGrowthRate:
				System.out.println("technicalGrowthRate");
				IndicatorParam param = new IndicatorParam();
				param.indicatorType = ((Integer)value.get("indicatorType")).intValue();
				param.pttClass = (String)value.get("pttClass");
				param.type = (String)value.get("type");
				param.keyWord = (String)value.get("keyWord");
				IndicatorProcess.getTechnicalGrowthRateData(param);
				break;
				
			case CommandConst.technicalMatureRate:
				System.out.println("technicalMatureRate");
				IndicatorParam param2 = new IndicatorParam();
				param2.indicatorType = ((Integer)value.get("indicatorType")).intValue();
				param2.pttClass = (String)value.get("pttClass");
				param2.type = (String)value.get("type");
				param2.keyWord = (String)value.get("keyWord");
				IndicatorProcess.getTechnicalMatureRateData(param2);
				break;
				
			case CommandConst.ClusterAnalysis:
				System.out.println("ClusterAnalysis");
				ClusterProcess.getClusterData((String)value.get("keyWord"));
				break;
				
			case CommandConst.PatentSearch:
				System.out.println("PatentSearch");
				PatentSearchProcess.patentSearch((String)value.get("keyWord"));
				break;
				
			case CommandConst.ClusterItemSearch:
				System.out.println("ClusterItemSearch");
				ClusterProcess.getClusterPatents((String)value.get("keyWord"),((Integer)value.get("pttClass")).intValue(), ((Integer)value.get("cluster")).intValue());
				break;
		}
	}
}
