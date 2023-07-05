# R8 Full mode Support Proguard: retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# R8 Full mode Support Proguard: kakao
-keep interface com.kakao.sdk.**.*Api

# R8 Full mode Support Proguard: Gson
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type

# R8 Full mode Support Proguard: Kotlin suspend functions and generic signatures
# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation