package com.jiguang.service;

import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbItemParam;

public interface ItemParamService {
	//根據分類id查詢
	JiGuangResult getItemParamByCid(long cid);
	//提交規格參數
	JiGuangResult insertItemParam(TbItemParam itemParam);
	

}