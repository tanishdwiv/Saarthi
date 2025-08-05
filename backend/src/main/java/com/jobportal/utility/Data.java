package com.jobportal.utility;

import java.time.Year;

public class Data {
	public static String getMessageBody(String otp, String name) {

		return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Your OTP Code</title>\n" + "    <style>\n" + "        body {\n"
				+ "            font-family: Arial, sans-serif;\n" + "            margin: 0;\n"
				+ "            padding: 0;\n" + "            background-color: #f4f4f4;\n" + "        }\n"
				+ "        .container {\n" + "            max-width: 600px;\n" + "            margin: 0 auto;\n"
				+ "            padding: 20px;\n" + "            background-color: #ffffff;\n"
				+ "            border-radius: 8px;\n" + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
				+ "        }\n" + "        .header {\n" + "            background-color: #4CAF50;\n"
				+ "            color: #ffffff;\n" + "            padding: 10px;\n" + "            text-align: center;\n"
				+ "            border-radius: 8px 8px 0 0;\n" + "        }\n" + "        .body {\n"
				+ "            padding: 20px;\n" + "            color: #333333;\n" + "            text-align: center;\n"
				+ "        }\n" + "        .otp {\n" + "            font-size: 24px;\n"
				+ "            font-weight: bold;\n" + "            color: #4CAF50;\n" + "            margin: 20px 0;\n"
				+ "        }\n" + "        .footer {\n" + "            margin-top: 20px;\n"
				+ "            font-size: 12px;\n" + "            color: #888888;\n"
				+ "            text-align: center;\n" + "        }\n" + "    </style>\n" + "</head>\n" + "<body>\n"
				+ "    <div class=\"container\">\n" + "        <div class=\"header\">\n"
				+ "            <h1>Your OTP Code</h1>\n" + "        </div>\n" + "        <div class=\"body\">\n"
				+ "            <p>Hello <strong>"+name+"</strong>,</p>\n"
				+ "            <p>We have received a request to verify your email address. Your OTP code is:</p>\n"
				+ "            <div class=\"otp\">" + otp + "</div>\n"
				+ "            <p>This OTP code is valid for 5 minutes. If you did not request this, please ignore this email.</p>\n"
				+ "            <p>Thank you for using our service!</p>\n" + "        </div>\n"
				+ "        <div class=\"footer\">\n" + "            <p>&copy;" + Year.now().getValue()
				+ " Saarthi . All rights reserved.</p>\n" + "        </div>\n" + "    </div>\n" + "</body>\n"
				+ "</html>";
	}
}
