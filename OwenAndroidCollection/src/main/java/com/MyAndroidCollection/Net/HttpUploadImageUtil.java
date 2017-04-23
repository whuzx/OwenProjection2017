package com.MyAndroidCollection.Net;

/***
 * 2.45.1.  描述
为手机客户端代理新增或更新用户头像。AccountServer内部再调头像文件服务器及UP相关接口。
客户端与服务器之间的接口协议采用HTTP，文件使用multipart/form-data格式编码为附件添加到HTTP报文中。Content-Type采用multipart/form-data格式，每个表单字段的参数值无需编码，但每个表单字段的charset部分需要指定为utf-8。
约束：配置大中小头像的文件最大size和允许的后缀，并进行相应检查。头像缺省最大配置：大头像 64k   中头像 16K  小头像4K。缺省允许的后缀：jpg, jpeg, gif, bmp,tif
2.45.2. 输入参数
参数名称    必须  类型  长度  描述信息
version O   String  5   协议版本号
缺省版本为：01.01
支持欧洲站点升级为：05.01（返回头像URL包含验证码）
userID  M   BigInt      用户ID
reqClientType   M   Int     请求客户端类型
fileCnt M   Int     文件数
Files   M   multipart/form-data     （file content）文件内容：上传文件内容（基于RFC1867标准的HTML中表单的文件）。上传大中小头像文件对应的字段名分别为：BigImage 、 MiddleImage 、SmallImage。
2.45.3. 返回值
参数名称    必须  类型  长度  描述信息
userID  M   Bigint      用户ID（内部）
fileUrlB    M   String      大头像文件URL
fileUrlM    M   String      中头像文件URL
fileUrlS    M   String      小头像文件URL

2.45.4. HTTPS接口示例
   1)请求消息(URL encode编码)
POST https://host:port/AccountServer/IUserInfoMng/ updateHeadPic  HTTPS/1.1
Authorization: 参考《统一账号 Session和Token安全.doc》携带Digest验证码。
消息体：
version =05.01&userID =12321434& reqClientType =7 & fileCnt=3
   正常响应消息(URL encode编码)
resultCode=0& userID =12321434&fileUrlB =xxx.xxx.xxx/f1.jpg& fileUrlM =xxx.xxx.xxx/f2.jpg& fileUrlS =xxx.xxx.xxx/f3.jpg

   异常响应消息(URL encode编码)
resultCode=701000021& errorDesc=Error account format.


 * @author z00201051
 *
 */
public class HttpUploadImageUtil {


    public static void main(String[] args) {
        // TODO Auto-generated method stub


    }



}
