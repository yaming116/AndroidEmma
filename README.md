AndroidEmma
====

项目参考TestHome 里面手工测试代码覆盖率手工版，只为提供一种方便大家调用的工具，和当前项目无侵入。

[参考信息](https://testerhome.com/wiki/androidcodecoveragebydockerq) 


#使用方法

* 在app/src目录下面建立debug目录，AndroidManifest.xml 文件信息如下

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="替换成自己的包名">

    <instrumentation
        android:handleProfiling="true"
        android:label="CoverageInstrumentation"
        android:name="me.shenfan.androidemma.EmmaInstrumentation"
        android:targetPackage="替换成自己的包名"/>

</manifest>
```


# 在app下面的build.gradle中添加

```
apply plugin: 'jacoco'
jacoco{
    toolVersion = "0.7.1.201405082137"
}

android{
    buildTypes {
        debug {
            testCoverageEnabled = true
        }   
    }
}
```