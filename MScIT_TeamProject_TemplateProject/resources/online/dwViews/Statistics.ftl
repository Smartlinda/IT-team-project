<html>

<head>
	<meta charset="utf-8" />
	<title>Top Trumps</title>

	<link href="../assets/imgs/favicon/favicon.ico" rel="icon" type="image/x-icon" />

	<link rel="stylesheet" href="../assets/styling/bootstrap.min.css">
	<link rel="stylesheet" href="../assets/styling/vex.css" />
	<link rel="stylesheet" href="../assets/styling/vex-theme-os.css" />
	<link rel="stylesheet" href="../assets/styling/font-awesome.min.css">
	<link rel="stylesheet" href="../assets/styling/w3.css">
	<link rel="stylesheet" href="../assets/styling/jquery-ui.css">

	<script src="../assets/js/jquery-2.1.1.js"></script>
	<script src="../assets/js/jquery-ui.js"></script>
	<script src="../assets/s/vex.combined.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>

	<link rel="stylesheet" href="../assets/styling/style.css">
	<link rel="stylesheet" href="../assets/styling/statistics_style.css"

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

		<img class="bg_img" src="../assets/imgs/background.jpg" alt="background image">
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
