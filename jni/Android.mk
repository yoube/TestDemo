LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#生成库文件名字
LOCAL_MODULE    := hello-jni
#c 文件的位置
LOCAL_SRC_FILES := hello.c
#log 日志输出库
LOCAL_LDLIBS    := -lm -llog -ljnigraphics

include $(BUILD_SHARED_LIBRARY)