/*
package com.couchbase.lite.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;





public class ConnectionManager {

	static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;
	public final static int PUT = 3;
	public final static int DELETE = 4;



	public ConnectionManager() {

	}
*/
/*
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}*//*



	public String fetchMarketList(String surl,String raw,HttpParams params){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		try{
			// Checking http request method type
			StringEntity entity = new StringEntity(raw, HTTP.UTF_8);
			entity.setContentType("application/json");

			HttpPost postMethod = new HttpPost(surl);
			postMethod.setEntity(entity);

			// Set the timeout in milliseconds until a connection is established.
			// The default value is zero, that means the timeout is not used.
			int timeoutConnection = 4000;
	//		HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT)
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 12000;
		//	HttpConnectionParams.setSoTimeout(params, timeoutSocket);

		*/
/*	if(params.getParameter("session_id") != null)
				postMethod.setHeader("session_id", params.getParameter("session_id").toString());*//*

			httpResponse = httpClient.execute(postMethod);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,HTTP.UTF_8);		//UTF-8 For accepting special character(',"",&,....)

		}
		catch(Exception ex){

		}

		return response;
	}
*/
/*
	public String fetchMarketListSync(String surl,String raw,HttpParams params){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		try{
			// Checking http request method type
			StringEntity entity = new StringEntity(raw, HTTP.UTF_8);
			entity.setContentType("application/json");

			HttpPost postMethod = new HttpPost(surl);
			postMethod.setEntity(entity);


			// Set the timeout in milliseconds until a connection is established.
			// The default value is zero, that means the timeout is not used.
			int timeoutConnection = 4000;
			HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT)
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 12000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);

			if(params.getParameter("session_id") != null)
				postMethod.setHeader("session_id", params.getParameter("session_id").toString());
			httpResponse = httpClient.execute(postMethod);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,HTTP.UTF_8);

		}
		catch(Exception ex){

		}

		return response;
	}
	public String makeServiceCall(String url, int method, HttpParams params) {
		try {
			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;

			// Checking http request method type
			if (method == POST) {
				HttpPost httpPost = new HttpPost(url);

				// adding post params
				if (params != null) {

					//	httpPost.setEntity(new StringEntity("UTF8"));
					//	httpPost.setHeader("Content-type", "application/json");


					//Set the timeout in milliseconds until a connection is established.
					// The default value is zero, that means the timeout is not used.
					int timeoutConnection = 4000;
					HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
					// Set the default socket timeout (SO_TIMEOUT)
					// in milliseconds which is the timeout for waiting for data.
					int timeoutSocket = 12000;
					HttpConnectionParams.setSoTimeout(params, timeoutSocket);


					if(params.getParameter("user_Id") != null)
						httpPost.setHeader("user_Id", params.getParameter("user_Id").toString());

					if(params.getParameter("username") != null)
						httpPost.setHeader("username", params.getParameter("username").toString());

					if(params.getParameter("password") != null)
						httpPost.setHeader("password", params.getParameter("password").toString());

					if(params.getParameter("device_Id")!=null)
						httpPost.setHeader("device_Id", params.getParameter("device_Id").toString());

				}



				httpResponse = httpClient.execute(httpPost);
				StatusLine statusLine = httpResponse.getStatusLine();
				if(statusLine.getStatusCode() != 200) {
					Log.i("Non-Json","response");

				}

			}
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,HTTP.UTF_8);


		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}

	public String makeServiceCallForMarketDetailsUpdate(Context ctx, String strUrl, String gson, HttpParams param) {
		String strResponse = "";

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = null;
			try {
				httpPost = new HttpPost(strUrl);
			} catch(Exception e) {
				System.out.println("httpost error-->"+e.getMessage());
			}

			if(param != null)
				httpPost.setHeader("session_id", param.getParameter("session_id").toString());
			StringEntity entity = new StringEntity(gson *//*
*/
/*titleObject.toString(), HTTP.UTF_8*//*
*/
/*);
			entity.setContentType("application/json");

			httpPost.setEntity(entity);

			//HttpConnectionParams.setConnectionTimeout(param, 10000);
			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,HTTP.UTF_8);
			StatusLine statusLine = httpResponse.getStatusLine();
			if(statusLine.getStatusCode() == 200){
				System.out.println("Tital Details connection successfull" + response.toString());
			} else {
				System.out.println("Tital Details update request unsuccessful--");
			}

			if(httpEntity != null) {
				httpEntity.consumeContent();
			}
		} catch (ConnectTimeoutException e) {
			System.out.println("Tital Details error in ConnectionTimeOut request-->"+e.getMessage());
			response = null;
		} catch (UnsupportedEncodingException e) {
			response = null;
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			response = null;
			e.printStackTrace();
		} catch (IOException e) {
			response = null;

			android.widget.Toast.makeText(ctx, "Server error. Please try again.", Toast.LENGTH_LONG).show();
			if(AssetNotUsingMarketHelper.mProgress != null) {
				AssetNotUsingMarketHelper.mProgress.dismiss();
			}

		} catch (Exception e) {
			System.out.println("Tital Details error in Exception request-->"+e.getMessage());
		}
		return response;
	}


	public String logoutService(String surl,HttpParams params){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		try{
			HttpPost postMethod = new HttpPost(surl);
			int timeoutConnection = 4000;
			HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT)
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 12000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);
			if(params.getParameter("session_id") != null)
				postMethod.setHeader("session_id", params.getParameter("session_id").toString());
			httpResponse = httpClient.execute(postMethod);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,HTTP.UTF_8);		//UTF-8 For accepting special character(',"",&,....)

		}
		catch(Exception ex){

		}

		return response;
	}*//*


}
*/
