<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="errors" value="${sessionScope.errors}" />
<c:remove var="errors" scope="session" />
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

      <!-- 検索フォーム -->
<form method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

          <!-- 入学年度 -->
<div class="col-2">
<label class="form-label" for="student-f1-select">入学年度</label>
<select class="form-select" id="student-f1-select" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${f1}">
<option value="${year}" <c:if test="${year == selectedF1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>

          <!-- クラス -->
<div class="col-2">
<label class="form-label" for="student-f2-select">クラス</label>
<select class="form-select" id="student-f2-select" name="f2">
<option value="0">--------</option>
<c:forEach var="classNum" items="${f2}">
<option value="${classNum}" <c:if test="${classNum == selectedF2}">selected</c:if>>${classNum}</option>
</c:forEach>
</select>
</div>

          <!-- 科目 -->
<div class="col-4">
<label class="form-label" for="student-f3-select">科目</label>
<select class="form-select" id="student-f3-select" name="f3">
<option value="0">--------</option>
<c:forEach var="subject" items="${f3}">
<option value="${subject.cd}" <c:if test="${subject.cd == selectedF3}">selected</c:if>>
                  ${subject.name}
</option>
</c:forEach>
</select>
</div>

          <!-- 回数 -->
<div class="col-2">
<label class="form-label" for="student-count-select">回数</label>
<select class="form-select" id="student-count-select" name="f4">
<option value="0">--------</option>
<c:forEach var="count" items="${f4}">
<option value="${count}" <c:if test="${count == selectedF4}">selected</c:if>>${count}</option>
</c:forEach>
</select>
</div>


    <!-- 検索ボタン (右上に配置、少し左に) -->
    <div class="col-2 d-flex justify-content-end" style="margin-left: -35px;">
      <label class="form-label d-block invisible">検索</label>
      <button class="btn btn-secondary" id="filter-button" style="background-color: #6c757d; color: white;">検索</button>
    </div>
</div>
</form>

<c:if test="${not empty error}">
  <div class="mt-2 text-warning" style = "margin-left:15px">
    ${error}
  </div>
</c:if>

<c:if test="${not empty tests}">
  <div class="mb-3 text-start">
  科目：${tests[0].subject.name}（${selectedF4}回）
  </div>
</c:if>

      <!-- 検索結果表示 -->
<c:choose>

<c:when test="${not empty tests}">

<form action = "TestRegistExecute.action" method="post">
<table class="table table-hover">
<thead>
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>点数</th>
</tr>
</thead>
<tbody>
<c:forEach var="test" items="${tests}">
<tr>
<td>${test.student.entYear}</td>
<td>${test.classNum}</td>
<td>${test.student.no}</td>
<td>${test.student.name}</td>
<td>
<input type="hidden" name="studentNoList" value="${test.student.no}" />
<input type="hidden" name="count" value="${selectedF4}" />
<input type="hidden" name="subject" value="${selectedF3}" />
<input type="number" name="pointList" value="${test.point}" min="0">
<c:if test="${not empty errors[test.student.no]}">
  <div class="mt-2 text-warning">${errors[test.student.no]}</div>
</c:if>
</td>
</tr>
</c:forEach>
</tbody>
</table>
<input type="hidden" name="classNum" value="${selectedF2}" />
<div class="text-start">
  <button type="submit" class="btn btn-secondary">登録して終了</button>
</div>
</form>
</c:when>
      </c:choose>
    </section>
  </c:param>
</c:import>



