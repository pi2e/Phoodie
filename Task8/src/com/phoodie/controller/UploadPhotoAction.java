package com.phoodie.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import com.phoodie.utility.OAuthUtility;

public class UploadPhotoAction extends Action {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "upload.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://up.flickr.com/services/upload");
		String description = "Pasta";// request.getParameter("description");
		String title = "Pastawhitesauce";// request.getParameter("title");
		String path = "/Users/nveeturi/Desktop/pasta.JPG";// request.getParameter("photoPath");
		String token = (String) request.getSession().getAttribute("oauth_token");
		String redirectUrl = "";
		try {
			// MultipartEntity url = OAuthUtility.uploadPhoto(description, path,
			// title, token);
			System.out.println("Requesting : " + httppost.getRequestLine());
			/*
			 * HttpURLConnection con = (HttpURLConnection) url
			 * .openConnection();
			 */

			MultipartEntity reqEntity = OAuthUtility.uploadPhoto(description,
					path, title, token);
			httppost.setEntity(reqEntity);
			//httppost.
			ByteArrayOutputStream out = new ByteArrayOutputStream((int)reqEntity.getContentLength());
			reqEntity.writeTo(out);
			//byte[] entityContentAsBytes = out.toByteArray();
			// or convert to string
			String entityContentAsString = new String(out.toByteArray());
			//System.out.println("entityContentAsString = "+entityContentAsString);
			reqEntity.getContent();
			
			
			System.out.println("Requesting : " + httppost.getRequestLine());
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httppost, responseHandler);

			System.out.println("responseBody : " + responseBody);
			/*
			 * BufferedReader br = new BufferedReader(new InputStreamReader(
			 * (con.getInputStream())));
			 * 
			 * String output; while ((output = br.readLine()) != null) {
			 * System.out.println(output); redirectUrl = output; }// //Test the
			 * output - End
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirectUrl;
	}

}
