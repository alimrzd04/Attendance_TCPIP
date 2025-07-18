package com.example.Attendance_TCPIP.sdk;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

public interface ZkemSDK extends Library {

    ZkemSDK INSTANCE = Native.load("zkemkeeper", ZkemSDK.class);

    boolean Connect_NET(String ip, int port);
    void Disconnect();
    boolean ReadGeneralLogData(int machineNumber);
    boolean SSR_GetGeneralLogData(
            int machineNumber,
            ByteByReference dwEnrollNumber,
            IntByReference dwVerifyMode,
            IntByReference dwInOutMode,
            IntByReference dwYear,
            IntByReference dwMonth,
            IntByReference dwDay,
            IntByReference dwHour,
            IntByReference dwMinute,
            IntByReference dwSecond,
            IntByReference dwWorkCode
    );

}
