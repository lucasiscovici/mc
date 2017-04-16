hljs.initHighlightingOnLoad();
env.highlight={}

$(function(){
	env.highlight.apply = function(){$('pre code').each(function(i, e) {hljs.highlightBlock(e);});}
});