package haji.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import haji.server.pojo.ArticlePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDao extends BaseMapper<ArticlePojo> {
}
