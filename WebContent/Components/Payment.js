$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	console.log(" statusxxxxxxxxxxxxxxxxx ");
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});


});



function onItemSaveComplete(response, status) {
	console.log("response "+ response);
	console.log(" status "+ status);
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formPayment")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "PaymentAPI",
		type : "DELETE",
		data : "pid=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	console.log("response "+ response);
	console.log(" status "+ status);
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
			$("#hidItemIDSave").val(
			$(this).closest("tr").find('#hidItemIDUpdate').val());
			$("#pid").val($(this).closest("tr").find('td:eq(0)').text());
			$("#pname").val($(this).closest("tr").find('td:eq(1)').text());
			$("#paddress").val($(this).closest("tr").find('td:eq(2)').text());
			$("#time").val($(this).closest("tr").find('td:eq(3)').text());
			$("#date").val($(this).closest("tr").find('td:eq(4)').text());
			$("#amount").val($(this).closest("tr").find('td:eq(5)').text());
			$("#Hname").val($(this).closest("tr").find('td:eq(6)').text());
		});

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// CODE
	if ($("#pid").val().trim() == "") {
		return "Insert  paymet Id.";
	}
	// NAME
	if ($("#pname").val().trim() == "") {
		return "Insert  Name.";
	}

	// PRICE-------------------------------
	if ($("#paddress").val().trim() == "") {
		return "Insert  Address.";
	}
	
	if ($("#time").val().trim() == "") {
		return "Insert time.";
	}
	
	if ($("#date").val().trim() == "") {
		return "Insert date.";
	}
	if ($("#amount").val().trim() == "") {
		return "Insert  amount.";
	}
	if ($("#Hname").val().trim() == "") {
		return "Insert Hospital name.";
	}
	// is numerical value
	var tmpPrice = $("#amount").val().trim();
	//var len = tmpPrice.length;
//	if(len != 4){
//		return "You enterd "+ len+ " Numbers! Enter 4 Numbers to continue."; 
//	}
	
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value.";

	}

	return true;
	
	
}
