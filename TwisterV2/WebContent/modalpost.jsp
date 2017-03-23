<div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width: 100%; height: 100%;">
  <div class="modal-content" style="width: 100%; height: 100%;">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			Update Status
      </div>
      <div class="modal-body">
          <form id="form_post" class="form-horizontal" role="form" action="addpost" method="GET" >
                                    <h4><input class="inputTitle" type="text" id="form_title" placeholder="Post's Title" name="title" /></h4>
                                   <div id="editor" style="width:100%;"></div>
                                     <div class="form-group" style="padding:1px;">
                                    <input type="hidden" id="key" value="${key}" name="key"/>
                                    </div>
                                      <div class="form-group">
                                    <div style="float: left;">
	                                    <select name="lg" id="form_lg" class="langageSelect">
	                                    	<option value="rien">Quel Language ?</option>
	                                    	<option value="java">Java</option>
	                                    	<option value="javascript">Javascript</option>
	                                    	<option value="c">C</option>
	                                    	<option value="php">Php</option>
	                                    	<option value="bash">Bash</option>
	                                    </select>
                                    </div>
                                    
                                    <button style="margin-right: 30px; width: 80px;" class="btn btn-primary pull-right" type="button" id="post">Post</button>
                                 </div>
                                  </form>
      </div>
      <div class="modal-footer">
          <div>
          <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
            <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
		  </div>	
      </div> 
  </div>
  </div>
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