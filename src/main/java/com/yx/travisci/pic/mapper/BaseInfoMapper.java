// package com.yx.travisci.pic.mapper;
//
// import java.sql.ResultSet;
// import java.sql.SQLException;
//
// import org.springframework.jdbc.core.RowMapper;
//
// import com.yx.travisci.pic.model.BaseInfo;
//
/// **
// * BaseInfoMapper.
// *
// * @author Zhipeng Ma.
// * @since 2020/7/6 1:37 下午
// */
// public class BaseInfoMapper implements RowMapper<BaseInfo> {
//
// @Override
// public BaseInfo mapRow(ResultSet rs, int i) throws SQLException {
// BaseInfo baseInfo = new BaseInfo();
// baseInfo.setUrl(rs.getString("url"));
// baseInfo.setTitle(rs.getString("title"));
// baseInfo.setUploaderName(rs.getString("uploader_name"));
// return baseInfo;
// }
//
// }
