<%@taglib uri="http://www.springframework.org/tags/form" 
prefix="sform"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp">
	<jsp:param value="TRANSFER" name="frmHeading"/>
</jsp:include>
<sform:form modelAttribute="frmTransfer" method="post" action="transferPath">
	Select From account:
	<sform:select path="frmFromAccNo">
		<sform:options items="${frmTransfer.frmAccounts}" itemLabel="accNo"
		itemValue="accNo"/>
	</sform:select><br><br>
	
	<c:if test="${frmType=='own'}">
		Select To account:
		<sform:select path="frmToAccNo">
			<sform:options items="${frmTransfer.frmAccounts}" itemLabel="accNo"
			itemValue="accNo"/>
		</sform:select> <br><br>
		Enter Transfer Amount:<sform:input path="frmAmount"/><br><br>
		<input type="submit" value="Transfer"/>
	</c:if>
	<c:if test="${frmType=='other'}">
		Enter To account:<sform:input path="frmToAccNo"/><br><br>
		Enter Transfer Amount:<sform:input path="frmAmount"/><br><br>
		<input type="submit" value="Transfer"/>
	</c:if>
	<input type="hidden" name="type" value="${frmType}"/>
</sform:form>
<font style="color:red">${frmTransfer.frmErr}</font>
<%@include file="footer.jsp"%>