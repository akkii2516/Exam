<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- base.jsp をインポート（パラメータ付き） -->
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<!-- ① タイトル -->
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">ユーザー管理</h2>

      <!-- ② 新規登録リンク -->
<div class="my-2 text-end px-4">
<a href="ClassCreate.action">新規登録</a>
</div>

      <!-- ③ 一覧表示 -->
<div class="px-4">
<table class="table table-bordered">
<thead class="table-light">
<tr>
<th>ID</th>
<th>ユーザー名</th>
<th>学校名</th>
</tr>
</thead>
<tbody>
<c:forEach var="teacher" items="${teachers}">
<tr>
<td>${teacher.id}</td>
<td>${teacher.name}</td>
<td>${teacher.school.name}</td>
<td><a href="ClassUpdate.action?classNum=${classNum}">変更</a></td>
<td><a href="ClassUpdate.action?classNum=${classNum}">削除</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</section>
</c:param>
</c:import>