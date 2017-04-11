#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_hansheng_studynote_jni_MainActivity_helloJni(JNIEnv *env, jclass type) {

    // TODO


    return (*env)->NewStringUTF(env, returnValue);
}

JNIEXPORT jint JNICALL
Java_com_hansheng_studynote_jni_MainActivity_addCalc(JNIEnv *env, jclass type, jint a, jint b) {

    // TODO

}