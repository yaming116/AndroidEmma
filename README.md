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

# Gradle

```groovy

allprojects {
    repositories {
        maven { url "https://www.jitpack.io" }
    }
}

dependencies {
    debugCompile  'com.github.yaming116.AndroidEmma:library:1.0.0'
    releaseCompile 'com.github.yaming116.AndroidEmma:androidemma-no-op:1.0.0'
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


