package tech.ychen.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ychen.blog.dto.TagDto;
import tech.ychen.blog.entiy.JsonData;
import tech.ychen.blog.entiy.Tag;
import tech.ychen.blog.service.TagService;

import java.util.Date;
import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 10:02
 */
@SuppressWarnings("unused")
@Api(tags = "管理员模块")
@RestController
@RequestMapping("admin/api/v1")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    /**
     * 增加tag
     * @param tagDto
     * @return
     */
    @ApiOperation("增加tag")
    @PostMapping("/tags")
    public JsonData insertTag(@RequestBody TagDto tagDto){

        Tag tag = new Tag();
        tag.setTagName(tagDto.getTagName());
        tag.setNumber(0);
        tag.setEffective(0);
        tag.setGmtCreate(new Date());
        tag.setGmtModified(new Date());

        tagService.insertTag(tag);

        return JsonData.buildSuccess(1,tag,"新增tag成功");
    }


    /**
     * 列出所有tag
     * @return
     */
    @ApiOperation("列出所有tag")
    @GetMapping("/tags")
    public JsonData listTag(){

       List<Tag> list = tagService.list();

       return JsonData.buildSuccess(1,list,"所有tag");

    }





    /**
     * 取消tag权限
     * @param tagName
     * @return
     */
    @ApiOperation("取消tag权限")
    @PutMapping("/tags/{tagName}")
    public JsonData cancelTagEffictive(@PathVariable("tagName") String tagName){

        Tag tag = tagService.selectTagByName(tagName);
        tag.setEffective(0);
        tag.setGmtModified(new Date());
        tagService.updateTag(tag);

        return JsonData.buildSuccess(1,tag,"权限更新成功");
    }








}
