// admin form functions

$(document).ready(function(){
	$('#back-all-products').click(function(){
		directAdminControlPage("allproducts");
	});

	$('#back-all-user').click(function(){
		directAdminControlPage("allusers");
	});

	$('#save-changes').click(function(){
		var target = $(this).attr("data-id");
		directDelete(target);
	});

	$('.sidenav .items .option').click(function(){
		var url = $(this).attr("data-id");
		directAdminControlPage(url);
	});

	$('.table table tr.content-product-row .edit').click(function(){
		var url = $(this).attr("data-id");
		directViewProductPage(url);
	});

	$('.table table tr.content-user-row .edit').click(function(){
		var url = $(this).attr("data-id");
		directViewUserPage(url);
	});

	$('#discard').click(function(){
		var back = $(this).attr("data-id");
		directDiscard(back);
	});

	$('#logout').click(directLogout);
	$('#edit-account').click(directEditAccount);
});

function directEditAccount(){
	$('body').append('<form method="post" id="directEditAccount" action="editaccount"></form>');
	$('form#directEditAccount').submit();
}

function directAdminControlPage(url){
	$('body').append('<form method="post" id="directAdminControlPage" action="' + url + '"></form>');
	$('form#directAdminControlPage').submit();
}

function directViewProductPage(url){
	$('body').append('<form method="get" action="viewproduct" id="directViewProductPage">' +
		'<input type="hidden" name="path" value="' + url + '" />' +
		'</form>');
	$('form#directViewProductPage').submit();
}

function directViewUserPage(url){
	$('body').append('<form method="get" action="viewuser" id="directViewUserPage">' +
		'<input type="hidden" name="path" value="' + url + '" />' +
		'</form>');
	$('form#directViewUserPage').submit();
}

function directDelete(target){
	var action = "archiveorders";
	var list = document.getElementsByClassName('delete-status');
	switch(target){
		case "do": action = "archiveorders";
		break;

		case "dp": action = "deleteproducts";
		break;

		case "du": action = "deleteusers";
		break;
	}

	$('body').append('<form method="post" id="directDelete" action="' + action + '"></form>');
	if(list != null)
		$('form#directDelete').append(list);

	else $('form#directDelete').append('<input type="checkbox" class="delete-status" name="deletelist" />');

	$('form#directDelete').submit();
}

function directDiscard(back){
	$('body').append('<form method="post" id="directDiscard" action="' + back + '"></form>');
	$('form#directDiscard').submit();
}

function directLogout(){
	$('body').append('<form action="/TheUnibag/logout" method="post" id="directLogout"></form>');
	$('#directLogout').submit();
}
