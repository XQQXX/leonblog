package tech.ychen.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ychen.blog.dto.ArticleDto;
import tech.ychen.blog.entiy.*;
import tech.ychen.blog.service.ArticleCommentService;
import tech.ychen.blog.service.ArticleService;
import tech.ychen.blog.service.ArticleTagService;
import tech.ychen.blog.service.TagService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author leon
 * @date 2019-04-08 16:46
 */
@SuppressWarnings("unused")
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin/api/v1")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDto articleDto;

    @Autowired
    private ArticleCommentService articleCommentService;


    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    /**
     * 发布文章
     * @param articleDto
     * @return
     */
    @ApiOperation("发布文章")
    @PostMapping("/articles")
    public JsonData insertArticle(@RequestBody ArticleDto articleDto){

        ArticleInfo articleInfo = new ArticleInfo();
        ArticleContent articleContent = new ArticleContent();
        String title = articleDto.getArticleTitle();

        List<String> articletagList= articleDto.getTagList();

        if(articleService.findArticleInfoByTitle(title) != null){
            return JsonData.buildError(0,articleDto,"文章标题已经存在");
        }

        articleInfo.setArticleTitle(title);
        articleInfo.setArticleSummary(articleDto.getArticleSummary());
        articleInfo.setArticlePic(articleDto.getArticlePic());
        articleInfo.setTop(articleDto.getTop());
        articleInfo.setAllowComment(1);
        articleInfo.setArticleTraffic(0);
        articleInfo.setGmtCreate(new Date());
        articleInfo.setGmtModified(new Date());

        articleService.insertArticleInfo(articleInfo);
        //int articleId = articleService.insertArticleInfo(articleInfo);

       ArticleInfo articleInfo1 = articleService.findArticleInfoByTitle(title);

       int articleId = articleInfo1.getId();

        articleContent.setArticleId(articleId);
        articleContent.setArticleContent(articleDto.getArticleContent());
        articleContent.setGmtCreate(new Date());
        articleContent.setGmtModified(new Date());

        articleService.inserArticleContent(articleContent);

        //更新tag numbel
        Iterator it = articletagList.iterator();

        while(it.hasNext()){             //无此tag 新建一个
            String tagName = it.next().toString();
            if(tagService.selectTagByName(tagName) == null){
                Tag tag1 = new Tag();
                tag1.setTagName(tagName);
                tag1.setNumber(1);
                tag1.setEffective(1);
                tag1.setGmtCreate(new Date());
                tag1.setGmtModified(new Date());
                tagService.insertTag(tag1);
            }

            Tag tag2 = tagService.selectTagByName(tagName);

            Integer num = tag2.getNumber() + 1; //该tag下文章 +1
            tag2.setNumber(num);

            tag2.setGmtModified(new Date());

            tagService.updateTag(tag2);

        }


        //更新Articletag

        for(String value:articletagList){
            String tagName1 = value;
            Tag tag3 = tagService.selectTagByName(tagName1);
            Integer tagid = tag3.getId();
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tagid);
            articleTag.setGmtCreate(new Date());
            articleTag.setGmtModified(new Date());
            articleTagService.insertArticleTag(articleTag);

        }

        return JsonData.buildSuccess(1,articleDto,"发布文章成功");
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @ApiOperation("删除文章")
    @DeleteMapping("articles/{id}")
    public JsonData deleteArticleById(@PathVariable("id") Integer id){



       List<ArticleComment> list =  articleCommentService.findAllCommentSByArticleId(id);//该文章所有评论list
        Iterator<ArticleComment> iterator = list.iterator(); //遍历评论，将所有评论与该文章有关的失活
        while(iterator.hasNext()){
            ArticleComment articleComment = iterator.next();
            articleComment.setEffective(0);
            articleComment.setGmtModified(new Date());
            articleCommentService.updateArticleComment(articleComment);

        }

       /* List<ArticleTag> listtag = articleTagService.listByArticleId(id);//与该文章有关的ArticleTag记录
        Iterator it = listtag.iterator(); //遍历 articleTag 将所有与这篇文章有关的 articleTag记录删除

        while(it.hasNext()){
            ArticleTag articleTag = (ArticleTag) it.next();


        }*/


       //删除文章同时将tag的number -1

       List<ArticleTag> listg = articleTagService.listByArticleId(id);

       Iterator<ArticleTag> iteratorg = listg.iterator();

       while(iteratorg.hasNext()){

           Tag tagg = tagService.selectTagById(iteratorg.next().getTagId());

           Integer num1 = tagg.getNumber()-1;

           tagg.setNumber(num1);

           tagService.updateTag(tagg);

       }

        articleTagService.deleteByArticleId(id);

        articleService.deleteArticle(id);



        return JsonData.buildSuccess(1,null,"文章删除成功");

    }

    /**
     * 文章置顶状态更改
     * @param id
     * @return
     */
    @ApiOperation("文章置顶状态更改")
    @PutMapping("articles/{id}/top")
    public JsonData changeTop(@PathVariable("id") Integer id){
        ArticleInfo articleInfo = articleService.findArticleInfotById(id);
        if(articleInfo == null){
            return JsonData.buildError(0,null,"文章不存在");
        }
        if(articleInfo.getTop() == 1){
            articleInfo.setTop(0);
            articleInfo.setGmtModified(new Date());
            articleService.updateArticle(articleInfo);
        }else {
            articleInfo.setTop(1);
            articleInfo.setGmtModified(new Date());
            articleService.updateArticle(articleInfo);
        }
        return JsonData.buildSuccess(1,articleInfo,"置顶状态更新成功");
    }

    /**
     * 文章评论权限更改
     * @param id
     * @return
     */
    @ApiOperation("文章评论权限更改")
    @PutMapping("articles/{id}/commentright")
    public JsonData changeCommentRight(@PathVariable("id") Integer id){
        ArticleInfo articleInfo = articleService.findArticleInfotById(id);
        if(articleInfo == null){
            return JsonData.buildError(0,null,"文章不存在");
        }
        if(articleInfo.getAllowComment() == 1){
            articleInfo.setAllowComment(0);
            articleInfo.setGmtModified(new Date());
            articleService.updateArticle(articleInfo);

        }else{
            articleInfo.setAllowComment(1);
            articleInfo.setGmtModified(new Date());
            articleService.updateArticle(articleInfo);

        }

        return JsonData.buildSuccess(1,articleInfo,"评论权限更新成功");
    }
}
