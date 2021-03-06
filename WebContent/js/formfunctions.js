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
	$('#clear-cart').click(directClearCart);
	$('#go-back').click(directCart);
	$('#confirm-button').click(directSuccess);
	$('#see-more').click(directAllProducts);
	$('#go-login').click(directLogin);
	$('#go-signup').click(directSignup);
	$('#add-to-cart').click(directAddToCart);
	$('#back-home').click(directHome);
	$('#browse-products').click(directAllProducts);
	$('#logout').click(directLogout);
	$('#forgot-password').click(directForgotPassword);
	$('#sortProducts').change(directSortProducts);

	$('nav ul.subnav li').click(function(){
		var category = $(this).attr('data-id');
		productCategorySelect(category);
	});

	$('.product-feed .content-wrapper').click(function(){
		var path = $(this).attr('data-id');
		productItemSelect(path);
	});

	$('#product-feed .content-wrapper .view-product').click(function(){
		var path = $(this).attr('data-id');
		productItemSelect(path);
	});

	$('#profile-discard-changes').click(function(){
		var profilePage = $(this).attr('data-id');
		redirectProfile(profilePage);
	});

	$('.side-filter').change(directFilteredProducts);

	$('#context-wrapper button.profile-nav').click(function(){
		var nav = $(this).attr("data-id");
		directProfileNav(nav);
	});

	$('#table-of-cart .remove').click(function(){
		var url = $(this).attr("data-id");
		directRemoveItem(url);
	});
});

function directHome(){
	$('body').append('<form action="home" method="post" id="directHome"></form>');
	$('form#directHome').submit();
}

function directProfile(){
	$('body').append('<form method="post" id="directProfile" action="profile-general"></form>');
	$('form#directProfile').submit();
}

function directLogin(){
	$('body').append('<form action="login" method="post" id="directLogin">'+
		'<input type="hidden" name="purpose" value="'+
		$('#pRedirect').val()+
		'" />'+
		'<input type="hidden" name="processAccount" value="login" />'+
		'</form>');
	$('form#directLogin').submit();
}

function directSignup(){
	$('body').append('<form action="signup" method="post" id="directSignup">'+
		'<input type="hidden" name="purpose" value="'+
		$('#pRedirect').val()+ 
		'" />'+
		'<input type="hidden" name="processAccount" value="login" />'+
		'</form>');
	$('form#directSignup').submit();
}

function directForgotPassword(){
	$('body').append('<form method="post" action="forgot" id="directForgotPassword"></form>');
	$('form#directForgotPassword').submit();
}

function directCart(){
	$('body').append('<form action="shoppingcart" method="post" id="directCart"></form>');
	$('form#directCart').submit();
}

function directClearCart(){
	$('body').append('<form action="clear" method="post" id="directClearCart"></form>');
	$('form#directClearCart').submit();
}

function directRemoveItem(url){
	$('body').append('<form action="removeitem" method="get" id="directRemoveItem">' +
		'<input type="hidden" name="item" value="' + url + '" />' +
		'</form>');
	$('form#directRemoveItem').submit();
}

function directCheckout(){
	$('body').append('<form action="checkout" method="post" id="directCheckout">' +
		'<input type="hidden" name="purpose" value="cart" />' +
		'</form>');
	$('form#directCheckout').submit();
}

function directSuccess(){
	$('form#billing-form').submit();
}

function directSortProducts(){
	var mode = $("#sortProducts").val();
	$('body').append('<form method="get" action="products" id="directSortProducts">' +
		'<input type="hidden" name="sortingMode" value="' + mode + '" />' +
		$("#typeFilter") + 
		'</form>');
	$('form#directSortProducts').submit();
}

function directFilteredProducts(){
	$("#product-feed .content-wrapper").hide();

	var prange1 = $("#price-range-1");
	var prange2 = $("#price-range-2");
	var prange3 = $("#price-range-3");
	var prange4 = $("#price-range-4");
	var prange5 = $("#price-range-5");

	// 1st wave
	if(prange1.is(":checked")){
		$("#product-feed .prange1").show();
		$("hr.prange1")
	}

	if(prange2.is(":checked")){
		$("#product-feed .prange2").show();
		$("hr.prange2")
	}

	if(prange3.is(":checked")){
		$("#product-feed .prange3").show();
		$("hr.prange3")
	}

	if(prange4.is(":checked")){
		$("#product-feed .prange4").show();
		$("hr.prange4")
	}

	if(prange5.is(":checked")){
		$("#product-feed .prange5").show();
		$("hr.prange5")
	}
}

function directAllProducts(){
	$('body').append('<form action="products" method="post" id="directAllProducts"></form>');
	$('form#directAllProducts').submit();
}

function directAddToCart(){
	$('body').append('<form action="addtocart" method="post" id="directAddToCart">' +
		'<input type="hidden" name="productPath" value = "' + $('#product-info').attr("data-id") + '"/>' +
		'</form>');
	$('form#directAddToCart').submit();
}

function directLogout(){
	$('body').append('<form action="/TheUnibag/logout" method="post" id="directLogout"></form>');
	$('#directLogout').submit();
}

function directProfileNav(nav){
	$('body').append('<form method="post" id="directProfileNav" action="' + nav + '"></form>');
	$('#directProfileNav').submit();
}

function redirectProfile(profilePage){
	if(profilePage === 'pa')
		$('body').append('<form action="profile-address" method="post" id="dicardChanges"></form>');

	else if(profilePage === 'pg'){
		$('body').append('<form action="profile-general" method="post" id="dicardChanges"></form>');
	}

	else if(profilePage === 'pp')
		$('body').append('<form action="profile-password" method="post" id="dicardChanges"></form>');

	else $('body').append('<form action="profile" method="post" id="dicardChanges"></form>');

	$('form#discardChanges').submit();
}

function showSearch(){
	// show the search modal
}

function productCategorySelect(targetCategory){
	var list = ["all", "backpack", "dufflebag", "handbag", "messengerbag", "shoulderbag", "tote", "totebag"];
	var found = false;
	for(var x = 0; x < list.length; x++){
		if(targetCategory === list[x]){
			found = true;
			break;
		}
	}

	if(found)
		$('body').append('<form action="products" method="get" id="bagItemSelect">'+
			'<input type="hidden" name="typeFilter" value="' + targetCategory +'" />'+
			'</form>');
	
	else $('body').append('<form action="products" method="get" id="bagItemSelect">'+
			'<input type="hidden" name="typeFilter" value="all" />'+
			'</form>');

	$('form#bagItemSelect').submit();
}

function productItemSelect(path){
	$('body').append('<form action="featured" method="get" id="bagItemSelect">' + 
		'<input type="hidden" name="path" value="' + path + '" />' +
		'</form>');

	$('form#bagItemSelect').submit();
}
