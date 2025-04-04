# 📜 EasyPermissionAndroid

**EasyPermissionAndroid** is a lightweight Android library that simplifies runtime permission handling in Android applications. It allows developers to request and check permissions with minimal code.


## Screenshots

<p align="center">
  <img src="assets/screen_2.png" width="30%" style="margin-right: 40px;" />
  <img src="assets/screen_1.png" width="30%" />
</p>





## ✨ Features

✅ Request runtime permissions easily  
✅ Support for multiple permission requests  
✅ Callback-based API for handling permission results  
✅ Compatible with Android 6.0+ (API 23+)  

---

## 🚀 Installation

### Step 1: Add JitPack Repository

Add the JitPack repository to your **root-level** `settings.gradle` file:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the Dependency

Add the following line to your **module-level** `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.pramodjinturkar999:easypermissionandroid:1.0.1'
}
```

---

## 📌 Usage

### Request a Single Permission

```kotlin
EasyPermissionManager.requestCamera(this) { isGranted ->
    if (isGranted) {
        // Permission granted ✅
    } else {
        // Permission denied ❌
    }
}
```

### Request Multiple Permissions

```kotlin
EasyPermissionManager.requestMultiplePermissions(
    this,
    arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
) { result ->
    if (result[Manifest.permission.CAMERA] == true) {
        // Camera permission granted ✅
    }
    if (result[Manifest.permission.RECORD_AUDIO] == true) {
        // Audio permission granted ✅
    }
}
```

### Available Methods

| Method                 | Description                               |
|------------------------|-------------------------------------------|
| `requestCamera()`      | Requests camera permission 📷             |
| `requestMicrophone()`  | Requests microphone permission 🎤         |
| `requestStorage()`     | Requests storage permission 📂            |
| `requestLocation()`    | Requests location permission 🌍           |
| `requestBluetooth()`   | Requests Bluetooth permission 🔵         |
| `requestCallPhone()`   | Requests phone call permission ☎️       |
| `requestReadContacts()`| Requests contacts permission 📇         |
| `requestReadCalendar()`| Requests calendar permission 📅         |



## 🔥 Contributing

Feel free to contribute to this project by submitting issues or pull requests!  

---

## 📜 License

```txt
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
```

Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  

See the full license in the [LICENSE](LICENSE) file.

