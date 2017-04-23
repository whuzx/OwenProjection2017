/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: G:\\深圳SVN\\HuaweiAccount\\trunk\\BaseLine\\HwID_Core\\src\\com\\huawei\\hwid\\api\\aidl\\ITaskCallBack.aidl
 */
package com.huawei.hwid.api.aidl;
public interface ITaskCallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.huawei.hwid.api.aidl.ITaskCallBack
{
private static final java.lang.String DESCRIPTOR = "com.huawei.hwid.api.aidl.ITaskCallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.huawei.hwid.api.aidl.ITaskCallBack interface,
 * generating a proxy if needed.
 */
public static com.huawei.hwid.api.aidl.ITaskCallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.huawei.hwid.api.aidl.ITaskCallBack))) {
return ((com.huawei.hwid.api.aidl.ITaskCallBack)iin);
}
return new com.huawei.hwid.api.aidl.ITaskCallBack.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_actionPerformed:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.actionPerformed(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.huawei.hwid.api.aidl.ITaskCallBack
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void actionPerformed(java.lang.String actionId, android.os.Bundle bundle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(actionId);
if ((bundle!=null)) {
_data.writeInt(1);
bundle.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_actionPerformed, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_actionPerformed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void actionPerformed(java.lang.String actionId, android.os.Bundle bundle) throws android.os.RemoteException;
}
