<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head><H1>CCPX Project</H1>
</head>

<body>
<form method="post" action="showLatestTransaction" id="showLatestExchange">
						<h2>Latest Transactions</h2>
						<ul class="form-list">
							<li>
								<label class="required" for="email">SellerFrom<em>*</em></label>
								<div class="input-box">
									
								<input type="text" name="sellerFrom" id="sellerFrom" tabindex="1" autocomplete="off" class="input-text required-entry validate-email" />
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="pass">SellerTo<em>*</em></label>
								<div class="input-box">
									<input type="text" name="sellerTo"
					maxlength="16" id="sellerTo" tabindex="2" class="input-text required-entry validate-password"/>
							
								</div>
								<div class="clear"></div>
							</li>						
						</ul>						
					</div>
					<div class="buttons-set">				
					<input type="submit" tabindex="5" class="colors-btn" class="colors-btn"  title="submit" value="提交" />

						
						<div class="clear"></div>
					</div>
            	</div>
            		</form>
          		<!--  -->	  	
            	
   
            	
            	
            	
				<div class="clear"></div>
			</div>
		</div>
		<div class="clearfix"></div>

	</div>
</section>
</div>
</body>
</html>



