package com.jiguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiguang.common.pojo.EUDataGridResult;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.pojo.TbContent;
import com.jiguang.service.ContentService;
/**
 * 	內容管理
 * @author 15731
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
		EUDataGridResult resultList = contentService.queryContentList(categoryId,page,rows);
		return resultList;
	}
	@RequestMapping("/save")
	@ResponseBody
	public JiGuangResult insertContent(TbContent content) {
		JiGuangResult result = contentService.insertContent(content);
		return result;
	}
}
