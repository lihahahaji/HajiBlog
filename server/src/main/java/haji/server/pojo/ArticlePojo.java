package haji.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter/setter
@AllArgsConstructor // 全参构造函数
@NoArgsConstructor // 无参构造函数
@TableName(value="article") // mybatis-plus 设置表名
public class ArticlePojo {

    @TableId(value = "article_id",type = IdType.AUTO)
    public Integer articleId;
    @TableField(value = "article_title")
    public String articleTitle;
    @TableField(value = "article_content")
    public String articleContent;

}
