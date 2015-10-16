#include <stdio.h>
#include "com_wg_myviews_JniActivity.h"
#include "android/log.h"

/*
 * ����log��־���
 */
#define  LOG_TAG    "cLog"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


//private native String fromC();
/*
 *	�涨��ʽ��java_���ȫ·����+������ ��"_" ����
 *	����ֵ���� jni.h �ļ�
 */
jstring Java_com_wg_myviews_JniActivity_fromC(JNIEnv* evn,jobject obj){

	// �����ַ������� (*NewStringUTF)(JNIEnv*, const char*);
	return (*(*evn)).NewStringUTF(evn,"from C !");
}

/*
 * Class:     com_wg_myviews_JniActivity
 * Method:    add ��������ֵ���
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
 * Method:    addString ƴ���ַ���
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
jstring JNICALL Java_com_wg_myviews_JniActivity_addString
  (JNIEnv * env, jobject obj, jstring str){

	char* chars = " hello!";


	return NULL;

}

/*
 * Class:     com_wg_myviews_JniActivity
 * Method:    setValue ���������ֵ
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_wg_myviews_JniActivity_setValue
  (JNIEnv * env, jobject obj, jintArray arr){
	//��java����ת��cָ��
	jint * cintarray = (*env)->GetIntArrayElements(env,arr,0);
	//��ȡ���鳤��
	int len = (*env)->GetArrayLength(env,arr);

	int i;

	for(i=0;i<len;i++){
		LOGI("[%d]:  %d",i,cintarray[i]);
		cintarray[i] = cintarray[i]+5;
	}
	return arr;

}

