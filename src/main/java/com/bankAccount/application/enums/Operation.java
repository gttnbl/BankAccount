package com.bankAccount.application.enums;

public enum Operation {

	
	
	DEPOSIT("deposit"), WITHDRAW("withdraw") ;  
    
    private String abreviation ;  
     
    private Operation(String abreviation) {  
        this.abreviation = abreviation ;  
   }  
     
    public String getAbreviation() {  
        return  this.abreviation ;  
   }  
}
