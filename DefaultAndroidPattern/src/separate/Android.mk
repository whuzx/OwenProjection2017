# DTS2015012006767 yubinke/00159260 20150120 create
# DTS2015021002179 xiongshiyi/x00165767 20150119 modify
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := com.huawei.systemmanager.separated.xml
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := $(LOCAL_MODULE)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_JAVA_LIBRARIES += conscrypt
LOCAL_STATIC_JAVA_LIBRARIES += tms5
LOCAL_STATIC_JAVA_LIBRARIES += Hianalytics
LOCAL_STATIC_JAVA_LIBRARIES += PushLite_SDK_V2606
LOCAL_MODULE := com.huawei.systemmanager.separated
ifeq ($(call sdk-great-equal, 23), true)
#TODO now, error happens while dex2oat, so diable it. delete this future
LOCAL_DEX_PREOPT:= false
else
endif
include $(BUILD_JAVA_LIBRARY) 

