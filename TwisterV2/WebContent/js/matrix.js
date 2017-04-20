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
	ms=env.messages_list.get(idp);
	post.find("#form_title").val(_.unescape(ms.title));
	post.find("#form_description").html(_.unescape(ms.description));
	editor.setValue(_.unescape(ms.text));
	post.find("#form_lg option[value='"+(ms.language)+"']").prop('selected', true);
	setSessionMode(ms.language);
	post.toggleClass("hidden");
	editor.clearSelection();
}

function removeFriend(id){
	env.my.removeFriend(id,
		function(d){
			if("response" in d){
				env.func_tools.reload(d);
			}
	})
}

function removeComment(id){
	env.comment.removecomment({id_post:id},
		function(d){
			if("response" in d){
				$(".grid-item[data-index='"+(id)+"']").remove();
				//env.func_tools.reload(d);
			}
	})
}

function addFriend(idUser){
	env.friend.addfriend({id_friend:idUser},
		function(d){
			if("response" in d){
				env.func_tools.reload(d);
			}
	})
}

$(function(){
	
	env.me.listusers({},function(d){
		console.log(d);
		a=new env.me.Mes(d);
		env.mes=a;
		env.my=a.users[0];
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
			
    	a=new env.post.Messages(d);
		env.messages_list=a;
    	
		$items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		myid_user=env.my.id;
		for (j in env.messages_list.mess){
			k=env.messages_list.get(j);
			if(k.id_user==myid_user){
				$(".grid-item[data-index='"+(k.id)+"'] .croixSuppression.m").removeClassAlways("hidden");
				$(".grid-item[data-index='"+(k.id)+"'] .modification.m").removeClassAlways("hidden");
			} else {
				$(".grid-item[data-index='"+(k.id)+"'] .removeFriend.m").removeClassAlways("hidden");
				$(".grid-item[data-index='"+(k.id)+"']").replace_motif("id_friend",k.id_user);
			}
		}
		setTimeout(function(){
			
			
			$("#boutonSubmit").click(function(){
				if($("#loginSearch").hasClass("hidden")){
					$("#loginSearch").toggleClass("hidden");
				}
				$("#modalspanSearch").replace_motif("baseImg",env.var.bases.baseImg);
				var text = document.getElementById("srch-term").value;
				var loginExiste = 0;
				var ami = 0;
				dd= {type:"ALL"};
				env.user.listusers(dd,function(d){
					if("response" in d){
						e=new env.user.Users(d);
						env.users_list=e;
						
						for (j in env.users_list.mess){
							w=env.users_list.get(j);
							if(w.login==text){
								loginExiste = 1;
								var myId=env.my.id;
								var idW = w.id;
								$("#modalspanSearch").replace_motif("index",idW);
								
								env.friend.listfriends({}, function(d) {
									if ("response" in d) {
										console.log(d);
										f=new env.friend.Friends(d);
										env.friends_list=f;
										console.log(env.friends_list);
										for (g in env.friends_list.mess) {
											if (env.friends_list.mess[g].id_friend==idW) {
												ami=1;
												if(!$("#existePas").hasClass("hidden")){
													$("#existePas").toggleClass("hidden");
												}
												$("#modalspanSearch").replace_motif("login",text);
												$("#removeFriendSearch").removeClassAlways("hidden");
											}
										}
										
										if (ami!=1) {
											if(!$("#existePas").hasClass("hidden")){
												$("#existePas").toggleClass("hidden");
											}
											$("#modalspanSearch").replace_motif("login",text);
											$("#addFriendSearch").removeClassAlways("hidden");
										}
									}
								});								
							}
						}
						
						if (loginExiste!=1) {
							if(!$("#removeFriendSearch").hasClass("hidden")){
								$("#removeFriendSearch").toggleClass("hidden");
							}
							if(!$("#addFriendSearch").hasClass("hidden")){
								$("#addFriendSearch").toggleClass("hidden");
							}
							$("#existePas").toggleClass("hidden");
							$("#existePas").html("Aucun utilisateur ne correspond à votre recherche.");
							$("#loginSearch").toggleClass("hidden");
						}		
					}
				});

				$("#postsearch").toggleClass("hidden");
				$("#modalspan").replace_motif("baseImg",env.var.bases.baseImg);
			});
			
			
			
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
					myid_user=env.my.id;
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
					
					if ( env.func_tools.inArray(env.my.id, env.func_tools.inObjToArr(j.likes,"id_user"))) {
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