package com.jiguang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiguang.common.pojo.EUTreeNode;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.service.ContentCategoryService;
/**
 * 	內容分類管理
 * @author 15731
 *
 */
@Controller
@RequestMapping("/content")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping("/category/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}
	@RequestMapping("/category/create")
	@ResponseBody
	public JiGuangResult createContentCategory(Long parentId, String name) {
		JiGuangResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	@RequestMapping("/category/delete")
	@ResponseBody
	public JiGuangResult deleteContentCategory( Long id) {
		JiGuangResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	@RequestMapping("/category/update")
	@ResponseBody
	public JiGuangResult updateContentCategory(Long id, String name) {
		JiGuangResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
}
