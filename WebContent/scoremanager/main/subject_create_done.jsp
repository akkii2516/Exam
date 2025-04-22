<%-- 学生登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<div id="wrap_box">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">科目情報登録</h2>
			<div class="my-2 text-end px-4">
			<a href="logout.jsp">ログアウト</a>
			</div>
			<div id="wrap_box">
				<p class="text-center" style="background-color:#66CC99; margin-bottom: 140px;">登録が完了しました</p>
				<a href = "SubjectCreate.action" style="margin-right: 70px;">戻る</a>
				<a href = "SubjectList.action">科目一覧</a>
			</div>
		</div>
	</c:param>
</c:import>