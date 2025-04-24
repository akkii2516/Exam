<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- base.jsp をインポート（パラメータ付き） -->
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<!-- ① タイトル -->
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧(学生)</h2>

<form action="SearchSubjectServlet" method="get">
<div class="label-column">科目情報</div>
<div class="form-section">
<label>入学年度</label>
<select name="year">
<option value="2021">2021</option>
<option value="2022">2022</option>
<option value="2023">2023</option>
</select>

            <label>クラス</label>
<select name="class">
<option value="201">201</option>
<option value="202">202</option>
<option value="203">203</option>
</select>

            <label>科目</label>
<select name="subject">
<option value="情報処理基礎知識Ⅰ">情報処理基礎知識Ⅰ</option>
<option value="情報処理基礎知識Ⅱ">情報処理基礎知識Ⅱ</option>
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
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>1回</th>
<th>2回</th>
</tr>
</thead>
<tbody>
<c:forEach var="subject" items="${subjects}">
<tr>
<td>${student.ent_year}</td>
<td>${student.class_num}</td>
<td>${student.no}</td>
<td>${student.name}</td>
<td>${test.no}</td>
<td>${subje}</td>
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