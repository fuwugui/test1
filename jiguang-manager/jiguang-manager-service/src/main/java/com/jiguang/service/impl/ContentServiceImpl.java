package com.jiguang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiguang.common.pojo.EUDataGridResult;
import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.mapper.TbContentMapper;
import com.jiguang.pojo.TbContent;
import com.jiguang.pojo.TbContentExample;
import com.jiguang.pojo.TbContentExample.Criteria;
import com.jiguang.service.ContentService;
/**
 * 	內容管理
 * @author 15731
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public EUDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
		// 查詢categoryId相匹配的所有TbContent
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 使用分頁插件
		//PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		//獲得共多少頁
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//創建EasyUIDataGridResult結果集
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	@Override
	public JiGuangResult insertContent(TbContent content) {
		//補全pojo內容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return JiGuangResult.ok();
	}

}