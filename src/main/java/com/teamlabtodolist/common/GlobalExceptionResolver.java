package com.teamlabtodolist.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例外のハンドラクラスです。
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
 
    public ModelAndView resolveException(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object object,
                        Exception ex) {
 
        ModelAndView mav = new ModelAndView();
 
        // JSPに表示するメッセージをセットします。
        mav.addObject("message", "予期せぬエラーが発生しました。" +
                        " 詳細：【" + ex + "】");
 
        // 遷移先のJSPを指定します。(error.jspに遷移します。)
        mav.setViewName("error");
        return mav;
 
    }
 
}