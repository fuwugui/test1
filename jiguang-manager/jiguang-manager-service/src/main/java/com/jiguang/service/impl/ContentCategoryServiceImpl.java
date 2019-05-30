package com.jiguang.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiguang.common.pojo.EUTreeNode;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.mapper.TbContentCategoryMapper;
import com.jiguang.pojo.TbContentCategory;
import com.jiguang.pojo.TbContentCategoryExample;
import com.jiguang.pojo.TbContentCategoryExample.Criteria;
import com.jiguang.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) {
		//根據parentId查詢節點列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria critera = example.createCriteria();
		critera.andParentIdEqualTo(parentId);
		//執行查詢
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list) {
			//創建一個節點
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public JiGuangResult insertContentCategory(Long parentId, String name) {
		//創建一個pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加記錄
		contentCategoryMapper.insert(contentCategory);
		//查看父節點的isparentId是否為true,如果不是則改為true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判斷是否為父節點
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父節點
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回結果
		return JiGuangResult.ok(contentCategory);
	}
	@Override
	public JiGuangResult deleteContentCategory(Long id) {
		deleteCategoryAndChildren(id);
		return JiGuangResult.ok();
	}
	private void deleteCategoryAndChildren(Long id) {
		//獲取要刪除的category
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//先更新自己的狀態
		tbContentCategory.setStatus(2);
		contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		//判斷是否為父節點
		if(tbContentCategory.getIsParent()) {
			//獲得所有的子節點
			List<TbContentCategory> list = getChildrenList(id);
			//遍歷刪除所有子節點
			for(TbContentCategory category : list) {
				deleteCategoryAndChildren(category.getId());
			}
		}
		//判斷父節點下是否還有其他子節點
		if(getCategoryList(tbContentCategory.getParentId()).size() == 0) {
			//沒有子節點將父節點標記為葉子節點
			TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
			parentCategory.setIsParent(false);
			//刪除本節點
			parentCategory.setStatus(2);
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
	}
	/**
	 * 	獲得所有該節點下的子節點
	 * @param id	父節點
	 * @return	所有子節點
	 */
	private List<TbContentCategory> getChildrenList(Long id) {
		//查詢所有父節點為id的節點
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		return contentCategoryMapper.selectByExample(example);
	}
	@Override
	public JiGuangResult updateContentCategory(Long id, String name) {
		//根據id找到category
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//封裝更新後的name
		tbContentCategory.setName(name);
		//更新內容分類表
		contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		return JiGuangResult.ok();
	}
	
}
