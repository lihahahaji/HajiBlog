package haji.server.controller;

import com.alibaba.fastjson2.JSONObject;
import haji.server.pojo.ArticlePojo;
import haji.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
    public String getArticleList()
    {
        List<ArticlePojo> articleList = articleService.selectAll();
        Integer ListLength = articleList.size();

        Map<String, Object> map =new HashMap<>();
        map.put("articleList",articleList);
        map.put("listLength",ListLength);



        String res = JSONObject.toJSONString(map);


        return res;
    }
    @RequestMapping(value = "/newBlog", method = RequestMethod.POST)
    public String addNewBlog(@RequestParam("articleTitle") String articleTitle, @RequestParam("articleContent") String articleContent)
    {
        articleService.insertArticle(articleTitle,articleContent);

        Map<String,String>map = new HashMap<>();
        map.put("code","200");
        map.put("message","success");
        return JSONObject.toJSONString(map);
    }


}
