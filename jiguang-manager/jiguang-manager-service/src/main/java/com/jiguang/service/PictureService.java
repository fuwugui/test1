package com.jiguang.service;

import org.springframework.web.multipart.MultipartFile;

import com.jiguang.common.pojo.PictureResult;

public interface PictureService {

	PictureResult uploadFile(MultipartFile uploadFile);

}