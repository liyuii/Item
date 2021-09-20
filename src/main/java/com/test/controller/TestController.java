package com.test.controller;

import com.test.common.PageReslt;
import com.test.common.Result;
import com.test.common.StateVlaue;
import com.test.entity.File;
import com.test.entity.Form;
import com.test.service.FileService;
import com.test.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "相关接口")
//@RequestMapping("/file")
public class TestController {

    @Autowired
    TableService tableService;

    @Autowired
    FileService fileService;

    /**
     * 获取列表
     * @return
     */
    @PostMapping("/getPage")
    @ResponseBody
    @ApiOperation(value = "获取用户接口",notes = "获取当前页的所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "页记录数", defaultValue = "10")
    }
    )
    public PageReslt<Form> getPage(@RequestParam("page") int page, @RequestParam("rows") int rows){
        return tableService.tablePage(page,rows);
    }


    /**
     * 到添加表单页面
     * @return
     */
    @GetMapping("/toAddPage")
    public String toAddPage(){
        return "table_add";
    }

    /**
     * 添加表单
     * @return
     */
    @PostMapping("/addPage")
    @ResponseBody
    public Map<String,Object> addPage(Form form,MultipartFile myFile){
        tableService.addTable(form,myFile);
        Map<String,Object> map = new HashMap<>();
        map.put("status","ok");
        return map;
    }

    /**
     * 删除表单
     * @return
     */
    @PostMapping("/deleteTable")
    @ResponseBody
    public Map<String,Object> deleteTable(String id){
        System.out.println("deleteTable");
        tableService.deleteTable(id);
        Map<String,Object> map = new HashMap<>();
        map.put("status","ok");
        return map;
//        return "redirect:table_list";
    }

    /**
     * 到修改表单页面
     * @return
     */
    @GetMapping("/toUpdatePage")
    public String toUpdatePage(String fid, Model model){
        System.out.println("fid：" + fid);
        Form form = tableService.getTableById(fid);
        System.out.println("update");
        System.out.println(form.toString());
        model.addAttribute("form",form);
        System.out.println("end");
        return "table_update";
    }

    /**
     * 修改表单
     * @return
     */
    @PostMapping("/modifyTable")
    @ResponseBody
    public Map<String,Object> modifyTable(Form form,MultipartFile myFile){
        System.out.println("modifyTable");
        tableService.updateTable(form,myFile);
        Map<String,Object> map = new HashMap<>();
        map.put("status","ok");
        return map;
//        return "redirect:table_list";
    }

//    /**
//     * 测试swagger
//     * @return
//     */
//    @PostMapping("/res")
//    @ResponseBody
//    public Form res(Form form){
//        return form;
//    }

    /**
     * 文件上传
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public Result<File> uploadFile(MultipartFile myFile){
        Result<File> result = new Result<>();
        File upload = fileService.upload(myFile);
        return result.setCode(StateVlaue.CODE_SUCCESS).setMessage(StateVlaue.MESSAGE_SUCCESS).setData(upload);
    }

    /**
     * 获取文件列表
     * @return
     */
    @PostMapping("/getFilePage")
    @ResponseBody
    public PageReslt<File> getFilePage(String fileIds){
        PageReslt<File> result = new PageReslt<>();
        List<File> files = fileService.getFilePage(fileIds);
        return result.setRows(files);
    }

}
