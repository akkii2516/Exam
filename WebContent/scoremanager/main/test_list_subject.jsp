<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- base.jsp をインポート（パラメータ付き） -->
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<!-- ① タイトル -->
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧(科目)</h2>

<form action="SearchSubjectServlet" method="get">
<div class="label-column">科目情報</div>
<div class="form-section">
<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="fotm-select" if="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set }">
							<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
							<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>

            <label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>

            <label class="form-label" for="student-f2-select">科目</label>
						<select class="form-select" id="student-f2-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${list }">
								<%-- 現在のsubject.cdと選択されていたf3が一致していた場合selectedを追記 --%>
								<option value="${subject.cd }" <c:if test="${subject.cd==f3 }">selected</c:if>>${subject.name }</option>
							</c:forEach>
						</select>

            <button type="submit" class="search-button">検索</button>
</div>
</form>

    <%-- <form action="SearchStudentServlet" method="get"> --%>
<div class="form-section">
<div class="label-column">学生情報</div>
<label>学生番号</label>
<input type="text" name="studentId" placeholder="学生番号を入力してください" />
<button type="submit" class="search-button">検索</button>
</div>

      <!-- ③ 一覧表示 -->
<div class="px-4">
<table class="table table-bordered">
<thead class="table-light">
<tr>
<th>科目名</th>
<th>科目コード</th>
<th>回数</th>
<th>点数</th>
</tr>
</thead>
<tbody>
<c:forEach var="subject" items="${subjects}">
<tr>
<td>${subject.name}</td>
<td>${subject.cd}</td>
<td>${student.no}</td>
<td>${test.point}</td>
<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
<td><a href="SubjectDelete.action?cd=${subject.cd}" onclick="return confirm('削除してもよろしいですか？');">削除</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</section>
</c:param>
</c:import>