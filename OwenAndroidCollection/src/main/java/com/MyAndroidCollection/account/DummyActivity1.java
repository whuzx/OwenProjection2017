package com.MyAndroidCollection.account;

import java.io.IOException;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;

import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.util.Proguard;

public class DummyActivity1 extends Activity {

	private static final String TAG = "DummyActivity1";
	private static final String ACTION_GET_TOKEN = "com.huawei.hwid.GET_AUTH_TOKEN";
	private static final String PARA_GET_USERID = "getUserId";
	private AccountManager AccMgr = null;
	private String tokenType = "";
	private String authToken = "";
	private String accountName = "";
	private String accountType = "";
	final int GET_TOKEN = 1;

	protected void onCreate(Bundle savedInstanceState) {
		LogX.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		AccMgr = AccountManager.get(this);

		Intent intent = getIntent();
		if (null == intent) {
			LogX.v(TAG, "we got a wrong intent");
			return;
		}

		
		tokenType = this.getPackageName();//APPID
		
		
		PackageManager pkgMgr = getPackageManager();
		if (null != pkgMgr) {
			List<ResolveInfo> list = pkgMgr.queryIntentActivities(new Intent(
					ACTION_GET_TOKEN), 0);

			if (list != null && list.size() > 0) {

				LogX.v(TAG, "the list size =" + list.size());

				Bundle bundle = new Bundle();
			

				bundle.putString("ServiceType", tokenType); 
				bundle.putBoolean("chooseAccount", false); 
				bundle.putBoolean("needAuth", false);

				bundle.putInt("scope", 1); 

				intent = new Intent(ACTION_GET_TOKEN);
				intent.putExtras(bundle);

				intent = new Intent(ACTION_GET_TOKEN);
				intent.putExtras(bundle);
				startActivityForResult(intent, GET_TOKEN);
				
			} else {
				LogX.v(TAG, "did not have the Access to HwID");
			}
			return;
		}

	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		LogX.v(TAG, "onActivityResult::requestCode==>" + requestCode);

		if (requestCode == GET_TOKEN) {
			Bundle bundle = null;
			int errCode = 0;
			String strReason = "";
			LogX.v(TAG, "onActivityResult  RESULT_OK" );
			if (resultCode == RESULT_OK) {
				bundle = data.getExtras();
				String exception = (String) bundle.get("Exception");
				if (exception != null && "".equals(exception)) {
					accountName = (String) bundle
							.get(AccountManager.KEY_ACCOUNT_NAME);
					accountType = (String) bundle
							.get(AccountManager.KEY_ACCOUNT_TYPE);
					authToken = (String) bundle
							.get(AccountManager.KEY_AUTHTOKEN);
					LogX.v(TAG,
							"AuthTokenCallBack: accountName="
									+ Proguard.getProguard(accountName)
									+ " accountType=" + accountType);
				} else {
					if ("AuthenticatorException".equals(exception)) {

						LogX.v(TAG, "AuthenticatorException");
					} else if ("IOException".equals(exception)) {

						LogX.v(TAG, "IOException");
					} else if ("AccessException".equals(exception)) {

						LogX.v(TAG, "AccessError:appID is not allowed");

					} else {

						LogX.v(TAG, "OperationCanceledException");
					}
				}
			} else {
				LogX.v(TAG, "OperationCanceledException");
			}
			if ((errCode != 0 && !TextUtils.isEmpty(strReason))
					|| bundle == null) {
	
				if (bundle == null) {
					LogX.v(TAG, "AuthTokenCallBack:run bundle is null");
				} else {
					LogX.v(TAG, "AuthTokenCallBack:error");

				}
				DummyActivity1.this.finish();
				return;
			}
		
			getUserInfo(authToken, accountName, errCode);
		}
	}

	private void getUserInfo(String authToken, String accountName, int errCode) {
		if (!TextUtils.isEmpty(authToken) && !TextUtils.isEmpty(accountName)) {
			Account account = new Account(accountName,
					"com.huawei.hwid");

			Bundle updateBundle = new Bundle();
			updateBundle.putBoolean(PARA_GET_USERID, true);
			AccMgr.updateCredentials(account, tokenType, updateBundle,
					DummyActivity1.this, new UserIdCallBack(), null);

		} else {
			LogX.v(TAG, "AuthTokenCallBack:error 锛宼oken or accountName is null");
			DummyActivity1.this.finish();
			return;
		}
	}

	
	private class UserIdCallBack implements AccountManagerCallback<Bundle> {

		public void run(AccountManagerFuture<Bundle> amFuture) {
			try {
				Bundle infobundle = (Bundle) amFuture.getResult();
				
				LogX.d(TAG, "userinfo=" + infobundle);
				 String userId = (String) infobundle.get("userId");
				 int siteId =  infobundle.getInt("siteId",0);
				 String deviceId = (String) infobundle.get("deviceId");
				 String deviceType = (String) infobundle.get("deviceType");


				
			} catch (OperationCanceledException e) {
				e.printStackTrace();
				LogX.e(TAG, "OperationCanceledException / " + e.toString(), e);
			} catch (AuthenticatorException e) {
				e.printStackTrace();
				LogX.e(TAG, "AuthenticatorException / " + e.toString(), e);
			} catch (IOException e) {
				e.printStackTrace();
				LogX.e(TAG, "IOException / " + e.toString(), e);
			} finally {
				DummyActivity1.this.finish();
			}
		}
	}

	@Override
	public void onBackPressed() {
		try {
			super.onBackPressed();
		} catch (IllegalStateException e) {
			LogX.e(TAG,
					"catch IllegalStateException throw by FragmentManager!", e);
		}
	}

}