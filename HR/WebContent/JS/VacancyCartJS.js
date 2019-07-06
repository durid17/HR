/**
 * 
 */

$('.interest').on('click', function() {
	var vacId = $(this).attr('value');
	$.ajax({
        url:'InterestServlet',
        data:{vacancyId : vacId},
        type:'post',
        cache:false,
        success:function(data){},
        error:function(){alert('error');}
    });
});


$("#update").on('click', function(event){
    alert("aaaa");
});



