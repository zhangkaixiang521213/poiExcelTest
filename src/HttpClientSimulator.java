
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientSimulator {
	public static final String defaultUrl = "http://114.215.130.32:8090/jiahe_web/server";
	public static final String password = "testmd5";
	public static final String username = "remote";
	public static void main(String[] args) {
//		getSoldSeats();
//		handleSeat();
//		citys();
//		cinemas();
//		halls();
//		seats();
		plans();
	}
	public static void getMovieDetail(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getmoviedetail");
		params.put("movieid", "8479");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	public static void handleSeatState(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getsoldseats");
		params.put("planId", "1411140600000010001");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	public static void getSoldSeats(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getsoldseats");
		params.put("planId", "1418393700000010102");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//影院详情
	public static void getCinemaDetail(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getcinemadetail");
		params.put("cinemaid", "121");
//		params.put("movieId", "157");
//		params.put("method", "getmoviesbycinemasanddate");
//		params.put("cinemaId", "103");
//		params.put("planDate", "2014-09-10");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//场次详情
	public static void getMoviesAndCinemas(){
		HashMap<String,String> params = new HashMap<String,String>();
//		params.put("method", "getcinemasbymoviesanddateandcity");
//		params.put("cityId", "46");
		params.put("movieId", "7213");
		params.put("method", "getmoviesbycinemasanddate");
		params.put("cinemaId", "114");
		params.put("planDate", "2014-09-12");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//场次详情
	public static void getPlansDetailByChannelForOuter(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getplandetailbyuid");
		params.put("planId", "1410761400000011403");
//		params.put("channelId", "1");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//场次
	public static void plans(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getPlans");
		params.put("planDate", "2015-01-22");
		params.put("cinemaId", "103");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//卖品
	public static void goods(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getgoods");
		params.put("cinemaId", "141");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//城市
	public static void citys(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getCitys");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//影院
	public static void cinemas(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getcinemas");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//座位
	public static void seats(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getSeatsByCinemaAndHall");
		params.put("hallNo", "1");
		params.put("cinemaId", "100");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//影厅
	public static void halls(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getHallsByCinemaId");
		params.put("cinemaId", "103");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//由影院和影片获取场次
	public static void plansByMovie(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getPlansByCinemaAndMovie");
		params.put("cinemaId", "114");
		params.put("movieId", "7193");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	//由影院和影片获取场次
	public static void plansHotMovies(){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("method", "getPlanHotMovies");
//		params.put("movieId", "7213");
//		params.put("cinemaId", "114");
		params.put("cityId", "46");
		params.put("startTime", "2014-09-19");
//		params.put("endTime", "2014-09-10");
		params.put("uid", username);
		post(defaultUrl,params,password);
	}
	/**
	 * 发送 get请求
	 */
	public void get() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.  
			HttpGet httpget = new HttpGet("http://121.40.96.40:8081/jiahe_web/server");
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态  
				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度  
					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容  
					System.out.println("Response content: " + EntityUtils.toString(entity));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取拼接后的url
	 * @param url
	 * @param formparams
	 * @return
	 */
	public static String getUrl(String url,List<NameValuePair> formparams){
		StringBuilder sb = new StringBuilder();
		sb.append(url).append("?");
		for(NameValuePair nV:formparams){
			String name = nV.getName();
			String value = nV.getValue();
			sb.append(name).append("=").append(value!=null?value:"").append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	/**
	 * 获取enc
	 * @param formparams
	 * @param pwd
	 * @return
	 */
	public static String getEnc(List<NameValuePair> formparams,String pwd){
		TreeMap<String,String> tm = new TreeMap<String,String>();
		for(NameValuePair p:formparams){
			tm.put(p.getName(), p.getValue());
		}
		StringBuilder sb = new StringBuilder();
		for(Entry<String,String> e:tm.entrySet()){
			String value = e.getValue();
			sb.append(value!=null?value:"");
		}
		sb.append(pwd);//密码
		String result = new String(sb.toString().getBytes(),Charset.forName("utf-8"));
		return MD5.getMD5(result).toLowerCase();
	}
	/**
	 * 拼接enc
	 * @param params
	 * @param pwd
	 * @return
	 */
	public static String getEnc(HashMap<String,String> params,String pwd){
		TreeMap<String,String> tm = new TreeMap<String,String>();
		for(Entry<String,String> e:params.entrySet()){
			tm.put(e.getKey(), e.getValue());
		}
		StringBuilder sb = new StringBuilder();
		for(Entry<String,String> e:tm.entrySet()){
			String value = e.getValue();
			sb.append(value!=null?value:"");
		}
		sb.append(pwd);//密码
		String result = new String(sb.toString().getBytes(),Charset.forName("utf-8"));
		return MD5.getMD5(result).toLowerCase();
	}
	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static void post(String url,HashMap<String,String> params,String pwd) {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for(Entry<String,String> e:params.entrySet()){
			formparams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}
		formparams.add(new BasicNameValuePair("time_stamp", String.valueOf(new Date().getTime())));
		formparams.add(new BasicNameValuePair("enc", getEnc(formparams, pwd)));
		System.out.println(getUrl(defaultUrl, formparams));
		//生成enc
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: \r\n" + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * HttpClient连接SSL
	 */
	public void ssl() {
		CloseableHttpClient httpclient = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
			try {
				// 加载keyStore d:\\tomcat.keystore  
				trustStore.load(instream, "123456".toCharArray());
			} catch (CertificateException e) {
				e.printStackTrace();
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}
			// 相信自己的CA和所有自签名的证书
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			// 只允许使用TLSv1协议
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(get方式)
			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
			System.out.println("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					System.out.println(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public void postForm() {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
		// 创建参数队列  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", "admin"));
		formparams.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 上传文件
	 */
	public void upload() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");

			FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

			httppost.setEntity(reqEntity);

			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}