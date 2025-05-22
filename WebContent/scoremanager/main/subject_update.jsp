<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<form action="SubjectUpdateExecute.action" method="get">

					<div class="mb-3">
						<label for="subject-cd" class="form-label">科目コード</label>
						<input type="text" class="form-control" id="subject-cd" name="cd" value="${subject.cd}" style="border: none;width: 800px;" readonly>
						<div class="mt-2 text-warning">${errors.get("f1") }</div>
						<c:remove var="errors" scope="session"/>
					</div>

					<div class="mb-3">
						<label for="subject-name" class="form-label">科目名</label>
						<input type="text" maxlength="20" class="form-control" id="name" name="name" value="${subject.name}" placeholder="科目名を入力してください" style="width: 800px;" required>
					</div>


					<div class="d-flex gap-2 mb-3">
						<button class="btn btn-secondary" name="end" id="filter-button"
						style="writing-mode: horizontal-tb; white-space: nowrap; background-color: #007BFF; color: white;">
						変更
						</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="SubjectList.action">戻る</a>
					</div>
			</form>
	</c:param>
</c:import>