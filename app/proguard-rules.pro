# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/janisharali/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#android iconics
-keep class .R
-keep class **.R$* {
    <fields>;
}
# proguard configuration common for all Android apps
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keep public class * {
#    public protected *;
#}
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}
##---------------End: proguard configuration common for all Android apps ----------

# ActiveAndroid
-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer


# ButterKnife 7
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}



#proguard-calligraphy
-keep class uk.co.chrisjenx.calligraphy.* { *; }
-keep class uk.co.chrisjenx.calligraphy.*$* { *; }


# Glide specific rules #
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


## GSON 2.2.4 specific rules ##

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

-keepattributes EnclosingMethod

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.dubai.fa.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }


# Rxjava-promises
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent

# Dagger ProGuard rules.
# https://github.com/square/dagger

-dontwarn dagger.internal.codegen.**
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection



# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

# design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }


## Google Play Services 4.3.23 specific rules ##
## https://developer.android.com/google/play-services/setup.html#Proguard ##

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#Leakcanary
-dontwarn com.squareup.haha.guava.**
-dontwarn com.squareup.haha.perflib.**
-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
-keep class com.squareup.haha.** { *; }
-keep class com.squareup.leakcanary.** { *; }
-dontwarn android.app.Notification

#Mockito
-dontwarn org.mockito.**

#Stetho
-keep class com.facebook.stetho.** { *; }
-dontwarn com.facebook.stetho.**

#Timber
-dontwarn org.jetbrains.annotations.**

##---junit
-dontwarn android.test.**


##---firebase
-keep class com.firebase.** { *; }
-keep class org.apache.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }
-dontwarn org.w3c.dom.**
-dontwarn org.joda.time.**
-dontwarn org.shaded.apache.**
-dontwarn org.ietf.jgss.**


## RecyclerView
#-keep public class * extends android.support.v7.widget.RecyclerView$LayoutManager {
#    public <init>(...);
#}
#
## Keep the BuildConfig
#-keep class com.example.BuildConfig { *; }

# Keep the support-v4 library
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }

### Fabric
# In order to provide the most meaningful crash reports
-keepattributes SourceFile,LineNumberTable
# If you're using custom Eception
-keep public class * extends java.lang.Exception

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

### FlexboxLayout
-keep public class com.google.android.flexbox.FlexboxLayoutManager

### daimajia.easing
-keep class com.daimajia.androidanimations.** { *; }
-keep interface com.daimajia.androidanimations.** { *; }
-keep class com.daimajia.easing.** { *; }
-keep interface com.daimajia.easing.** { *; }

### mikepenz:materialdrawer
-keep class com.mikepenz.** { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn com.squareup.okhttp.OkHttpClient
-dontwarn com.squareup.okhttp.Cache
-dontwarn com.squareup.okhttp.CacheControl
-dontwarn com.squareup.okhttp.CacheControl$Builder
-dontwarn com.squareup.okhttp.Call
-dontwarn com.squareup.okhttp.Response
-dontwarn com.squareup.okhttp.Request
-dontwarn com.squareup.okhttp.Request$Builder
-dontwarn com.squareup.okhttp.ResponseBody

###crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**