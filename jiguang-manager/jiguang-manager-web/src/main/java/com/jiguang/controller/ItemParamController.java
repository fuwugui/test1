package com.jiguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbItemParam;
import com.jiguang.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public JiGuangResult getItemParamByCid(@PathVariable Long itemCatId) {
		JiGuangResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public JiGuangResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		JiGuangResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

}