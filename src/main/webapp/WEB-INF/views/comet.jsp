<%@ page import="com.he.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Hepeng
  Date: 2018/8/7 0007
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<html>

<script type="text/javascript" src="${js }/comet4j.js"></script>
<script type="text/javascript" src="${js }/jquery.min.js"></script>


<script type="text/javascript">
    var count = 0;
    window.onload = function(){
        // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>
        JS.Engine.start('${ctx}/conn');
        <%

             //保存用户id到session中

            User user = (User) request.getAttribute("user");
            session.setAttribute("currentUserId",user.getId());
            System.out.println(user.getId());
        %>
        // 监听后台某个频道
        JS.Engine.on('start',function(cId, channelList, engine){
            alert('连接已建立，连接ID为：' + cId);
        });
        JS.Engine.on('stop',function(cause, cId, url, engine){
            alert('连接已断开，连接ID为：' + cId + ',断开原因：' + cause + ',断开的连接地址：'+ url);
        });

        JS.Engine.on(
            {
                // 对应服务端 “频道1” 的值 msgCount
                msgCount : function(msgCount){
                    $("#msgCount").html(msgCount);
                },
                // 对应服务端 “频道2” 的值 msgData
                msgData : function(msgData){
                    $("#msgData").html(msgData);
                },
            }
        );
    }
</script>

<body>

消息数量：<span id="msgCount"></span>

消息数据：<span id="msgData"></span>

</body>
</html>
