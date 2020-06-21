package rmiMatrixCalulation;

import java.util.zip.DataFormatException;

/*
author : juminiy
date : 2020.06.18
log : add a new MatrixFormatException in order to log the error to tell the client the matrix is illegal not to print in command
 */
public class MatrixFormatException extends DataFormatException {
    public final String EXCEPTION_DESCRIPTION="MatrixFormatException";
    public MatrixFormatException(String msg){
        super(msg);
    }
}
