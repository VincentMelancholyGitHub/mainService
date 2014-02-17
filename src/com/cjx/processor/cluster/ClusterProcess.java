package com.cjx.processor.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Hits;

import com.cjx.config.CommandConst;
import com.cjx.model.ClusterData;
import com.cjx.model.PatentHitDocumentModel;
import com.cjx.utils.LuceneHelper;
import com.cjx.utils.MessageHelper;

public class ClusterProcess {

	/**
	 * 聚类查询
	 * 
	 * @param word
	 */
	public static void getClusterData(String word)
	{
		ClusterData data = new ClusterData();
		try
		{
			System.out.println("getClusterData:" + word);
			
			data.type = CommandConst.ClusterAnalysis;
			
			Hits hits = LuceneHelper.search(word);
			
			Map<String, ClusterValueItem> map = new HashMap<String, ClusterValueItem>();
			List<PatentHitDocumentModel> patentsList = new ArrayList<PatentHitDocumentModel>();
			int len = hits.length();
			String key;
			for(int i=0; i<len; i++)
			{
				PatentHitDocumentModel p = PatentHitDocumentModel.readDoc(hits.doc(i));
				patentsList.add(p);
				
				key = p.classNumG06q + p.cluster;
				if(map.containsKey(key))
				{
					map.get(key).count += 1;
				}
				else
				{
					ClusterValueItem item = new ClusterValueItem();
					item.pttClass = getIntByClassNum(p.classNumG06q);
					item.cluster = Integer.parseInt(p.cluster) + 1;
					item.count = 1;
					item.keyWord = word;
					map.put(key, item);
				}
			}
			
			for(String k : map.keySet())
			{
				data.dataprovider.add(map.get(k));
			}
			
			MessageHelper.send(data,String.valueOf(CommandConst.ClusterAnalysis));
			MessageHelper.send(patentsList, String.valueOf(CommandConst.ClusterSearchResult));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MessageHelper.send(data,String.valueOf(CommandConst.ClusterAnalysis));
		}
	}
	
	public static void getClusterPatents(String keyWord,int pttClass,int cluster)
	{
		System.out.println("getClusterPatents:" + keyWord + "," + pttClass + "," + cluster);
		
		ArrayList<PatentHitDocumentModel> patentsList = new ArrayList<PatentHitDocumentModel>();
		try
		{
			Hits hits = LuceneHelper.search(keyWord);
			for(int i=0; i<hits.length(); i++)
			{
				PatentHitDocumentModel p = PatentHitDocumentModel.readDoc(hits.doc(i));
				if(p.classNumG06q.equals(getClassNumByInt(pttClass)) && p.cluster.equals(String.valueOf(cluster)))
				{
					patentsList.add(p);
				}
			}
			MessageHelper.send(patentsList, String.valueOf(CommandConst.ClusterItemSearch));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MessageHelper.send(patentsList, String.valueOf(CommandConst.ClusterItemSearch));
		}
	}
	
	private static int getIntByClassNum(String classnum)
	{
		if(classnum.equals("G06Q10/00"))
			return 1;
		else if(classnum.equals("G06Q20/00"))
			return 2;
		else if(classnum.equals("G06Q30/00"))
			return 3;
		else if(classnum.equals("G06Q40/00"))
			return 4;
		else if(classnum.equals("G06Q50/00"))
			return 5;
		else if(classnum.equals("G06Q90/00"))
			return 6;
		else if(classnum.equals("G06Q99/00"))
			return 7;
		else 
			return 0;
	}
	
	private static String getClassNumByInt(int i)
	{
		switch(i)
		{
			case 1:
				return "G06Q10/00";
			case 2:
				return "G06Q20/00";
			case 3:
				return "G06Q30/00";
			case 4:
				return "G06Q40/00";
			case 5:
				return "G06Q50/00";
			case 6:
				return "G06Q90/00";
			case 7:
				return "G06Q99/00";
		}
		return "";
	}
}
