package rmiMatrixCalulation;

import java.io.Serializable;
import java.util.Scanner;

public class Matrix implements Serializable{//必须可以序列化 能够进行网络传输
    private int m,n;
    private int matrix [][];

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int i,int j,int k) {
        matrix[i][j]=k;
    }
    public int getMatrix(int i,int j){
        return matrix[i][j];
    }
    public boolean isSamesize(Matrix p){
        if(!((this.getM()==p.getM())&&(this.getN()==p.getN()))) return false;
        else return true;
    }
    public boolean canPlus(Matrix p){
        if(this.getN()==p.getM()) return true;
        else return false;
    }
    public Matrix(int m, int n){
        this.m=m;
        this.n=n;
        this.matrix=new int[m+1][n+1];
    }
    public void InputMatrix(){
        Scanner in=new Scanner(System.in);
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                matrix[i][j]=in.nextInt();
            }
        }
    }
    public void OutputMatrix(){
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public Matrix lambdaPlusx(int k){
        Matrix result=new Matrix(this.m,this.n);
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                result.matrix[i][j]=this.matrix[i][j]*k;
            }
        }
        return result;
    }
    //把Matrix 的String 以字节流方式建立
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                sb.append(matrix[i][j]+" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
