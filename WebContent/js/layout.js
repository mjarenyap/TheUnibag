// javascript for design

$(document).ready(function(){
	var navHeight = $('nav.sticky').height();
	$('body.nav-sticky').css('margin-top', navHeight + 'px');

	$('div.error-banner .fa-close').click(function(){
		$('div.error-banner').hide();
	});
});