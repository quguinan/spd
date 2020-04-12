package com.cms.controller.api.supply.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PharmacyResponse {
	private String result = "";

	public void ReadData(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		this.result = new String(baos.toByteArray(), "utf-8");
	}

	public String getResult() {
		return this.result;
	}
}