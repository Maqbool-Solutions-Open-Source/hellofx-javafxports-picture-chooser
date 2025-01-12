# JavaFX Project: Picture Selection on Desktop and Android

This is a simple JavaFX application demonstrating how to select pictures from the gallery on both desktop and Android platforms. It utilizes the **JavaFXMobile-Plugin** (or **JavaFXPorts**) to build and deploy the application.

------

## Instructions

### **Desktop**

**Requirements**: JDK 8
To run the application on your desktop:

1. From your IDE, simply execute the `main()` method.

2. Alternatively, use the command line:

   ```
   gradlew run
   ```

------

### **Android**

**Requirements**: JDK 8 and Android SDK

To run the application on Android:

1. Connect your Android device.

2. Execute the following command:

   ```
   gradlew androidInstall
   ```

**Note**:

- For **Android 10 and earlier**, you can directly install the app to your connected device using the command above.
- For **Android 11 and later**, the APK must be properly signed. Follow the steps below to sign and install your APK.

------

## Signing and Installing the APK for Android 11+

1. **Generate a debug keystore** (if not already available):

   ```
   keytool -genkeypair -v -keystore debug.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias debugkey
   ```

2. **Build the debug APK**:

   ```
   gradlew apkDebug
   ```

3. **Copy the APK to your current directory**:

   ```
   copy "build\javafxports\android\Hello FX-unaligned.apk" .
   ```

4. **Sign the APK** using the provided batch script:

   ```
   LIAPP_apksign_windows.bat "Hello FX-unaligned.apk"
   ```

5. **Install the signed APK** on your connected device:

   ```
   adb install "Hello FX-unaligned.signed.apk"
   ```

------

## Additional Resources

- **Related Stack Overflow Question**:
  [Image chooser JavaFX Android mobile](https://stackoverflow.com/questions/53901703/image-chooser-javafx-android-mobile/79348358#79348358)
- **Documentation**:
  [JavaFXPorts](https://docs.gluonhq.com/javafxports/)
  [Charm Down Plugins Javadocs](https://docs.gluonhq.com/charm/javadoc/5.0.2/)
- **Sample Resources**:
  [Gluon Mobile Samples](https://github.com/gluonhq/gluon-samples/releases/tag/gluonmobile5)

------

### **Key Notes**

- This project showcases compatibility with both desktop and Android using JavaFX.
- The signing process ensures the APK meets Android 11+ requirements for installation.
- The provided `LIAPP_apksign_windows.bat` script simplifies the APK signing workflow.