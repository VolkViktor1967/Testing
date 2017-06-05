<%-- 
    Document   : content
    Created on : May 25, 2017, 9:13:41 AM
    Author     : User
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_page.jspf" %>
<div class="sidebar2">  
    <%
        int index = Integer.valueOf(request.getParameter("index"));
    %>
    <%--
    <p style="margin: 10px;"> <a href="<%=request.getContextPath()%>/PdfContent?index=<%=index%>">Читать внимательно</a></p>
    --%>
    <iframe 
        
            src="<%=request.getContextPath()%>/PdfContent?index=<%=index%>"
            style="width: 100%; height: 400px;" frameborder="0">Ваш браузер не поддерживает фреймы</iframe
    <p>Александр Клименков - Четырнадцать</p>
    <audio controls>
        <source src="../audio/01.mp3" type="audio/mpeg">
        Тег audio не поддерживается вашим браузером. 
        <a href="audio/music.mp3">Скачайте музыку</a>.
    </audio>
    
</div>

