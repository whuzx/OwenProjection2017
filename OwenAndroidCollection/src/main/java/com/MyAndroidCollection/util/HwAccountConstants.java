/**
 * CopyRight (c)2013: Huawei
 * Project:            HwID
 * Comments:           HwAccountConstants
 * Author:             z00201051
 * Create Date:        2013-3-1
 * Version:            v1.0
 */
package com.MyAndroidCollection.util;

import android.accounts.AccountManager;
import android.net.Uri;

public class HwAccountConstants {

    private HwAccountConstants(){}
    /**
     * SDK log tag
     */

	public static final String SDK_VERSION = "1.4.14";//格式一定要正确合规范
	public static final String HwID_SDK_LOG = "HwID_SDK_log" + "["+ SDK_VERSION + "]";
    /**
     * account type of huawei cloud(数据库帐号类型)
     */
    public static final String HUAWEI_ACCOUNT_TYPE = "com.huawei.hwid";

    /**
     * token type of huawei account
     */
    public static final String HUAWEI_CLOUND_AUTHTOKEN_TYPE = "cloud";

    /**
     * service token prefix length, we will create service token liken
     * token.substring(0,HUAWEI_SERVICE_AUTHTOKEN_PREFIX_LENGTH) +
     * md5(token:serviceTokenType)
     */
    public static final int HUAWEI_SERVICE_AUTHTOKEN_PREFIX_LENGTH = 20;

    /**
     * HwID app id(包名)
     */
    public static final String HWID_APPID = "com.huawei.hwid";








    /**
     * account name last login
     */
    public static final String PREFERENCES_KEY_LAST_ACCOUNT_NAME = "lastAccountName";

    /**
     * account type last login
     */
    public static final String PREFERENCES_KEY_LAST_ACCOUNTTYPE = "accountType";

    /**
     * account type last login country code
     */
    public static final String PREFERENCES_KEY_LAST_COUNTRYCALLINGCODE = "countryCallingCode";

    /**
     * the country calling code get from ip
     */
    public static final String PREFERENCES_KEY_IP_COUNTRYCALLING = "ip_countryCallingCode";


    /**
     * the country code get from ip
     */
    public static final String PREFERENCES_KEY_IP_COUNTRYCODE = "ip_countryCode";

    /**
     * the country siteID get from ip
     */
    public static final String PREFERENCES_KEY_IP_COUNTRYSITEID = "ip_countrySiteID";

    /**
     * the country native name get from ip
     */
    public static final String PREFERENCES_KEY_IP_COUNTRYNATIVENAME = "ip_countryNativeName";

    /**
     * the country english name get from ip
     */
    public static final String PREFERENCES_KEY_IP_COUNTRYENGLISHNAME = "ip_countryEnglishName";



    /**
     * the flag indicate email is not activate
     */
    public static final String EMAIL_NOT_ACTIVATE = "0";

    /**
     * the flag indicate email is activate
     */
    public static final String EMAIL_ACTIVATE = "1";

    /**
     * Password min length
     */
    public static final int PASSWORD_MIN_LENGTH = 6;

    /**
     * Password max length
     */
    public static final int PASSWORD_MAX_LENGTH = 32;

    /**
     * username login min length
     */
    public static final int USERNAME_MIN_LENGTH = 4;

    /**
     * username register min length
     */
    public static final int USERNAME_REGISTER_MIN_LENGTH = 4;

    /**
     * empty string
     */
    public static final String EMPTY = "";

    /**
     * return default number
     */
    public static final int DEFAULT_NUMBER = -1;

    /**
     * the key of mLoginData when it transfer between activity
     */
    public static final String PARA_LOGIN_DATA = "loginData";

    /**
     * parameter of login complete
     */
    public static final String PARA_COMPLETED = "completed";

    /**
     * token invalidated
     */
    public static final String PARA_TOKEN_INVALIDATED = "tokenInvalidate";

    /**
     * login with user name
     */
    public static final String PARA_LOGIN_WITH_USERNAME = "loginWithUserName";

    /**
     * login with user type
     */
    public static final String PARA_LOGIN_WITH_USERTYPE = "loginWithUserType";

    /**
     * top activity key transfer between setupwizard activity
     */
    public static final String PARA_TOP_ACTIVITY = "topActivity";

    /**
     * request extra from account agent caller
     */
    public static final String PARA_REQUEST_EXTRA = "requestExtra";

    /**
     * request extra from phone+
     */
    public static final String PARA_REQUEST_NEEDBIND = "need_bind";

    /**
     * flag indicate if we skip verify mail interface when register one user
     */
    public static final String PARA_SKIP_VERIFY_MAIL = "skipVerifyMail";

    /**
     * parameter of entry type
     */
    public static final String EXTRA_USERID = "userId";

    /**
     * 开启云服务字段名
     */
    public static final String EXTRA_OPEN_CLOUD = "openCloud";

    /**
     * parameter of site id
     */
    public static final String EXTRA_SITEID = "siteId";

    /**
     * 安全邮箱（activity之间传输数据key值）
     */
    public static final String SECURITY_EMAILS = "securityEmails";

    /**
     * 安全手机号码（activity之间传输数据key值）
     */
    public static final String SECURITY_PHONES = "securityPhones";

    /**
     * 安全手机邮箱和号码（activity之间传输数据key值）
     */
    public static final String SECURITY_EMAILS_AND_PHONES = "securityEmailsAndPhones";

    /**
     * parameter of site id
     */
    public static final String EXTRA_COOKIE = "Cookie";

    /**
     * parameter of device id
     */
    public static final String EXTRA_DEVICEID = "deviceId";

    /**
     * parameter of united id
     */
    public static final String EXTRA_UNITEDID = "unitedId";

    /**
     * parameter of device type
     */
    public static final String EXTRA_DEVICETYPE = "deviceType";

    /**
     * parameter of united type
     */
    public static final String EXTRA_UNITEDTYPE = "unitedType";

    /**
     * parameter of device DDID
     */
    public static final String EXTRA_DDID = "DDID";

    /**
     * parameter of device DDID
     */
    public static final String EXTRA_DEVID = "DEVID";

    /**
     * parameter of device DDTP
     */
    public static final String EXTRA_DDTP = "DEVTP";

    /**
     * parameter of device MHID
     */
    public static final String EXTRA_MHID = "MHID";

    /**
     * parameter of device UUID
     */
    public static final String EXTRA_UUID = "UUID";

    /**
     * parameter of phonenumber
     */
    public static final String EXTRA_PHONE = "phonenumber";

    /**
     * tag for save account name
     */
    public static final String USERNAME = "username";

    public static final String USERINFO = "userInfo";

    public static final String DEVICEINFO = "deviceInfo";

    public static final String EXTRA_MEMBERRIGHTS = "memberRights";
    public static final String EXTRA_ENVEXTRA = "envExtra";
    public static final int ZERO = 0;

    /**
     * date format pattern yyyyMMdd
     */
    public static final String DATE_FORMAT_PATTERN = "yyyyMMdd";

    /**
     * date format pattern yyyy-MM-dd
     */
    public static final String DATE_FORMAT_PAT = "yyyy-MM-dd";

    /**
     * default year
     */
    public static final int DEFAULT_YEAR = 1900;

    /**
     * action that sent when user login or logout
     */
    public static final String ACTION_ACCOUTAGENT_STATUS_CHANGE = "com.huawei.hwid.Account.Status";


    public static final String ACTION_HUAWEI_ID_CHANGED="com.huawei.android.HuaweiId.changed";

    public static final String ACTION_PASSWORD_CHANGED="com.huawei.android.password.changed";


    /**
     * account name parameter that send with ACTION_ACCOUTAGENT_STATUS_CHANGE
     */
    public static final String PARA_ACCOUNT_NAME = "accountName";

    /**
     * this flag indicate user login
     */
    public static final int ACCOUNT_STATE_LOGIN = 1;

    /**
     * this flag indicate user logout
     */
    public static final int ACCOUNT_STATE_LOGOUT = 0;

    /**
     * last userId of cloud account
     */
    public static final String PREFERENCES_KEY_LAST_USERID = "LastUserId";

    /**
     * last phone number of cloud account
     */
    public static final String PREFERENCES_KEY_LAST_BIND_PHONENUM = "LastPhoneNum";

    public static final String PREFERENCES_KEY_RIGHTSID = "rightsID";
    public static final String PREFERENCES_KEY_VIPEXPIREDDATE = "vipExpiredDate";
    /* DTS2012041104163 yKF56955 20120411 add the code start */
    /**
     * action that sent when phone bind status changed
     */
    public static final String ACTION_PHONE_BIND_STATUS_CHANGE = "com.huawei.android.PhoneBind.Status";

    /**
     * set parametres when we send the broadcast to the third cloud app
     * set the bind phone number parametres
     */
    public static final String PARA_PHONE_NUM = "PhoneNum";

    /**
     * set parametres when we send the broadcast to the third cloud app
     * set the bind status parametres
     */
    public static final String PARA_PHONE_BIND_STATUS = "BindStatus";

    /**
     * this flag indicate the binded phoneNum have changed
     */
    public static final int PHONE_NUM_BIND_CHANGED = 3;
    /**
     * this flag indicate the user cancel the check action
     */
    public static final int USER_CANCEL_CHECK = 2;

    /**
     * this flag indicate bing the phone number
     */
    public static final int PHONE_BIND_STATUS = 1;

    /**
     * this flag indicate unbind the phone number
     */
    public static final int PHONE_RELEASE_STATUS = 0;

    /* DTS2012041104163 yKF56955 20120411 add the code end */

    /**
     * this flag indicate bind phone
     */
    public static final String ACCOUNT_PHONE_UNKNOW = "unknow";

    /**
     * this flag indicate bind phone
     */
    public static final String ACCOUNT_PHONE_UNBIND = "unbind";

    /**
     * this flag indicate bind phone
     */
    public static final String ACCOUNT_PHONE_BIND = "bind";


    /**
     * portal address
     */
    public static final String PORATL_ADDRESS = "http://www1.hicloud.com/";



    public static final String PHONENUMBER_TYPE = "2";

    public static final String EMAIL_TYPE = "1";

    public static final String DEFAULT_COUNTRYCALLING_CODE = "+86";

    public static final String DEFAULT_COUNTRY_NATIVE_NAME = "中国";

    public static final String DEFAULT_COUNTRY_ENGLISH_NAME = "CHINA";

    public static final String DEFAULT_COUNTRY_CODE = "cn";

    public static final String DEFAULT_COUNTRY_MNC = "460";


    /**
     * parameter of bind phone number
     */
    public static final String PARA_BIND_PHONENUM = "BindPhoneNumber";

    public static final String PARA_SKIP_BIND = "SkipBind";

    public static final String PARA_ALREADY_LOGIN = "already_login";

    public static final String QUERYR_ANGE_FLAG = "1011";

     /**
      * 查询范围标志
	每位取值：
	1：包含
	0或空：不含
	第1位：用户基本信息
	第2位：用户登录信息
	第3位：用户绑定设备信息
	第4位：用户帐号信息
      */

    //目前的页面只需要包含信息： 用户基本信息 ，用户绑定设备信息
    public static final String QUERYR_USERINFO_FLAG = "1010";

    public static final String QUERYR_ANGE_FLAG_FOR_PHONEPLUS = "0001";

    public static final String PHONE_NUMBER = "PhoneNumber";



    public static final int GOBACK = 100;

    public static final String DEFAULT_DEVICEPLMN = "00000";


    public static final String BIND_TEXT = "verifyValue";

    public static final String USER_ACCOUNT = "userAccount";

    public static final String BIND_TYPE = "verifyType";

    public static final String SEC_TYPE_PHONE = "phone";

    public static final String SEC_TYPE_EMAIL = "email";

    public static final String ACCOUNT_AGREEMENT_VERSION = "agreement_version";

    public static final String ACCOUNT_AGREEMENT_COMMENT = "agreement_comment";

    public static final int DELETE_ACCOUNT_SUCCESS = 10001;

    public static final String SHOW_INTRO = "showIntroduce";
    public static final int SMS_AUTH_CODE_TIME = 60;
    public static final int AUTH_CODE_CHECK_INTER = 200;

    public static final String DEVICEALAISNAME = "device_aliasName";

    public static final String SHARED_ACCOUNT_DEVICENAME = "Account_DeviceName";

    public static final String KEY_REQCLIENTTYPE_INTENTPARAM = "reqClientType";


    public static final String SERVICE_TOKEN = "serviceToken";

    public static final String COOKIE = "Cookie";

    public static final String ACCOUNT_TYPE = "accountType";

    public static final String ACCOUNT_KEY = "account";

    public static final String ADD_ACCOUNT = "addAccount";

    public static final String HWID = "hwid";

    /*
     * the below definition for Account Ttpe
     * */

    /* the common userName */
    public static final String TYPE_USER_NAME = "0";

    /* the email */
    public static final String TYPE_EMAIL = "1";

    /* the phone */
    public static final String TYPE_PHONE = "2";

    /* 第三方帐号类型  天天浏览器临时账号（UP只注册，无密码，UP不提供认证，天天浏览器查看账号存在即可使用） */
    public static final String TYPE_TIANTIAN = "3";

    /* 第三方帐号类型  the sina account */
    public static final String TYPE_SINA = "4";

    /* the security email, for reseting password */
    public static final String TYPE_SECURITY_EMAIL = "5";

    /* the security phone, for reseting password */
    public static final String TYPE_SECURITY_PHONE = "6";

    /* 第三方帐号  the tencent account */
    public static final String TYPE_TENCENT = "7";

    /*  DBank预留 */
    public static final String TYPE_DBANK = "8";


    /*  DBank U卡 */
    public static final String TYPE_DBANKU = "9";

    // 默认对话框的样式
    public static final int NEEDLESS_SETTING_THEME = 0;

    /**
     * preference name of huawei id
     */
    public static final String ACCOUNT_SHARED_PREFERENCES = "AccountLastName";

    public static final String SECSETTING_ACTION = "com.huawei.hwid.setting.secsetting";

    public static final String OLD_PASSWORD = "oldPassword";

    public static final String ACTION_HWID_ACCOUNT_REMOVE = "com.huawei.hwid.ACTION_REMOVE_ACCOUNT";
    public static final String KEY_REMOVED_ACCOUNT_NAME = "removedAccountName";

    public static final String KEY_ALLOW_CHANGEACCOUNT = "allowChangeAccount";

    public static final String IS_EMOTION_INTRODUCE = "isEmotionIntroduce";

    /**
     * 绑定华为账号key.
     */
    public static final String BIND_NEW_HWACCOUNT = "BindNewHwAccount";

    /**
     * 老版本开机向导key.
     */
    public static final String IS_OLD_INTRODUCE = "isOldIntroduce";

    public static final String IS_EMOTION_REGISTER_SUCCESS = "emotionRegisterOk";




    //是否是从内部调用addAccount 函数
    public static final String SHOW_SETTINGS_WELCOME="showWelcomeSettings";


    /**
     *  AccountManager KEY
     *
     */

    public static final  String KEY_ACCOUNTS =AccountManager.KEY_ACCOUNTS;
    public static final  String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE =AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE;
    public static final  String KEY_ACCOUNT_MANAGER_RESPONSE  =AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE;
    public static final  String KEY_ACCOUNT_NAME =AccountManager.KEY_ACCOUNT_NAME;
    public static final  String KEY_ACCOUNT_TYPE  =AccountManager.KEY_ACCOUNT_TYPE;
    public static final  String KEY_AUTHENTICATOR_TYPES  =AccountManager.KEY_AUTHENTICATOR_TYPES;
    public static final  String KEY_AUTHTOKEN =AccountManager.KEY_AUTHTOKEN;
    public static final  String KEY_AUTH_FAILED_MESSAGE  =AccountManager.KEY_AUTH_FAILED_MESSAGE;
    public static final  String KEY_AUTH_TOKEN_LABEL  =AccountManager.KEY_AUTH_TOKEN_LABEL;
    public static final  String KEY_BOOLEAN_RESULT  =AccountManager.KEY_BOOLEAN_RESULT;
    public static final  String KEY_ERROR_CODE  =AccountManager.KEY_ERROR_CODE;
    public static final  String KEY_ERROR_MESSAGE  =AccountManager.KEY_ERROR_MESSAGE;
    public static final  String KEY_INTENT =AccountManager.KEY_INTENT;
    public static final  String KEY_PASSWORD =AccountManager.KEY_PASSWORD;
    public static final  String KEY_USERDATA  =AccountManager.KEY_USERDATA;
    public static final  String LOGIN_ACCOUNTS_CHANGED_ACTION =AccountManager.LOGIN_ACCOUNTS_CHANGED_ACTION;

    /**
     * parameter of phonenumber
     */
    public static final String EXTRA_EMAIL_ADDRESS = "email";

    public static final String EXTRA_BUNDLE = "bundle";
    // 第三方帐号调用时返回给业务的帐号Bundle的Key值
    public static final String EXTRA_ACCOUNT_BUNDLE = "accountBundle";

    public static final String EXTRA_LOGIN_HANDLE = "loginHandle";

    public static final String AUTH_ACCOUNT_NAME = "authAccount";

    /***
     * SDK登录、注册的时候需要使用该key值，默认为false，作用为确定是否使用SDK进行登录、注册
     */
    public static final String EXTRA_IS_USE_SDK = "isUseSDK";

    public static final String EXTRA_PARCE = "parce";

    public static final String SERVICE_TOKEN_TYPE = "ServiceTokenType";

    public static final String CURRENT_ACCOUNT = "currAccount";

    public static final String FROM_ACCOUNT_MANAGER = "isFromManager";

    public static final String IS_CHANGE_ACCOUNT = "isChangeAccount";

    public static final String SERVICETOKENAUTH_IS_SECCUSS = "isSuccess";

    /**
     * the key of curerntLoginUserName
     */
    public static final String PREFERENCES_KEY_CURRENTLOGINUSERNAME = "curName";

    /**
     * parameter of check repeat login
     */
    public static final String PARA_CHECK_REPEAT_LOGIN = "checkRepeatLogin";

    /**
     * parameter of phonenumber
     */
    public static final String EXTRA_CALLING_CODE = "callingCode";

    public static final String ACCOUNT_INFO_PREFERENCES_NAME ="AccountInfo";

    public static final String EXTRA_HWACCOUNT = "hwaccount";

    public static final String ACTION_LOGIN_SUCCESS = "com.huawei.cloudserive.loginSuccess";

    public static final String ACTION_LOGIN_CANCEL = "com.huawei.cloudserive.loginCancel";

    public static final String ACTION_LOGIN_FAILED = "com.huawei.cloudserive.loginFailed";

    public static final String ACTION_QUICK_REGISTER = "com.huawei.hwid.QUICK_REGISTER";

    public static final String ACTION_MODIFY_PWD = "com.huawei.hwid.MODIFY_PWD";

    public static final String ACTION_SECURITY_SETTING = "com.huawei.hwid.SECURITY_SETTING";
    // 第三方帐号绑定华为帐号成功页面action
    public static final String ACTION_UPGRADE_SUCCESS = "com.huawei.third.ACTION_UPGRADE_SUCCESS";
    // 绑定账号页面的Action
    public static final String ACTION_BIND_ACCOUNT = "com.huawei.third.ACTION_BIND_ACCOUNT";
    // 二次密码验证页面的action
    public static final String ACTION_CHECK_LOGINED_PASSWORD = "com.huawei.hwid.CHECK_LOGINED_PASSWORD";

    public static final String PARA_PRIVACY_TYPE = "privacyType";

    public static final String PARA_TREMS_OR_POLICY = "termsOrPolicy";

    public static final String ACTION_CHECKPSD_SUCCESS = "com.huawei.cloudserive.checkPsdSuccess";

    public static final String ACTION_CHECKPSD_CANCEL = "com.huawei.cloudserive.checkPsdCancel";
    // 二次验证密码失败action
    public static final String ACTION_CHECKPSD_FAILED = "com.huawei.cloudserive.checkPsdFailed";

    //注销账号页面的Action
    public static final String ACTION_LOGOUT_FOR_APP = "com.huawei.hwid.ACTION_LOGOUT_FOR_APP";

    //注销账号页面的Action
    public static final String ACTION_LOGOUT_FOR_APP_BY_USERID = "com.huawei.hwid.ACTION_LOGOUT_FOR_APP_BY_USERID";

    public static final String CHECK_PSD_RESULT = "checkPsdResult";

    public static final int NO_SUBID = -999;

    public static class ERROR_STATUS{

        public static final String ERROR_CANCEL_REASON = "getAuthTokenByFeatures : OperationCanceledException occur";

        public static final String EXTRA_ERROR_CODE = "errorcode";

        public static final String EXTRA_ERROR_REASON = "errorreason";

        //传递ErrorStatus的key值
        public static final String EXTRA_ERROR = "error";
    }

    public static final String STR_STARTACTIVITYWAY = "startActivityWay";
    //是否从欢迎界面调用登录界面
    public static final String STR_STARTACTIVITYFORM = "isFromGuide";
    //从何处调用CheckLoginActivity的key值
    public static final String STR_STARTCHECKLOGINPASSWORDWAY = "StartCheckLoginPasswordWay";

    public static final String LOGIN_DIALOG_ACTION = "com.huawei.hwid.LOGIN_DIALOG";

    public static final String LOGIN_ACTION = "com.huawei.hwid.LOGIN";

    public static final String ACCOUNT_MANAGER_ACTION = "com.huawei.hwid.ACCOUNT_MANAGER";

    public static final String EXTRA_LOGININFO = "userLoginInfo";

    public static final String EXTRA_USERACCOUNTINFO = "userAccountInfo";

    public static final String TOKEN_TYPE = "tokenType";

    //保存accessToken到文件的key值
    public static final String ACCESS_TOKEN = "accessToken";

    /**
     * 用户协议条款ID
     * <功能详细描述>
     *
     * @author  z00192955
     * @version  [版本号, 2013-12-14]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class AgreementID{
        /**
         * 隐私政策
         */
        public final static String PRIVACYPOLICYID = "2";

        /**
         * 用户条款
         */
        public final static String TERMSID = "0";

        /**
         * 用户条款和隐私政策
         */
        public final static String PRIVACYPOLICYID_AND_TERMSID = "1";
        //VIP会员协议,根据GetAgreement接口文档，id传6
        public final static String VIP_TERMSID = "6";
    }
    //欢迎界面的action
    public static final String ACTION_STARTUP_GUIDE = "com.huawei.hwid.START_BY_OOBE";
    //欢迎页面是否开启云服务，若开启了，则在登录成功或注册成功后发送广播给cloud+
    public static final String ACTION_OPEN_CLOUDSERVICE = "com.huawei.hwid.ACTION_LOGIN_OPEN_CLOUDSERVICE";
    public static final String IS_OPEN_CLOUD_SERVICE = "isOpenCloudservice";
    public static final String ACTION_PREPARE_LOGOUT_ACCOUNT = "com.huawei.hwid.ACTION_PREPARE_LOGOUT_ACCOUNT";
    public static final String ACTION_LOGOUT_FAIL = "com.huawei.hwid.ACTION_LOGOUT_FAIL";
    // 取消注销华为帐号的action
    public static final String ACTION_LOGOUT_CANCEL = "com.huawei.hwid.ACTION_LOGOUT_CANCEL";

    /**
     * 拉起hicloud退出界面的action
     */
    public static final String ACTION_HICLOUD_LOUGOUT = "com.huawei.android.ds.action.LOGOUT";


    //保存登录的业务应用名，以便注销账户的时候提示用户会删除这些业务的相关数据
    public static final String LOGIN_CLIENT_INFO = "LoginClient";
    //保存注销账户的时候云服务对话框是否勾选同时删除联系人、相片等配置
    public static final String CLOUDSERVICE_CONFIGS = "CloudserviceConfigs";

    public static final String EXTRA_USE_SELF_ACCOUNT = "useSelfAccount";


    //AuthenticatorActivity的路径名(className)
    public static final String AUTHENTICATORACTIVITY_FILE_PATH = "com.huawei.hwid.manager.accountmgr.AuthenticatorActivity";
    //开机向导的包名
    public static final String OOBE_PACKAGE = "com.huawei.hwstartupguide";


    //登录传递的参数结构体(业务传入)
    public static class LOGIN_STATUS{
        /**
         * account name last login
         */
        public static final String PREFERENCES_KEY_LAST_ACCOUNTNAME = "accountName";

        /*key of the flag which marks if the request from HwAccountManager or not*/
        public static final String IS_FROM_APK = "isFromApk";

        /**
         * request account token type
         */
        public static final String PARA_REQUEST_TOKEN_TYPE = "requestTokenType";

        /**
         * the flag to login with pop activity, if set this flag we will use pop
         * Dialog to login
         */
        public static final String PARA_POP_LOGIN = "popLogin";

        public static final String CHOOSE_ACCOUNT = "chooseAccount";

        public static final String NEED_AUTH = "needAuth";

        public static final String SCOPE = "scope";

        public static final String CHOOSE_WINDOW = "chooseWindow";

        public static final String PARA_APPCHANNEL = "loginChannel";

        public static final String PARA_REQCLIENTTYPE = "reqClientType";

        public static final String PARA_CHECK_PASSWORD = "isCheckPassword";

        //是否将业务包名存储到文件中，用于注销时显示的key值
        public static final String IS_SAVE_LOGIN_CLIENT_INFO = "sdkType";
        //业务登录传入此标志判断是否激活会员
        public static final String ACTIVATE_VIP = "activateVip";
    }

    //是否从开机向导进入
    public static final String IS_FROM_OOBE = "isFromOOBE";

    //是否从注册页面进入邮箱验证界面
    public static final String IS_FROM_REGISTER = "isFromRegister";

    //重置密码的邮箱
    public static final String VERIFY_EMAILL_NAME = "emailName";

    //支付绑卡页面的action
    public static final String ACTION_BIND_CARD = "com.huawei.android.hwpay.BIND_CARD";

    //支付的包名
    public static final String PACKAGE_PAY = "com.huawei.android.hwpay";

	/**
	 * hicloud传递Bundle数据的key值
	 */
	public static final String KEY_HICLOUD_LOGOUTINFO = "com.huawei.android.ds.logoutinfo";


    //同步业务的包名
    public static final String APPID_HICLOUD = "com.huawei.android.ds";
    //智慧云的包名
    public static final String APPID_HISPACE = "com.huawei.appmarket";
    //manifest里配置的权限
    public static class PERMISSION {
        public static final String READ_SMS = "android.permission.READ_SMS";
        public static final String SEND_SMS = "android.permission.SEND_SMS";
    }

    //云服务融合
    public static class Cloud {
        public static final String ACTION_ACCOUNT_DETAIL = "com.huawei.hwid.ACTION_MAIN_SETTINGS";
        public static final String ACTION_TAKE_PICTURE = "com.huawei.hwid.ACTION_TAKE_PICTURE";
        public static final String ACTION_HEAD_PIC_CHANGE = "com.huawei.hwid.ACTION_HEAD_PIC_CHANGE";
        //保存头像在服务器的url到本地的key
        public static final String KEY_LAST_HEAD_PICTURE_URL = "last_head_picture_url";
        public static final String HEAD_PIC_URL = "headPicUrl";
        public static final String EXTRA_PICTURE_TYPE = "picture_type";
        public static final int TYPE_TAKE_PICTURE = 1;
        public static final int TYPE_CHOOSE_PICTURE = 2;
        //一下为preference的key，与xml里配置一致
        public static final String KEY_VIP_PECULIAR = "key_vip_peculiar";
        public static final String KEY_ACCOUNT_NAME = "key_account_name";
        public static final String KEY_HEAD_PICTURE = "key_head_picture";
        public static final String KEY_GENDER = "key_gender";
        public static final String KEY_NICK_NAME = "key_nick_name";
        public static final String KEY_ADDRESS = "key_address";
        public static final String KEY_PETAL = "key_petal";
        public static final String KEY_BASE_INFO = "key_basic_info";
        public static final String KEY_CLOUD_MEMORY = "key_cloud_memory";
        public static final String KEY_MODIFY_PWD = "key_modify_pwd";
        public static final String KEY_SAFE_EMAIL = "key_safe_email";
        public static final String KEY_SAFE_PHONE = "key_safe_phone";
        public static final String KEY_LOGOUT = "key_logout";
        public static final String PROVINCE_ID = "provinceId";
        public static final String CITY_NAME = "cityName";
        public static final String PROVINCE_NAME = "provinceName";
        public static final String ARRAY = "array";
    }

    public static class FINGERPRINT {
        public static final String FINGER_ST = "fingerST";
        public static final String VERIFY_TYPE = "verifyType";
        public static final String TEMP_ST="tempST" ;

        /**
         * request account token type
         */
        public static final String EXTRA_REQUEST_TOKEN_TYPE = "requestTokenType";

        //返回登录、注册指纹绑定是否成功的key值
        public static final String EXTRA_FINGER_BIND_SUCCESS = "isSuccess";

        //保存指纹绑定的key值
        public static final String EXTRA_FINGERPRINT_BIND_TYPE = "fingerprintBindType";

        //已开启指纹绑定的userId的key值
        public static final String EXTRA_BIND_FINGER_USERID = "bindFingetUserId";

        //开启指纹绑定的信息
        public static final String EXTRA_OPEN_FINGERPRINT_KEY = "1";

        //关闭指纹绑定的信息
        public static final String EXTRA_CLOSED_FINGERPRINT_KEY = "0";

        //清空指纹验证的次数
        public static final String CLEAN_VERIFY_TIME = "0";

        //是否显示错误提示对话框
        public static final String IS_SHOW_DIALOG = "isShowDialog";
        //判断是否指纹界面还是密码界面
        public static final String IS_PWD_FLAG= "isPwdFlag";

        //验证指纹页面的action
        public static final String ACTION_RESET_PWD_BY_FINGER = "com.huawei.hwid.RESET_PWD_BY_FINGER";

        //登录、注册绑定指纹页面的action
        public static final String ACTION_BIND_FINGER_BY_LOGIN = "com.huawei.hwid.BIND_FINGER_BY_LOGIN";

        //指纹验证密码页面的action
        public static final String ACTION_FINGER_AUTH = "com.huawei.hwid.FINGER_AUTH";

        //指纹验证成功广播
        public static final String ACTION_FINGER_SUCCESS = "com.huawei.cloudserive.fingerSuccess";
        //指纹验证失败广播
        public static final String ACTION_FINGER_CANCEL = "com.huawei.cloudserive.fingerCancel";
        //指纹验证成功关闭activity的广播
        public static final String ACTION_FINGER_SUCCESS_FINISH = "com.huawei.hwid.fingerFinish";
        public static final String USE_FINGER = "use_finger";//是否使用指纹，默认为true
        public static final String START_WAY = "startway";//启动FingerAuthActivity的方式
        public static final int FLAG_FROM_SECURITY = 1;//帐号安全设置
        public static final int FLAG_FROM_LOGOUT = 2;//注销帐号验证指纹、密码
        public static final int FLAG_FROM_APP = 3;//其他业务跳进来
        public static final int FLAG_FROM_ACCOUNT_DETAIL = 4;//云服务帐号详情点击安全邮箱/安全手机
        //查询华为帐号是否支持指纹的url
        public static final String CONTENT_PRIVATER_URL = "content://com.huawei.hwid.api.provider/is_support_fingerprint/1";

        //保存指纹验证次数的key值
        public static final String EXTRA_VERIFY_TIMES = "verifyTimes";
    }
    //default reqClientType
    public static final int DEFAULT_REQ_CLIENT_TYPE = 7;
    //default channel
    public static final int DEFAULT_APP_CHANNEL = 7000000;
    //从开机向导进入的渠道号
    public static final String OOBE_CHANNEL = "8000000";

    //Uid验证密码页面的action
    public static final String ACTION_UID_AUTH = "com.huawei.hwid.UID_AUTH";

    public static final String EXTRA_THIRD_NAME = "thirdName";

    public static final String THIRD_KEY_OPENID = "thirdId";

    public static final String SPLIIT_UNDERLINE = "_";

    public static final String THIRD_KEY_ACCESSTOKEN = "thirdAccessToken";

    public static final String THIRD_KEY_EXPIRSIN = "thirdExpiesTime";

    public static final String EXTRA_FAILED_REASON = "failedReason";
    // 第三方帐号默认帐号名
    public static final String THIRD_ACCOUNT_DEFAULT_NAME = "第三方帐号";
    // 是否已经绑定华为帐号
    public static final String THIRD_ACCOUNT_ALREADY_BIND = "isAlreadyBind";
    // 是否需要发送删除帐号广播
    public static final String IS_SEND_REMOVE_ACCOUNT_BROADCAST = "isSendBroadcast";

    //服务器侧返回的等级
    public static final int VIP_LEVEL_NOT_INIT = -1;
    public static final int VIP_LEVEL_NOT_SUPPORT = 0;
    public static final int VIP_LEVEL_NORMAL = 100000;
    public static final int VIP_LEVEL_SILVER = 100100;
    public static final int VIP_LEVEL_GOLD = 100200;

    public static class VIP {
        //手机本地配置的等级
        public static final int CONFIG_LEVEL_NOT_INIT = -1;  //待初始化
        public static final int CONFIG_LEVEL_NOT_SUPPORT = 0;//不支持
        public static final int CONFIG_LEVEL_NORMAL = 1;     //普卡
        public static final int CONFIG_LEVEL_SILVER = 10010; //银卡
        public static final int CONFIG_LEVEL_GOLD = 10011;   //金卡

        public static final String PARMA_USERID = "userId";
        public static final String PARMA_NEED_ACTIVATE_VIP = "needActivateVip";

        public static final String ACTION_SHOWUSERRIGHTS = "com.huawei.vip.showUserRights";
        //VIP欢迎界面的action
        public static final String ACTION_VIP_WELCOME = "com.huawei.vip.ACTION_VIP_WELCOME";
        //VIP权益展示及登录注册的action
        public static final String ACTION_VIP_STARTUP_GUIDE = "com.huawei.vip.ACTION_VIP_STARTUP_GUIDE";
        //VIP激活成功页面的action
        public static final String ACTION_ACTIVATE_SUCCESS = "com.huawei.vip.ACTION_ACTIVATE_SUCCESS";
        //监听定时器及开机广播的receiver
        public static final String ACTION_NOTIFY_RECEIVER = "com.huawei.vip.ACTION_NOTIFY_RECEIVER";
        //第1、2、3次弹通知的id
        public static final int VIP_NOTIFY_ID = 10011;
        //通知的次数
        public static final String NOTIFY_COUNT = "notify_count";
        private static final long[] NOTIFI_INTERVAL =
                new long[]{
                        1 * 24 * 3600 * 1000l, //1天
                        7 * 24 * 3600 * 1000l, //7天
                        30 * 24 * 3600 * 1000l,//30天
                };
        //解决findbugs问题，不能直接使用数组
        public static long[] getNotifyInterval() {
            long[] interval = new long[NOTIFI_INTERVAL.length];
            System.arraycopy(NOTIFI_INTERVAL, 0, interval, 0, NOTIFI_INTERVAL.length);
            return interval;
        }

        public static final String LAST_NOTIFY_TIME = "last_notify_time";
        public static final String CURRENT_NOTIFY_TIMES = "current_notify_times";//当前第几次通知
        public static final String HAS_ACTIVATED = "has_activated";
        //点击会员欢迎界面的跳过按钮
        public static final String FROM_VIP_SKIP = "from_vip_skip";

        public static final String AUTHORITY = "com.huawei.vip.provider.HwIDVipProvider";
        public static final String VIPDATAbASE_TABLE_CONFIG = "vip_config";
        public static final Uri VIP_PROVIDER = Uri.parse("content://" + AUTHORITY + "/" + VIPDATAbASE_TABLE_CONFIG);
    }
    //验证码过期
    public static final String AUTHCODE_INVALID = "authcode_invalid";

    // 微博客户端不支持第三方帐号登录
    public static final String BLOG_NO_MATCH = "noMatch";
}
