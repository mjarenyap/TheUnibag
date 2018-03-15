// Javascript functions for the navigation

$(document).ready(function(){
	$('#search').click(showSearch);
	$('#login').click(directLogin);
	$('#cart').click(directCart);

	$('nav ul.subnav li').click(function(){
		var category = $(this).attr('data-id');
		bagItemSelect(category);
	});
});

function directLogin(){
	$('body').append('<form action="login" method="post" id="directLogin"></form>');
	$('form#directLogin').submit();
}

function directCart(){
	$('body').append('<form action="shoppingcart" method="post" id="directCart"></form>');
	$('form#directCart').submit();
}

function showSearch(){

}

function bagItemSelect(targetCategory){
	var list = ["all", "backpack", "handbag", "totebag", "messengerbag",
	"travelbag", "slingbag", "weekenderbag"];

	var found = false;
	for(var x = 0; x < list.length; x++){
		if(targetCategory === list[i]){
			found = true;
			break;
		}
	}

	if(found)
		$('body').append('<form action="products/' + targetCategory +'" method="post" id="bagItemSelect"></form>');
	
	else $('body').append('<form action="products/all" method="post" id="bagItemSelect"></form>');

	$('form#bagItemSelect').submit();
}