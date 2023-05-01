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

public class LampSetting implements java.lang.Cloneable,
                                    java.io.Serializable
{
    public float power;

    public Color color;

    public LampSetting()
    {
        this.color = new Color();
    }

    public LampSetting(float power, Color color)
    {
        this.power = power;
        this.color = color;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        LampSetting r = null;
        if(rhs instanceof LampSetting)
        {
            r = (LampSetting)rhs;
        }

        if(r != null)
        {
            if(this.power != r.power)
            {
                return false;
            }
            if(this.color != r.color)
            {
                if(this.color == null || r.color == null || !this.color.equals(r.color))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Devices::LampSetting");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, power);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, color);
        return h_;
    }

    public LampSetting clone()
    {
        LampSetting c = null;
        try
        {
            c = (LampSetting)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeFloat(this.power);
        Color.ice_write(ostr, this.color);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.power = istr.readFloat();
        this.color = Color.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, LampSetting v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public LampSetting ice_read(com.zeroc.Ice.InputStream istr)
    {
        LampSetting v = new LampSetting();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LampSetting> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, LampSetting v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(16);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<LampSetting> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(LampSetting.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final LampSetting _nullMarshalValue = new LampSetting();

    /** @hidden */
    public static final long serialVersionUID = 1612280516L;
}
