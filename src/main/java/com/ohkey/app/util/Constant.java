package com.ohkey.app.util;

import java.util.ResourceBundle;

public class Constant {
	public static enum MODEL_STATUS {
		ONLINE(1), WAIT(2), OFFLINE(3), DELETE(0);
		private int code;

		private MODEL_STATUS(int code) {
			this.code = code;
		};

		public int getCode() {
			return code;
		}
	}

	public static enum FAVORITE_STATUS {
		DELETE(0), ACTIVE(1);
		private int code;

		private FAVORITE_STATUS(int code) {
			this.code = code;
		};

		public int getCode() {
			return code;
		}
	}

	public static enum ADD_FAVORITE_STATUS {
		NOT_LOGGED("0"), ALREADYIN("1"), MAXSIZE("2"), OK("3");
		private String code;

		private ADD_FAVORITE_STATUS(String code) {
			this.code = code;
		};

		public String getCode() {
			return code;
		}
	}

	public static enum DELETE_FAVORITE_STATUS {
		SUCCESS("1"), FAILED("0");
		private String code;

		private DELETE_FAVORITE_STATUS(String code) {
			this.code = code;
		};

		public String getCode() {
			return code;
		}
	}

	public static final ResourceBundle UPLOAD_PATH_CONFIG = ResourceBundle
			.getBundle("uploadPath");

	public static final String[] ALLOWED_IMG_EXTENSTION = { "jpg", "jpeg",
			"png", "gif" };

	public static final long UPLOAD_GENERNAL_MAX_SIZE = 2 * 1024 * 1024;
}
