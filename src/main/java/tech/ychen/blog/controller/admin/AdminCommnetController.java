package tech.ychen.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ychen.blog.entiy.ArticleComment;
import tech.ychen.blog.entiy.JsonData;
import tech.ychen.blog.service.ArticleCommentService;

/**
 * @author leon
 * @date 2019-04-09 14:34
 */
@SuppressWarnings("unused")
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin/api/v1")
public class AdminCommnetController {

    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 根据id将一条评论失活
     * @param id
     * @return
     */
    @ApiOperation("根据id将一条评论失活")
    @PutMapping("comment/{id}")
    public JsonData cancelComEffective(@PathVariable("id") Integer id){


        ArticleComment articleComment = articleCommentService.findArticleCommentById(id);

        articleComment.setEffective(0);

        articleCommentService.updateArticleComment(articleComment);

        return JsonData.buildSuccess(1,articleComment,"该条评论已失活");
    }
}
