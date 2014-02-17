package com.cjx.model;

import org.apache.lucene.document.Document;

public class PatentHitDocumentModel {

	public String pttNum;
	public String pttDate;
	public String pttType;
	public String classNumG06q;
	public String inventor;
	public String pttName;
	public String pttAbstract;
	public String cluster;
	
	public static PatentHitDocumentModel readDoc(Document doc)
	{
		PatentHitDocumentModel p = new PatentHitDocumentModel();
		p.pttNum = doc.get("PTT_NUM");
		p.pttDate = doc.get("PTT_DATE");
		p.pttType = doc.get("PTT_TYPE");
		p.classNumG06q = doc.get("CLASS_NUM_G06Q");
		p.inventor = doc.get("INVENTOR");
		p.pttName = doc.get("PTT_NAME");
		p.pttAbstract = doc.get("PTT_ABSTRACT");
		p.cluster = doc.get("CLUSTER");
		return p;
	}
	
}
