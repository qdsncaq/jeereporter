<%@ page language="java" pageEncoding="UTF-8"%>
<c:if test="${pageItem!=null && pageItem.totalPage!=0 }">
<div class="page-list">
<ul class="pagination pagination-lg">
  <c:choose>  
       <c:when test="${pageItem.previousPageAvailable}">
        <li ><a href="javascript:fillPage(${pageItem.previousPage})">&laquo;</a></li>
       </c:when>
       <c:otherwise>
    	   <li class="disabled"><a href="javascript:void(0)">&laquo;</a></li>
       </c:otherwise>
  </c:choose>
  
  <c:choose>  
        <c:when test="${pageItem.index - 3 >2}">
            <li ><a href="javascript:fillPage(1)">1</a></li>
            <li ><a href="javascript:void(0)">…</a></li>
            <li ><a href="javascript:fillPage(${pageItem.index - 3})">${pageItem.index - 3}</a></li>
            <li ><a href="javascript:fillPage(${pageItem.index - 2})">${pageItem.index - 2}</a></li>
            <li ><a href="javascript:fillPage(${pageItem.index - 1})">${pageItem.index - 1}</a></li>
        </c:when>
        <c:otherwise>
            
            
           <c:choose>  
		        <c:when test="${pageItem.index - 3 <= 0}">
		        </c:when>
		        <c:otherwise>
		        
		            <c:if test="${pageItem.index - 3 != 1}">
                    	<li ><a href="javascript:fillPage(1)">1</a></li>
                    </c:if>
                    <li ><a href="javascript:fillPage(${pageItem.index - 3})">${pageItem.index - 3}</a></li>
		        </c:otherwise>
		     </c:choose> 
		     
		     
           <c:choose>  
		        <c:when test="${pageItem.index - 2 <= 0}">
		        </c:when>
		        <c:otherwise>
                    <li ><a href="javascript:fillPage(${pageItem.index - 2})">${pageItem.index - 2}</a></li>
		        </c:otherwise>
		     </c:choose> 
           <c:choose>  
		        <c:when test="${pageItem.index - 1 <= 0}">
		        </c:when>
		        <c:otherwise>
		            <li ><a href="javascript:fillPage(${pageItem.index - 1})">${pageItem.index - 1}</a></li>
		        </c:otherwise>
		     </c:choose> 
        
        </c:otherwise>
     </c:choose>
     
     
        <li class="active"><a href="javascript:void(0)" class="current">${pageItem.index }</a></li>
        <c:if test="${pageItem.index != pageItem.totalPage && pageItem.totalPage!=0}">
        
             <c:choose>  
		        <c:when test="${(pageItem.index+2) < (pageItem.totalPage - 1) }">
		            <li ><a href="javascript:fillPage(${(pageItem.index+1)})">${(pageItem.index+1)}</a></li>
		            <li ><a href="javascript:fillPage(${(pageItem.index+2)})">${(pageItem.index+2)}</a></li>
		            <li ><a href="javascript:void(0)">…</a></li>
		        </c:when>
		        <c:when test="${(pageItem.index+2) == (pageItem.totalPage - 1) }">
	                <li ><a href="javascript:fillPage(${(pageItem.index+1)})">${(pageItem.index+1)}</a></li>
		            <li ><a href="javascript:fillPage(${(pageItem.index+2)})">${(pageItem.index+2)}</a></li>
	                
		        </c:when>
		        <c:when test="${(pageItem.index+2) == (pageItem.totalPage) }">
	                <li ><a href="javascript:fillPage(${(pageItem.index+1)})">${(pageItem.index+1)}</a></li>
		        </c:when>
		        <c:otherwise>
                    <li ><a href="javascript:fillPage(${(pageItem.totalPage)})">${(pageItem.totalPage)}</a></li>
		        </c:otherwise>
		     </c:choose> 
         </c:if>
     
    <c:choose>  
        <c:when test="${pageItem.nextPageAvailable}">
	        <li><a href="javascript:fillPage(${pageItem.nextPage})">&raquo;</a></li>
        </c:when>
        <c:otherwise>
     	   <li class="disabled"><a href="javascript:void(0)">&raquo;</a></li>
        </c:otherwise>
     </c:choose>
     
  
</ul>
</div>

<input  type="hidden" value="${pageItem.totalItem }" id="totalPageItem" name="totalPageItem"/>
</c:if>