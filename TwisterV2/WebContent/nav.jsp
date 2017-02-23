<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer id="menu">
	<div class="premier">
		<a href="#">TwisterV2</a>
	</div>
	<div class="div">
			<a href="matrix">The matrix</a>

			<a href="#">Me</a>

			<form action="" method="GET">
				<input type="text" name="search" id="search" placeholder="Recherche..." />
			</form>
		
			<a href="#" class="button">Logout</a>
	</div>
</footer>
<style type="text/css">

#search {
	width: 200px;
	height: 45%;
	border: 1px solid white;
	font-size: 21px;
	background-color: transparent;
	color: #ffffff;
	transition: 1.5s;
	text-align: center;

}

#search:hover {
	border: 1px solid white;
	width: 400px;
}

::-webkit-input-placeholder {
   color: white;
}

:-moz-placeholder {
   color: white;  
}

::-moz-placeholder {
   color: white;  
}

:-ms-input-placeholder {  
   color: white;  
}

.button {
	width: 100%;
	height: 100%;
}

#menu {
	width: 100vw;
	height: 50px;
	left: 0px;
	background-color: darkred;
	margin-bottom: 0px;
	position: fixed;
	bottom: 0;
	font-family: cursive;
	font-size: 19px;
}

a {
	text-decoration: none;
	color: white;
}

form {
	margin-top: 6px;
	display: inline;
}

.div {
	float: right;
	margin-top: 10px;
}

.premier {
	display: -webkit-inline-box;
	margin-top: 10px;
}

.div a {
	height: 100%;
	list-style: none;
	right:0px;
	bottom: 0px;
	margin-right: 35px;
	margin-left: 30px;
}
</style>