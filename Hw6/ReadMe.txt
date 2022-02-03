----- Data Structures -----
HashTable     ---> Customers 		//To Use Customer's Id To Get Customer 
LinkedList    ---> Traders	 		//No Particular Reason
Tree          ---> Categories		//To Make Hierarchy On Lower To Higher Level Category
ArrayList     ---> Sorting			//Easy To Use In Sorting --get(index) --set(index,item)
Queue         ---> Customer Orders	//To Base On First In First Out

----- Note -----
I Divided Driver Code Into Two Pieces Because When All Test Methods Run,
	You Can Not See All The Customer's Test Methods, So If You Want To Run Driver Codes,
		You Have To Choose Which Driver Code( Customer Or Trader ) That You Want To See.
		
I Did Not Print Descriptions When Product Searching Because Descriptions Are Too Long,
	But Search Process Is Working Well.

----- Test Users -----
Customer: 
	ID: -1 
	PASSWORD: "admin"

Traders:
	ID: 10000000
	PASSWORD: "Alisha"

	ID: -2
	PASSWORD: "admin"
	Note: This Trader Does Not Have Any Product

----- Note 2 -----
Only These Users(Test Users) Id Is Known Others Id Is Created Randomly,
	If You Want To Use Different Trader, You Have To Know Its Id,
	 	To Know Its Id, You Can Print Their Id From App Class -addTrader Method-.

Only These Users(Test Users) Id Is Known Others Id Is Created Randomly,
	If You Want To Use Different Custemer, You Have To Sign Up First.
