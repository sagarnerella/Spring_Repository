<html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
   <!-- <script src="js/jquery.min.js"></script> -->
   
<script>
$(document).ready(function() {
	
	$("#register").on("click",function(){
		 $.ajax({
			url:"registerUser",
			type:"post",
			dataType:'json',
			data:$("#regForm").serialize(),
			success:function(result){
				$.each(result,function(key,value){
					console.log("key "+key+" value "+value);
				});
				$.each(result.modules,function(ke,val){
					console.log("key "+ke+" value "+val);
				});
			}
		}); 
		
		
		/* var data = {}
		data["username"] = "sagar";
      $.ajax({
			url:"login",
			type:"post",
			dataType:'json',
			data:JSON.stringify(data),
			success:function(result){
				$.each(result,function(key,value){
					console.log("key "+key+" value "+value);
				});
				$.each(result.modules,function(ke,val){
					console.log("key "+ke+" value "+val);
				});
			}
		}); */
});
});
</script>
<body>
<form name="regForm" id="regForm">
UserName:<input type="text" name="username"/><br>
Password:<input type="password" name="password"/><br>
<input type="button" value="register" id="register">
</form>
</body>
</html>