// javascript for design

$(document).ready(function(){
	var navHeight = $('nav.sticky').height();
	$('body.nav-sticky').css('margin-top', navHeight + 'px');
});