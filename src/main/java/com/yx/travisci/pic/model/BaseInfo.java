package com.yx.travisci.pic.model;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * BaseInfo.
 *
 * @author Zhipeng Ma.
 * @since 2020/7/6 1:28 下午
 */
@Data
public class BaseInfo {

	/**
	 * 作品名
	 */
	private String title;

	/**
	 * 作者名.
	 */
	private String uploaderName;

	/**
	 * 图片的url.
	 */
	private String url;

	/**
	 * 文件名.
	 */
	private String fileName;

	/**
	 * ossName.
	 */
	private String ossName;

	public String getOssName() {
		String[] split = StringUtils.split(this.url, ".me/");
		return split[1];
	}

	public String getFileName() {
		return this.uploaderName + "+" + this.title + this.url.split("_d")[1];
	}

	public String returnFileName() {
		return fileName;
	}

}
