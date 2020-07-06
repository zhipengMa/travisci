package com.yx.travisci.pic;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yx.travisci.pic.mapper.BaseInfoMapper;
import com.yx.travisci.pic.model.BaseInfo;

/**
 * PicDownload.
 *
 * @author Zhipeng Ma.
 * @since 2020/7/6 1:26 下午
 */
@RestController
@RequestMapping("download")
public class PicDownload {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DownLoadService downLoadService;

	@GetMapping
	public void download() {
		String sql = "SELECT resource_seted_id as picId, C.title , workId, url, uploader_id, uploader_name FROM (SELECT B.resource_seted_id,A.title,A.id as workId FROM (SELECT r.id,r.title FROM resource r \n"
				+ "RIGHT JOIN contest_resource_contest_contested c ON r.id=c.resource_contested_id WHERE c.contest_contest_id='8f735575fae0423291f9920433118efd') A \n"
				+ "LEFT JOIN resource_resource_set_seted B ON A.id = B.resource_set_id) C LEFT JOIN resource D ON C.resource_seted_id = D.id WHERE D.resource_type = 6";
		List<BaseInfo> infos = this.jdbcTemplate.query(sql, new BaseInfoMapper());
		HashSet<String> baseInfos = new HashSet<>();
		// 编辑文件名.
		infos.forEach(item -> {
			String fileName = item.getFileName();
			final String finalFileName = fileName;
			int i = 1;
			while (true) {
				if (baseInfos.contains(fileName)) {
					String[] split = StringUtils.split(finalFileName, ".");
					fileName = split[0] + i + "." + split[1];
					i++;
				}
				else {
					baseInfos.add(fileName);
					item.setFileName(fileName);
					this.downLoadService.download(item.getOssName(),
							item.returnFileName());
					break;
				}
			}
		});
	}

}
