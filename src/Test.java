import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.cjx.processor.indicator.IndicatorParam;
import com.cjx.processor.indicator.IndicatorProcess;
import com.cjx.utils.ConnectionHelper;
import com.cjx.utils.LuceneHelper;

import flex.messaging.config.ConfigMap;
import flex.messaging.services.AbstractBootstrapService;


public class Test extends AbstractBootstrapService{
	
	public Test()
	{
		System.out.println("cccjjjxxx");
	}

	public static void main(String[] args) {
		
//		String fileContent = "中国科学院计算技术研究所在多年研究基础上，" +
//				"耗时一年研制出了ICTCLAS汉语词法分析系统";
//		SegTag segTag = new SegTag(1);// 分词路径的数目		
//		String segResult = segTag.split(fileContent.trim());
////		String classifyContent = segResult.getFinalResult();
//		System.out.println("分词结果\n"+segResult);
		
//		try {
//			Connection con = ConnectionHelper.getConnection();
//			@SuppressWarnings("unused")
//			Statement sta = con.createStatement();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		IndicatorParam param = new IndicatorParam();
		param.indicatorType = 2222;
		param.pttClass = "pttClass";
		param.type = "type";
		param.keyWord = "+";
		IndicatorProcess.getTechnicalGrowthRateData(param);
		
	}

	@Override
	public void initialize(String arg0, ConfigMap arg1) {
		
	}

	@Override
	public void start() {
		
	}

	@Override
	public void stop() {
		
	}
	
}
