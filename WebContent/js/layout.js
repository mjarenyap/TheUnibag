// javascript for design

$(document).ready(function(){
	initSubNavigation();
	var navHeight = $('nav.sticky').height();
	$('body.nav-sticky').css('margin-top', navHeight + 'px');

	$('div.error-banner .fa-close').click(function(){
		$('div.error-banner').hide();
	});

	$('#search').click(function(){
		$('#modal-overlay').show();
	});

	$('#close-modal').click(function(){
		$('#modal-overlay').hide();
	});
});

function initSubNavigation(){
	$('ul.subnav').append('<li data-id="all">All</li>');
	$('ul.subnav').append('<li data-id="backpack">Backpack</li>');
	$('ul.subnav').append('<li data-id="dufflebag">Duffle Bag</li>');
	$('ul.subnav').append('<li data-id="handbag">Handbag</li>');
	$('ul.subnav').append('<li data-id="messengerbag">Messenger Bag</li>');
	$('ul.subnav').append('<li data-id="shoulderbag">Shoulder Bag</li>');
	$('ul.subnav').append('<li data-id="tote">Tote</li>');
	$('ul.subnav').append('<li data-id="totebag">Tote Bag</li>');
}