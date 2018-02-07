package com.beta;

public class TestPurposeForSendingEmail {

	PurposeType purposeType;
	
	public TestPurposeForSendingEmail(PurposeType purposeType)

	{
		this.purposeType=purposeType;
	}
	
	public void ChooseThePurpose()
	{
		   switch (purposeType) {
           case VendorRequest:
               System.out.println("For VendorRequest");
               break;
                   
           case VendorApplicationStatus:
               System.out.println("For VendorApplication Status");
               break;
                        
           case VendorApplicationRequestRejected:
               System.out.println("For VendorApplicationRequestRejected");
               break;
               
           case VendorApplicationAccepted:
               System.out.println("For VendorApplicationRequestRejected");
               break;     
           case SendToNextEmployeeVettor:
               System.out.println("For VendorApplicationRequestRejected");
               break;        
               
           case CompanyRegistrationEmailVerification:
               System.out.println("For VendorApplicationRequestRejected");
               break;
               
           case ServiceRequestToVendor:
        	   System.out.println("For VendorServiceRequirement");
        	   break;
           default:
               System.out.println("-----------");
               break;
       }
   }
		
	}

