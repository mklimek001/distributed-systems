//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Devices;

public class LampError extends com.zeroc.Ice.UserException
{
    public LampError()
    {
        this.message = "Wrong power value!";
    }

    public LampError(Throwable cause)
    {
        super(cause);
        this.message = "Wrong power value!";
    }

    public LampError(String message)
    {
        this.message = message;
    }

    public LampError(String message, Throwable cause)
    {
        super(cause);
        this.message = message;
    }

    public String ice_id()
    {
        return "::Devices::LampError";
    }

    public String message;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::Devices::LampError", -1, true);
        ostr_.writeString(message);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        message = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = -472666076L;
}
