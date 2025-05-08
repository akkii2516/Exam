<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

			<form action="SubjectDeleteExecute.action" method="get">
				<div class="col-5" style="margin-left: 15px; margin-top: 30px" id="filter">
					<p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>

					<div class="d-flex gap-2 mb-3">
						<button type="submit" class="btn btn-secondary">削除</button>
					</div>

					<div class="col-4" style="margin-top:10px">
						<a href="SubjectList.action">戻る</a>
					</div>
					<div>
					<input type="hidden" name="subject_cd" value="${subject_cd }" />
					<input type="hidden" name="subject_name" value="${subject_name }" />
				</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
