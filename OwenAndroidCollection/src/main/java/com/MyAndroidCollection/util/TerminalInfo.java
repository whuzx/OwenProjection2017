package com.MyAndroidCollection.util;

import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class TerminalInfo {

	/*
	 * 获取当前的手机号
	 */
	public String getLocalNumber(Context context) {
		TelephonyManager tManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String number = tManager.getLine1Number();
		return number;
	}

	public boolean checkInternet(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			// 能连接Internet
			return true;
		} else {
			// 不能连接到
			return false;
		}
	}

	public static String GetDate(String url) {
		HttpGet get = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);//
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			return null;
		}
	}

	private String getSign(Context context) {
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> apps = pm
				.getInstalledPackages(PackageManager.GET_SIGNATURES);
		Iterator<PackageInfo> iter = apps.iterator();
		while (iter.hasNext()) {
			PackageInfo packageinfo = iter.next();
			String packageName = packageinfo.packageName;

			if (packageName.equals(context.getPackageName())) {

				return packageinfo.signatures[0].toCharsString();
			}
		}
		return null;
	}

	/**
	 * 获取当前的接入点是ctwap还是ctnet
	 * 
	 */
	private String CTWAP = "ctwap";
	private String CTNET = "ctnet";
	private Uri PREFERRED_APN_URI = Uri
			.parse("content://telephony/carriers/preferapn");

	public String getApnType(Context context) {
		String apntype = "nomatch";
		Cursor c = context.getContentResolver().query(PREFERRED_APN_URI, null,
				null, null, null);
		c.moveToFirst();
		String user = c.getString(c.getColumnIndex("apn"));
		if (user.startsWith(CTNET)) {
			apntype = CTNET;
		} else if (user.startsWith(CTWAP)) {
			apntype = CTWAP;
		}
		return apntype;
	}

	public void getIMEI(Context context) {
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		System.out.println("-----" + telManager.getSubscriberId()); // 2.-----460007423947
		System.out.println("-----" + telManager.getSimSerialNumber()); // 1.-----89860089281174247
		System.out.println("-----" + telManager.getSimOperator());
		System.out.println("-----" + telManager.getSimCountryIso());
		System.out.println("-----" + telManager.getSimOperatorName());
		System.out.println("-----" + telManager.getSimState());
	}

	/**
	 * 判断当前网络连接状态
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		NetworkInfo networkInfo = ((ConnectivityManager) context
				.getApplicationContext().getSystemService("connectivity"))
				.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isConnectedOrConnecting();
		}
		return false;
	}

	public void startbrowser(Activity activity) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse("www.baidu.com");
		intent.setData(content_url);
		activity.startActivity(intent);
	}

	public void netInfo(Context context) {
		// 获取手机的IMSI码
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String sendNum = null;
		String imsi = telManager.getSubscriberId();
		;
		if (imsi != null) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
				// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/19号段使用了此编号
				Toast.makeText(context, "当前移动号码发送完毕", 0).show();

				// sendNum = Constants.sendNumber_GD;

			} else if (imsi.startsWith("46001")) {// 中国联通

				Toast.makeText(context, "当前联通号码发送完毕", 0).show();
				// sendNum = Constants.sendNumber_GD;

			} else if (imsi.startsWith("46003")) {// 中国电信
				Toast.makeText(context, "当前电信号码发送完毕", 0).show();
				// sendNum = Constants.sendNumber_JT;

			}
		} else {
			// sendNum = Constants.sendNumber_JT;// 集团号码
		}
	}

	/**
	 * 判断该应用在手机中的安装情况
	 * 
	 * @param packageName
	 *            要判断应用的包名
	 */
	private boolean checkAPK(String packageName, Context context) {
		List<PackageInfo> pakageinfos = context.getPackageManager()
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo pi : pakageinfos) {
			String pi_packageName = pi.packageName;
			if (packageName.endsWith(pi_packageName)) {
				return true;
			}
		}
		return false;
	}

	public static byte[] long2Bytes(long l) {
		byte[] bytes = new byte[8];

		int i = 8;
		for (byte b : bytes) {
			b = (byte) (l & 0xff);
			bytes[--i] = b;
			l >>= 8;
		}

		return bytes;
	}

	private String toHexUtil(int n) {
		String rt = "";
		switch (n) {
		case 10:
			rt += "A";
			break;
		case 11:
			rt += "B";
			break;
		case 12:
			rt += "C";
			break;
		case 13:
			rt += "D";
			break;
		case 14:
			rt += "E";
			break;
		case 1:
			rt += "F";
			break;
		default:
			rt += n;
		}
		return rt;
	}

	public String toHex(int n) {
		StringBuilder sb = new StringBuilder();
		if (n / 16 == 0) {
			return toHexUtil(n);
		} else {
			String t = toHex(n / 16);
			int nn = n % 16;
			sb.append(t).append(toHexUtil(nn));
		}
		return sb.toString();
	}

	public String parseAscii(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] bs = str.getBytes();
		for (int i = 0; i < bs.length; i++)
			sb.append(toHex(bs[i]));
		return sb.toString();
	}

}
