
package com.MyAndroidCollection.Net;

/***
 * 
 * @author z00201051
 *
 */
public class HttpConfig {

	//榛樿鐨?
	//褰撳悜鏈嶅姟鍣ㄥ彂鍑鸿姹傚悗锛岃姹傚拰鏈嶅姟鍣ㄥ缓绔媠ocket杩炴帴锛屼絾鏄緢闀挎椂闂村唴閮芥病鏈夊缓绔媠ocket杩炴帴锛岃繖灏辨椂绗竴绉嶈姹傝秴鏃讹紝杩欑鎯呭喌涓昏鍙戠敓鍦ㄨ姹備簡
	//涓?釜涓嶅瓨鍦ㄧ殑鏈嶅姟鍣ㄣ?瓒呮椂涔嬪悗锛屼細鎶涘嚭InterruptedIOException寮傚父
    public static final int SOCKET_TIMEOUT = 20 * 1000;
    

    //榛樿鐨?
    //瀹㈡埛绔凡缁忎笌鏈嶅姟鍣ㄥ缓绔嬩簡socket杩炴帴锛屼絾鏄湇鍔″櫒骞舵病鏈夊鐞嗗鎴风鐨勮姹傦紝娌℃湁鐩稿簲鏈嶅姟鍣紝杩欏氨鏄浜岀杩炴帴瓒呮椂銆傝繖涓秴鏃朵細鎶涘嚭ConnectTimeoutException寮傚父锛?
    //ConnectTimeoutException缁ф壙鑷狪nterruptedIOException锛屾墍浠ュ彧瑕佹崟鑾稢onnectTimeoutException灏卞彲浠ヤ簡銆?
    public static final int CONNECTION_TIMEOUT =20 * 1000;
    

    public static final String HEADER_NAME_CONNECTION = "Connection";

    public static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";

    public static final int MAX_ROUTE = 8;

    public static final int HTTP_PORT = 8080;

    public static final int HTTPS_PORT = 443;
    
    public static final int MAX_TOTAL_CONNECTIONS = 25;

    public static final String DIGEST_USER = "Digest user";

    public static final String AUTHORIZATION = "Authorization";

    public static final String NONCE = "nonce";

    public static final String RESPONSE = "response";
    
    public static final int TIMEOUT = 10*1000;
    
    public static final int MAX_REQUEST_TIMES = 3;//鎬诲叡3娆? 
    

    
    

}
