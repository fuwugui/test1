package com.jiguang.service;

import com.jiguang.common.pojo.EUDataGridResult;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	//商品列表查詢
	EUDataGridResult getItemList(int page, int rows);
	//添加商品
	JiGuangResult createItem(TbItem item, String desc,String itemParams) throws Exception;
	
}
