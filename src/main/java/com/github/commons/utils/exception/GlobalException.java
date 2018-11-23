package com.github.commons.utils.exception;

import com.github.commons.utils.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    private GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

    public static GlobalException error(CodeMsg codeMsg){
        return new GlobalException(codeMsg);
    }

}
