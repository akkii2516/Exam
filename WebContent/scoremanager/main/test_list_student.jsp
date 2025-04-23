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

      <!-- ② 新規登録リンク -->
<div class="my-2 text-end px-4">
<a href="SubjectCreate.action">新規登録</a>
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