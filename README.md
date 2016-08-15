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

#  dump覆盖率文件的俩种方式：

*  一： 主界面的*Activity*实现接口 *EmmaInstrumentationListener*, 添加如下方法，会在程序退出的时候dump测试覆盖率

```java

    private FinishListener finishListener;

    @Override
    public void setFinishListener(FinishListener listener) {
        finishListener = listener;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (finishListener != null){
            finishListener.onActivityFinished();
        }
    }

```

*  二： 在需要的时候调用下面方法

```java
   EmmaUtil.dump(Context);
```

更多信息见demo项目

# 开始手工测试

打开桌面图标 EmmaInstrumentation ,点击 open即可，其实就是执行了

```
adb shell am instrument 当前应用包名/me.shenfan.androidemma.EmmaInstrumentation
```

# 覆盖率结果查看

默认数据保存在 *sdcard/Android/当前项目包名/coverage.ec*

根据coverage.ec [生成报告，请看这里](http://blog.csdn.net/itfootball/article/details/45644159)

# Gradle

```groovy

allprojects {
    repositories {
        maven { url "https://www.jitpack.io" }
    }
}

dependencies {
    debugCompile  'com.github.yaming116.AndroidEmma:library:1.0.1'
    releaseCompile 'com.github.yaming116.AndroidEmma:androidemma-no-op:1.0.1'
}
```

License
-------

    Copyright (C) 2011 花开堪折枝 Software Ltd

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


