<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<link rel="stylesheet" href="${css}/ydui/ydui.css?rev=@@hash&version=${jsVersion}"/>
<link rel="stylesheet" href="${css }/ydui/base.css?version=${jsVersion}"/>

<script src="${js}/ydui/ydui.flexible.js?version=${jsVersion}"></script>
<script src="${js }/ydui/jquery.min.js?version=${jsVersion}"></script>
<script src="${js }/ydui/common.js?version=${jsVersion}"></script>
<style>
	.toast{position: fixed;left:0.266667rem;right:0.266667rem;bottom:0.266667rem;text-align: center;line-height:0.533333rem;z-index:10000;}
	.toast span{display: inline-block;padding:0.266667rem;border-radius: 5px;background-color:rgba(0,0,0,.65);color: #fff;font-size: 0.373333rem;}
	.mt45{margin-top: 45px;}
</style>
<script>
var ctx;
$(function(){
	ctx= '<c:url value = "/" />';
	if(ctx.indexOf(";jsessionid=")!=-1){
		var reg=/;jsessionid\=([0-9]|[A-z])*/g;
		ctx = ctx.replace(reg,""); 
	}
	$(document.body).append("<p id='errMsg'><span id='mssage'></span></p>");
});


function showErrMsg(msg){
	//YDUI.dialog.alert(msg);
	 /* $("#mssage").text(msg);
	 $("#errMsg").addClass("toast animated");
	 var wait=setInterval(function(){
         if(!$("#errMsg").is(":animated")){
             clearInterval(wait);
             $("#mssage").text("");
             $("#errMsg").removeClass("toast animated");
         }
     },1500); */
}
</script>