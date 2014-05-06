package com.cjx.processor.indicator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.search.Hits;

import com.cjx.config.CommandConst;
import com.cjx.model.IndicatorData;
import com.cjx.model.IndicatorValueItem;
import com.cjx.model.PatentHitDocumentModel;
import com.cjx.utils.DateUtil;
import com.cjx.utils.LuceneHelper;
import com.cjx.utils.MessageHelper;

public class IndicatorProcess {

	/**
	 * 查询并计算技术生长率
	 * 
	 * @param param
	 */
	public static void getTechnicalGrowthRateData(IndicatorParam param) {
		System.out.println("getTechnicalGrowthRateData");
		IndicatorData data = new IndicatorData();
		try {
			data.indicatorType = param.indicatorType;

			System.out.println(param.keyWord);

			Hits hits = LuceneHelper.search(param.keyWord);

			Map<String, Integer> map = new HashMap<String, Integer>();
			double sum = 0;
			int len = hits.length();
			for (int i = 0; i < len; i++) {
				PatentHitDocumentModel p = PatentHitDocumentModel.readDoc(hits
						.doc(i));
				if (p.pttType.equals("11")) {
					String year = DateUtil.getYear(p.pttDate);
					if (map.containsKey(year)) {
						int count = map.get(year).intValue();
						count += 1;
						map.put(year, count);
					} else {
						map.put(year, 1);
					}
					sum += 1;
				}
			}

			for (int i = 2006; i <= 2010; i++) {
				String y = String.valueOf(i);
				if (map.containsKey(y)) {
					data.value11.add(new IndicatorValueItem(y, map.get(y)
							.doubleValue() / sum));
				} else {
					data.value11.add(new IndicatorValueItem(y, 0));
				}
			}

			MessageHelper.send(data,
					String.valueOf(CommandConst.technicalGrowthRate));
		} catch (IOException e) {
			e.printStackTrace();
			MessageHelper.send(data,
					String.valueOf(CommandConst.technicalGrowthRate));
		}
	}

	/**
	 * 查询并计算技术成熟率
	 * 
	 * @param param
	 */
	public static void getTechnicalMatureRateData(IndicatorParam param) {
		try {
			System.out.println("getTechnicalMatureRateData");
			IndicatorData data = new IndicatorData();
			data.indicatorType = param.indicatorType;
			System.out.println(param.keyWord);

			Hits hits = LuceneHelper.search(param.keyWord);
			Map<String, Integer> map11 = new HashMap<String, Integer>();
			Map<String, Integer> map22 = new HashMap<String, Integer>();
			Map<String, Integer> yearSum = new HashMap<String, Integer>();
			double sum = 0;
			int len = hits.length();
			for (int i = 0; i < len; i++) {
				int count;
				PatentHitDocumentModel p = PatentHitDocumentModel.readDoc(hits
						.doc(i));
				String year = DateUtil.getYear(p.pttDate);
				if (yearSum.containsKey(year)) {
					count = yearSum.get(year).intValue();
					count += 1;
					yearSum.put(year, count);
				} else {
					yearSum.put(year, 1);
				}

				if (p.pttType.equals("11")) {
					if (map11.containsKey(year)) {
						count = map11.get(year).intValue();
						count += 1;
						map11.put(year, count);
					} else {
						map11.put(year, 1);
					}
					sum += 1;
				} else if (p.pttType.equals("22")) {
					if (map22.containsKey(year)) {
						count = map22.get(year).intValue();
						count += 1;
						map22.put(year, count);
					} else {
						map22.put(year, 1);
					}
					sum += 1;
				}
			}

			for (int i = 2006; i <= 2010; i++) {
				String y = String.valueOf(i);
				if (map11.containsKey(y)) {
					data.value11.add(new IndicatorValueItem(y, map11.get(y)
							.doubleValue() / yearSum.get(y).doubleValue()));
				} else {
					data.value11.add(new IndicatorValueItem(y, 0));
				}
			}

			MessageHelper.send(data,
					String.valueOf(CommandConst.technicalMatureRate));
		} catch (Exception e) {
			e.printStackTrace();
			IndicatorData nullData = new IndicatorData();
			MessageHelper.send(nullData,
					String.valueOf(CommandConst.technicalMatureRate));
		}

	}

}
