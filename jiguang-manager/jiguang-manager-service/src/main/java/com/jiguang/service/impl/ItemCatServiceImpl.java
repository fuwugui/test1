package com.jiguang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiguang.mapper.TbItemCatMapper;
import com.jiguang.pojo.TbItemCat;
import com.jiguang.pojo.TbItemCatExample;
import com.jiguang.pojo.TbItemCatExample.Criteria;
import com.jiguang.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception {

		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

}