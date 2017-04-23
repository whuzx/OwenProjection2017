package com.MyAndroidCollection.account;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.MyAndroidCollection.util.BaseUtil;
import com.MyAndroidCollection.util.BroadcastUtil;
import com.MyAndroidCollection.util.HwAccountConstants;
import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.util.Proguard;
import com.MyAndroidCollection.view.BaseActivity;
import com.huawei.hwid.api.aidl.IHwID;

/***
 *同一进程
 * getUserData
 * getPassword
 * peekAuthToken
 * setAuthToken
 * setPassword
 * setUserData
 * addAccountExplicitly
 *
 *
 * 预置
 * getAccountsByTypeForPackage
 * @author z0020101
 *
 */
public class AccountActivity extends BaseActivity {

	private static final String TAG = "AccountActivity";

	private MyHandler myHandler = null;
	private IHwID mHwIDBackgroundService;

	private OnAccountsUpdateListener listener =null;
	public void onCreate(Bundle savedBundle) {
		setNumber(20);
		super.onCreate(savedBundle);



		bindService(new Intent("com.huawei.hwid.ACTION_HWIDAIDLSERVICE"),
				serviceConnection, Context.BIND_AUTO_CREATE);

		myHandler = new MyHandler(getMainLooper());
		getButtons().get(0).setText("getUserInfoInHwIDBackground");
		getButtons().get(0).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				gotoSyncSetting();
//				aidltest();
				//getUserInfoInHwIDBackground();
				//TestGetSign.test(getApplicationContext());
				//String cityName=MyLocation.getCityName(AccountActivity.this);
				//LogX.i(TAG, "cityName="  +cityName);
				//testSendBoradCast();
			}
		});

		getButtons().get(1).setText("testAddAccount");
		getButtons().get(1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				 //testAddAccount();

				testAddAccountInThread();

			}
		});

		getButtons().get(2).setText("testAddAccountImlicate");
		getButtons().get(2).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testAddAccountImlicate();

			}
		});
		getButtons().get(3).setText("testaddOnAccountsUpdatedListener");
		getButtons().get(3).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testaddOnAccountsUpdatedListener();

			}
		});
		getButtons().get(4).setText("testblockingGetAuthToken");
		getButtons().get(4).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testblockingGetAuthToken();
			}
		});



		getButtons().get(5).setText("testgetAuthenticatorTypes");
		getButtons().get(5).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testgetAuthenticatorTypes();
			}
		});




		getButtons().get(6).setText("testconfirmCredentials");
		getButtons().get(6).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testconfirmCredentials();
			}
		});




		getButtons().get(7).setText("testeditProperties");
		getButtons().get(7).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testeditProperties();
			}
		});




		getButtons().get(8).setText("testgetAccountsByTypeAndFeatures");
		getButtons().get(8).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testgetAccountsByTypeAndFeatures();
			}
		});


		getButtons().get(9).setText("testhasFeatures");
		getButtons().get(9).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testhasFeatures();
			}
		});


		getButtons().get(10).setText("testgetAuthToken");
		getButtons().get(10).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testgetAuthToken();
			}
		});


		getButtons().get(11).setText("testgetAuthTokenhasActivity");
		getButtons().get(11).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testgetAuthTokenhasActivity();
			}
		});


		getButtons().get(11).setText("testgetAuthTokenByFeatures");
		getButtons().get(11).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testgetAuthTokenByFeatures();
			}
		});

		getButtons().get(12).setText("testremoveAccount");
		getButtons().get(12).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				testremoveAccount();
			}
		});



		getButtons().get(13).setText("testgetAccounts");
		getButtons().get(13).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				getAllTypesAccounts();
			}
		});



	}









	protected void testSendBoradCast() {
	    Intent removeAccountIntent = new Intent();
        removeAccountIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        String userid = "111111111111111111111111";
        removeAccountIntent.putExtra(HwAccountConstants.EXTRA_USERID, userid);
        BroadcastUtil.sendAccountRemoveBroadcast(getApplicationContext(), removeAccountIntent);

    }









    protected void getAllTypesAccounts() {
		LogX.i(TAG,
				Proguard.getProguard(AccountManager.get(this).getAccounts()));
		Account[] accounts= AccountManager.get(this).getAccounts();


		for (Account account : accounts) {
			LogX.i(TAG, "name= " + account.name  + " type= " + account.type);
		}

	}

	// 往帐号中添加帐号 对应类型的帐号而已, 有UI页面的。
	private void testAddAccount() {

		/* <uses-permission android:name="android.permission.MANAGE_ACCOUNTS" /> */
		AccountManager.get(this).addAccount("com.huawei.hwid", "cloud1", null,
				null, AccountActivity.this,
				new AccountManagerCallback<Bundle>() {

					@Override
					public void run(AccountManagerFuture<Bundle> arg0) {
						try {
							LogX.i(TAG,
									"addAccount callback::"
											+ Proguard.getProguard(arg0
													.getResult()));
						} catch (OperationCanceledException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (AuthenticatorException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, myHandler);

	}

	private void testAddAccountInThread() {

		new Thread(new Runnable() {

			Looper curLooper = Looper.myLooper();

			MyHandler myHandler = new MyHandler(curLooper);

			@Override
			public void run() {
				/*
				 * <uses-permission
				 * android:name="android.permission.MANAGE_ACCOUNTS" />
				 */
				AccountManager.get(AccountActivity.this).addAccount(
						"com.huawei.hwid", "cloud1", null, null,
						AccountActivity.this,
						new AccountManagerCallback<Bundle>() {

							@Override
							public void run(AccountManagerFuture<Bundle> arg0) {
								try {
									LogX.i(TAG,
											"addAccount callback::"
													+ Proguard.getProguard(arg0
															.getResult()));
								} catch (OperationCanceledException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (AuthenticatorException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}, myHandler);

			}
		}).start();

	}

	// 意义不大，添加到的必须是系统帐号，除非主token也搞到手，要不只是业务自己的一个信息，
	// 没有主token，什么也是白干，除非有密码，但是这个不现实, 此外要求是一个进程（如果签名不一致也不OK）
	private void testAddAccountImlicate() {

		// This method requires the caller to hold the permission
		// AUTHENTICATE_ACCOUNTS and to
		// have the same UID as the added account's authenticator.

		try {

			Bundle userDataBundle = new Bundle();
			userDataBundle.putString("TEST", "test00000");
			AccountManager.get(this).addAccountExplicitly(
					new Account("13871362863", "com.huawei.hwid"), "qwerty",
					userDataBundle);
		} catch (Exception e) {
			LogX.e(TAG, e.toString(), e);
		}


	}

	private void testaddOnAccountsUpdatedListener() {

		// This method requires the caller to hold the permission GET_ACCOUNTS.


		listener=new OnAccountsUpdateListener(){

			@Override
			public void onAccountsUpdated(Account[] accounts) {
				for (Account account : accounts) {
					LogX.i(TAG, account.toString());
				}

			}

		};

		AccountManager.get(this).addOnAccountsUpdatedListener(listener, myHandler, true);
	}

	// 只有新增的帐号（调用登录接口）才会被存储 ST，否则是不会被存储的。 不能在主线程中做事情
	private void testblockingGetAuthToken() {

		// This method requires the caller to hold the permission
		// USE_CREDENTIALS.

		new Thread(new Runnable() {

			@Override
			public void run() {
				Account[] accounts = AccountManager.get(AccountActivity.this)
						.getAccountsByType("com.huawei.hwid");

				for (Account account : accounts) {
					String Token;
					try {
						Token = AccountManager.get(AccountActivity.this)
								.blockingGetAuthToken(account,
										"com.huawei.hidisk", false);
						LogX.i(TAG, Token);
					} catch (OperationCanceledException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AuthenticatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	private void testconfirmCredentials() {
		// This method requires the caller to hold the permission
		// MANAGE_ACCOUNTS.

		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid1");

		for (Account account : accounts) {
			Bundle options =new Bundle();
			options.putString(AccountManager.KEY_PASSWORD, "qwerty");
			//AccountManager.get(this).confirmCredentialsAsUser(account, options, activity, callback, handler, userHandle)
		AccountManager.get(this).confirmCredentials(account, options, AccountActivity.this, new AccountManagerCallback<Bundle>() {

			@Override
			public void run(AccountManagerFuture<Bundle> arg0) {
				try {
					LogX.i(TAG, Proguard.getProguard(arg0.getResult()));
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, myHandler);

		}
	}

	private void testeditProperties() {
		// This method requires the caller to hold the permission
		// MANAGE_ACCOUNTS.
		AccountManager.get(this).editProperties("com.huawei.hwid", this, new AccountManagerCallback<Bundle>() {

			@Override
			public void run(AccountManagerFuture<Bundle> arg0) {
				try {
					LogX.i(TAG, "call back=" +Proguard.getProguard(arg0.getResult()));
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, myHandler);

	}

	// getAccountsByTypeForPackage 为API 18 新增的函数
	private void testgetAccountsByTypeForPackage() {
		// accounts. This method can only be called by system apps.

	//	AccountManager.get(this).get

	}

	@SuppressWarnings("deprecation")
	//This method was deprecated in API level 14.

	private void testgetAuthToken(){

		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid");

		if (accounts.length>0) {
			Bundle options =new Bundle();
			AccountManager.get(this).getAuthToken(accounts[0], "com.huawei.hidisk", options, true, new AccountManagerCallback<Bundle>() {

				@Override
				public void run(AccountManagerFuture<Bundle> arg0) {
					try {
						LogX.i(TAG, Proguard.getProguard(arg0.getResult()));
					} catch (OperationCanceledException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AuthenticatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}, myHandler);


		/*AccountManager.get(this).getAuthToken(accounts[0], "com.huawei.hidisk", true, new AccountManagerCallback<Bundle>() {

			@Override
			public void run(AccountManagerFuture<Bundle> arg0) {
				try {
					LogX.i(TAG, Proguard.getProguard(arg0.getResult()));
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, myHandler);
		}*/
	}
	}
	private void testinvalidateAuthToken(){


		AccountManager.get(this).invalidateAuthToken("com.huawei.hwid", "");

	}


	private void testnewChooseAccountIntent(){

		//AccountManager.get(this).
		//newChooseAccountIntent(selectedAccount, allowableAccounts, allowableAccountTypes, alwaysPromptForAccount, descriptionOverrideText, addAccountAuthTokenType, addAccountRequiredFeatures, addAccountOptions)
	}

	private void testremoveAccount(){

		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid");

		for (Account account : accounts) {
			AccountManager.get(this).removeAccount(account, new AccountManagerCallback<Boolean>() {

				@Override
				public void run(AccountManagerFuture<Boolean> arg0) {
					try {
						LogX.i(TAG, "res="+ arg0.getResult());
					} catch (OperationCanceledException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AuthenticatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}, myHandler);
		}

	}

	private void testgetAuthTokenhasActivity(){

		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid");

		Bundle options =new Bundle();



		if (accounts.length>0) {
			AccountManager.get(this).getAuthToken(accounts[0], "com.huawei.hidisk", options, AccountActivity.this, new AccountManagerCallback<Bundle>() {

			@Override
			public void run(AccountManagerFuture<Bundle> arg0) {
				try {
					LogX.i(TAG, Proguard.getProguard(arg0.getResult()));
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, myHandler);
		}
	}


	private void testgetAuthTokenByFeatures(){
		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid");

		Bundle options =new Bundle();





			String[] features={"basic"};
			AccountManager.get(this).getAuthTokenByFeatures("com.huawei.hwid", "com.huawei.hidisk",features, AccountActivity.this,options, options, new AccountManagerCallback<Bundle>() {

			@Override
			public void run(AccountManagerFuture<Bundle> arg0) {
				try {
					LogX.i(TAG, Proguard.getProguard(arg0.getResult()));
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, myHandler);

	}


	// 登录之后才知道有没有该特性
	private void testhasFeatures(){

		Account[] accounts = AccountManager.get(AccountActivity.this)
				.getAccountsByType("com.huawei.hwid");


		if (!BaseUtil.getVersionName(getApplicationContext(), "com.huawei.hwid").startsWith("1.4")) {
			LogX.i(TAG, "has no feature");
			return ;

		}



		if (accounts.length>0) {
			String[] features={"basic","fingerprint"};
			AccountManager.get(this).hasFeatures(accounts[0], features, new AccountManagerCallback<Boolean>() {

				@Override
				public void run(AccountManagerFuture<Boolean> arg0) {
				   try {
					LogX.i(TAG, ""+ arg0.getResult());
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				}
			}, myHandler);

		}

	}

	private void testgetAccountsByTypeAndFeatures() {
		// This method requires the caller to hold the permission GET_ACCOUNTS.

		//String[] featureStrings={"hwid","google"};

		String[] features={"basic"};

		AccountManager.get(this).getAccountsByTypeAndFeatures("com.huawei.hwid", features,
				new AccountManagerCallback<Account[]>() {

			@Override
			public void run(AccountManagerFuture<Account[]> arg0) {

				Account[] accounts;
				try {
					accounts = arg0.getResult();
					for (Account account : accounts) {
						LogX.i(TAG, account.toString());
					}
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}, myHandler);



	}

	// 直接通过自由接口获取信息。 不包括ST。
	private void getUserInfoInHwIDBackground() {


		android.util.Log.println(1, "{OWEN}", "TEST=====");

		LogX.i(TAG, "getUserInfoInHwIDBackground");

		Account[] accounts = AccountManager.get(this).getAccountsByType(
				"com.huawei.hwid");

		for (Account account : accounts) {
			LogX.i(TAG, account.toString());

			Bundle updateBundle = new Bundle();
			updateBundle.putBoolean("getUserId", true);

			AccountManager.get(this).updateCredentials(account, "cloud",
					updateBundle, AccountActivity.this,
					new AccountManagerCallback<Bundle>() {

						@Override
						public void run(AccountManagerFuture<Bundle> amFuture) {
							Bundle resBundle;
							try {
								resBundle = (Bundle) amFuture.getResult();
								String userId = (String) resBundle
										.get("userId");
								int siteId = resBundle.getInt("siteId", 0);
								String deviceId = (String) resBundle
										.get("deviceId");
								String deviceType = (String) resBundle
										.get("deviceType");

								LogX.i(TAG,
										"resBundle"
												+ Proguard
														.getProguard(resBundle));
							} catch (OperationCanceledException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (AuthenticatorException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}, null);
		}

	}


	private void testgetAuthenticatorTypes(){
		 AuthenticatorDescription[] authenticatorDescriptions= AccountManager.get(this).getAuthenticatorTypes();
		 for (AuthenticatorDescription authenticatorDescription : authenticatorDescriptions) {
			LogX.i(TAG, "packageName" +authenticatorDescription.packageName   + "accountPreferencesId"+ authenticatorDescription.accountPreferencesId
			+ "customTokens"+ authenticatorDescription.customTokens  +authenticatorDescription.iconId +authenticatorDescription.labelId
			+authenticatorDescription.smallIconId+ "type"+authenticatorDescription.type+ authenticatorDescription.describeContents()
			);
		}

	}

	private class MyHandler extends Handler {

		public MyHandler(Looper looper) {
			super(looper);

		}

		@Override
		public void handleMessage(Message msg) {// 处理消息
			LogX.i(TAG, "handleMessage");
			Log.e("looptest", msg.obj.toString() + "");
			LogX.i(TAG, Proguard.getProguard(msg.obj.toString()));

		}

	}


	public void onDestory(){
		super.onDestroy();
		if (null != listener) {
			AccountManager.get(this).removeOnAccountsUpdatedListener(listener);
		}



	}
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			   mHwIDBackgroundService = IHwID.Stub.asInterface(service);
		        /*try {
		            if(null != mHwIDBackgroundService) {
		                mHwIDBackgroundService.registeCallBack(mCallback,new Bundle());
		            }
		        } catch (RemoteException e) {
		            LogX.e(TAG, "RemoteException: /" + e.toString(), e);
		        }*/

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	};

/*	 private ITaskCallBack mCallback = new ITaskCallBack.Stub() {

		@Override
		public void actionPerformed(String actionId, Bundle bundle)
				throws RemoteException {
			// TODO Auto-generated method stub

		}

	 };*/

	public void aidltest(){
		try {
			Bundle bundle=mHwIDBackgroundService.queryFingerPrintBoundedStatus(null);
			LogX.i("TEST", Proguard.getProguard(bundle));
		} catch (RemoteException e) {

			e.printStackTrace();
		}

	}

	public void gotoSyncSetting(){
		Intent i = new Intent("android.settings.ACCOUNT_SYNC_SETTINGS");
        i.putExtra("account",
            new Account("15927582402",
                "com.huawei.hwid"));
        startActivity(i);

	}
}
