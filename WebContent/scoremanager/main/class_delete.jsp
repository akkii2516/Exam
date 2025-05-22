<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報削除</h2>

			<form action="ClassDeleteExecute.action" method="post">
    <div class="col-5" style="margin-left: 15px; margin-top: 30px" id="filter">
        <div class="d-flex gap-2 mb-3" style="margin-top:40px">
            <p>「${classnum.class_num}」を削除してもよろしいですか？</p>
        </div>

        <div class="d-flex gap-2 mb-3" style="margin-top:-15px">
            <button type="submit" class="btn btn-danger">削除</button>
        </div>

        <div class="col-4" style="margin-top:80px">
            <a href="ClassList.action">戻る</a>
        </div>

        <div>
            <input type="hidden" name="class_num" value="${classnum.class_num}" />
        </div>
    </div>
</form>

		</section>
	</c:param>
</c:import>
