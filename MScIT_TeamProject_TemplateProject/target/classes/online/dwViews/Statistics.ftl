<html>
<head>
	<meta charset="utf-8" />
	<title>Top Trumps</title>

	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	<!-- Styling -->
	<style>
		body {
		  background-color: black;
			position:relative;
			position:fixed;
			height:100%;
			width:100%;
			left:0;
			top:0;
			z-index:1;
			color: white;
		}

		br {
			line-height: 50%;
		}

		h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
		  font-family: inherit;
		  font-weight: 500;
		  line-height: 1.1;
		  color: inherit
		}

		h1 small, h1 .small, h2 small, h2 .small, h3 small, h3 .small, h4 small, h4 .small, h5 small, h5 .small, h6 small, h6 .small, .h1 small, .h1 .small, .h2 small, .h2 .small, .h3 small, .h3 .small, .h4 small, .h4 .small, .h5 small, .h5 .small, .h6 small, .h6 .small {
		  font-weight: 400;
		  line-height: 1;
		  color: #777
		}

		h1, .h1, h2, .h2, h3, .h3 {
		  margin-top: 20px;
		  margin-bottom: 10px
		}

		h1 small, h1 .small, .h1 small, .h1 .small, h2 small, h2 .small, .h2 small, .h2 .small, h3 small, h3 .small, .h3 small, .h3 .small {
		  font-size: 65%
		}

		h4, .h4, h5, .h5, h6, .h6 {
		  margin-top: 10px;
		  margin-bottom: 10px
		}

		h4 small, h4 .small, .h4 small, .h4 .small, h5 small, h5 .small, .h5 small, .h5 .small, h6 small, h6 .small, .h6 small, .h6 .small {
		  font-size: 75%
		}

		h1, .h1 {
		  font-size: 36px
		}

		h2, .h2 {
		  font-size: 30px
		}

		h3, .h3 {
		  font-size: 24px
		}

		h4, .h4 {
		  font-size: 18px
		}

		h5, .h5 {
		  font-size: 14px
			font-family: "Impact", Times, serif;
		}

		h6, .h6 {
		  font-size: 12px
		}

		p {
		  margin: 0 0 10px
		}

	/* background cover header */
	img {
		opacity: 0.5;
		width: 100%;
	  height: auto;
		vertical-align: middle;
		background-size: cover;
		background-position: top center;
		background-repeat: no-repeat;
		position:absolute;
		left:0px;
		top:0px;
		z-index:-1;
	}

	/* navigation bar */
	*{
	  margin: 0;
		padding: 0;
		outline: none;
		border: none;
		-webkit-box-sizing: border-box;
	}

	*:before,
	*:after{
		-webkit-box-sizing: border-box;
	}

	.clearfix {
	  *zoom: 1;
	}

	.clearfix:before,
	.clearfix:after {
	  display: table;
	  line-height: 0;
	  content: "";
	}

	.clearfix:after {
	  clear: both;
	}

	.container{
		width: 80%;
		margin: 0 auto;
	}

	header{
		width: 100%;
		height: auto;
		background: black;
	}

	.header-left,
	.header-right{
		position: relative;
		color: white;
		float: left;
	}

	.header-left{
		width: 30%;
	}

	.header-right label{
		position: absolute;
		top: -3.7em;
		right: 0;
		cursor: pointer;
	}

	.header-right span{
		position: relative;
		width: 2em;
		height: 2em;
		background: rgba(255,255,255,.3);
		-webkit-transition: all .3s ease;
	}

	.header-right span:hover{
		background: rgba(255,255,255,.6);
	}

	.header-right span:before,
	.header-right span:after{
		content: '';
		position: absolute;
		width: 2em;
		height: .5em;
		top: 4px;
		left: 0;
		background: black;
	}

	.header-right span:after{
		top: 14px;
	}

	.header-right{
		width: 70%;
		text-align: right;
	}

	#open{
		display: none;
	}

	a{
		text-decoration: none;
		color: white;
	}

	nav>a{
		position: relative;
		display: inline-block;
		font-size: 13px;
		line-height: 40px;
		padding: 0 2em;
		-webkit-transition: all .3s ease;
	}

	nav>a:hover{
		background: rgba(255,255,255,.9);
		color: black;
	}

	.hidden-desktop{
		display: none;
	}

	section{
		width: 100%;
		height: auto;
		background-image: -webkit-linear-gradient(#80059E 0%, #550486 100%);
	}

	.section-left,
	.section-right{
		float: left;
	}

	.section-left{
		width: 70%;
		padding: 3em 0;
	}

	.section-right{
		width: 30%;
	}

	.section-title,
	.section-tagline{
		color: white;
		font-weight: 300;
		margin: 0;
		padding: 0;
		-webkit-transition: all .4s ease;
	}

	.section-title{
		font-size: 4em;
		margin-bottom: .3em;
		text-shadow: 0 3px 0px black,
					 0 4px 0px rgba(150,150,150,.5);
	}

	.section-tagline{
		font-size: 1em;
	}

	.learn-more{
		display: table;
		margin: 3em auto 0;
		padding: 1em 6em;
		cursor: pointer;
		border-radius: 3px;
		box-shadow: inset 0 -3px 0 rgba(0,0,0,.8);
		background: #EAAF00;
		background-image: -webkit-linear-gradient(#EAAF00 0%, #D78100 100%);
		font-size: 1.2em;
	}

	.learn-more:hover{
		background: #EAAF00;
	}

	.learn-more:active{
		box-shadow: inset 0 2px 0 rgba(0,0,0,.8);
	}

	/* Portrait tablet to landscape and desktop */
	@media (min-width: 768px) and (max-width: 979px) {
		.box{
			width: 49%;
			margin-bottom: 5%;
		}

		ul li:nth-child(3) .box{
			clear: both;
		}

		ul li:nth-child(2n) .box{
			margin-right: 0;
			clear: right;
		}
	}

	/* Small monitor */
	@media (max-width: 979px){
		nav>a{
			padding: 0 1.5em;
		}
		.section-left,
		.section-right{
			width: 100%;
		}
		.section-left{
			text-align: center;
		}
		.section-right{
			padding: 0 0 2em;
		}
		.section-title{
			font-size: 4em;
			margin-bottom: .2em;
		}
		.section-tagline{
			font-size: 1.3em;
		}
		.learn-more{
			margin: 0 auto;
		}
	}

	/* Landscape phone to portrait tablet */
	@media (max-width: 767px) {
		.container{
			width: 95%;
		}
		nav>a{
			padding: 0 2em;
		}
		h1{
			padding: .5em 0;
		}
		.header-left,
		.header-right{
			width: 100%;
			text-align: center;
		}
		.section-left,
		.section-right{
			width: 100%;
		}
		.section-left{
			text-align: center;
			padding: 2em 0;
		}
		.section-right{
			padding: 1em 0 2em;
		}
		.section-title{
			font-size: 3em;
			margin-bottom: .2em;
		}
		.section-tagline{
			font-size: 1.3em;
		}
		.learn-more{
			margin: 0 auto;
		}
	}

	/* Landscape phones and down */
	@media (max-width: 480px) {
		body{
			padding: 0 .5em;
		}
		.container{
			width: 90%;
		}
		nav{
			height: 0;
			overflow: hidden;
			-webkit-transition: all .3s ease;
		}
		input[type="checkbox"]:checked + nav{
			height: 205px;
		}
		nav>a{
			padding: 0 1em;
			display: block;
			border-bottom: solid 1px rgba(255,255,255,.1);
		}
		h1{
			padding: .5em 0;
		}
		.header-left,
		.header-right{
			width: 100%;
			text-align: center;
		}
		.section-left,
		.section-right{
			width: 100%;
		}
		.section-left{
			text-align: center;
			padding: 1em 0;
		}
		.section-right{
			text-align: center;
			padding: 1em 0 2em;
		}
		.section-title{
			font-size: 2.4em;
			margin-bottom: 0;
		}
		.section-tagline{
			font-size: 1em;
		}
		.learn-more{
			display: table;
			margin: 0 auto;
		}
		.hidden-desktop{
			display: block;
		}
	}

	/* end of nevigation bar */

	.animated {
		 padding-top:95px;
		 margin-bottom:60px;
		 -webkit-animation-duration: 7s;
		 animation-duration: 7s;
		 -webkit-animation-fill-mode: both;
		 animation-fill-mode: both;
		 padding-left: 19%;
		 text-shadow: 2px 2px 4px #000000;
	}

	@-webkit-keyframes fadeIn {
		 0% {opacity: 0;}
		 100% {opacity: 1;}
	}

	@keyframes fadeIn {
		 0% {opacity: 0;}
		 100% {opacity: 1;}
	}

	.fadeIn {
		 -webkit-animation-name: fadeIn;
		 animation-name: fadeIn;
	}

	div.table-title {
	   display: block;
	  margin: auto;
	  max-width: 600px;
	  padding:5px;
	  width: 100%;
	}

	.table-title h3 {
	   color: #fafafa;
	   font-size: 30px;
	   font-weight: 400;
	   font-style:normal;
	   font-family: "Roboto", helvetica, arial, sans-serif;
	   text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
	   text-transform:uppercase;
	}

	/*** Table Styles **/

	.table-fill {
	  background: white;
	  border-radius:3px;
	  border-collapse: collapse;
	  height: 320px;
	  margin: auto;
	  max-width: auto;
	  padding:5px;
	  width: 100%;
	  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	  animation: float 5s infinite;
		padding-top: 30%;
	}

	th {
	  color:#D5DDE5;;
	  background:#1b1e24;
	  border-bottom:4px solid #9ea7af;
	  border-right: 1px solid #343a45;
	  font-size:23px;
	  font-weight: 100;
	  padding:24px;
	  text-align:left;
	  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
	  vertical-align:middle;
	}

	th:first-child {
	  border-top-left-radius:3px;
	}

	th:last-child {
	  border-top-right-radius:3px;
	  border-right:none;
	}

	tr {
	  border-top: 1px solid #C1C3D1;
	  border-bottom-: 1px solid #C1C3D1;
	  color:#666B85;
	  font-size:16px;
	  font-weight:normal;
	  text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
	}

	tr:first-child {
	  border-top:none;
	}

	tr:last-child {
	  border-bottom:none;
	}

	tr:nth-child(odd) td {
	  background:#EBEBEB;
	}

	tr:last-child td:first-child {
	  border-bottom-left-radius:3px;
	}

	tr:last-child td:last-child {
	  border-bottom-right-radius:3px;
	}

	td {
	  background:#FFFFFF;
	  padding:20px;
	  text-align:left;
	  vertical-align:middle;
	  font-weight:300;
	  font-size:18px;
	  text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
	  border-right: 1px solid #C1C3D1;
	}

	td:last-child {
	  border-right: 0px;
	}

	th.text-left {
	  text-align: left;
	}

	th.text-center {
	  text-align: center;
	}

	th.text-right {
	  text-align: right;
	}

	td.text-left {
	  text-align: left;
	}

	td.text-center {
	  text-align: center;
	}

	td.text-right {
	  text-align: right;
	}
	</style>

</head>

<body onload="initalize()">

	<header class="clearfix">
		<div class="container">
			<div class="header-left">
				<h5>Top Trumps Game!</h5>
			</div>
			<div class="header-right">
				<label for="open">
					<span class="hidden-desktop"></span>
				</label>
				<input type="checkbox" name="" id="open">
				<nav>
					<a href="../toptrumps/">Home</a>
					<a href="../toptrumps/game" style="color:red;">Play now!</a>
					<a href="../toptrumps/statistics">Statistics</a>
				</nav>
			</div>
		</div>
	</header>

	<div class="container">

		<table class="table-fill" style="margin:10em auto;">
			<thead>
				<tr>
					<th class="text-center">Games Played Overall</th>
					<th class="text-center">AI Times Won</th>
					<th class="text-center">Human Times Won</th>
					<th class="text-center">AVG Number of Draws</th>
					<th class="text-center">Highest Amount of Rounds in One Game</th>
				</tr>
			</thead>
			<tr>
				<td class="text-center" id="stat1"></td>
				<td class="text-center" id="stat2"></td>
				<td class="text-center" id="stat3"></td>
				<td class="text-center" id="stat4"></td>
				<td class="text-center" id="stat5"></td>
			</tr>
			</tbody>
		</table>

		<img src="https://i.imgur.com/CJ351WN.jpg" alt="background image">
	</div>

<script type="text/javascript">
	// Method that is called on page load
	function initalize() {
		downloadStats();
	}

	// This is a reusable method for creating a CORS request. Do not edit this.
	function createCORSRequest(method, url) {
		var xhr = new XMLHttpRequest();
		if ("withCredentials" in xhr) {
			// Check if the XMLHttpRequest object has a "withCredentials" property.
			// "withCredentials" only exists on XMLHTTPRequest2 objects.
			xhr.open(method, url, true);
		} else if (typeof XDomainRequest != "undefined") {
			// Otherwise, check if XDomainRequest.
			// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
			xhr = new XDomainRequest();
			xhr.open(method, url);
		} else {
			// Otherwise, CORS is not supported by the browser.
			xhr = null;
		}
		return xhr;
	}

	function createCORSReq(method, url) {

		var xhr = createCORSRequest(method, url); // Request type and URL

		if (!xhr) {
			alert("CORS not supported");
		}

		return xhr;

	}

	function setStats(getArray) {
		var statistics = JSON.parse(getArray);
		$("#stat1").html(statistics[0]);
		$("#stat2").html(statistics[1]);
		$("#stat3").html(statistics[2]);
		$("#stat4").html(statistics[3]);
		$("#stat5").html(statistics[4]);
	}

	function downloadStats() {
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/statistics/download_stats");
		xhr.send();

		xhr.onload = function(e) {
			setStats(xhr.response);
		}
	}
</script>

</body>

</html>
