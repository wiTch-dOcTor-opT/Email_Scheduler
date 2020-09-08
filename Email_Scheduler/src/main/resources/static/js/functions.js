function zoomInFamily() {
	$('.frame1').animate({
		width : '10%',
		top : '26%',
		left : '62%'
	});
	$('.hidingdiv1').animate({
		left : '63%'
	});
	$('.family').animate({
		width : '4%',
		left : '65%',
	});
	$('#birthdayscrollIndex').animate({
		width : '17%'
	});
}

function zoomOutFamily() {
	$('#birthdayscrollIndex').animate({
		width : '1%'
	});
	$('.hidingdiv1').animate({
		left : '62%'
	});
	$('.frame1').animate({
		width : '7%',
		top : '29%',
		left : '64%'
	});
	$('.family').animate({
		width : '3%',
		left : '66%'
	})
}

function zoomInGears() {
	$('.frame2').animate({
		width : '10%',
		top : '51%',
		left : '63%'
	});
	$('.hidingdiv2').animate({
		left : '64%'
	});
	$('.gears').animate({
		width : '4%',
		top : '57%'
	});
	$('#propertiesscrollIndex').animate({
		width : '17%'
	});
}

function zoomOutGears() {
	$('#propertiesscrollIndex').animate({
		width : '1%'
	});
	$('.hidingdiv2').animate({
		left : '62%'
	});
	$('.frame2').animate({
		width : '7%',
		top : '54%',
		left : '64%'
	});
	$('.gears').animate({
		width : '3%',
		top : '58%'
	});
}

function registerChangePointer(){
	$('.remove').on('mouseover', function() {
		$('.remove').css('cursor', 'pointer');
	});
	$('.remove').on('mouseout', function() {
		$('.remove').css('cursor', 'default');
	});	
}

function getEmployeeDates() {
	$.ajax({
		url : "/getemployees",
		method : "GET",
		success : function(data) {
			$('#employee_table').append(data);
			registerChangePointer();
		}
	});
}

function getProperties(){
	$.ajax({
		url : "/getproperties",
		method : "GET",
		success : function(data) {
			$('#properties_table').append(data);
			registerChangePointer();
		}
	});
}

function saveEmployess() {
	var empList = [];
	var count = 0;
	$('#employee_table tr').each(function() {
		var employee = {};
		$(this).find('td').each(function() {
			if ($(this).attr('class') == "employee_name") {
				employee["name"] = $(this).text();
			} else if ($(this).attr('class') == "employee_email") {
				employee["email"] = $(this).text();
			} else if ($(this).find(':input').attr('class') == "employee_birthdate"){
				employee["birthdate"] = $(this).find(':input').val();
			}
			count += 1;
		});
		if (count != 0) {
			empList.push(employee);
		}
	});

	var jsonEmpList = JSON.stringify(empList);

	$.ajax({
		url : "/saveemployees",
		method : "POST",
		data : jsonEmpList,
		contentType : "application/json",
		success : function(data) {
			$('#employee_table').empty();
			$('#employee_table').append(data);
			registerChangePointer();
		    $('.successdiv').fadeIn(3000);
			$('.successdiv').fadeOut(3000);
		}
	});
}

function saveProperties() {
	var property = {};
	$('#properties_table tr').each(function() {
		$(this).find('td').each(function() {
			if ($(this).attr('class') == "from_email") {
				property["fromEmail"] = $(this).text();
			} else if ($(this).attr('class') == "dl_email") {
				property["dlEmail"] = $(this).text();
			}else if ($(this).attr('class') == "mailer_name") {
				property["mailerName"] = $(this).text();
			}else if ($(this).attr('class') == "sender_email") {
				property["senderCCMail"] = $(this).text();
			}
		});
	});

	var jsonPorpertyList = JSON.stringify(property);

	$.ajax({
		url : "/saveproperties",
		method : "POST",
		data : jsonPorpertyList,
		contentType : "application/json",
		success : function(data) {
			$('#properties_table').empty();
			$('#properties_table').append(data);
			registerChangePointer();
		    $('.successdiv').fadeIn(3000);
			$('.successdiv').fadeOut(3000);
		}
	});
}

$(document)
		.ready(
				function() {
					
					$('#add').click(
									function() {
										var count = $('#employee_table tr').length;
										var html_code = "<tr id='row" + count + "'>";
										html_code += "<td contenteditable='true' class='employee_name'></td>";
										html_code += "<td contenteditable='true' class='employee_email'></td>";
										html_code += "<td><input type='date' class='employee_birthdate'></input></td>";
										html_code += "<td><img src='/images/delete.png' data-row='row"+ count
												+ "' class='remove'></img></td>";
										html_code += "</tr>";
										$('#employee_table').append(html_code);
										registerChangePointer();
					});
					
					 $(document).on('click', '.remove', function(){
						  var delete_row = $(this).data("row");
						  $('#' + delete_row).remove();
					 });
					 
					 $('[data-toggle="tooltip"]').tooltip();  

					$('.homeImg').on('mouseover', function() {
						$('.homeImg').animate({
							width : '7%',
							position : 'absolute',
							top : '29%',
							left : '45%'
						});
						$('#bdayscroll').animate({
							width : '23%'
						});
						// $('#propButton').css('background-image','radial-gradient(circle
						// farthest-side,rgb(204, 204, 204), rgb(242, 242,
						// 242))');
						// $('#background').css('display','inline-block');
					});

					$('.homeImg').on('mouseout', function() {
						$('.homeImg').animate({
							width : '5%',
							position : 'absolute',
							top : '30%',
							left : '46%'
						});
						$('#bdayscroll').animate({
							width : '1%'
						});
						// $('#propButton').css('background-image','radial-gradient(circle
						// farthest-side, rgb(242, 242, 242),rgb(230, 230,
						// 230))');
						// $('#background').css('display','none');
					});

					$('.propImg').on('mouseover', function() {
						$('.propImg').animate({
							width : '8%',
							position : 'absolute',
							top : '54%',
							left : '45%'
						});
						$('#propertiesscroll').animate({
							width : '23%'
						});
					});

					$('.propImg').on('mouseout', function() {
						$('.propImg').animate({
							width : '6%',
							position : 'absolute',
							top : '55%',
							left : '46%'
						});
						$('#propertiesscroll').animate({
							width : '1%'
						});

					});					

					$('.frame1').on('mouseover', function() {
						zoomInFamily();
					});

					$('.frame1').on('mouseout', function() {
						zoomOutFamily();
					});

					$('.frame2').on('mouseover', function() {
						zoomInGears();
					});

					$('.frame2').on('mouseout', function() {
						zoomOutGears();
					});
					
					$('#display').on('click', getEmployeeDates);
					$('#display').click();
					
					$('#displayproperties').on('click',getProperties);
					$('#displayproperties').click();
					
					$('#save_employees').on('click', saveEmployess);
					$('#save_properties').on('click', saveProperties);

});
