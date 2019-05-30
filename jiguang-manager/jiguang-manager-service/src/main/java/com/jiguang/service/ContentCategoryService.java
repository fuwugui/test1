package com.jiguang.service;

import java.util.List;

import com.jiguang.common.pojo.EUTreeNode;
import com.jiguang.common.pojo.JiGuangResult;

public interface ContentCategoryService {
	//查詢分類列表
	List<EUTreeNode> getCategoryList(Long parentId);
	//添加
	JiGuangResult insertContentCategory(Long parentId, String name);
	//刪除
	JiGuangResult deleteContentCategory(Long id);
	//重命名
	JiGuangResult updateContentCategory(Long id, String name);
	
}
