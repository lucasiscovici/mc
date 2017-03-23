<div id="postModal" class="hidden" style="position:fixed;width:100vw;height:100vh;top:0px;left:0px;background-color: #161616;z-index: 277;">
          <form id="form_post" class="form-horizontal" role="form" action="addpost" method="GET" >
                                    <h4><input class="inputTitle" type="text" id="form_title" placeholder="Post's Title" name="title" /></h4>
                                   <div id="editor" style="width:100%;height:80vh"></div>
                                     <div class="form-group" style="padding:1px;">
                                    <input type="hidden" id="key" value="${key}" name="key"/>
                                    </div>
                                      <div class="form-group">
                                    <div style="float: left;">
	                                    <select name="lg" id="form_lg" onchange="if(this.value!='rien') {setSessionMode(this.value);};" class="langageSelect">
	                                    	<option value="rien">Quel Language ?</option>
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
		background-color: rgb(229, 229, 229);
	}
</style>