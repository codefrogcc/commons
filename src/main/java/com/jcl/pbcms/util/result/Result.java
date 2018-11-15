package com.jcl.pbcms.util.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	public static  <T> Result<T> success(CodeMsg codeMsg){

		return new Result<T>(codeMsg);
	}
	public static <T> Result<T> success(T data, CodeMsg codeMsg){
		return new Result<T>(data,codeMsg);
	}
	public static <T> Result<T> success(){
		return new Result<T>(CodeMsg.SUCCESS);
	}
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	public static <T> Result<T> error(T data, CodeMsg codeMsg){
		return new Result<T>(data,codeMsg);
	}

	private Result(T data) {
		this.data = data;
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}

	private Result(T data,CodeMsg codeMsg){
		this.data = data;
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}
}
