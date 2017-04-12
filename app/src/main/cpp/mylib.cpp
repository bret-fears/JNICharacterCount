//
// Created by Bret Fears on 4/11/17.
//

#include "com_willowtreeapps_jniexample_MyNativeActivity.h"
#include <string>
#include <sstream>

class Util {
public:
    static int countWords(const std::string &strString) {
        int length = 0;
        while (strString[length] != '\0') {
            length++;
        }
        return length;
    }
};

void
Java_com_willowtreeapps_jniexample_MyNativeActivity_updateWordCounter(JNIEnv *env, jobject obj, jstring text) {
    jclass activityClass = env->GetObjectClass(obj);
    jfieldID charCountFId = env->GetFieldID(activityClass, "charCountTv", "Landroid/widget/TextView;");
    jobject tvObj = env->GetObjectField(obj, charCountFId);

    jclass textViewClass = env->GetObjectClass(tvObj);
    jmethodID setTextMId = env->GetMethodID(textViewClass, "setText", "(Ljava/lang/CharSequence;)V");

    std::string content(env->GetStringUTFChars(text, 0));
    int word_cnt = Util::countWords(content);
    std::ostringstream oss;
    oss << word_cnt;

    env->CallVoidMethod(tvObj, setTextMId, env->NewStringUTF(oss.str().c_str()));
}