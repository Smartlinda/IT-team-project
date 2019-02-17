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
				<h1>How many enemies can you handle?</h1>  <!--see below, src is redirected to different files-->
				<a onclick="updateURL(1); disappear(); go('../assets/html/1_AI.htm'); setupGame(1);" class="button4" style="background-color:#84f14e" value="1">1 Enemy<br>(I'm too young to die)</a>&nbsp;
				<a onclick="updateURL(2); disappear(); go('../assets/html/2_AI.htm'); setupGame(2);" class="button4" style="background-color:#4e9af1" value="2">2 Enemies<br>(Hey, not too rough)</a>&nbsp;
				<a onclick="updateURL(3); disappear(); go('../assets/html/3_AI.htm'); setupGame(3);" class="button4" style="background-color:#f1bb4e" value="3">3 Enemies<br>(Hurt me plenty)</a>&nbsp;
				<a onclick="updateURL(4); disappear(); go('../assets/html/4_AI.htm'); setupGame(4);" class="button4" style="background-color:#f14e4e" value="4">4 Enemies<br>(Ultra-Violence)</a>
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
		<iframe class="embed-responsive-item" id="cardframe" src='about:blank'></iframe>
	</div>

	<div class="container">

		<img class="bg_img" src="../assets/imgs/background.JPG" alt="background image">

		<!-- cards! -->

		<script>

		//THE WAY THE WEBSITE WORKS, IS THAT THERE IS AN IFRAME.
		//WHEN THE USER SELECTS THE NUMBER OF AI PLAYERS, THE IFRAME
		//STARTS REFERRING TO ONE OF 4 FILES (WITH A DIFFERENT NUMBER OF PLAYERS)
		//IN THE ASSETS/HTML FOLDER. WHEN REFERRING TO THINGS IN THOSE FILES,
		//AN ADDITIONAL COMMAND HAS TO BE USED.

		var AI; //the number of AI
		var numberOfAI = getUrlParameter('Number');  //again, the number of ai from the url

		// Was too lazy to load in the headers, we can do it later.
		var namesArray = new Array("Health", "Stamina", "Attack","Armor", "Size"); //category names


		// a function to change the src of the iframe
		function go(location){
			document.getElementById('cardframe').src = location;
		}

		// when we select the number of ai, update the url without reloading the page, to have ?Number=1 (or 2 or 3 or 4)
		function updateURL(number) {
			if (history.pushState) {
				var newurl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?Number=' + number;
				window.history.pushState({
					path: newurl
				}, '', newurl);
			}
		}

		// when the page loads, do all methods in here (see body tag up top)
		function initalize() {
			preStart()
		}

		// function to get the parameters from the url (i.e. getUrlParameter(Number) would return 1 or 2 or 3 or 4)
		function getUrlParameter(sParam) {
			var sPageURL = window.location.search.substring(1);
			var sURLVariables = sPageURL.split('&');
			for (var i = 0; i < sURLVariables.length; i++) {
				var sParameterName = sURLVariables[i].split('=');
				if (sParameterName[0] == sParam) {
					return sParameterName[1];
				}
			}
		}

		// see the restapi java file. we are getting the response it sends from there. run game/ is opened.
		function preStart() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/preStart");
			xhr.send();

			xhr.onload = function(e) {
				var responseText = xhr.response;
				//alert(responseText);
			}
		}

		// setup the game, objButton is a number. see the buttons above. get top cards after we get a response.
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
				//alert(responseText);
				getTopCards();
				//setPlayerTurn(responseText);
			}
		}

		// function setPlayerTurn(index){
		// 	if (index === 0){
		// 		window.frames[0].document.getElementById("whose-turn").innerHTML = "Your turn.";
		// 	} else {
		// 		window.frames[0].document.getElementById("whose-turn").innerHTML = "Player " + index "'s turn.";
		// 	}
		// }

		// get the state of the player (active or not active)
		function getPlayerState() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getPlayerState?Number="+AI);
			xhr.send();

			xhr.onload = function(e) {
				var responseText = xhr.response;
				//alert(responseText);
			}
		}

		// get the top cards from all players, convert them to a json array
		//timeout becuase it's a race condition
		function getTopCards() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getTopCards?Number=" + AI);
			xhr.send();

			xhr.onload = function(e) {
				var responseText = xhr.response;
				var cardArray = JSON.parse(responseText);

				setTimeout(setCardValues, 500, xhr.response);
				getDrawPile();
				//alert(targetFrame.contentDocument.getElementById("pl1cat1").value);
			}
		}

		// actually set the values of each card in the iframe
		//methods are directly below
		function setCardValues(getArray) {
			var obj = JSON.parse(getArray);
			if (AI > 0){
				// FOR HUMAN CARD, FILL IN STUFF
				getCard1(obj);

				// FOR AI 1, FILL IN STUFF
				getCard2(obj);

				// FOR AI 2, FILL IN STUFF
			} if (AI > 1){
				getCard3(obj);

				// FOR AI 3, FILL IN STUFF
			} if (AI > 2){
				getCard4(obj);

				// FOR AI 4, FILL IN STUFF
			} if (AI > 3){
				getCard5(obj);
			}
		}

		//one function for each card
		function getCard1(obj){
			window.frames[0].document.getElementById("pl1name").innerHTML = obj[0].cardName;
			window.frames[0].document.getElementById("pl1cat1").innerHTML = namesArray[0] + ": " + obj[0].attributeValues[0];
			window.frames[0].document.getElementById("pl1cat2").innerHTML = namesArray[1] + ": " + obj[0].attributeValues[1];
			window.frames[0].document.getElementById("pl1cat3").innerHTML = namesArray[2] + ": " + obj[0].attributeValues[2];
			window.frames[0].document.getElementById("pl1cat4").innerHTML = namesArray[3] + ": " + obj[0].attributeValues[3];
			window.frames[0].document.getElementById("pl1cat5").innerHTML = namesArray[4] + ": " + obj[0].attributeValues[4];
		}

		function getCard2(obj){
			window.frames[0].document.getElementById("pl2name").innerHTML = obj[1].cardName;
			window.frames[0].document.getElementById("pl2cat1").innerHTML = namesArray[0] + ": " + obj[1].attributeValues[0];
			window.frames[0].document.getElementById("pl2cat2").innerHTML = namesArray[1] + ": " + obj[1].attributeValues[1];
			window.frames[0].document.getElementById("pl2cat3").innerHTML = namesArray[2] + ": " + obj[1].attributeValues[2];
			window.frames[0].document.getElementById("pl2cat4").innerHTML = namesArray[3] + ": " + obj[1].attributeValues[3];
			window.frames[0].document.getElementById("pl2cat5").innerHTML = namesArray[4] + ": " + obj[1].attributeValues[4];
		}

		function getCard3(obj){
			window.frames[0].document.getElementById("pl3name").innerHTML = obj[2].cardName;
			window.frames[0].document.getElementById("pl3cat1").innerHTML = namesArray[0] + ": " + obj[2].attributeValues[0];
			window.frames[0].document.getElementById("pl3cat2").innerHTML = namesArray[1] + ": " + obj[2].attributeValues[1];
			window.frames[0].document.getElementById("pl3cat3").innerHTML = namesArray[2] + ": " + obj[2].attributeValues[2];
			window.frames[0].document.getElementById("pl3cat4").innerHTML = namesArray[3] + ": " + obj[2].attributeValues[3];
			window.frames[0].document.getElementById("pl3cat5").innerHTML = namesArray[4] + ": " + obj[2].attributeValues[4];
		}

		function getCard4(obj){
			window.frames[0].document.getElementById("pl4name").innerHTML = obj[3].cardName;
			window.frames[0].document.getElementById("pl4cat1").innerHTML = namesArray[0] + ": " + obj[3].attributeValues[0];
			window.frames[0].document.getElementById("pl4cat2").innerHTML = namesArray[1] + ": " + obj[3].attributeValues[1];
			window.frames[0].document.getElementById("pl4cat3").innerHTML = namesArray[2] + ": " + obj[3].attributeValues[2];
			window.frames[0].document.getElementById("pl4cat4").innerHTML = namesArray[3] + ": " + obj[3].attributeValues[3];
			window.frames[0].document.getElementById("pl4cat5").innerHTML = namesArray[4] + ": " + obj[3].attributeValues[4];
		}
		function getCard5(obj){
			window.frames[0].document.getElementById("pl5name").innerHTML = obj[4].cardName;
			window.frames[0].document.getElementById("pl5cat1").innerHTML = namesArray[0] + ": " + obj[4].attributeValues[0];
			window.frames[0].document.getElementById("pl5cat2").innerHTML = namesArray[1] + ": " + obj[4].attributeValues[1];
			window.frames[0].document.getElementById("pl5cat3").innerHTML = namesArray[2] + ": " + obj[4].attributeValues[2];
			window.frames[0].document.getElementById("pl5cat4").innerHTML = namesArray[3] + ": " + obj[4].attributeValues[3];
			window.frames[0].document.getElementById("pl5cat5").innerHTML = namesArray[4] + ": " + obj[4].attributeValues[4];
		}

		// get the number of cards in the common pile with this method
		function getDrawPile() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/getDrawPile?Number=" + AI);
			xhr.send();

			xhr.onload = function(e) {
				var drawContents = xhr.response;
				//alert(drawContents);
				var draw = JSON.parse(drawContents);
				window.frames[0].document.getElementById("common-pile").innerHTML = "Common pile has " + draw + " cards.";

				//document.getElementById("drawStack").innerHTML = "The common pile: " + draw + " cards.";
			}
		}


		//THE REST OF THIS IS UNIMPORTANT -------------------------------------------------------------------------------------------------
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
