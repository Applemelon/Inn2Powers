/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BE;

/**
 *
 * @author Asbamz
 */
public enum SME
{
    unknown(-1), isnotSME(0), isSME(1);

    int number;

    SME(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }
}
