package com.example.demo.utils.tonglian.pay;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * SSL管理助手类
 *
 * @author Administrator
 */
public class SSLUtil implements X509TrustManager {
    private static SSLUtil _instance = null;
    private SSLSocketFactory sslFactory = null;

    private SSLUtil() {
    }

    /**
     * 获取SSL管理助手类实例
     */
    synchronized public static SSLUtil getInstance() throws NoSuchAlgorithmException, KeyManagementException {
        if (_instance == null) {
            _instance = new SSLUtil();
            SSLContext sc = SSLContext.getInstance("SSLv3");
            sc.init(null, new TrustManager[]{new SSLUtil()}, null);
            _instance.sslFactory = sc.getSocketFactory();
        }
        return _instance;
    }

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    /**
     * 获取SSL Socket工厂
     */
    public SSLSocketFactory getSSLSocketFactory() {
        return sslFactory;
    }

}
