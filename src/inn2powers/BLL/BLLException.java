/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

/**
 *
 * @author Lea Freidahl
 */
public class BLLException extends Exception
{

    public BLLException(String message)
    {
        super(message);
    }

    public BLLException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BLLException(Throwable cause)
    {
        super(cause);
    }

}
