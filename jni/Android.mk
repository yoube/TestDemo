LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#���ɿ��ļ�����
LOCAL_MODULE    := hello-jni
#c �ļ���λ��
LOCAL_SRC_FILES := hello.c
#log ��־�����
LOCAL_LDLIBS    := -lm -llog -ljnigraphics

include $(BUILD_SHARED_LIBRARY)