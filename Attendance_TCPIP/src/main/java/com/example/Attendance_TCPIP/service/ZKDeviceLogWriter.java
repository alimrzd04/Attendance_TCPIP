package com.example.Attendance_TCPIP.service;

import com.example.Attendance_TCPIP.sdk.ZkemSDK;
import com.example.Attendance_TCPIP.util.FileUtil;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

public class ZKDeviceLogWriter {

    private static final String DEVICE_IP = "192.168.1.201";
    private static final int PORT = 4370;
    private static final int MACHINE_NO = 1;

    public void fetchLogsToFile() {
        ZkemSDK sdk = ZkemSDK.INSTANCE;

        boolean connected = sdk.Connect_NET(DEVICE_IP, PORT);
        if (!connected) {
            System.out.println("❌ Cihaza qoşula bilmədi.");
            return;
        }

        String fileName = FileUtil.generateFilename();
        System.out.println("✅ Qoşuldu, loglar alınır...");

        if (sdk.ReadGeneralLogData(MACHINE_NO)) {
            ByteByReference enrollNo = new ByteByReference();
            IntByReference verifyMode = new IntByReference();
            IntByReference inOutMode = new IntByReference();
            IntByReference year = new IntByReference();
            IntByReference month = new IntByReference();
            IntByReference day = new IntByReference();
            IntByReference hour = new IntByReference();
            IntByReference minute = new IntByReference();
            IntByReference second = new IntByReference();
            IntByReference workCode = new IntByReference();

            while (sdk.SSR_GetGeneralLogData(
                    MACHINE_NO,
                    enrollNo,
                    verifyMode,
                    inOutMode,
                    year,
                    month,
                    day,
                    hour,
                    minute,
                    second,
                    workCode)) {

                String log = String.format(
                        "User: %s | Date: %04d-%02d-%02d %02d:%02d:%02d | Mode: %d",
                        enrollNo.getValue(),
                        year.getValue(), month.getValue(), day.getValue(),
                        hour.getValue(), minute.getValue(), second.getValue(),
                        inOutMode.getValue());

                FileUtil.writeLineToFile(log, fileName);
                System.out.println(log);
            }

            System.out.println("✅ Loglar fayla yazıldı: " + fileName);
        } else {
            System.out.println("⚠️ Heç bir log tapılmadı.");
        }

        sdk.Disconnect();
        System.out.println("🔌 Cihazdan ayrıldı.");
    }

}
