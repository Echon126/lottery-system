package com.wen.web.lotterysystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;

/**
 * @author admin
 * @date 2018-11-19 11:06
 */
@Controller
public class PageController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PageController.class);

    /**
     * 页面跳转
     *
     * @param pageTemplate
     * @return
     */
    @GetMapping("/page")
    public String pageController(String pageTemplate) throws FileNotFoundException {
        logger.info("页面跳转----" + pageTemplate);
        if (StringUtils.isEmpty(pageTemplate)) {
            throw new FileNotFoundException("模板不存在");
        }
        return pageTemplate;
    }
}
