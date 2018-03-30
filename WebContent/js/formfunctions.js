// link handlers for the navigation bar

$(document).ready(function(){
	$('#main-logo').click(directHome);
	$('#search').click(showSearch);
	$('#login').click(directLogin);
	$('#cart').click(directCart);
	$('#logged-account').click(directProfile);
	$('#view-profile').click(directProfile);
	$('#cta').click(directSignup);
	$('#checkout').click(directCheckout);
	$('#continue-shopping').click(directHome);
	$('#go-back').click(directCart);
	$('#confirm-button').click(directSuccess);
	$('#see-more').click(directAllProducts);
	$('#go-login').click(directLogin);
	$('#go-signup').click(directSignup);
	$('#add-to-cart').click(directAddToCart);
	$('#back-home').click(directHome);
	$('#browse-products').click(directAllProducts);

	$('nav ul.subnav li').click(function(){
		var category = $(this).attr('data-id');
		productCategorySelect(category);
	});

	$('.product-feed .content-wrapper').click(function(){
		var path = $(this).attr('data-id');
		productItemSelect(path);
	});

	$('#product-feed .content-wrapper').click(function(){
		var path = $(this).attr('data-id');
		productItemSelect(path);
	});

	$('#profile-discard-changes').click(function(){
		var profilePage = $(this).attr('data-id');
		redirectProfile(profilePage);
	});
});

function directHome(){
	$('body').append('<form action="home" method="post" id="directHome"></form>');
	$('form#directHome').submit();
}

function directProfile(){
	$('body').append('<form action="profile" method="post" id="directProfile"></form>');
	$('form#directProfile').submit();
}

function directLogin(){
	$('body').append('<form action="login" method="post" id="directLogin">'+
		'<input type="hidden" name="purpose" value="'+
		$('#pRedirect').val()+ 
		'" />'+
		'</form>');
	$('form#directLogin').submit();
}

function directSignup(){
	$('body').append('<form action="signup" method="post" id="directSignup">'+
		'<input type="hidden" name="purpose" value="'+
		$('#pRedirect').val()+ 
		'" />'+
		'</form>');
	$('form#directSignup').submit();
}

function directCart(){
	$('body').append('<form action="shoppingcart" method="post" id="directCart"></form>');
	$('form#directCart').submit();
}

function directCheckout(){
	$('body').append('<form action="checkout" method="post" id="directCheckout"></form>');
	$('form#directCheckout').submit();
}

function directSuccess(){
	$('body').append('<form action="success" method="post" id="directSuccess"></form>');
	$('form#directSuccess').submit();
}

function directAllProducts(){
	$('body').append('<form action="products/all" method="post" id="directAllProducts"></form>');
	$('form#directAllProducts').submit();
}

function directAddToCart(){
	$('body').append('<form action="addtocart" method="post" id="directAddToCart"></form>');
	$('form#directAddToCart').submit();
}

function redirectProfile(profilePage){
	if(profilePage === 'pa')
		$('body').append('<form action="profile/address" method="post" id="dicardChanges"></form>');

	else if(profilePage === 'pg'){
		$('body').append('<form action="profile/general" method="post" id="dicardChanges"></form>');
		$('form#discardChanges').submit();
	}

	else if(profilePage === 'pp')
		$('body').append('<form action="profile/password" method="post" id="dicardChanges"></form>');

	else $('body').append('<form action="profile" method="post" id="dicardChanges"></form>');

	$('form#discardChanges').submit();
}

function showSearch(){
	// show the search modal
}

function productCategorySelect(targetCategory){
	var list = ["all", "backpack", "handbag", "totebag", "messengerbag", "travelbag", "slingbag", "weekenderbag"];
	var found = false;
	for(var x = 0; x < list.length; x++){
		if(targetCategory === list[x]){
			found = true;
			break;
		}
	}

	if(found)
		$('body').append('<form action="products/' + targetCategory +'" method="post" id="bagItemSelect"></form>');
	
	else $('body').append('<form action="products/all" method="post" id="bagItemSelect"></form>');

	$('form#bagItemSelect').submit();
}

function productItemSelect(path){
	$('body').append('<form action="featured/'+ path +'" method="post" id="bagItemSelect">'
		+ 
		+ '</form>');
}