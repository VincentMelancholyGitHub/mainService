package com.cjx.processor.search;

import java.util.ArrayList;

import org.apache.lucene.search.Hits;

import com.cjx.config.CommandConst;
import com.cjx.model.PatentHitDocumentModel;
import com.cjx.utils.LuceneHelper;
import com.cjx.utils.MessageHelper;


public class PatentSearchProcess {

	public static void patentSearch(String keyWord)
	{
		System.out.println("patentSearch:" + keyWord);
		
		ArrayList<PatentHitDocumentModel> patentsList = new ArrayList<PatentHitDocumentModel>();
		try
		{
			Hits hits = LuceneHelper.search(keyWord);
			for(int i=0; i<hits.length(); i++)
			{
				PatentHitDocumentModel p = PatentHitDocumentModel.readDoc(hits.doc(i));
				patentsList.add(p);
			}
			MessageHelper.send(patentsList, String.valueOf(CommandConst.PatentSearch));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MessageHelper.send(patentsList, String.valueOf(CommandConst.PatentSearch));
		}
	}
	
}
