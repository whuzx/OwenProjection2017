/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\01mydev\\2017-android\\MyAndroidCollectionProject\\OwenAndroidCollection\\src\\main\\aidl\\com\\MyAndroidCollection\\aidl\\server\\IMyService.aidl
 */
package com.MyAndroidCollection.aidl.server;
public interface IMyService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.MyAndroidCollection.aidl.server.IMyService
{
private static final java.lang.String DESCRIPTOR = "com.MyAndroidCollection.aidl.server.IMyService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.MyAndroidCollection.aidl.server.IMyService interface,
 * generating a proxy if needed.
 */
public static com.MyAndroidCollection.aidl.server.IMyService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.MyAndroidCollection.aidl.server.IMyService))) {
return ((com.MyAndroidCollection.aidl.server.IMyService)iin);
}
return new com.MyAndroidCollection.aidl.server.IMyService.Stub.Proxy(obj);
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
case TRANSACTION_getMap:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
Student _arg1;
if ((0!=data.readInt())) {
_arg1 = Student.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
java.util.Map _result = this.getMap(_arg0, _arg1);
reply.writeNoException();
reply.writeMap(_result);
return true;
}
case TRANSACTION_getStudent:
{
data.enforceInterface(DESCRIPTOR);
Student _result = this.getStudent();
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
case TRANSACTION_registeCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.MyAndroidCollection.aidl.server.ITaskCallBack _arg0;
_arg0 = com.MyAndroidCollection.aidl.server.ITaskCallBack.Stub.asInterface(data.readStrongBinder());
this.registeCallBack(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.MyAndroidCollection.aidl.server.ITaskCallBack _arg0;
_arg0 = com.MyAndroidCollection.aidl.server.ITaskCallBack.Stub.asInterface(data.readStrongBinder());
this.unregisterCallBack(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isTaskRunning:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isTaskRunning();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_stopRunningTask:
{
data.enforceInterface(DESCRIPTOR);
this.stopRunningTask();
reply.writeNoException();
return true;
}
case TRANSACTION_getToken:
{
data.enforceInterface(DESCRIPTOR);
this.getToken();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.MyAndroidCollection.aidl.server.IMyService
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
@Override public java.util.Map getMap(java.lang.String test_class, Student student) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.Map _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(test_class);
if ((student!=null)) {
_data.writeInt(1);
student.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getMap, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readHashMap(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public Student getStudent() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
Student _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getStudent, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = Student.CREATOR.createFromParcel(_reply);
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
@Override public void registeCallBack(com.MyAndroidCollection.aidl.server.ITaskCallBack cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registeCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallBack(com.MyAndroidCollection.aidl.server.ITaskCallBack cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isTaskRunning() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isTaskRunning, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void stopRunningTask() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopRunningTask, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void getToken() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getToken, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getMap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getStudent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registeCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_isTaskRunning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_stopRunningTask = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getToken = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public java.util.Map getMap(java.lang.String test_class, Student student) throws android.os.RemoteException;
public Student getStudent() throws android.os.RemoteException;
public void registeCallBack(com.MyAndroidCollection.aidl.server.ITaskCallBack cb) throws android.os.RemoteException;
public void unregisterCallBack(com.MyAndroidCollection.aidl.server.ITaskCallBack cb) throws android.os.RemoteException;
public boolean isTaskRunning() throws android.os.RemoteException;
public void stopRunningTask() throws android.os.RemoteException;
public void getToken() throws android.os.RemoteException;
}
