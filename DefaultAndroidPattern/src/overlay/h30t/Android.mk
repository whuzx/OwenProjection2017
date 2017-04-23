LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE_PATH := $(TARGET_OUT)/vendor/overlay/app

LOCAL_MODULE_STEM := HwSystemManager

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := com.owen.systemmanager.overlay.h30t

include $(BUILD_PACKAGE)
