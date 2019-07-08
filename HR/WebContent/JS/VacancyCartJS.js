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
	
	var url = "FilterServlet?";
	url += "professions=" +  toString($('#professions').val()) + "&";
	url += "companies=" + toString($('#companies').val()) + "&";
	url += "locations=" + toString($('#locations').val()) + "&";
	url += "tags=" + toString($('#tags').val()) + "&";
	url += "jobs_type=" + toString($('#jobs_type').val()) + "&";
	url += "degree=" + toString($('#degree').val());
	
	location.href = "http://localhost:8082/HR/" + url;
});

function toString(myStringArray){
	if(myStringArray == null) return "";
	
	var arrayLength = myStringArray.length;
	var res = "";
	for (var i = 0; i < arrayLength - 1; i++) {
		res += "'" + myStringArray[i].trim() + "',";
	}
	
	if(arrayLength > 0) {
		res += "'" + myStringArray[arrayLength - 1].trim() + "'";		
	}
	
	return "(" + res + ")";
}






