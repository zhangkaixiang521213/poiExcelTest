package test;

import java.rmi.RemoteException;

import org.tempuri.MemberManageServiceSoapProxy;
import org.tempuri.QueryMemberRequest;
import org.tempuri.QueryMemberResponse;

public class Test {

	public static void main(String[] args) {
		try {  
			MemberManageServiceSoapProxy proxy = new MemberManageServiceSoapProxy();
			proxy.setEndpoint("http://223.223.205.16:11205/MemberManageService.asmx?wsdl");
			QueryMemberRequest request =new QueryMemberRequest();
			request.setMobilePhone("13311213117");
			request.setUserType(100000000);
			request.setValidateCode("CrmConfig");
			request.setWebMemberID("123456");
			QueryMemberResponse response = proxy.queryMember(request);
																																
            System.out.println(response.getResultCode());  
            System.out.println(response.getResultDesc());  
              
        } catch (RemoteException e) {  
            e.printStackTrace();  
        }  

	}

}
