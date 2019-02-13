<html>

<head>
	<meta charset="utf-8" />
	<!-- Web page title -->
	<title>Top Trumps</title>

	<link href="../assets/imgs/favicon/favicon.ico" rel="icon" type="image/x-icon" />

	<!-- <link rel="stylesheet" href="../assets/styling/modal.css"> -->

	<link rel="stylesheet" href="../assets/styling/bootstrap.min.css">
	<link rel="stylesheet" href="../assets/styling/vex.css" />
	<link rel="stylesheet" href="../assets/styling/vex-theme-os.css" />
	<link rel="stylesheet" href="../assets/styling/font-awesome.min.css">
	<link rel="stylesheet" href="../assets/styling/style.css">
	<link rel="stylesheet" href="../assets/styling/w3.css">
	<link rel="stylesheet" href="../assets/styling/jquery-ui.css">


	<script src="../assets/js/jquery-2.1.1.js"></script>
	<script src="../assets/js/jquery-ui.js"></script>
	<script src="../assets/js/vex.combined.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="../assets/styling/blur_style.css">
	<link rel="stylesheet" href="../assets/styling/buttons.css">

</head>

<!-- Call the initalize method when the page loads -->

<body onload="initalize()">
	<!-- TOP TASK BAR -->
	<header class="clearfix">
		<div class="container">
			<div class="header-left">
				<h5>Top Trumps: Skyrim Edition</h5>
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
	<!-- END OF TOP TASK BAR -->

	<!-- BLUR BOX -->
	<div id="bg" name="ai_select">
		<div id="magic-container">
			<div id="blur-bg"></div>
			<div id="text">
				<h1>How many enemies can you handle?</h1>
				<a onclick="updateURL(1); disappear(); setupGame(1);" class="button4" style="background-color:#84f14e" value="1">1 Enemy<br>(I'm too young to die)</a>&nbsp;
				<a onclick="updateURL(2); disappear(); setupGame(2);" class="button4" style="background-color:#4e9af1" value="2">2 Enemies<br>(Hey, not too rough)</a>&nbsp;
				<a onclick="updateURL(3); disappear(); setupGame(3);" class="button4" style="background-color:#f1bb4e" value="3">3 Enemies<br>(Hurt me plenty)</a>&nbsp;
				<a onclick="updateURL(4); disappear(); setupGame(4);" class="button4" style="background-color:#f14e4e" value="4">4 Enemies<br>(Ultra-Violence)</a>
			</div>
		</div>
	</div>

	<script>
		function disappear() {
			var x = document.getElementById("bg");
			if (x.style.display === "none") {
				x.style.display = "block";
			} else {
				x.style.display = "none";
			}
		}
	</script>


	<!-- END OF BLUR BOX -->

	<div style="width:100%;" class="embed-responsive embed-responsive-16by9">
		<iframe class="embed-responsive-item" id="mybutt" src="../assets/html/4_ai.htm"></iframe>
	</div>

	<div class="container">

		<img class="bg_img" src="../assets/imgs/background.JPG" alt="background image">



		<!-- cards! -->



		<!-- <h2>Player Card</h2>
	<div class="card1">
	<img src="../assets/imgs/card_layout.png" alt="card" style="height: 30%; position: relative;">
	<h2 class="centered">Paarthurnax</h2>
	<img class="img_hero" src="../assets/imgs/hero1.gif" alt="dragonight"">

	<div class=" skill_select">
	<form style="text-align: center;" accept-charset="utf-8">
	<select class="dropdown1" name="Number">
	<option value="hello">Strength 2</option>
	<option value="two">Size 10</option>
	<option value="three">Dexterity 8</option>
	<option value="four">Armor 8</option>
	<option value="five">Sneak 8</option>
</select>
<br><br><input class="w3-button w3-black" type="submit" onclick="skill_select.style.display='none'; this.style.display = 'none';" value="Attack!">
</form>
</div>
<button id="mybutt" class="btn1" type="button">Strength 2</button>

</div> -->

		<script>
			var AI;
			//alert(AI);
			//var myyyy = getUrlParameter('ai_select');


			// if (AI === '1') {
			// 	document.getElementById('mybutt').src = "../assets/html/blah2.html";
			//}

			// Was too lazy to load in the headers, we can do it later.
			var namesArray = new Array("Size", "Speed", "Range", "Firepower", "Cargo");

			function updateURL(number) {
				if (history.pushState) {
					var newurl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?Number=' + number;
					window.history.pushState({
						path: newurl
					}, '', newurl);
				}
			}

			function initalize() {
				preStart()
			}

			// function getUrlParameter(sParam) {
			// 	var sPageURL = window.location.search.substring(1);
			// 	var sURLVariables = sPageURL.split('&');
			// 	for (var i = 0; i < sURLVariables.length; i++) {
			// 		var sParameterName = sURLVariables[i].split('=');
			// 		if (sParameterName[0] == sParam) {
			// 			return sParameterName[1];
			// 		}
			// 	}
			// }

			function preStart() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/preStart");
				xhr.send();

				xhr.onload = function(e) {
					var responseText = xhr.response;
					alert(responseText);
				}
			}

			function setupGame(objButton) {
				AI = objButton;
				//  create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET',
					"http://localhost:7777/toptrumps/game/setupGame?Number=" + AI); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.send()
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					//		getTopCard();
					alert(responseText);
				}

			}

			function getTopCard() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getTopCard");
				xhr.send();

				xhr.onload = function(e) {
					var responseText = xhr.response;
					//		setCardValues(xhr.response);
					getDrawPile();
					alert(responseText);
				}
			}

			function getDrawPile() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getDrawPile");
				xhr.send();

				xhr.onload = function(e) {
					var drawContents = xhr.response;
					alert(drawContents);
					var draw = JSON.parse(drawContents);
					document.getElementById("drawStack").innerHTML = "The common pile: " + draw + " cards.";
				}
			}

			// function setCardValues(getArray) {
			// 	var obj = JSON.parse(getArray);
			// 	document.getElementById("playercardname").innerHTML = obj.cardName;
			// 	document.getElementById("cardbtn1").innerHTML = namesArray[0] + ": " + obj.attributeValues[0];
			// 	document.getElementById("cardbtn2").innerHTML = namesArray[1] + ": " + obj.attributeValues[1];
			// 	document.getElementById("cardbtn3").innerHTML = namesArray[2] + ": " + obj.attributeValues[2];
			// 	document.getElementById("cardbtn4").innerHTML = namesArray[3] + ": " + obj.attributeValues[3];
			// 	document.getElementById("cardbtn5").innerHTML = namesArray[4] + ": " + obj.attributeValues[4];
			// }

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

			function hideAICards() {
				//sets the AI card details to hidden at the start
				var x = document.getElementsByClassName("desc");
				var i;
				for (i = 0; i < x.length; i++) {
					x[i].style.visibility = "hidden";
				}
			}

			function showAICards() {
				//sets AI card details to visible once a category has been chosen
				var x = document.getElementsByClassName("desc");
				var i;
				for (i = 0; i < x.length; i++) {
					x[i].style.visibility = "visible";
				}
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
				var xhr = createCORSRequest(method, url);
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				return xhr;
			}

			function testResponse(jsonAsString) {
				//var response = JSON.parse(jsonAsString)
				if (jsonAsString == "state error") {
					$("#error").html("Function cant be used in this state");
				} else if (jsonAsString == "") {
					$("#error").html($('#error').text() + " + 1")
				} else {
					$("#response").html(jsonAsString);
					$("#error").html("--------");
				}

				function newGame() {
					hideAICards();
					var xhr = createCORSReq('GET', "http://localhost:7777/toptrumps/game/newGame");
					xhr.send();
					xhr.onload = function(e) {
						testResponse(xhr.response);
					}
				}
			}

			function initialiseGame() {
				var xhr = createCORSReq('GET', "http://localhost:7777/toptrumps/game/initialiseGameplay");
				xhr.send();
				xhr.onload = function(e) {
					testResponse(xhr.response);
				}
			}

			function startARound() {
				var xhr = createCORSReq('GET', "http://localhost:7777/toptrumps/game/startARound");
				xhr.send();
				xhr.onload = function(e) {
					testResponse(xhr.response);
				}
				hideAICards();
			}

			function chosenCategory(category) {
				var xhr = createCORSReq('GET', "http://localhost:7777/toptrumps/game/chosenCategory?category=" + category);
				xhr.send();
				xhr.onload = function(e) {
					testResponse(xhr.response);
				}
				showAICards();
			}
		</script>

</body>

</html>
