<div id="postModal" class="hidden" style="position:fixed;width:100vw;height:100vh;top:0px;left:0px;background-color: #161616;z-index: 27777;">

          <form id="form_post" class="form-horizontal" role="form" action="addpost" method="GET" >
          	<span onclick="$('#postModal').toggleClass('hidden');openModalPost();" style="cursor:pointer"><img style="position: absolute; top: 0; right: 0; margin-top: 10px;" width="30px" src="http://li328.lip6.fr:8280/TwisterV2/img/fermer.png" /></span>
                                    <h4><input class="inputTitle" type="text" id="form_title" placeholder="Post's Title" name="title" /></h4>
                                    <textarea name="description" style="width: 100vw; font-size: 18px;" class="tcenter" id="form_description"  placeholder="#Description..."></textarea>
                                   <div id="editor" style="width:100%;height:74vh; margin-top: 5px;"></div>
                                     <div class="form-group" style="padding:1px;">
                                    <input type="hidden" id="key" value="${key}" name="key"/>
                                    </div>
                                      <div class="form-group">
                                    <div style="float: left;">
	                                    <select name="lg" id="form_lg" onchange="if(this.value!='rien') {setSessionMode(this.value);};" class="langageSelect">
	                                    	<option value="java" selected>Java</option>
	                                    	<option value="javascript">Javascript</option>
	                                    	<option value="c_cpp">C</option>
	                                    	<option value="php">Php</option>
	                                    	<option value="sh">Shell</option>
	                                    </select>
                                    </div>
                                    
                                    <button style="margin-right: 30px; width: 80px;" class="btn btn-primary pull-right" type="button" id="post">Post</button>
                                 </div>
                                  </form>
    
</div>


 <style>
	.hh{
		height:90vh !important;
	}
	#editor{
		z-index: 999;
		height: 150px;
	}
	#form_post{
		z-index: 999;
	}
	.langageSelect {
		margin-left: 30px;
		background-color: #3B5999;
		color: #ffffff;
		height: 35px;
	}
	.inputTitle {
		border: 0;
		color: #000000;
		left: 50%;
		width: 100%;
		text-align: center;
		height: 30px;
		vertical-align: middle;
		background-color: white;
	}
</style>


<script type="text/javascript">

function openModalPost(){
	$("#form_title").val('');
	$("#form_description").val('');
	
	setTimeout(function(){
		editor.setValue("");
	},1000);
}

</script>