<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ユーザー情報変更</h2>
			<form action="TeacherUpdateExecute.action" method="post">

			<input type="hidden" name="original_id" value="${id}" />



					<div class="mb-3">
						<label for="teacher-id" class="form-label">ID</label>
						<input type="text" class="form-control" id="id" name="id" value="${teacher.id}" placeholder="IDを入力してください" style="width: 800px;" required>
					</div>

					<div class="mb-3">
						<label for="teacher-password" class="form-label">パスワード</label>
						<input type="text" class="form-control" id="password" name="password" value="${teacher.password}" placeholder="パスワードを入力してください" style="width: 800px;" required>
					</div>

					<div class="mb-3">
						<label for="teacher-name" class="form-label">ユーザー名</label>
						<input type="text" class="form-control" id="name" name="name" value="${teacher.name}" placeholder="ユーザー名を入力してください" style="width: 800px;" required>
					</div>


					<div class="d-flex gap-2 mb-3">
						<button class="btn btn-secondary" name="end" id="filter-button"
						style="writing-mode: horizontal-tb; white-space: nowrap; background-color: #007BFF; color: white;">
						変更
						</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="TeacherList.action">戻る</a>
					</div>
			</form>
	</c:param>
</c:import>