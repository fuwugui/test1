package com.jiguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiguang.service.ItemParamItemService;
/**
 * 接收商品id调用Service查询规格参数信息，得到规格参数的html
 * 返回一个逻辑视图
 * 把html展示到页面
 * @author 15731
 *
 */
@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping(value="/item/{itemId}",method=RequestMethod.POST)
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
}