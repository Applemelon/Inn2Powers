/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.be;

/**
 *
 * @author Asbamz
 */
public enum SME
{
    UNKNOWN(-1), ISNOTSME(0), ISSME(1);

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
