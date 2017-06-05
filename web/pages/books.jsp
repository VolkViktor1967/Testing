<%-- 
    Document   : books
    Created on : May 17, 2017, 4:21:09 PM
    Author     : User
--%>

<%@page import="enums.SearchType"%>
<%@page import="enums.SearchType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="beans.BookList"%>
<%@page import="beans.Book"%>
<%@page import="beans.GanreList"%>
<%@page import="beans.Ganre"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../WEB-INF/jspf/left_page.jspf" %>


<jsp:useBean id="bookList" class="beans.BookList" scope="page" />

<%@include file="../WEB-INF/jspf/letters.jspf" %>

<%request.setCharacterEncoding("UTF-8");
 
long genreId=0L;

try{
  genreId=Long.valueOf(request.getParameter("genre_id"));
}catch(Exception ex){
    ex.printStackTrace();
}

%>


<div class="sidebar2">
    
       
        
        <% 
            
            ArrayList<Book> list =null;
            
            if (request.getParameter("search_string")!=null){
                String searchStr = request.getParameter("search_string");
                SearchType type = SearchType.TITLE;
                if (request.getParameter("search_option").equals("Автор")){
                    type = SearchType.AUTHOR;
                }
                if ((searchStr!=null)&&(!searchStr.trim().equals("")))                        {
                    list = bookList.getBookBySearch(searchStr,type);
                }
            }
            else{
                if (request.getParameter("letters")!=null){
                    String searchLetters = request.getParameter("letters");
                    if ((searchLetters!=null)&&(!searchLetters.trim().equals("")))                        {
                        list = bookList.getBookByLetters(searchLetters);
                    }
                }
                else{
                   if (genreId!=0){
                    list = bookList.getBookByGenre(genreId);    
                   }else {
                     list = bookList.getAllBook();    
                  }
                }
            } 
        %>
        <h5 style="text-align: left; margin-top: 5px;">Найдено книг <%=list.size()%></h5>
        <%
            session.setAttribute("currentBookList", list);
            for (Book book : list) {
        %>
        <div class="book_info">
            
            
            
            <div class="book_title">
                <p><%=book.getName()%></p>
            </div>
            <div class="book_image">
                <%-- <img src="../images/Стив Джобс.jpg" width="76" height="97" > --%>
                <a href="content.jsp?index=<%=list.indexOf(book)%>"> <img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book)%>" width="76" height="97" ></a>
                
            </div>
            <div class="book_details">
                <br><strong>Автор:</strong><%=book.getAuthor()%> 
                <br><strong>ISBN:</strong><%=book.getIsbn()%> 
                <br><strong>Издательство:</strong><%=book.getPublisher()%> 
                <br><strong>Жанр:</strong><%=book.getGenre()%> 
                <p style="margin: 10px;"> <a href="content.jsp?index=<%=list.indexOf(book)%>">Читать</a></p>
                
                
                
            </div>
            <br>
            
                  
        </div>  <%-- book_info --%>    
        
        <%}%>
    
</div>

