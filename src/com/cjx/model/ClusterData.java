package com.cjx.model;

import java.util.ArrayList;
import java.util.List;
import com.cjx.processor.cluster.ClusterValueItem;

/**
 * 记录聚类结果的各项数据
 * 
 * @author CJX
 * 
 */
public class ClusterData {

	public int type;
	public List<ClusterValueItem> dataprovider = new ArrayList<ClusterValueItem>();
}
