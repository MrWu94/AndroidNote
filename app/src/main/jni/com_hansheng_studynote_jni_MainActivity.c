//引入上面生成的头文件，并实现头文件中声明的方法
#include "com_hansheng_studynote_jni_MainActivity.h"
JNIEXPORT jstring JNICALL Java_com_test_jcit_helloworld_MainActivity_helloJni
        (JNIEnv *env, jclass jobj) {
    return (*env)->NewStringUTF(env,"Hello JNI!");
}

JNIEXPORT jint JNICALL Java_com_test_jcit_helloworld_MainActivity_addCalc
        (JNIEnv *env, jclass jobj, jint ja, jint jb) {
    return ja + jb;
}