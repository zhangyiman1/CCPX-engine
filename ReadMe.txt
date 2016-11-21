Explaination:

(1) offer status:    1  available
	                 2  decline
	                 3  finish
	                 4  remove

    request status:  1 accept 
	                 2 declined 
	                 3 pending 
	                 4 remove       

(2) in the PlatformController
	removeExchange(HttpServletRequest req, String request_id, String user_from)
		Explaination: according to 	request_id and user_from, remove a Exchange(Request). In the Database Request table, just set Request's status as 4. 

	declineExchange(HttpServletRequest req, String request_id, String user_to) 
		Explaination: according to 	request_id and user_to, decline a Exchange(Request);  In the Database Request table, just set Request's status as 2

	removeOffer(HttpServletRequest req, String offer_id, String user_id)
		Explaination: according to 	offer_id and user_id, remove a Offer;  In the Database Offer table, just set Offer's status as 4

(3) in the PlatformService
	public Boolean removeExchange(String request_id, String user_from);
	public Boolean declineExchange(String request_id, String user_to);
	public Boolean removeOffer(String offer_id, String user_id);		

	Explaination: PlatformService is a interface. It contains above three functions. PlatformServiceImp implements PlatformService and realises above three functions

(4) in the PlatformDao
	public Boolean removeExchange(String request_id, String user_from);
	public Boolean declineExchange(String request_id, String user_to);
	public Boolean removeOffer(String offer_id, String user_id);	

	xplaination: PlatformDao is a interface. It contains above three functions. PlatformDaoImp implements PlatformDao and realises above three functions.

(5) for example

	PlatformController            removeExchange
										|| call(调用)
										\/
	PlatformService               removeExchange
										|| call(调用)
										\/
	PlatformDao			           removeExchange

（6）in the WebContent, there has three "jsp" files. these files are used for testing the above three functions.
	RemoveExchange.jsp
	RemoveOffer.jsp
	DeclineExchange.jsp

	input website int the browser to test above three functions: 
		http://localhost:8080/ccpx/RemoveExchange.jsp	   for testing: {request id: 41; user_from: 2}
		http://localhost:8080/ccpx/DeclineExchange.jsp     for testing: {request id: 41; user_to: 3}
		http://localhost:8080/ccpx/RemoveOffer.jsp         for testing: {offer id: 1; user_id: 3}

	you can see the result in the console or in the database.

(7)  If you don't want to use PlatformCotroller above three functions, you can design a new function to call the PlatformService. That's all OK.