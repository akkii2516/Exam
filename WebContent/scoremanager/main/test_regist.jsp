<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">成績管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me-4">
			<div class="my-2 text-end px-4">
				<a href="TestRegist.action" class="btn btn-primary">テスト登録</a>
			</div>
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
					<div class="col-4">
						<label class="form-label" for="select-year">入学年度</label>
						<select class="form-select" id="select-year" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
					<div class="col-4">
						<label class="form-label" for="select-class">クラス</label>
						<select class="form-select" id="select-class" name="f2">
							<option value="0">--------</option>
							<c:forEach var="cls" items="${class_list}">
								<option value="${class}" <c:if test="${class == f2}">selected</c:if>>${class}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
					<div class="col-4">
						<label class="form-label" for="select-subject">科目</label>
						<select class="form-select" id="select-subject" name="f3" style="width: 200%; max-width: none;">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_list}">
								<option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
					<div class="col-4">
						<label class="form-label" for="select-times">回数</label>
						<select class="form-select" id="select-times" name="f4">
							<option value="0">--------</option>
							<c:forEach var="times" items="${times_list}">
								<option value="${times}" <c:if test="${times == f4}">selected</c:if>>${times}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="col-2 text-center">
					<button class="btn btn-secondary" id="filter-button">検索</button>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</form>

			<c:choose>
				<c:when test="${tests.size() > 0}">
					<div>検索結果：${tests.size()}件</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>科目</th>
							<th>回数</th>
						</tr>
						<c:forEach var="test" items="${tests}">
							<tr>
								<td>${test.entYear}</td>
								<td>${test.class}</td>
								<td>${test.subject}</td>
								<td>${test.number_of_times}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>検索結果がありません。</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
<c:choose>
<c:when test="${not empty tests}">
  <div>検索結果：${tests.size()}件</div>

  <form action="TestRegistUpdateExecute.action" method="get">
    <div class="d-flex" style="margin: 30px 0 20px 15px; gap: 30px;" id="filter">

      <!-- 入学年度 -->
      <div class="d-flex flex-column">
        <label class="form-label" for="student-ent_year">入学年度</label>
        <input type="text" id="student-ent_year" name="ent_year"
               value="${test.entYear}"
               style="border: none;" readonly>
      </div>

      <!-- クラス -->
      <div class="d-flex flex-column">
        <label class="form-label" for="test-class">クラス</label>
        <input type="text" id="test-class" name="class"
               value="${test.classNum}"
               style="border: none;" readonly>
      </div>

      <!-- 学生番号 -->
      <div class="d-flex flex-column">
        <label class="form-label" for="test-no">学生番号</label>
        <input type="text" id="test-no" name="no"
               value="${test.no}"
               style="border: none;" readonly>
      </div>

      <!-- 氏名 -->
      <div class="d-flex flex-column">
        <label class="form-label" for="test-name">氏名</label>
        <input type="text" id="test-name" name="name"
               value="${test.name}"
               style="border: none;" readonly>
      </div>
		<!-- 点数入力 -->
		<div class="d-flex flex-column">
		  <label class="form-label" for="test-point">点数</label>
		  <input type="number" id="test-point" name="point"
		         value="${test.point}" min="0" max="100"
		         style="margin-bottom: 20px;">
		</div>

    </div>
		<div class="col-2 text^center">
			<button class="btn btn-secondary" id="filter-button">登録して終了</button>
		</div>
  </form>

  <table>
    <!-- 表の内容 -->
  </table>
</c:when>

  <c:otherwise>
    <div>検索結果がありません。</div>
  </c:otherwise>
</c:choose>

</c:import>
