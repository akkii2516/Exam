<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス登録</h2>

			<form action="ClassCreateExecute.action" method="post">
				<div class="col-4" style="margin-left: 15px; margin-top: 30px" id="filter">

            <div class="mb-3">
			<label for="class-no" class="form-label">クラス</label>
			<input type="text" class="form-control" id="class_num" name="class_num" value="${ClassNum.class_num}" placeholder="クラス名を入力してください" style="width: 800px;" required>
			<div class="mt-2 text-warning">${errors.get("er2") }</div>
			</div>
					<div class="d-flex gap-2 mb-3">
						<button type="submit" class="btn btn-secondary">登録して終了</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="ClassList.action">戻る</a>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
