ace.require("ace/ext/language_tools");
 var editor = ace.edit("editor");
editor.setTheme("ace/theme/chaos");
var editor2 = ace.edit("editor2");
editor2.setTheme("ace/theme/chaos");
var editorDiv = document.getElementById("editor");     // its container
var doc = editor.getSession().getDocument();  // a reference to the doc
var sess = editor.getSession();
editor.setOption("showPrintMargin", false)
editor2.setOption("showPrintMargin", false)

function setSessionMode(lg){
	sess.setMode("ace/mode/"+lg);
}
var sess2 = editor2.getSession();
function setSessionMode2(lg){
	sess2.setMode("ace/mode/"+lg);
}
editor2.setOptions({
	readOnly: true	
});
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