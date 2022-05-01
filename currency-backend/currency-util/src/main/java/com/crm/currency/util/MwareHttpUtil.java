package com.crm.currency.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;

public class MwareHttpUtil implements TrustManager {

	public String getResponseContent(String url, String method, Map<String, String> properties, String params)
			throws IOException, KeyManagementException, NoSuchAlgorithmException {

		URL request = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) request.openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("charset", "utf-8");

		conn.setUseCaches(false);

		InputStream is = null;
		InputStreamReader isr = null;

		try {
			if (conn instanceof HttpsURLConnection) {
				conn.connect();
				is = conn.getInputStream();
			} else if (conn instanceof HttpURLConnection) {
				conn.connect();
				if (((HttpURLConnection) conn).getResponseCode() != 200) {
					is = ((HttpURLConnection) conn).getErrorStream() == null ? null
							: ((HttpURLConnection) conn).getErrorStream();
				} else {
					is = conn.getInputStream();
				}
			}
			StringBuilder sb = new StringBuilder();
			if (is != null && is.available() < 50000) {
				isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				int c;
				while ((c = isr.read()) > -1) {
					sb.append((char) c);
				}
			}
			String response = sb.toString().trim();
			LogUtil.info(this.getClass(), String.format("getResponseContent output : %s, res: %s", url, response));
			return response;
		} finally {
			if (isr != null)
				isr.close();
			if (is != null)
				is.close();
		}
	}
}
