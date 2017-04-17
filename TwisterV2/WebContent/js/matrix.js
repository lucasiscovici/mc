env.comments_list=null;
env.me=null;
function openPostModal(){
	post=$("#postModal");
	post.attr("idp","");
	post.attr("type","0");
	post.find("#form_title").val();
	post.find("#form_description").html();
	editor.setValue("");
	fs=post.find("#form_lg option").eq(0)
	fs.prop('selected', true);
	setSessionMode(fs.attr("value"));
	post.toggleClass("hidden");
}
function suppressionPost(idp){
	env.post.removepost({id:idp},
			function(d){
				if("response" in d){
				
						$(".grid-item[data-index='"+(idp)+"']").remove();
				if(!$("#modalpostLecture").hasClass("hidden")){
					$("#modalpostLecture").toggleClass("hidden");
				}
					//env.func_tools.reload(d);
				}
	})
}

function modificationPost(idp){
	$(".modale").addClassAlways("hidden");
	post=$("#postModal");
	post.attr("idp",idp);
	post.attr("type","2");
	ms=env.comments_list.get(idp);
	post.find("#form_title").val(_.unescape(ms.title));
	post.find("#form_description").html(_.unescape(ms.description));
	editor.setValue(_.unescape(ms.text));
	post.find("#form_lg option[value='"+(ms.language)+"']").prop('selected', true);
	setSessionMode(ms.language);
	post.toggleClass("hidden");
	editor.clearSelection();
}

$(function(){
	
	env.user.listusers({},function(d){
		console.log(d);
		a=new Mes(d);
		env.me=a;
	});

o=0;	


function init(){
	dd ={};
	if(fd=="posts") {
		dd={}
	}else{
		dd= {
				type:"TOTAL"
			};
	}
	
	env.post.listposts(dd,function(d){
		console.log(d);
		if ("response" in d) {
			
    	a=new Messages(d);
		env.messages_list=a;
    	
		$items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		myid_user=env.me.get(0).id;
		for (j in env.messages_list.mess){
			k=env.messages_list.get(j);
			if(k.id_user==myid_user){
				$(".grid-item[data-index='"+(k.id)+"'] .croixSuppression.m").removeClassAlways("hidden");
				$(".grid-item[data-index='"+(k.id)+"'] .modification.m").removeClassAlways("hidden");
			}
		}
		setTimeout(function(){
			
			
			
			$(".title_mess").click(function(){
				//env.highlight.apply();
				id=$(this).attr('data-index');
				
				$("#postModalLecture #modalspan .croixSuppression.mpl").addClassAlways("hidden");
				$("#postModalLecture #modalspan .modification.mpl").addClassAlways("hidden");
				console.log(id);
				console.log(env.messages_list)
				console.log(env.messages_list.get(id));
				mess=env.messages_list.get(id);
				mess.reload();
				mess=env.messages_list.get(id);
				if(mess){
					k=mess;
					myid_user=env.me.get(0).id;
					if(k.id_user==myid_user){
						$("#postModalLecture #modalspan .croixSuppression.mpl").removeClassAlways("hidden");
						$("#postModalLecture #modalspan .modification.mpl").removeClassAlways("hidden");
					}
				$("#postModalLecture").attr("data-index",id);
				
				$("#postModalLecture").toggleClass("hidden");
				
				lg=mess.language;
				if(lg=="c") {
					lg="c_cpp";
				}
				setSessionMode2(lg);
				editor2.setValue(_.unescape(mess.text),-1);
				
				if(mess.description==null){
					$("#modal_desc").parent().hide();
				}else{
				$("#modal_desc").html(mess.description);
				}
				$("#modal_lg").html(mess.language);
				$("#modal_date").html(mess.date.format("dddd DD MMM YYYY \\à HH\\h\\\\mm"));
				
				$("#modal_title").html(mess.title);
				$("#modal_user").html(mess.login);
				$("#likep").html(mess.nb_like);
				$("#comsp").html(mess.nb_comments);
				$("#likes").attr('selectedf',"false");
				$("#likes").css("background-color","black");
				env.auth.kk=false;
				mess.getLikes(env,id,function(j){
					
					if ( env.func_tools.inArray(env.me.get(0).id, env.func_tools.inObjToArr(j.likes,"id_user"))) {
						$("#likes").css("background-color","blue");
						$("#likes").attr('selectedf',"true");
						
					}
					env.auth.kk=true;
				});
				
				$("#modalspan").replace_motif("baseImg",env.var.bases.baseImg);
				$("#modalspan").replace_motif("index",id);
				editor2.clearSelection();
				}
			});
			
		},1000);
		
  }
	});
}
init();






































})