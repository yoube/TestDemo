#include <stdio.h>
#include "com_wg_myviews_JniActivity.h"
#include "android/log.h"

/*
 * 设置log日志输出
 */
#define  LOG_TAG    "cLog"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


//private native String fromC();
/*
 *	规定格式：java_类的全路径名+方法名 用"_" 隔开
 *	返回值查找 jni.h 文件
 */
jstring Java_com_wg_myviews_JniActivity_fromC(JNIEnv* evn,jobject obj){

	// 创建字符串方法 (*NewStringUTF)(JNIEnv*, const char*);
	return (*(*evn)).NewStringUTF(evn,"from C !");
}

/*
 * Class:     com_wg_myviews_JniActivity
 * Method:    add 计算两个值相加
 * Signature: (II)I
 */
jint JNICALL Java_com_wg_myviews_JniActivity_add
  (JNIEnv * env, jobject obj, jint intx, jint inty){

	LOGI("x = %d",intx);
	LOGI("y = %d",inty);

	return intx+inty;
}

/*
 * Class:     com_wg_myviews_JniActivity
 * Method:    addString 拼接字符串
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
jstring JNICALL Java_com_wg_myviews_JniActivity_addString
  (JNIEnv * env, jobject obj, jstring str){

	char* chars = " hello!";


	return NULL;

}

/*
 * Class:     com_wg_myviews_JniActivity
 * Method:    setValue 设置数组的值
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_wg_myviews_JniActivity_setValue
  (JNIEnv * env, jobject obj, jintArray arr){
	//把java数组转成c指针
	jint * cintarray = (*env)->GetIntArrayElements(env,arr,0);
	//获取数组长度
	int len = (*env)->GetArrayLength(env,arr);

	int i;

	for(i=0;i<len;i++){
		LOGI("[%d]:  %d",i,cintarray[i]);
		cintarray[i] = cintarray[i]+5;
	}
	return arr;

}

