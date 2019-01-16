<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/paginainiziale.css">
<link rel="stylesheet" type="text/css" href="CSS/slideshow.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chi siamo.</title>
</head>
<body>

	<center class="miglioramento">Entra, per poter usufruire di
		tutte le funzionalita' da noi offerte.</center>

	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>

	<h1>Chi siamo...</h1>

	<p class="presentazione">
		Rappresentiamo una libreria online, con lo scopo di risolvere il
		problema dovuto dalla reperibilita' di alcuni libri.<br>
		Cerchiamo di offrire un servizio <b>efficiente, completo e
			consistente</b>.<br> La nostra idea principale e' quella di offrire
		funzionalità elementari, che con pochi e <b>semplici</b> passaggi
		consentono di ricercare, osservare ed eventualmente acquistare i
		nostri libri. E' disponibile una sezione di <b>offerte</b> dove potrai
		comprare libri scontati. Il nostro punta ad essere un servizio serio,
		infatti se il libro non dovesse giungere a destinazione per un tempo
		massimo di <b>5</b> giorni sara' possibile effettuare un reclamo.<br>
		Inoltre, se un libro ricercato non è presente nel nostro sistema
		grazie alla funzionalità <b>richiesta</b> sarà possibile richiederlo,
		poi un nostro amministratore provvederà di inserire il libro nella
		piattaforma. <br> <br>
	</p>

	<div class="slideshow-container">
		<div class="mySlides fade">
			<div class="numbertext">1 / 4</div>
			<img src="Img/slide1.jpg" style="width: 100%">
		</div>

		<div class="mySlides fade">
			<div class="numbertext">2 / 4</div>
			<img src="Img/slide2.jpg" style="width: 100%">
		</div>

		<div class="mySlides fade">
			<div class="numbertext">3 / 4</div>
			<img src="Img/slide3.jpg" style="width: 100%">
		</div>
		
		<div class="mySlides fade">
			<div class="numbertext">4 / 4</div>
			<img src="Img/slide4.jpg" style="width: 100%">
		</div>

		<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
			onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>

	<div style="text-align: center">
		<span class="dot" onclick="currentSlide(1)"></span> <span class="dot"
			onclick="currentSlide(2)"></span> <span class="dot"
			onclick="currentSlide(3)"></span>
	</div>

	<script type="text/javascript">
	var slideIndex = 0;
	showSlides();

	function showSlides() {
	    var i;
	    var slides = document.getElementsByClassName("mySlides");
	    for (i = 0; i < slides.length; i++) {
	        slides[i].style.display = "none"; 
	    }
	    slideIndex++;
	    if (slideIndex > slides.length) {slideIndex = 1} 
	    slides[slideIndex-1].style.display = "block"; 
	    setTimeout(showSlides, 2000); // Change image every 2 seconds
	}
	</script>

</body>
</html>