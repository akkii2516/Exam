<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">

		<%-- <section class="me-4"> --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			<form action="SubjectCreateExecute.action" method="get" style="margin-left:20px">

					<div class="mb-3">
						<label for="subject-cd" class="form-label">科目コード</label>
						<input type="text" maxlength="3" class="form-control" id="cd" name="cd" value="${cd}" placeholder="科目コードを入力してください" style="width: 800px;" required>
						<div class="mt-2 text-warning">${errors.get("er1") }</div>
						<div class="mt-2 text-warning">${errors.get("er2") }</div>
						<%
    Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
    if (errors != null) {
        // エラーメッセージの表示処理
        session.removeAttribute("errors");
    }

    String errorMessage = (String) session.getAttribute("errorMessage");
    if (errorMessage != null) {
        // エラーメッセージの表示処理
        session.removeAttribute("errorMessage");
    }
%>
					</div>

					<div class="mb-3">
						<label for="subject-name" class="form-label">科目名</label>
						<input type="text" maxlength="20" class="form-control" id="name" name="name" value="${name}" placeholder="科目名を入力してください" style="width: 800px;" required>
					</div>

					<% session.removeAttribute("cd"); %>
					<% session.removeAttribute("name"); %>


					<button class="btn btn-secondary" name="end" id="filter-button"
						style="writing-mode: horizontal-tb; white-space: nowrap; background-color: #007BFF; color: white;">
						登録
					</button>

					<div class="col-4" style="margin-top:10px">
						<a href="SubjectList.action">戻る</a>
					</div>

			</form>
	</c:param>
</c:import>
