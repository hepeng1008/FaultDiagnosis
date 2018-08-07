package com.he.controller;


import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang.StringUtils;

public class BaseController{
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);




    protected static String makeJsonResponse(boolean result, String message) {
        if (result) {
            return "{\"success\":"+result+",\"msg\":\"" + message + "\"}";
        }
        return "{\"success\":"+result+",\"msg\":\"" + message + "\"}";
    }

    protected static String makeJsonObjectResponse(boolean result, String message) {
        if (result) {
            return "{\"success\":"+result+",\"msg\":" + message + "}";
        }
        return "{\"success\":"+result+",\"msg\":" + message + "}";
    }


    public void ajaxJsonResponse(HttpServletResponse response,boolean result,String errMsg){
        writeToPageForResponseBody(makeJsonResponse(result,errMsg),response);
    }


    public void ajaxObjectResponse(HttpServletResponse response,boolean result,String errMsg){
        writeToPageForResponseBody(makeJsonObjectResponse(result,errMsg),response);
    }

    /**
     * 获取
     * @param request
     * @return
     */
    public String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder ret = new StringBuilder(128);
            br = request.getReader();

            String line = null;

            while ((line = br.readLine()) != null) {
                if (ret.length() > 0) {
                    ret.append('\n');
                }
                ret.append(line);
            }

            return ret.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 有ResponseBody注解时输出错误信息
     * @param content
     * @param response
     */
    public static void writeToPageForResponseBody(String content, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache

        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(content.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            logger.warn("write to page error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

/*    protected PageObject getPageObject(HttpServletRequest request,
                                         String orderByClause){
        PageObject pageObject = new PageObject();
        String currPageStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        try{
            pageObject.setCurrPage(Integer.valueOf(currPageStr));
            pageObject.setPageSize(Integer.valueOf(pageSizeStr));
        }catch(NumberFormatException e){
            pageObject.setCurrPage(1);
            pageObject.setPageSize(Integer.MAX_VALUE);
        }
        pageObject.addCondition("orderByClause", orderByClause);
        return pageObject;
    }*/


    protected static String makeJsonReResponse(String code, String message) {
        return "{\"code\":\""+code+"\",\"msg\":\"" + message + "\"}";
    }

    public static final String IP_REGEX = "^((([1-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])))\\.((([0-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])))\\.((([0-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5]))|\\*)\\.((([0-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5]))|\\*)$";
    /**
     * @param request
     * @return ip address
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            ip = ip.trim().replaceAll("\\s", "");
            String[] ipList = ip.split(",");
            for(int i=0; i<ipList.length; i++){
                if(!ipList[i].equalsIgnoreCase("unknown") && ipList[i].matches(IP_REGEX)) {
                    ip = ipList[i];
                    break;
                }
            }
        }
        if(ip == null || StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRealIpV2(HttpServletRequest request) {
        String accessIP = request.getHeader("x-forwarded-for");
        if (null == accessIP)
            return request.getRemoteAddr();
        return accessIP;
    }




}
