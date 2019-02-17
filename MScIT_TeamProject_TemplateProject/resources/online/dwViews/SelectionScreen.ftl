<html>

<head>
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
	<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
	<link rel="stylesheet" href="../assets/styling/selection_style.css">

</head>

<body onload="initalize()">
	<script>
		window.onload = function() {
			var mp3 = document.getElementById("my_audio");
  			mp3.volume = 0.4;
	    document.getElementById("my_audio").play();
	}
	</script>
	<audio id="my_audio" src="../assets/audio/bg.mp3" loop="loop"></audio>
	<img src="../assets/imgs/background.JPG" alt="background image">

	<!-- Call the initalize method when the page loads -->
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

	<div id="animated-example" class="animated fadeIn">
		<h1 class="first-text">Welcome to Skyrim traveller,</h1>
		<h4 class="first-text">Are you ready for the battle?</h1><br><br>
			<h4 class="first-text"><i>Get ready to face the most powerful enemies of Skyrim.<br>
					You have one objective. Defeat every enemy within your sight<br>
					in order to survive, and become the next biggest Dragonborn.<br><br>
					Goodluck out there, traveller.</i></h4>
	</div>
	</div>

	<script>
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
	</script>

</body>

</html>
