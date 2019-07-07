$('.interest').on('click', function() {
	var vacId = $(this).attr('value');
	
	$.ajax({
        url:'http://localhost:8082/HR/InterestServlet',
        data:{vacancyId : vacId},
        type:'post',
        cache:false,
        success:function(data){},
        error:function(){alert('error');}
    });
});




$("#update").click(function(){
	
	var data = {
		professions : toString($('#professions').val()),
		companies : toString($('#companies').val()),
		locations : toString($('#locations').val()),
		tags : toString($('#tags').val()),
		jobs_type : toString($('#jobs_type').val()),
		degree : toString($('#degree').val())
    }
	
	var url = "VacanciesServlet";
	
	$.post(url,data, 
			function(data, status){
//				var json = JSON.parse(data);
//				console.log(json);
//				var id = json.vacancyId;
               location.href = "http://localhost:8082/HR/FilterServlet";
			}
	);
});

function toString(myStringArray){
	if(myStringArray == null) return "";
	var arrayLength = myStringArray.length;
	var res = "";
	for (var i = 0; i < arrayLength; i++) {
		res = res + myStringArray[i].trim() + ",";
		//console.log(myStringArray[i]);
	}
	return res;
}





