package com.jiguang.service;

import java.util.List;

import com.jiguang.pojo.TbItemCat;

public interface ItemCatService {

	List<TbItemCat> getItemCatList(Long parentId) throws Exception;

}
