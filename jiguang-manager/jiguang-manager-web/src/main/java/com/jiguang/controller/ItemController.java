package com.jiguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiguang.common.pojo.EUDataGridResult;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbItem;
import com.jiguang.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	private JiGuangResult createItem(TbItem item, String desc,String itemParams) throws Exception {
		JiGuangResult result = itemService.createItem(item,desc,itemParams);
		return result;
	}

}