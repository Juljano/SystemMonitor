package com.example.demo;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;


public class SensorState {

    private SystemInfo systemInfo;



    public SensorState(){

        systemInfo = new SystemInfo();
    }


    public void getSensorStates() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        // CPU-Informationen
        CentralProcessor centralProcessor = hardware.getProcessor();
        System.out.println("Prozessor: " + centralProcessor.getProcessorIdentifier().getName());
        new SensorStateData().setProcessorName(centralProcessor.getProcessorIdentifier().getName());
        System.out.println("Anzahl der physischen Kerne: " + centralProcessor.getPhysicalProcessorCount());
        System.out.println("Anzahl der logischen Kerne: " + centralProcessor.getLogicalProcessorCount());
        System.out.println(centralProcessor.getMaxFreq() + " Hertz");
        System.out.println(Arrays.toString(centralProcessor.getCurrentFreq()) + " Hertz");

    }


    public void getOperationSystemInfo() {

        // Betriebssystem-Informationen
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        System.out.println("Betriebssystem: " + operatingSystem.toString());
        System.out.println(operatingSystem.getSystemUptime() / 3600 + " Stunden");
        System.out.println(operatingSystem.getNetworkParams().getHostName());
        System.out.println(Arrays.toString(operatingSystem.getNetworkParams().getDnsServers()));

    }


    public void getDiskInfo() {
        HWDiskStore[] diskStores = systemInfo.getHardware().getDiskStores().toArray(new HWDiskStore[0]);

        for (HWDiskStore diskStore : diskStores) {

            System.out.println("Festplatte: " + diskStore.getName());
            System.out.println("Größe: " + convert(diskStore.getSize()));
            System.out.println("Belegt: " + (diskStore.getWrites() / Math.pow(1024, 2)));
            System.out.println("Verfügbar: " + diskStore.getReads() / Math.pow(1024, 2));


        }


    }


    public void getNetworkInfo() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        NetworkIF[] networkIFs = hardware.getNetworkIFs().toArray(new NetworkIF[0]);
        for (NetworkIF net : networkIFs) {
            System.out.println("Netzwerkschnittstelle: " + net.getName());
            System.out.println("IP-Adresse: " + Arrays.toString(net.getIPv4addr()));
            System.out.println("Download: " + net.getSpeed() / Math.pow(1000, 2) + " MBps");
            System.out.println("Upload: " + net.getSpeed() / Math.pow(1000, 2) / 8 + " MBps");
        }
    }


    //convert byte to TB
    private String convert(long bytes) {

        return bytes / Math.pow(1024, 4) + " Terabyte";

    }

}
