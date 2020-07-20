package com.ljq.demo.springboot.web.controller;

import com.github.pagehelper.PageInfo;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.common.page.PageUtil;
import com.ljq.demo.springboot.entity.ArticleEntity;
import com.ljq.demo.springboot.service.ArticleService;
import com.ljq.demo.springboot.vo.article.ArticleListParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章表
 * 
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Controller
@RequestMapping(value = "/api/article")
@Slf4j
@Api(value = "文章表控制层", tags = "文章表控制层")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


    /**
     * 查询列表
     *
     * @param articleListParam
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "文章表查询列表",  notes = "文章表查询列表")
    @ResponseBody
    public ResponseEntity<ApiResult<PageInfo<ArticleEntity>>> list(ArticleListParam articleListParam) throws Exception {
        ApiResult apiResult = articleService.list(articleListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表-对比测试-2
     *
     * @param articleListParam
     * @return
     */
    @RequestMapping(value = "/list/2", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "文章表查询列表-对比测试-2",  notes = "文章表查询列表-对比测试-2")
    @ResponseBody
    public ResponseEntity<ApiResult<PageUtil<ArticleEntity>>> list2(ArticleListParam articleListParam) throws Exception {
        ApiResult apiResult = articleService.list2(articleListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表-对比测试-3
     *
     * @param articleListParam
     * @return
     */
    @RequestMapping(value = "/list/3", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "文章表查询列表-对比测试-3",  notes = "文章表查询列表-对比测试-3")
    @ResponseBody
    public ResponseEntity<ApiResult<PageUtil<ArticleEntity>>> list3(ArticleListParam articleListParam) throws Exception {
        ApiResult apiResult = articleService.list3(articleListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表-对比测试-4
     *
     * @param articleListParam
     * @return
     */
    @RequestMapping(value = "/list/4", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation(value = "文章表查询列表-对比测试-4",  notes = "文章表查询列表-对比测试-4")
    @ResponseBody
    public ResponseEntity<ApiResult<PageUtil<ArticleEntity>>> list4(ArticleListParam articleListParam) throws Exception {
        ApiResult apiResult = articleService.list4(articleListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }



}
