
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Display</title>
<style >
img{

margin-top:10px;

}
#display_panel{


margin-top:20px;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0;
     
}
#display_body{
position: relative;
  overflow: hidden;
  
}

.hover_effect>a>img:hover{
border:2px solid #f2613c;
padding:2px;
}

#display{

max-width:150px;
min-height:80px;



}
.btn-mar{
margin-bottom: 10px;
}

</style>
<script >

$(document).ready(function(){
    $("#image").hover(function(){
      var val=$("#image").attr("src");
      $("#display").attr("src",val );
      
    });
    $("#image1").hover(function(){
        var val=$("#image1").attr("src");
        $("#display").attr("src",val );
        
      });
    $("#image2").hover(function(){
        var val=$("#image2").attr("src");
        $("#display").attr("src",val );
        
      });
    $("#image3").hover(function(){
        var val=$("#image3").attr("src");
        $("#display").attr("src",val );
        
      });
    $("#image4").hover(function(){
        var val=$("#image4").attr("src");
        $("#display").attr("src",val );
        
      });

});

</script>
</head>
<body>
<div class="container-fluid">
<div class="panel panel-default" id="display_panel">
<div class="container-fluid " style="background-color:#eeeeee;">
<d:forEach items="${pro_part_list}" var="pro_part">
<div class="panel-heading">
<div class="col-sm-6">
<div class="panel-title"><b style="font-size: 18px">${pro_part.pro_desc}</b></div>
</div>
<div class="col-sm-2 col-sm-offset-2">
<a href="buy" class="btn btn-danger  btn-block btn-mar"><i class="fa fa-money" aria-hidden="true"></i> BUY</a> 
</div>
<div class="col-sm-2 ">
<a href="cart?pro_id=${pro_part.pro_id}" class="btn btn-success  btn-block btn-mar addto"><i class="fa fa-shopping-cart fa-lg" aria-hidden="true"></i> ADD TO CART</a>
</div>
</div>

</d:forEach>
</div>

<div class="panel-body ">
<div class="col-sm-6">
<div class="panel panel-default" id="display_panel"  width="1000px" height="300px" >

<div class="panel-body text-center" id="display_body">
<div  class="col-sm-2">
<d:forEach items="${pro_part_list}" var="pro_part">
<div class="hover_effect">
<a href="#"><img  id="image"  alt="product" class="img-responsive" src="pro_images/${pro_part.pro_id}.jpg" width="30px" height="30px"></a>
<a href="#"><img  id="image1"   class="img-responsive " alt="product1" src="pro_images/${pro_part.image_one}" width="30px" height="30px"></a>
<a href="#"><img id="image2" class="img-responsive " alt="product2" src="pro_images/${pro_part.image_two}" width="30px" height="30px"></a>
<a href="#"><img id="image3" class="img-responsive " alt="product3" src="pro_images/${pro_part.image_three}" width="30px" height="30px"></a>
<a href="#"><img id="image4"  class="img-responsive " alt="product4" src="pro_images/${pro_part.image_four}" width="30px" height="30px"></a>
</div>
</d:forEach>
</div>

<d:forEach items="${pro_part_list}" var="pro_part">
<div  class="col-sm-10">
<div class="panel-body" id="single_image">
<img  id="display"  alt="product" src="pro_images/${pro_part.pro_id}.jpg">
</div>
</div>
</d:forEach>
</div>

</div>

</div>


<div class="col-sm-6">


<d:forEach items="${pro_part_list}" var="pro_part">
<h2></h2>


<div class="panel panel-default">
<div class="panel-heading">
<div class="panel-heading"><b style="font-size: 18px">${pro_part.pro_name}</b>
<div class="pull-right"><b style="color:red; font-size: 18px">Rs.${pro_part.pro_price}</b></div>
</div>
</div>
<div>
<pre style=" background-color: #ffffff; border:0px;" ><b>${line}</b></pre>
</div>
</div>
</d:forEach>

</div>
</div>
</div>
</div>
</body>
</html>