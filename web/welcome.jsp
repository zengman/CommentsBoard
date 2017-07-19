<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Map" %>
<%@ page import="struts2.vo.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%

    String username = (String)session.getAttribute("user");
     ArrayList<Comment> commentList = (ArrayList) request.getAttribute("commentList");
//    String errormessage = (String)request.getAttribute("errorMessage");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Struts2 Person Login Result</title>
</head>
<body>
<style type = "text/css">

    .welcome{
        background-color: #00c96a;
        font-size: 2.5rem;
        line-height: 1.15;
        margin-bottom: 1.25rem;
        text-decoration: #e5e5e5;
        height:60px;
        text-align: center;
    }
    .welcome h1{
        padding-top: 10px;
    }
    li{
        list-style: none;
    }

</style>
<script src="jquery.min.js"></script>
<script>
    $(function(){
        $('.up').click(function(){
           console.log(this);
           var data_id = $(this).attr('data-id');
           $.ajax({
               type: "POST",
               url : "ThumbUp",
               dataType :"html",
               data : {"id": data_id},
               success: function(){
                   location.reload();
               }
           });
        });
    });
//    function clickthumup(int value){
//        var id_data = $('#thumbUpButton'+value).attr('value');
//        var show = $('#thumbUpNumberShow'+value).attr('value');
//        var id_post ={"id":id_data};
//        $.ajax({
//            type : "POST",
//            url : "ThumbUp",
//            data: id_post,
//            dataType:"json",
//
//        },funtion());
//        document.getElementById('thumbUpNumberShow').value =show+1;
//
//    }
</script>
<link rel="stylesheet" href="bootstrap.css">
<div class="welcome"><h1>Hi <%=username%>, Welcome</h1></div>
<%--Hi:<br>--%>
<%--<%=username%><br>--%>
<%--Welcome... <br>--%>

<div id = "commitDisplay" class="panel panel-success">
    <div class="panel-heading"><P>评论区</P></div>
    <div class="panel-body">
    <% for(int i=0 ; i< commentList.size();i++){ %>
        <% Comment c = commentList.get(i);%>
        <div class="panel panel-primary">
            <div class="panel-heading"><%= c.getId()%></div>
            <div class="panel-body"><%= c.getUsername()%>: <%= c.getComment()%></div>
            <%--<div class="panel-footer">user:<%= c.getUsername()%></div>--%>
            <form action="delComAction">
                <button name="id" value="<%=c.getId()%>" class="btn btn-success">delete</button>
            </form>
            <button  type="button" class="btn btn-default up" data-id="<%=c.getId()%>" >
                <span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>
                <span value="<%=c.getThumbupNumber()%>"><%=c.getThumbupNumber()%></span>
            </button>




        </div>
    <% } %>
    </div>
</div>

<form action="AddComAction">
    <p>评论：</p>
    <%--<p><%=errormessage%></p>--%>
    <div class="input-group" style="text-align: center">
        <textarea class="form-control" name="com.comment" ></textarea>
        <input type="hidden" name="com.username" value="<%=username%>"/>
        <button class="btn btn-success" type="submit" action="AddComAction">submit</button>
    </div>
</form>
</body>
</html>
