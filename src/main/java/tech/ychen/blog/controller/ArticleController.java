package tech.ychen.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.bind.v2.TODO;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ychen.blog.entiy.*;
import tech.ychen.blog.service.ArticleCommentService;
import tech.ychen.blog.service.ArticleService;
import tech.ychen.blog.service.UserService;
import tech.ychen.blog.utils.JwtUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author leon
 * @date 2019-04-06 20:08
 */

@SuppressWarnings("unused")
@Api(tags = "文章模块")
@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private UserService userService;

    /**
     * 文章list分页
     * @param page
     * @param size
     * @return
     */
    //@ApiOperation("分页显示文章，默认第1页显示1条")
    @ApiOperation("分页显示文章")
    @GetMapping("/articles")
    public JsonData listArticle(@RequestParam(value = "page",defaultValue = "1")int page,
                                @RequestParam(value = "size",defaultValue = "1")int size
    ){

        PageHelper.startPage(page,size);

        List<ArticleInfo> articleInfosList = articleService.findAllArticleInfo();

        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleInfosList);

        Map<String,Object> data = new HashMap<>();
        data.put("total_size",pageInfo.getTotal());//总条数
        data.put("total_page",pageInfo.getPages());//总页数
        data.put("current_page",pageInfo.getPages());//当前页

        data.put("data",pageInfo.getList());//数据

        return JsonData.buildSuccess(1,data,"分页显示ok");
    }

    /**
     * 得到指定id的文章
     * @param articleId
     * @return
     */
    @ApiOperation("通过文章id得到对应文章信息和内容")
    //@ApiImplicitParam(name = "articleId",value = "文章id",required = true,dataType = "int")
    @GetMapping("/articles/{id}")
    public JsonData getArticle(@PathVariable("id") Integer articleId){


        ArticleContent articleContent = articleService.findArticleContentByArticleId(articleId);
        ArticleInfo articleInfo = articleService.findArticleInfotById(articleId);

        String title = articleInfo.getArticleTitle();

        Map<String,Object> data = new HashMap<>();
        data.put("title",title);
        data.put("content",articleContent.getArticleContent());
        data.put("createtime",articleContent.getGmtModified());

        ArticleInfo articleInfot = articleService.findArticleInfotById(articleId);//更新文章访问量
        Integer num = articleInfot.getArticleTraffic() + 1;
        articleInfot.setArticleTraffic(num);
        articleService.updateArticle(articleInfot);

        return JsonData.buildSuccess(1,data,"ok");
    }

    /**
     * 列出指定文章所有有效的评论
     * @param articleId
     * @return
     */
    @ApiOperation("列出指定id文章所有有效的评论")
    @GetMapping("/articles/{id}/comments")
   // @ApiOperation("列出指定id文章所有有效的评论")
    //@ApiImplicitParam(name = "articleId",value = "文章id",required = true,dataType = "int")
    public JsonData getArticleComment(@PathVariable("id") Integer articleId){

        if(articleService.findArticleInfotById(articleId).getAllowComment()==0){
            return JsonData.buildError(0,null,"该文章不允许评论");
        }

        List<ArticleComment> allCommentsByArticleId = articleCommentService.findAllCommentSByArticleId(articleId);

        Iterator<ArticleComment> iterator = allCommentsByArticleId.iterator();
        while(iterator.hasNext()){                             //去掉失活的评论
            ArticleComment articleComment = iterator.next();
            if(articleComment.getEffective()==0){
                iterator.remove();
            }

        }

        return JsonData.buildSuccess(1,allCommentsByArticleId,"该文章所有评论");
    }

    /**
     * 写评论
     * @param articleId
     * @param request
     * @return
     */
    @ApiOperation("写评论")
    //@ApiImplicitParam(name = "articleId",value = "文章id",required = true,dataType = "int")
    @GetMapping("/articles/{articleId}/comment")
    public JsonData writeComment(@PathVariable("articleId") Integer articleId, HttpServletRequest request,String comContent){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");
                    User user = userService.findByname(name);
                    if(user.getEffective()==0){
                        return JsonData.buildError(0,user,"该账号未通过邮箱激活");
                    }
                    Integer userId = user.getId();

                    if(articleService.findArticleInfotById(articleId).getAllowComment()==0){
                        return JsonData.buildError(0,null,"该文章不允许评论");
                    }

                    ArticleComment articleComment = new ArticleComment();

                    articleComment.setArticleId(articleId);
                    articleComment.setUserId(userId);
                    articleComment.setComContent(comContent);
                    articleComment.setEffective(1);
                    articleComment.setGmtCreate(new Date());
                    articleComment.setGmtModified(new Date());

                    articleCommentService.insertComment(articleComment);

                    return JsonData.buildSuccess(1,null,"评论成功");


                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }


        return JsonData.buildError(0,null,"未登录");
    }




}
