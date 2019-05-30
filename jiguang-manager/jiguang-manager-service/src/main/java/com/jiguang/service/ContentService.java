package com.jiguang.service;

import com.jiguang.common.pojo.EUDataGridResult;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbContent;

public interface ContentService {
	//內容列表
	EUDataGridResult queryContentList(Long categoryId, Integer page, Integer rows);
	//添加
	JiGuangResult insertContent(TbContent content);
}
