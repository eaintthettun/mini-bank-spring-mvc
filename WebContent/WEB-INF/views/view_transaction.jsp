<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="header.jsp">
	<jsp:param value="VIEW ACCOUNT" name="frmHeading"/>
</jsp:include>
<table border="1" width="75%">
	<!-- <thead>
		<tr>
			<th>No</th>
			<th>Account No</th>
			<th>Transaction Account No</th>
			<th>Tran Amount</th>
			<th>Tran Date</th>
		</tr>
	</thead> -->
	<tbody>
		<c:set var="no" value="1"></c:set>
		<c:forEach var="acc" items="${frmCustTran.accounts}"
		varStatus="vs1">
			<c:forEach var="tran" items="${acc.transferTrans}"
			varStatus="vs2">
				<tr>
					<td>${no}</td>
					<td>
						<b> ${acc.accNo} transferred 
						${tran.tranAmount} mmk. to
						${tran.toTranAccNo} </b>
					</td>
					<td>
						<fmt:formatDate value="${tran.tranDate}" 
						pattern="dd-MM-yyyy hh:mm:ss"/>
					</td>
					<c:set var="no" value="${no+1}"></c:set>
				</tr>
			</c:forEach>
			<c:forEach var="tran" items="${acc.receiveTrans}"
			varStatus="vs3">
				<tr>
					<td>${no}</td>
					<td>
						<b> ${acc.accNo} received 
						${tran.tranAmount} mmk. from
						${tran.fromTranAccNo} </b>
					</td>
					<td>
						<fmt:formatDate value="${tran.tranDate}" 
						pattern="dd-MM-yyyy hh:mm:ss"/>
					</td>
					<c:set var="no" value="${no+1}"></c:set>
				</tr>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>
<%@include file="footer.jsp" %>
