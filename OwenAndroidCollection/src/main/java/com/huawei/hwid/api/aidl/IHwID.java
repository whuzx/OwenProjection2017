/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: G:\\深圳SVN\\HuaweiAccount\\trunk\\BaseLine\\HwID_Core\\src\\com\\huawei\\hwid\\api\\aidl\\IHwID.aidl
 */
package com.huawei.hwid.api.aidl;
public interface IHwID extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.huawei.hwid.api.aidl.IHwID
{
private static final java.lang.String DESCRIPTOR = "com.huawei.hwid.api.aidl.IHwID";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.huawei.hwid.api.aidl.IHwID interface,
 * generating a proxy if needed.
 */
public static com.huawei.hwid.api.aidl.IHwID asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.huawei.hwid.api.aidl.IHwID))) {
return ((com.huawei.hwid.api.aidl.IHwID)iin);
}
return new com.huawei.hwid.api.aidl.IHwID.Stub.Proxy(obj);
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
case TRANSACTION_getToken:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
android.os.Bundle _arg3;
if ((0!=data.readInt())) {
_arg3 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg3 = null;
}
this.getToken(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_registeCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.huawei.hwid.api.aidl.ITaskCallBack _arg0;
_arg0 = com.huawei.hwid.api.aidl.ITaskCallBack.Stub.asInterface(data.readStrongBinder());
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.registeCallBack(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.huawei.hwid.api.aidl.ITaskCallBack _arg0;
_arg0 = com.huawei.hwid.api.aidl.ITaskCallBack.Stub.asInterface(data.readStrongBinder());
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.unregisterCallBack(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_queryFingerPrintBoundedStatus:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.os.Bundle _result = this.queryFingerPrintBoundedStatus(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.huawei.hwid.api.aidl.IHwID
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
@Override public void getToken(java.lang.String packageName, java.lang.String tokenType, java.lang.String accountName, android.os.Bundle bd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(tokenType);
_data.writeString(accountName);
if ((bd!=null)) {
_data.writeInt(1);
bd.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getToken, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registeCallBack(com.huawei.hwid.api.aidl.ITaskCallBack cb, android.os.Bundle bd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
if ((bd!=null)) {
_data.writeInt(1);
bd.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_registeCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallBack(com.huawei.hwid.api.aidl.ITaskCallBack cb, android.os.Bundle bd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
if ((bd!=null)) {
_data.writeInt(1);
bd.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.os.Bundle queryFingerPrintBoundedStatus(android.os.Bundle bd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((bd!=null)) {
_data.writeInt(1);
bd.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_queryFingerPrintBoundedStatus, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getToken = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registeCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_queryFingerPrintBoundedStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void getToken(java.lang.String packageName, java.lang.String tokenType, java.lang.String accountName, android.os.Bundle bd) throws android.os.RemoteException;
public void registeCallBack(com.huawei.hwid.api.aidl.ITaskCallBack cb, android.os.Bundle bd) throws android.os.RemoteException;
public void unregisterCallBack(com.huawei.hwid.api.aidl.ITaskCallBack cb, android.os.Bundle bd) throws android.os.RemoteException;
public android.os.Bundle queryFingerPrintBoundedStatus(android.os.Bundle bd) throws android.os.RemoteException;
}
