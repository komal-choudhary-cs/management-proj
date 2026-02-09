package com.common.management.Service;




public class LoginEmailtask extends Thread {
//	 private UserServicePdf servicePdf;
	private final EmailService emailService;
    private final String email;
    private final String username;

    public LoginEmailtask( 
    	
            EmailService emailService,
            String email,
            String username
    ) {
    	
        this.emailService = emailService;
        this.email = email;
        this.username = username;
    }

    @Override
    public void run() {
    	System.out.println(" before thread is executing");
    	try {
    		Thread.sleep(1000);
        UserServicePdf servicePdf = new UserServicePdf();
        byte[] pdf = servicePdf.generateLoginPdf( username);
  		emailService.sendLoginSuccessEmail(email, username,  pdf);}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	System.out.println(" after  thread is executing");

    }
}
