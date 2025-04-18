<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

			<form action="StudentCreateExecute.action" method="get">
				<div class="col-4" style="margin-left: 15px; margin-top: 30px" id="filter">
					<div class="mb-3">
						<label for="student-ent_year-select" class="form-label">入学年度</label>
						<select class="form-select" id="student-ent_year-select" name="ent_year" style="width: 800px;">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("1") }</div>
					</div>

					<div class="mb-3">
						<label for="student-no" class="form-label">学生番号</label>
						<input type="text" class="form-control" id="student-no" name="no" value="${no}" placeholder="学生番号を入力してください" style="width: 800px;" required>
						<div class="mt-2 text-warning">${errors.get("2") }</div>
					</div>

					<div class="mb-3">
						<label for="student-name" class="form-label">氏名</label>
						<input type="text" class="form-control" id="student-name" name="name" value="${name}" placeholder="氏名を入力してください" style="width: 800px;" required>
					</div>

					<div class="mb-3">
						<label for="student-class_num-select" class="form-label">クラス</label>
						<select class="form-select" id="student-class_num-select" name="class_num" style="width: 800px;">
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="d-flex gap-2 mb-3">
						<button type="submit" class="btn btn-secondary">登録して終了</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="StudentList.action">戻る</a>
					</div>
				</div>
			</form>
		</session>
	</c:param>
</c:import>
