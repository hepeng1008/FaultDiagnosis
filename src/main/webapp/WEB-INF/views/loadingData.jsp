<%--
  Created by IntelliJ IDEA.
  User: Hepeng
  Date: 2018/8/8 0008
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/yduiTaglibs.jsp" %>


<html>
<head>
    <title>LoadingData</title>
</head>
<body>
<section class="g-flexview">
</section>

</body>
<%@ include file="/WEB-INF/views/yduiEndTaglibs.jsp" %>

<script type="text/javascript">

/*    $.ajax({
        type: 'POST',
        url: "${ctx }"+"/LoadData",
        dataType: "json",
        data: {},
        beforeSend: function () {
            console.log("hello");
        },
        success: function (data) {
            alert('加载成功' + data.msg);
        },
        complete: function () {
            alert('Complete');
        },
        error:function(result){
            alert(result);
        }
    });*/
    !function ($, win) {
        var dialog = YDUI.dialog;

        $.ajax({
            type: 'GET',
            url:"${ctx }"+"/LoadData",
            dataType: "json",
            data: {},
            beforeSend: function () {
                dialog.loading.open('很快加载好了');
            },
            success: function (data) {
                dialog.alert('加载成功');
                dialog.loading.close();
            },
            complete: function () {
                //dialog.alert('Complete');

            },
            error:function(result){
                alert(result);
                console.log(result);
            }
        });

    }(jQuery, window);
</script>
</html>
