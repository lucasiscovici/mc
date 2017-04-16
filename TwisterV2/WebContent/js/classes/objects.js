function replace_motif(key,d,ou){
		var find = "{{"+key+"}}";
    	var re = new RegExp(find, 'g');
    	return ou.replace(re,d);
    	
	}

function template(arr,name){
    html = $("[data-template='"+name+"']").html();
    for(key in arr){
       html = replace_motif(key,arr[key],html);
    }
    return html;
}