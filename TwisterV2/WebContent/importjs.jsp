				<script src="http://lagencewebetudiante.fr/lucux.js"></script>
		<script src="/TwisterV2/js/bootstrap.min.js"></script>
				<script src="/TwisterV2/js/moment.js"></script>
				<script src="/TwisterV2/js/jquery.cookie.js"></script>
						<script src="/TwisterV2/js/objects.js"></script>
		
		<script src="/TwisterV2/js/scripts.js"></script>
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/highlight.min.js"></script>

<script>hljs.initHighlightingOnLoad();</script>

		<script src="http://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-language_tools.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/4.1.1/masonry.pkgd.min.js"></script>

<script type="text/javascript">
ace.require("ace/ext/language_tools");
 var editor = ace.edit("editor");
editor.setTheme("ace/theme/chaos");
var editorDiv = document.getElementById("editor");     // its container
var doc = editor.getSession().getDocument();  // a reference to the doc
var sess = editor.getSession();
function setSessionMode(lg){
	sess.setMode("ace/mode/"+lg);
}
editor.setOptions({
    enableBasicAutocompletion: true,
    enableSnippets: true,
    enableLiveAutocompletion: false
});
setSessionMode("java");

// editor.on("change", function() {
//     var lineHeight = 16;                 // assuming a 16px line height
//     editorDiv.style.height = lineHeight * doc.getLength() + "px";
//     editor.resize();
// });
editor.$blockScrolling = Infinity;

$("#logout").click(function(){
	$.get("logout",{key:"${key}"},function(f){console.log(f);window.location.href=window.location.href;window.location.reload();});

});
$("#post").click(function(){
	if($("#form_lg").val()=="rien"){
		return;
	}

	//text:_.escape(editor.getValue())}
	$.post($("#form_post").attr("action"),{text:" ",key:$("#key").val(),lg:$("#form_lg").val(),title:$("#form_title").val()},function(f){console.log(f);window.location.href=window.location.href;window.location.reload();});
});
	

$('.grid').masonry({
	 
	  itemSelector: '.grid-item'
	});
</script>