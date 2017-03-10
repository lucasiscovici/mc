<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="/TwisterV2/js/bootstrap.min.js"></script>
		<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
				<script src="/TwisterV2/js/moment.js"></script>
				<script src="/TwisterV2/js/jquery.cookie.js"></script>
						<script src="/TwisterV2/js/objects.js"></script>
		
		<script src="/TwisterV2/js/scripts.js"></script>
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/highlight.min.js"></script>

<script>hljs.initHighlightingOnLoad();</script>

		<script src="http://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"></script>

<script type="text/javascript">
var editor = ace.edit("editor");
editor.setTheme("ace/theme/iplastic");
editor.getSession().setMode("ace/mode/java");
var editorDiv = document.getElementById("editor");     // its container
var doc = editor.getSession().getDocument();  // a reference to the doc

editor.on("change", function() {
    var lineHeight = 16;                 // assuming a 16px line height
    editorDiv.style.height = lineHeight * doc.getLength() + "px";
    editor.resize();
});
//editor.$blockScrolling = Infinity;
$("#logout").click(function(){
	$.get("logout",{key:"${key}"},function(f){console.log(f);window.location.href=window.location.href;window.location.reload();});

});
$("#post").click(function(){
	
	$.post($("#form_post").attr("action"),{key:$("#key").val(),text:_.escape(editor.getValue())},function(f){console.log(f);window.location.href=window.location.href;});
});
$('.grid').masonry({
	 
	  itemSelector: '.grid-item'
	});
</script>