// admin form functions

$(document).ready(function(){
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
});

function directAdminControlPage(url){
	$('body').append('<form method="post" id="directAdminControlPage" action="admin/' + url + '"></form>');
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
		for(var x = 0; x < list.length; x++)
			$('form#directDelete').append(list[x]);

	else $('form#directDelete').append('<input type="checkbox" class="delete-status" name="deletelist" />');

	$('form#directDelete').submit();
}
