env.func_tools = {};
env.func_tools.inObjToArr =  function(obj,mo){
	g=[];
	for(i=0;i<obj.length;i++){
		g.push(obj[i][mo]);
	}
	return g;
}
env.func_tools.copyTextToClipboard= function(text) {
	  var textArea = document.createElement("textarea");

	  //
	  // *** This styling is an extra step which is likely not required. ***
	  //
	  // Why is it here? To ensure:
	  // 1. the element is able to have focus and selection.
	  // 2. if element was to flash render it has minimal visual impact.
	  // 3. less flakyness with selection and copying which **might** occur if
	  //    the textarea element is not visible.
	  //
	  // The likelihood is the element won't even render, not even a flash,
	  // so some of these are just precautions. However in IE the element
	  // is visible whilst the popup box asking the user for permission for
	  // the web page to copy to the clipboard.
	  //

	  // Place in top-left corner of screen regardless of scroll position.
	  textArea.style.position = 'fixed';
	  textArea.style.top = 0;
	  textArea.style.left = 0;

	  // Ensure it has a small width and height. Setting to 1px / 1em
	  // doesn't work as this gives a negative w/h on some browsers.
	  textArea.style.width = '2em';
	  textArea.style.height = '2em';

	  // We don't need padding, reducing the size if it does flash render.
	  textArea.style.padding = 0;

	  // Clean up any borders.
	  textArea.style.border = 'none';
	  textArea.style.outline = 'none';
	  textArea.style.boxShadow = 'none';

	  // Avoid flash of white box if rendered for any reason.
	  textArea.style.background = 'transparent';


	  textArea.value = text;

	  document.body.appendChild(textArea);

	  textArea.select();

	  try {
	    var successful = document.execCommand('copy');
	    var msg = successful ? 'successful' : 'unsuccessful';
	    console.log('Copying text command was ' + msg);
	  } catch (err) {
	    console.log('Oops, unable to copy');
	  }

	  document.body.removeChild(textArea);
	}

$(function(){
	
	env.func_tools.inArray = function(t,a) {
	return $.inArray(t,a) != -1
	}
});

(function($){
	$.fn.toggleClassAlways = function(cls){
		return $(this).each(function(){
			if(!$(this).hasClass(cls)){
				$(this).addClass(cls)
			}
		})
	}
	$.fn.replace_motif = function(key,d){
    	var e = $(this).html();
    	var df = replace_motif2(key,d,e);
    	$(this).html(df);
    	console.log(df);
	};
	function replace_motif2(key,d,ou){
		var find = "{{"+key+"}}";
    	var re = new RegExp(find, 'g');
    	return ou.replace(re,d);
    	
	}
})(jQuery);

env.func_tools._reload = function(){
	window.location.href=window.location.href;
	window.location.reload();
}
env.func_tools.reload = function(d){
	console.log(d);
	env.func_tools._reload();
}