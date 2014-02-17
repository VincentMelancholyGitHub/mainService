package com.cjx.utils;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class LuceneHelper {

	public static String indexPath = "D:/Xampp 1.8.3/tomcat/webapps/patentOLAM/luceneIndex";
	
	public static Hits search(String queryWord)
	{
		System.out.println("search:" + queryWord);
		Hits hits = null;
		
		try 
		{
			IndexSearcher is = new IndexSearcher(indexPath);
			Query query = QueryParser.parse(queryWord, "PTT_ABSTRACT", new StandardAnalyzer());
			hits = is.search(query);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return hits;
	}
	
}
