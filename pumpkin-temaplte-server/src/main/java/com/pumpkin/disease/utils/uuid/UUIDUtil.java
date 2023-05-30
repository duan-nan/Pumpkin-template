package com.pumpkin.disease.utils.uuid;

import java.util.Random;
import java.util.UUID;

/**
 * @description UUID工具类
 * @author Pumpkin
 * @date 2022/7/13
 * @version 1.0
 */
public class UUIDUtil {

	/**
	 * 获得4个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get4UUID() {
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[1];
	}

	/**
	 * 获得8个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get8UUID() {
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0];
	}

	/**
	 * 获得12个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get12UUID() {
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1];
	}

	/**
	 * 获得16个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get16UUID() {

		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2];
	}

	/**
	 * 获得20个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get20UUID() {

		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2] + idd[3];
	}

	/**
	 * 获得24个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get24UUID() {
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[4];
	}

	/**
	 * 获得32个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get32UUID() {
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2] + idd[3] + idd[4];
	}

	/**
	 * 生成6位邮箱验证码
	 *
	 * @param number 验证码位数
	 * @return 验证码
	 */
	public static String createEmailCaptcha(int number) {
		StringBuilder codeNum = new StringBuilder();
		int[] code = new int[3];
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			int num = random.nextInt(10) + 48;
			int uppercase = random.nextInt(26) + 65;
			int lowercase = random.nextInt(26) + 97;
			code[0] = num;
			code[1] = uppercase;
			code[2] = lowercase;
			codeNum.append((char) code[random.nextInt(3)]);
		}
		return codeNum.toString();
	}
}

