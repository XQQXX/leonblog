package tech.ychen.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ychen.blog.entiy.ArticleInfo;
import tech.ychen.blog.entiy.ArticleTag;
import tech.ychen.blog.entiy.JsonData;
import tech.ychen.blog.entiy.Tag;
import tech.ychen.blog.service.ArticleService;
import tech.ychen.blog.service.ArticleTagService;
import tech.ychen.blog.service.TagService;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leon
 * @date 2019-04-10 11:05
 */
@SuppressWarnings("unused")
@Api(tags = "文章模块")
@RestController
@RequestMapping("/api/v1/articles")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    /**
     * 列出所有tag
     * @return
     */
    @ApiOperation("列出所有tag")
    @GetMapping("/tags")
    public JsonData showAlltag(){

        List<Tag> alltag = tagService.list();


       return JsonData.buildSuccess(1,alltag,"所有tag");
    }


    /**
     * 根据tagid得到该tag下所有文章
     * @param id
     * @return
     */
    @GetMapping("/tags/{id}")
    @ApiOperation("根据tagid得到该tag下所有文章info")
    public JsonData showArtcilesByTag(@PathVariable("id") Integer id){

        List<ArticleInfo> articleInfosList = new LinkedList<>();

      List<ArticleTag> articleTagsList = articleTagService.listByTagId(id);

      Iterator<ArticleTag> iterator  = articleTagsList.iterator();

      while(iterator.hasNext()){

          Integer articleId = iterator.next().getArticleId();

          articleInfosList.add(articleService.findArticleInfotById(articleId));


      }

        return JsonData.buildSuccess(1,articleInfosList,"该tag下所有文章");
    }





}
