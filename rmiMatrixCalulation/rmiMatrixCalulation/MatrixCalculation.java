package rmiMatrixCalulation;

import java.io.Serializable;
import java.util.HashMap;

public class MatrixCalculation implements  Serializable{//可以序列化 能够进行网络传输
    private Matrix a,b;
    public MatrixCalculation(Matrix a,Matrix b){
        this.a=a;
        this.b=b;
    }
    public Matrix Add() throws MatrixFormatException {
        if(!a.isSamesize(b)) {
            throw new MatrixFormatException("MatrixFormatException");
            //这里继承个异常更好
        }
        Matrix result=new Matrix(this.a.getM(),this.a.getN());
        for(int i=1;i<=a.getM();i++){
            for(int j=1;j<=a.getN();j++){
                result.setMatrix(i,j,a.getMatrix(i,j)+b.getMatrix(i,j));
            }
        }
        return result;
    }
    private int vectorPlus(Matrix p1,int line,Matrix p2,int row) throws MatrixFormatException {
        int k=p1.getN();
        if(k!=p2.getM()){
            throw new MatrixFormatException("MatrixFormatException");
        }
        int sum=0;
        for(int i=1;i<=k;i++){
            sum+=p1.getMatrix(line,i)*p2.getMatrix(i,row);
        }
        return sum;
    }
    public Matrix Plus() throws MatrixFormatException {
        if(!a.canPlus(b)){
            throw new MatrixFormatException("MatrixFormatException");
        }
        Matrix result=new Matrix(this.a.getM(),this.b.getN());
        for(int i=1;i<=a.getM();i++){
            for(int j=1;j<=b.getN();j++){
                result.setMatrix(i,j,vectorPlus(a,i,b,j));
            }
        }
        return  result;
    }

//    }
}
