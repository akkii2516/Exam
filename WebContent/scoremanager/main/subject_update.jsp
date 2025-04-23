<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<div class="my-2 text-end px-4">
			<a href="logout.jsp">ログアウト</a>
			</div>
			<form action="SubjectUpdateExecute.action" method="get">

					<div class="mb-3">
						<label for="subject-cd" class="form-label">科目コード</label>
						<div class="form-control bg-light" style="width: 800px;">${cd}</div>

						<div class="mt-2 text-warning">${errors.get("2") }</div>
					</div>

					<div class="mb-3">
						<label for="subject-name" class="form-label">科目名</label>
						<input type="text" class="form-control" id="name" name="name" value="${name}" placeholder="科目名を入力してください" style="width: 800px;" required>
					</div>


					<div class="d-flex gap-2 mb-3">
						<button type="submit" class="btn btn-secondary">変更</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="StudentList.action">戻る</a>
					</div>
			</form>
	</c:param>
</c:import>