package com.jiguang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiguang.common.pojo.JiGuangResult;
import com.jiguang.mapper.TbItemParamMapper;
import com.jiguang.pojo.TbItemParam;
import com.jiguang.pojo.TbItemParamExample;
import com.jiguang.pojo.TbItemParamExample.Criteria;
import com.jiguang.service.ItemParamService;

/**
 *  實現商品的規格
 * @author 15731
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public JiGuangResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return JiGuangResult.ok(list.get(0));
		}

		return JiGuangResult.ok();
	}
	@Override
	public JiGuangResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return JiGuangResult.ok();
	}

}
