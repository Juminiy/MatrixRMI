package rmiMatrixCalulation;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcuInterface extends Remote  {
    Matrix add(Matrix p1,Matrix p2) throws RemoteException, MatrixFormatException;
    Matrix plus(Matrix p1,Matrix p2) throws RemoteException, MatrixFormatException;
    Matrix lambdaPlus(Matrix p,int k) throws RemoteException;
}
