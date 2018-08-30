<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.userid }">
	<c:redirect url="../../login.jsp" />
</c:if>

<div style="width: 100%">
<link rel="stylesheet" href="page/articles/tinyeditor.css">
<script src="page/articles/tiny.editor.packed.js"></script>
<form role="form" action="./editarticle?article=${article.postId}" method="post" onsubmit="validatesForm()">
	<div class="form-group">
		<label>Article Name</label> 
		<input name="articlename" type="text" class="form-control" value="${article.postTitle}">
	</div>
	<div class="form-group">
		<textarea name="textarea" id="tinyeditor" style="width: 100%; height: 200px">${article.postContent}</textarea>
		<script>
			var editor = new TINY.editor.edit('editor', {
				id : 'tinyeditor',
				width : 600,
				//height : 175,
				cssclass : 'tinyeditor',
				controlclass : 'tinyeditor-control',
				rowclass : 'tinyeditor-header',
				dividerclass : 'tinyeditor-divider',
				controls : [ 'bold', 'italic', 'underline',
						'strikethrough', '|', 'subscript', 'superscript',
						'|', 'orderedlist', 'unorderedlist', '|',
						'outdent', 'indent', '|', 'leftalign',
						'centeralign', 'rightalign', 'blockjustify', '|',
						'unformat', '|', 'undo', 'redo', 'n', 'font',
						'size', 'style', '|', 'image', 'hr', 'link',
						'unlink', '|', 'print' ],
				footer : true,
				fonts : [ 'Verdana', 'Arial', 'Georgia', 'Trebuchet MS' ],
				xhtml : true,
				cssfile : 'custom.css',
				bodyid : 'editor',
				footerclass : 'tinyeditor-footer',
				toggle : {
					text : 'source',
					activetext : 'wysiwyg',
					cssclass : 'toggle'
				},
				resize : {
					cssclass : 'resize'
				}
			});
			
			function validatesForm(){
				editor.post();
			}
		</script>
	</div>
	<div>
		<button type="submit" class="btn btn-primary">Edit Article</button>
		<a href="./getarticles"><button type="button" class="btn btn-default">Cancel</button></a>
	</div>
</form>
</div>
