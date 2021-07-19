package com.example.demo.utils.common;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.UUID;

/**
 * CodeGenerator
 *
 * @author hui.tao
 * @description
 * @date 2017/2/8
 */
public class CodeGenerator {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    public static volatile HashMap<String, String> hashMap = Maps.newHashMapWithExpectedSize(1000000);

    public static String generateShortUuid(int length) {
        length = length < 6 ? 6 : length;
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = generateUuid();
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static void main(String[] args) {


        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int abs = Math.abs(7);
                String uuid = generateShortUuid(abs);
                hashMap.put(uuid, uuid);
            }
            System.out.println(hashMap.size());
        }).run();
        System.out.println(hashMap.size());

    }
}
