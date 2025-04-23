<%-- 学生情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form action="StudentUpdateExecute.action" method="get">
				<div class="col-4" style="margin-left: 15px; margin-top: 30px" id="filter">
					<div class="col-4">
						<label class="form-label" for="student-ent_year">入学年度</label>
						<input type="text" id="student-ent_year" name="ent_year" value="${student.entYear}" style="border: none; margin-left: 20px; margin-bottom: 20px;" readonly>
					</div>
					<label class="form-label" for="student-no">学生番号</label>
					<div class="form-floating mx-10">
						<input type="text" id="student-no" name="no" value="${student.no }" style="border: none; margin-left: 20px; margin-bottom: 20px;" readonly>
					</div>
					<div class="mb-3">
							<label class="form-label" for="student-name-select">氏名</label>
							<input class="form-control" autocomplete="off"
								id="id-input" maxlength="10" name="name"
								style="width: 800px;" type="text" value="${name }" required />
					</div>
					<div class="col-4">
						<label class="form-label" for="student-class_num-select">クラス</label>
						<select class="form-select" style="width: 800px;" id="student-class_num-select" name="class_num">
							<c:forEach var="num" items="${class_num_set }">
								<%-- 現在のnumと選択されていたclass_numが一致していた場合selectedを追記 --%>
								<option value="${num }" <c:if test="${num==class_num }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4" style="margin-top:10px; margin-bottom:20px">
						<label class="form-check-label" for="student-stuent-attend-check">在学中</label>
							<%-- パラメーターstuednt-attendが存在している場合checkedを追記 --%>
							<input class="form-check-input" type="checkbox"
							id="student-attend-check" name="is_attend" value="t"
							<c:if test="${student.attend}">checked</c:if> />
					</div>
					<button class="btn btn-secondary" name="end" id="filter-button"
						style="writing-mode: horizontal-tb; white-space: nowrap; background-color: #007BFF; color: white;">
						変更
					</button>
					<div class="col-4" style="margin-top:10px">
						<a href="StudentList.action">戻る</a>
					</div>
					<div class="mt-2 text-warning">${errors.get("ent_year") }</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>